package io.github.seleniumquery.selector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringEscapeUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.WrapsDriver;

public class SelectorUtils {
	
	/**<p> 
	 * Sequence to be used in regexes to prevent matching escaped symbols.
	 * </p>
	 * <p>
	 * For instance:
	 * <strong><code>String myRegex = ESCAPED_SLASHES + ":pseudo"</code></strong>
	 * </p>
	 * <p>
	 * Will NOT match the string <code>"\:pseudo"</code>, as it will consider the <code>":"</code>
	 * to be escaped and thus the <code>"pseudo"</code> will not really be a <code>:pseudo</code> but
	 * a string <code>":pseudo"</code>.
	 * </p>
	 * <p>
	 * On the other hande, it WILL match the string <code>"\\:pseudo"</code>, because it considers
	 * the char before a <code>"\"</code> by itself and thus not a escaping chat to the <code>":"</code>.
	 * </p>
	 */
	public static final String ESCAPED_SLASHES = "(?<!(?:^|[^\\\\])\\\\)";

	public static WebElement parent(WebElement element) {
		try {
			return element.findElement(By.xpath(".."));
		} catch (RuntimeException e) {
//			String exceptionMessage = e.getMessage();
//			System.err.println(exceptionMessage.substring(0, Math.min(200, exceptionMessage.length())));
			return null;
		}
	}
	
	public static List<WebElement> parents(WebElement element) {
		return element.findElements(By.xpath("ancestor::node()[count(ancestor-or-self::html) > 0]"));
	}

	public static String lang(WebElement element) {
		WebElement currElement = element;
		while (currElement != null) {
			String lang = currElement.getAttribute("lang");
			// #Cross-Driver
			// Absent lang attribute returns:
			// - null in HtmlUnitDriver
			// - "" in FirefoxDriver
			if (lang != null && !lang.isEmpty()) {
				return lang;
			}
			currElement = SelectorUtils.parent(currElement);
		}
		return null;
	}

	public static boolean hasAttribute(WebElement element, String localName) {
		return element.getAttribute(localName) != null;
	}
	
	public static boolean isVisible(WebDriver driver, WebElement element) {
		if (!element.isDisplayed()) {
			return false;
		}
		// #Cross-Driver
		// at this point, it is visible...
		if (!DriverSupportService.isHtmlUnitDriver(driver)) {
			return true;
		}
		// ...or we are in HtmlUnitDriver. In this case we must check if it is under <body>
		
		// Firefox and Chrome don't consider elements directly under <html> (such as, commonly, <title>, <meta>, etc.,
		// EXCEPT for <body>) to be visible, so we filter them, because HtmlUnitDriver thinks they ARE visible.
		boolean isBodyOrChildOfBody = !element.findElements(By.xpath("ancestor-or-self::body")).isEmpty();
		// in other words, it is visible only if it is <body> or if it has <body> as its parent
		return isBodyOrChildOfBody;
	}

	public static List<WebElement> itselfWithSiblings(WebElement element) {
		WebElement parent = SelectorUtils.parent(element);
		// if parent is null, then element is <HTML>, thus have no siblings
		if (parent == null) {
			return new ArrayList<WebElement>(Arrays.asList(element));
		}
		return getDirectChildren(parent);
	}

	public static List<WebElement> getDirectChildren(WebElement parent) {
		return parent.findElements(By.xpath("./*"));
	}
	
	public static List<WebElement> getPreviousSiblings(WebElement elementItSelf) {
		List<WebElement> itselfWithSiblings = SelectorUtils.itselfWithSiblings(elementItSelf);
		
		List<WebElement> previousSiblings = new ArrayList<WebElement>();
		for (WebElement siblingOrItSelf : itselfWithSiblings) {
			if (elementItSelf.equals(siblingOrItSelf)) {
				break;
			}
			previousSiblings.add(siblingOrItSelf);
		}
		return previousSiblings;
	}
	
	public static WebElement getPreviousSibling(WebElement element) {
		List<WebElement> previousSiblings = getPreviousSiblings(element);
		int siblingCount = previousSiblings.size();
		// no previous siblings
		if (siblingCount == 0) {
			return null;
		}
		return previousSiblings.get(siblingCount-1);
	}
	
	/**
	 * Escapes a CSS identifier.
	 * 
	 * Future references for CSS escaping:
	 * http://mathiasbynens.be/notes/css-escapes
	 * http://mothereff.in/css-escapes
	 * https://github.com/mathiasbynens/mothereff.in/blob/master/css-escapes/eff.js
	 */
	public static String escapeSelector(String unescapedSelector) {
		String escapedSelector = unescapedSelector;
		if (Character.isDigit(unescapedSelector.charAt(0))) {
			escapedSelector = "\\\\3"+unescapedSelector.charAt(0)+" "+escapedSelector.substring(1);
		}
		escapedSelector = escapedSelector.replace(":", "\\:");
		if (escapedSelector.charAt(0) == '-') {
			if (escapedSelector.length() == 1
					||
				(escapedSelector.length() > 1 && (Character.isDigit(escapedSelector.charAt(1)) || escapedSelector.charAt(1) == '-'))
				) {
			escapedSelector = "\\"+escapedSelector;
			}
		}
		return escapedSelector;
	}

	/**
	 * Escapes the attributes values into a valid CSS string.
	 * Deals with the way the CSS parser gives the attributes' values to us.
	 */
	public static String escapeAttributeValue(String attributeValue) {
		// " comes escaped already, so we unescape them (otherwise the next step will escape its \)
		attributeValue = attributeValue.replace("\\\"", "\"");
		// now we escape all \
		attributeValue = attributeValue.replace("\\", "\\\\");
		// and escape "
		attributeValue = attributeValue.replace("\"", "\\\"");
		// finally, surround with "s
		return '"'+attributeValue+'"';
	}
	
	public static WebDriver getWebDriver(SearchContext context) {
		if (context instanceof WebDriver) {
			return (WebDriver) context;
		}
		if (context instanceof WrapsDriver) {
			return ((WrapsDriver) context).getWrappedDriver();
		}
		throw new RuntimeException("We don't know how to extract the WebDriver from this context: "
				+context.getClass()+" -> "+context);
	}
	
	/**
	 * Escapes the attributes values into a valid CSS string.
	 * Deals with the way the CSS parser gives the attributes' values to us.
	 */
	public static String unescapeString(String stringValue) {
		String escapedString = stringValue;
		char firstChar = stringValue.charAt(0);
		if (firstChar == '\'' || firstChar == '"') {
			escapedString = StringEscapeUtils.unescapeEcmaScript(stringValue);
			escapedString = escapedString.substring(1, escapedString.length()-1);
		}
		return escapedString;
	}

	public static String intoEscapedXPathString(String value) {
		if (value.indexOf('\'') == -1) {
			return "'" + value + "'";
		}
		return "concat('" + value.replace("'", "', \"'\", '") + "')";
	}

}
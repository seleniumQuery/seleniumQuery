/*
 * Copyright (c) 2016 seleniumQuery authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.seleniumquery.utils;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlHiddenInput;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitWebElement;
import org.openqa.selenium.internal.WrapsDriver;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.singletonList;

/**
 * Contains some utility functions for dealing with WebDrivers, such as inspecting their version.
 *
 * @author acdcjunior
 * @author ricardo-sc
 * @since 0.9.0
 */
public class SelectorUtils {

	private static final Log LOGGER = LogFactory.getLog(SelectorUtils.class);
	
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
	
	private SelectorUtils() {}

	public static WebElement parent(WebElement element) {
		try {
			return element.findElement(By.xpath(".."));
		} catch (RuntimeException e) {
			LOGGER.debug("parent() (XPath \"..\") on element ("+element+") failed. It probably is because element is <html>." +
					" Still, it could be something else, thus this logging.", e);
			return null;
		}
	}
	
	@SuppressWarnings("unused")
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

    @SuppressWarnings("UnnecessaryLocalVariable")
	public static boolean isVisible(WebElement element) {
		boolean elementIsNotVisible = !element.isDisplayed();
		if (elementIsNotVisible) {
			return false;
		}
		// at this point, it is visible!

		// And either we are not in HtmlUnitDriver
		if (!(element instanceof HtmlUnitWebElement)) {
			return true; // in that case, if the driver said it was visible, then it is
		}
		// ...or we are in HtmlUnitDriver.

		// #Cross-Driver
		// HtmlUnitDriver, says some elements are visible when they arent. So we must do some additional
		// checking to correct its result.

		// CHECKING #1: if JS is OFF, HtmlUnitDriver says everyone is visible, when that's not the case!
		// so we do some force-checking. If it finds the element is not visible, we return as so.
		if (!isHtmlUnitWebElementReallyDisplayed(element)) {
			return false;
		}

		// CHECKING #2: If all verification above passed, then we still think it is visible.
		// The last checking is seeing if the given element is under <body>:
		//         Firefox and Chrome don't consider elements directly under <html> (such as, commonly, <title>, <meta>, etc.,
		//      EXCEPT for <body>) to be visible, so we filter them, because HtmlUnitDriver thinks they ARE visible.
		boolean isBodyOrChildOfBody = !element.findElements(By.xpath("ancestor-or-self::body")).isEmpty();
		// in other words, it is visible only if it is <body> or if it has <body> as its parent
		return isBodyOrChildOfBody;
	}

	private static boolean isHtmlUnitWebElementReallyDisplayed(WebElement webElement) {
		try {
			// #Cross-Driver #HtmlUnit #reflection #hack
			HtmlUnitWebElement htmlUnitWebElement = (HtmlUnitWebElement) webElement;
			Method getElementMethod = HtmlUnitWebElement.class.getDeclaredMethod("getElement");
			getElementMethod.setAccessible(true);
			HtmlElement htmlElement = (HtmlElement) getElementMethod.invoke(htmlUnitWebElement);
			return !(htmlElement instanceof HtmlHiddenInput) && htmlElement.isDisplayed();
		} catch (Exception e) {
			LOGGER.debug("Unable to retrieve real HtmlUnitWebElement#isDisplayed().", e);
			return true;
		}
	}

	public static List<WebElement> itselfWithSiblings(WebElement element) {
		WebElement parent = SelectorUtils.parent(element);
		// if parent is null, then element is <HTML>, thus have no siblings
		if (parent == null) {
			return singletonList(element);
		}
		return getDirectChildren(parent);
	}

	public static List<WebElement> getDirectChildren(WebElement parent) {
		return parent.findElements(By.xpath("./*"));
	}
	
	public static List<WebElement> getPreviousSiblings(WebElement elementItSelf) {
		List<WebElement> itselfWithSiblings = SelectorUtils.itselfWithSiblings(elementItSelf);
		
		List<WebElement> previousSiblings = new ArrayList<>();
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
	 *
	 * @param unescapedSelector The CSS selector to escape
     * @return The CSS selector escaped
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
     *
     * @param attributeValue The CSS attribute value to escape
     * @return The CSS attribute value escaped
	 */
	public static String escapeAttributeValue(String attributeValue) {
		// " comes escaped already, so we unescape them (otherwise the next step will escape its \)
        String escapedAttributeValue = attributeValue.replace("\\\"", "\"");
		// now we escape all \
		escapedAttributeValue = escapedAttributeValue.replace("\\", "\\\\");
		// and escape "
		escapedAttributeValue = escapedAttributeValue.replace("\"", "\\\"");
		// finally, surround with "s
		return '"'+escapedAttributeValue+'"';
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
     *
     * @param stringValue The string value to escape into a valid CSS string
     * @return The string value escaped to into a valid CSS string
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
package io.github.seleniumquery.by.evaluator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

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

	public static String lang(WebElement element) {
		WebElement currElement = element;
		while (currElement != null) {
			String lang = currElement.getAttribute("lang");
			if (lang != null) {
				return lang;
			}
			currElement = SelectorUtils.parent(currElement);
		}
		return null;
	}

	public static boolean hasAttribute(WebElement element, String localName) {
		return element.getAttribute(localName) != null;
	}

	public static List<WebElement> itselfWithSiblings(WebElement element) {
		WebElement parent = SelectorUtils.parent(element);
		// if parent is null, then element is <HTML>, thus have no siblings
		if (parent == null) {
			return new ArrayList<WebElement>(Arrays.asList(element));
		}
		return parent.findElements(By.xpath("./*"));
	}
	
	public static List<WebElement> getPreviousSiblings(WebElement element) {
		List<WebElement> itselfWithSiblings = SelectorUtils.itselfWithSiblings(element);
		
		List<WebElement> previousSiblings = new ArrayList<WebElement>();
		for (WebElement itselfOrSibling : itselfWithSiblings) {
			if (element.equals(itselfOrSibling)) {
				break;
			}
			previousSiblings.add(itselfOrSibling);
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

}
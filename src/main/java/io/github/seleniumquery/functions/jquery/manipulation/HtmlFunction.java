package io.github.seleniumquery.functions.jquery.manipulation;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.javascript.host.html.HTMLElement;
import io.github.seleniumquery.SeleniumQueryObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.htmlunit.HtmlUnitWebElement;

import java.lang.reflect.Method;
import java.util.List;

/**
 * $("selector").html()
 *
 * @author acdcjunior
 *
 * @since 0.9.0
 */
public class HtmlFunction {

	private static final Log LOGGER = LogFactory.getLog(HtmlFunction.class);

	public static String html(SeleniumQueryObject seleniumQueryObject) {
		return html(seleniumQueryObject.get());
	}

	/**
	 * Returns the HTML of the first element of the list.
	 * @param elements The list of elements.
	 * @return The HTML of the first element.
	 */
	public static String html(List<WebElement> elements) {
		if (elements.isEmpty()) {
			return null;
		}
		return html(elements.get(0));
	}

	private static String html(WebElement element) {
		if (isHtmlUnitDriver(element)) {
			return getHtmlForHtmlUnitDriver(element);
		}
		return element.getAttribute("innerHTML");
	}

	private static boolean isHtmlUnitDriver(WebElement element) {
		return element instanceof HtmlUnitWebElement;
	}

	// #Cross-Driver
	// element.getAttribute("innerHTML") does not work for HtmlUnitDriver, so...
	private static String getHtmlForHtmlUnitDriver(WebElement element) {
		String html = getHtmlUnitInnerHTML((HtmlUnitWebElement) element);

		// #Cross-Driver
		// HtmlUnitDriver does not append a "\n" to the HTML of the body tag
		// as Chrome, Firefox and IE10 seem to.
		// So we add it!
		if ("body".equals(element.getTagName())) {
            return html + "\n";
        }
		return html;
	}

	private static String getHtmlUnitInnerHTML(HtmlUnitWebElement element) {
		WebDriver driver = element.getWrappedDriver();
		// we resort to JavaScript when it is enabled
		if (((HtmlUnitDriver) driver).isJavascriptEnabled()) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			return js.executeScript("return arguments[0].innerHTML", element).toString();
		}
		
		// or use reflection if JS is not enabled
		// (this method is not preferred as it relies on HtmlUnit's internals, which can change without notice)
		try {
			// #HtmlUnit #reflection #hack
			Method getElementMethod = org.openqa.selenium.htmlunit.HtmlUnitWebElement.class.getDeclaredMethod("getElement");
			getElementMethod.setAccessible(true);
			
			HtmlElement he = (HtmlElement) getElementMethod.invoke(element);
			HTMLElement e = (HTMLElement) he.getScriptObject();
			
			return e.getInnerHTML();
		} catch (Exception e) {
			LOGGER.warn("Unable to get WebElement's innerHTML. Returning empty string.", e);
			return "";
		}
	}

}
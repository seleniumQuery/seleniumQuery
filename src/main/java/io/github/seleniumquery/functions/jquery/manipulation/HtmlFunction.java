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

import java.lang.reflect.Method;
import java.util.List;

import static io.github.seleniumquery.selector.DriverVersionUtils.isHtmlUnitDriver;

/**
 * $("selector").html()
 *
 * @author acdcjunior
 *
 * @since 1.0.0
 */
public class HtmlFunction {

	private static final Log LOGGER = LogFactory.getLog(HtmlFunction.class);

	public static String html(SeleniumQueryObject seleniumQueryObject, List<WebElement> elements) {
		return html(seleniumQueryObject.getWebDriver(), elements);
	}
	
	public static String html(WebDriver driver, List<WebElement> elements) {
		if (elements.isEmpty()) {
			return null;
		}
		return html(driver, elements.get(0));
	}

	public static String html(WebDriver driver, WebElement element) {
		if (isHtmlUnitDriver(driver)) {
			return getHtmlForHtmlUnitDriver(driver, element);
		}
		return element.getAttribute("innerHTML");
	}

	// #Cross-Driver
	// element.getAttribute("innerHTML") does not work for HtmlUnitDriver, so...
	private static String getHtmlForHtmlUnitDriver(WebDriver driver, WebElement element) {
		String html = getHtmlUnitInnerHTML(driver, element);

		// #Cross-Driver
		// HtmlUnitDriver does not append a "\n" to the HTML of the body tag
		// as Chrome, Firefox and IE10 seem to.
		// So we add it!
		if ("body".equals(element.getTagName())) {
            return html + "\n";
        }
		return html;
	}

	private static String getHtmlUnitInnerHTML(WebDriver driver, WebElement element) {
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
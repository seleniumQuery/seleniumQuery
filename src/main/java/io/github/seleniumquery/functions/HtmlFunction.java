package io.github.seleniumquery.functions;

import io.github.seleniumquery.SeleniumQueryObject;
import io.github.seleniumquery.selector.DriverSupportService;

import java.lang.reflect.Method;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.javascript.host.html.HTMLElement;

public class HtmlFunction {

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
		if (DriverSupportService.isNotHtmlUnitDriver(driver)) {
			return element.getAttribute("innerHTML");
		}
		String html = getHtmlUnitInnerHTML(driver, element);
		
		// #Cross-Driver
		// HtmlUnitDriver does not append a "\n" to the HTML of the body tag
		// as Chrome, Firefox and IE10 seem to.
		// So we add it!
		if ("body".equals(element.getTagName())) {
			html = html + "\n";
		}
		return html;
	}
	
	// #Cross-Driver
	// element.getAttribute("innerHTML") does not work for HtmlUnitDriver, so...
	private static String getHtmlUnitInnerHTML(WebDriver driver, WebElement element) {
		// we resort to JavaScript when it is being used as driver.
		if (((HtmlUnitDriver) driver).isJavascriptEnabled()) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			return js.executeScript("return arguments[0].innerHTML", element).toString();
		}
		
		// or use reflection if JS is not enabled
		// (this method is not preferred as it relies on HtmlUnit's internals, which can change without notice)
		try {
			Method method = org.openqa.selenium.htmlunit.HtmlUnitWebElement.class.getDeclaredMethod("getElement");
			method.setAccessible(true);
			
			HtmlElement he = (HtmlElement) method.invoke(element);
			HTMLElement e = (HTMLElement) he.getScriptObject();
			
			return e.getInnerHTML();
		} catch (Exception e) {
			System.err.println("Unable to get WebElement's innerHTML: "+element+ "("+e.getMessage()+")");
			return "";
		}
	}

}
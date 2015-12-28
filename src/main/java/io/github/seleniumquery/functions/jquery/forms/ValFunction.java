/*
 * Copyright (c) 2015 seleniumQuery authors
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

package io.github.seleniumquery.functions.jquery.forms;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.javascript.host.html.HTMLElement;
import io.github.seleniumquery.SeleniumQueryObject;
import io.github.seleniumquery.utils.DriverVersionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.Select;

import java.lang.reflect.Method;
import java.util.List;

import static io.github.seleniumquery.utils.WebElementUtils.*;
import static org.apache.commons.lang3.StringUtils.capitalize;

public class ValFunction {

	private static final Log LOGGER = LogFactory.getLog(SeleniumQueryObject.class);
	
	/**
	 * $(".selector").val();
	 */
	public static String val(List<WebElement> elements) {
		if (elements.isEmpty()) {
			return null;
		}
		return val(elements.get(0));
	}

	/**
	 * <p>Gets the value of the given element, if its tag name is INPUT, OPTION, SELECT or TEXTAREA.</p>
	 * Otherwise it returns an empty string.
	 * 
	 * @param element The element you want the value of.
	 * @return The value of the element.
	 * @since 0.9.0
	 */
	public static String val(WebElement element) {
		String tagName = element.getTagName();
		if (isInputTag(element) || isOptionTag(element)) {
			return element.getAttribute("value");
		} else if (isSelectTag(element)) {
			return new Select(element).getFirstSelectedOption().getAttribute("value");
		} else if (isTextareaTag(element)) {
			// see issue#59 - <textarea> returns wrong value (original value) for getText() in Firefox
			// It used to be element.getText(), .getAttribute("value") OTOH, works for everyone.
			return element.getAttribute("value");
		}
		LOGGER.warn("Attempting to call .val() in an element of type '"+tagName+"': "+element+". Returning empty string.");
		return "";
	}
	
	/**
	 * $(".selector").val(123);
	 */
	public static SeleniumQueryObject val(SeleniumQueryObject caller, List<WebElement> elements, Number value) {
        SeleniumQueryObject.LOGGER.debug("Setting value of "+ caller +" to: "+value+".");
		return val(caller, elements, value.toString());
	}

	/**
	 * $(".selector").val("string");
	 */
	public static SeleniumQueryObject val(SeleniumQueryObject seleniumQueryObject, List<WebElement> elements, String value) {
		SeleniumQueryObject.LOGGER.debug("Setting value of "+ seleniumQueryObject +" to: \""+value+"\".");
		for (WebElement element : elements) {
			val(seleniumQueryObject.getWebDriver(), element, value);
		}
		return seleniumQueryObject;
	}
	
	private static void val(WebDriver driver, WebElement element, String value) {
		if (isSelectTag(element)) {
			new Select(element).selectByValue(value);
		} else if (isInputTag(element) || isTextareaTag(element)) {
			if (isInputWithTypeWeDontChangeValue(element)) {
				String type = element.getAttribute("type");
				warnAboutNotChangingValue("<input type=\"" + type + "\">", "#my" + capitalize(type));
				return;
			}
			if (!isInputFileTag(element)) {
				element.clear();
			}
			element.sendKeys(value);
		} else if (isOptionTag(element)) {
			warnAboutNotChangingValue("<option>", "#myOption");
		} else if (isContentEditable(element)) {
			// #Cross-Driver
            if (DriverVersionUtils.getInstance().isHtmlUnitDriver(driver)) {
				changeContentEditableValueInHtmlUnit(driver, element, value);
			} else {
				if (driver instanceof FirefoxDriver) {
					// #Cross-Driver
					// in firefox, an editable div cannot be empty
					try {
						element.sendKeys(Keys.chord(Keys.CONTROL, Keys.HOME), Keys.chord(Keys.CONTROL, Keys.SHIFT, Keys.END));
						element.sendKeys(value);
					} catch (ElementNotVisibleException e) {
						// we could work it out, possibly via JavaScript, but we decided not to, as a user would not be able to edit it!
						throw new ElementNotVisibleException("Empty contenteditable elements are not visible in Firefox, " +
								"so a user can't directly interact with them. Try picking an element before the contenteditable one " +
								"and sending the TAB key to it, so the focus is switched, and then try calling .val() to change its value.", e);
					}
				} else {
					element.clear();
					element.sendKeys(value);
				}
			}
		} else {
			LOGGER.warn("Function .val() called in element not known to be editable. Will attempt to send keys anyway. Element: "+element);
			element.clear();
			element.sendKeys(value);
		}
	}

	private static boolean isInputWithTypeWeDontChangeValue(WebElement element) {
		return isInputRadioTag(element) || isInputCheckboxTag(element) || isInputHiddenTag(element)
                || isInputButtonTag(element) || isInputSubmitTag(element);
	}

	private static void warnAboutNotChangingValue(String exampleHtml, String exampleId) {
		LOGGER.warn("Users can't (and thus Selenium will not allow you to) change the 'value' attribute of " +
                exampleHtml + " elements. seleniumQuery's $(\"" + exampleId + "\").val(\"str\") will have no " +
				"effect on such elements. It is ill advised, but if you really have to," +
                " use $(\"" + exampleId + "\").attr(\"value\", \"newValue\");");
	}

	private static void changeContentEditableValueInHtmlUnit(WebDriver driver, WebElement element, String value) {
		// we resort to JavaScript when it is enabled
		if (((HtmlUnitDriver) driver).isJavascriptEnabled()) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0]['innerText' in arguments[0] ? 'innerText' : 'textContent'] = arguments[1];", element, value);
		} else {
			// or use reflection if JS is not enabled
			// (this method is not preferred as it relies on HtmlUnit's internals, which can change without notice)
			try {
				// #HtmlUnit #reflection #hack
				Method getElementMethod = org.openqa.selenium.htmlunit.HtmlUnitWebElement.class.getDeclaredMethod("getElement");
				getElementMethod.setAccessible(true);

				HtmlElement he = (HtmlElement) getElementMethod.invoke(element);
				HTMLElement e = (HTMLElement) he.getScriptObject();

				e.setInnerText(value);
			} catch (Exception e) {
				LOGGER.warn("Unable to set HtmlUnitWebElement's innerText.", e);
			}
		}
	}

}
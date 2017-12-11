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

package io.github.seleniumquery.functions.jquery.forms;

import static io.github.seleniumquery.utils.WebElementUtils.isContentEditable;
import static io.github.seleniumquery.utils.WebElementUtils.isInputButtonTag;
import static io.github.seleniumquery.utils.WebElementUtils.isInputCheckboxTag;
import static io.github.seleniumquery.utils.WebElementUtils.isInputFileTag;
import static io.github.seleniumquery.utils.WebElementUtils.isInputHiddenTag;
import static io.github.seleniumquery.utils.WebElementUtils.isInputRadioTag;
import static io.github.seleniumquery.utils.WebElementUtils.isInputSubmitTag;
import static io.github.seleniumquery.utils.WebElementUtils.isInputTag;
import static io.github.seleniumquery.utils.WebElementUtils.isOptionTag;
import static io.github.seleniumquery.utils.WebElementUtils.isSelectTag;
import static io.github.seleniumquery.utils.WebElementUtils.isTextareaTag;
import static org.apache.commons.lang3.StringUtils.capitalize;

import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.Select;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.javascript.host.html.HTMLElement;
import io.github.seleniumquery.SeleniumQueryObject;
import io.github.seleniumquery.utils.DriverVersionUtils;

public class ValFunction {

	private static final Log LOGGER = LogFactory.getLog(ValFunction.class);

	private ValFunction() {}

	/*
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
            Select select = new Select(element);
            if (select.isMultiple())
                return select.getAllSelectedOptions().stream().map(we -> we.getAttribute("value")).collect(Collectors.joining(","));
            else
                return select.getFirstSelectedOption().getAttribute("value");
		} else if (isTextareaTag(element)) {
			// see issue#59 - <textarea> returns wrong value (original value) for getText() in Firefox
			// It used to be element.getText(). We use .getAttribute("value") because it works for everyone.
			return element.getAttribute("value");
		}
		LOGGER.warn("Attempting to call .val() in an element of type '"+tagName+"': "+element+". Returning empty string.");
		return "";
	}

	/*
	 * $(".selector").val(123);
	 */
	public static SeleniumQueryObject val(SeleniumQueryObject caller, List<WebElement> elements, Number value) {
        LOGGER.debug("Setting value of "+ caller +" to: "+value+".");
		return val(caller, elements, value.toString());
	}

	/*
	 * $(".selector").val("string");
	 */
	public static SeleniumQueryObject val(SeleniumQueryObject seleniumQueryObject, List<WebElement> elements, String value) {
		LOGGER.debug("Setting value of "+ seleniumQueryObject +" to: \""+value+"\".");
		elements.forEach(element -> changeElementValue(seleniumQueryObject.getWebDriver(), element, value));
		return seleniumQueryObject;
	}

	private static void changeElementValue(WebDriver driver, WebElement element, String value) {
		if (isSelectTag(element)) {
            changeValueOfSelectElement(element, value);
        } else if (isInputTag(element) || isTextareaTag(element)) {
            changeValueOfInputElement(element, value);
        } else if (isOptionTag(element)) {
			warnAboutNotChangingValueOfElement("<option>", "#myOption");
		} else if (isContentEditable(driver, element)) {
			changeValueOfContentEditableElement(driver, element, value);
		} else {
            changeValueOfUnknownElement(element, value);
		}
	}

    private static void changeValueOfSelectElement(WebElement element, String value) {
        new Select(element).selectByValue(value);
    }

    private static void changeValueOfInputElement(WebElement element, String value) {
        if (isAnInputWithTypeWhichUserCantChangeValue(element)) {
            String type = element.getAttribute("type");
            warnAboutNotChangingValueOfElement("<input type=\"" + type + "\">", "#my" + capitalize(type));
        } else {
            if (!isInputFileTag(element)) {
                element.clear();
            }
            element.sendKeys(value);
        }
    }

    private static boolean isAnInputWithTypeWhichUserCantChangeValue(WebElement element) {
        return isInputRadioTag(element) || isInputCheckboxTag(element) || isInputHiddenTag(element)
            || isInputButtonTag(element) || isInputSubmitTag(element);
    }

    private static void warnAboutNotChangingValueOfElement(String exampleHtml, String exampleId) {
        LOGGER.warn("Users can't (and thus Selenium will not allow you to) change the 'value' attribute of " +
            exampleHtml + " elements. seleniumQuery's $(\"" + exampleId + "\").val(\"str\") will have no " +
            "effect on such elements. It is ill advised, but if you really have to," +
            " use $(\"" + exampleId + "\").attr(\"value\", \"newValue\");");
    }

    private static void changeValueOfContentEditableElement(WebDriver driver, WebElement element, String value) {
		// #Cross-Driver
		if (DriverVersionUtils.getInstance().isHtmlUnitDriver(driver)) {
            changeContentEditableValueInHtmlUnit(driver, element, value);
        } else {
            element.clear();
            element.sendKeys(value);
        }
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
				HTMLElement e = he.getScriptableObject();

				e.setInnerText(value);
			} catch (Exception e) {
				LOGGER.warn("Unable to set HtmlUnitWebElement's innerText.", e);
			}
		}
	}

    private static void changeValueOfUnknownElement(WebElement element, String value) {
        clearElementOrSelectAllOfItsText(element);
        element.sendKeys(value);
    }

    private static void clearElementOrSelectAllOfItsText(WebElement element) {
        try {
            element.clear();
        } catch (WebDriverException ignored) {
            // this is a "best effort" clear
            // frequent exception: "Element must be user-editable in order to clear it."
        }
    }

}

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

package io.github.seleniumquery.functions.jquery.events;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import io.github.seleniumquery.SeleniumQueryObject;

/**
 * $("selector").trigger("event-name");
 *
 * @author acdcjunior
 * @since 0.9.0
 */
public class TriggerFunction {

	public static SeleniumQueryObject trigger(SeleniumQueryObject caller, WebElement element, String event) {
		WebDriver driver = caller.getWebDriver();
		((JavascriptExecutor) driver).executeScript("return arguments[0]."+event+"();", element); 
		return caller;
	}

}
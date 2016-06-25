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

package io.github.seleniumquery.functions.jquery.manipulation;

import io.github.seleniumquery.SeleniumQueryObject;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * $("#selector").text();
 *  
 * @author acdcjunior
 * @author ricardo-sc
 * @since 0.9.0
 */
public class TextFunction {
	
	private TextFunction() {}
	
	public static String text(SeleniumQueryObject seleniumQueryObject) {
		return text(seleniumQueryObject.get());
	}

	public static String text(List<WebElement> webElements) {
		StringBuilder sb = new StringBuilder();
		for (WebElement element : webElements) {
			// Warning!
			// It is impossible to read text from hidden elements in Selenium:
			// Since a user cannot read text in a hidden element, WebDriver will not allow access to it as well.
			// More in WebDriver FAQs: https://code.google.com/p/selenium/wiki/FrequentlyAskedQuestions#Q:_Why_is_it_not_possible_to_interact_with_hidden_elements?
			sb.append(element.getText()).append(" ");
		}
		if (sb.length() > 0) {
			// remove the last added " "
			sb.deleteCharAt(sb.length()-1);
		}
		return sb.toString();
	}
	
}
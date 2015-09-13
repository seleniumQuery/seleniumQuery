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

package io.github.seleniumquery.functions.as;

import io.github.seleniumquery.SeleniumQueryObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import static io.github.seleniumquery.utils.WebElementUtils.isSelectTag;


/**
 * $("#myCombo").as().select() functions.
 *
 * @author acdcjunior
 * @author ricardo-sc
 * @since 0.9.0
 */
public class AsSelect {

	private static final Log LOGGER = LogFactory.getLog(AsSelect.class);

	private SeleniumQueryObject caller;

	AsSelect(SeleniumQueryObject caller) {
		this.caller = caller;
	}

	/**
	 * Selects all <code>&lt;option&gt;</code>s that display text matching the argument.
	 * That is, when given <code>"Bar"</code> this would select an option like:
	 *
	 * <code>&lt;option value="foo"&gt;Bar&lt;/option&gt;</code>
	 *
	 * @param text The visible text to match against.
	 * @return A (self) reference to the {@link io.github.seleniumquery.SeleniumQueryObject} this was called on.
	 *
	 * @since 0.9.0
	 */
	public SeleniumQueryObject selectByVisibleText(String text) {
		LOGGER.debug("Selecting <option>s on "+caller+" by visible text: \""+text+"\".");
		for (WebElement element : caller.get()) {
			if (isSelectTag(element)) {
				new Select(element).selectByVisibleText(text);
			}
		}
		return caller;
	}

	/**
	 * Selects all <code>&lt;option&gt;</code>s that have a value matching the argument.
	 * That is, when given <code>"foo"</code> this would select an option like:
	 *
	 * <code>&lt;option value="foo"&gt;Bar&lt;/option&gt;</code>
	 *
	 * @param value The value to match against.
	 * @return A (self) reference to the {@link io.github.seleniumquery.SeleniumQueryObject} this was called on.
	 */
	public SeleniumQueryObject selectByValue(String value) {
		LOGGER.debug("Selecting <option>s on "+caller+" by value: \""+value+"\".");
		for (WebElement element : caller.get()) {
			if (isSelectTag(element)) {
				new Select(element).selectByValue(value);
			}
		}
		return caller;
	}

}
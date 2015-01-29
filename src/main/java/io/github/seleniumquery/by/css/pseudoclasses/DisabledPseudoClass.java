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

package io.github.seleniumquery.by.css.pseudoclasses;

import io.github.seleniumquery.by.DriverVersionUtils;
import io.github.seleniumquery.by.SelectorUtils;
import io.github.seleniumquery.by.xpath.component.ConditionSimpleComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.join;

/**
 * https://developer.mozilla.org/en-US/docs/Web/CSS/:disabled
 * 
 * #Cross-Driver
 * HtmlUnitDriver has problems with :disabled, so we consider it can never be handler by the browser
 * by "problems" we mean it is inconsistent, changing depending on what browser it is attempting to emulate
 *
 * @author acdcjunior
 *
 * @since 0.9.0
 */
public class DisabledPseudoClass implements PseudoClass<ConditionSimpleComponent> {

	private static final String DISABLED_PSEUDO_CLASS_NO_COLON = "disabled";

	private static final String INPUT = "input";
	private static final String BUTTON = "button";
	private static final String OPTION = "option";
	private static final String OPTGROUP = "optgroup";
	private static final String SELECT = "select";
	private static final String TEXTAREA = "textarea";

	public static final List<String> DISABLEABLE_TAGS = Arrays.asList(INPUT, BUTTON, OPTGROUP, OPTION, SELECT, TEXTAREA);

	public static final String DISABLEABLE_TAGS_XPATH = "(self::" + join(DISABLEABLE_TAGS, " or self::") + ")";

	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return DISABLED_PSEUDO_CLASS_NO_COLON.equals(pseudoClassValue);
	}

	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, PseudoClassSelector pseudoClassSelector) {
		// #Cross-Driver
		// When there is a not disabled <option> under a disabled <optgroup>, HtmlUnitDriver considers
		// the <option> to be enabled, when it is not
		if (DriverVersionUtils.isHtmlUnitDriver(driver) && OPTION.equals(element.getTagName())) {
			WebElement optionParent = SelectorUtils.parent(element);
			if (OPTGROUP.equals(optionParent.getTagName()) && !optionParent.isEnabled()) {
				return true;
			}
		}
		return !element.isEnabled() && DISABLEABLE_TAGS.contains(element.getTagName());
	}

	@Override
	public ConditionSimpleComponent pseudoClassToXPath(PseudoClassSelector pseudoClassSelector) {
		return new ConditionSimpleComponent("[(@disabled and " + DISABLEABLE_TAGS_XPATH + ")]");
	}

}
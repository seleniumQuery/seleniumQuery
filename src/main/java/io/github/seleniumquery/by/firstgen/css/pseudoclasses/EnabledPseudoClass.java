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

package io.github.seleniumquery.by.firstgen.css.pseudoclasses;

import io.github.seleniumquery.by.firstgen.xpath.component.ConditionSimpleComponent;
import io.github.seleniumquery.utils.DriverVersionUtils;
import io.github.seleniumquery.utils.SelectorUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * https://developer.mozilla.org/en-US/docs/Web/CSS/:enabled
 * 
 * #Cross-Driver
 * HtmlUnitDriver has problems with :enabled, so we consider it can never be handler by the browser
 * by "problems" we mean it is inconsistent, changing depending on what browser it is attempting to emulate.
 *
 * @author acdcjunior
 *
 * @since 0.9.0
 */
public class EnabledPseudoClass implements PseudoClass<ConditionSimpleComponent> {
	
	private static final String ENABLED_PSEUDO_CLASS_NO_COLON = "enabled";
	
	private static final String OPTGROUP_TAG = "optgroup";
	private static final String OPTION_TAG = "option";
	public static final List<String> ENABLEABLE_TAGS = DisabledPseudoClass.DISABLEABLE_TAGS;
	
	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return ENABLED_PSEUDO_CLASS_NO_COLON.equals(pseudoClassValue);
	}
	
	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, PseudoClassSelector pseudoClassSelector) {
		// #Cross-Driver
		// When there is a not disabled <option> under a disabled <optgroup>, HtmlUnitDriver considers
		// the <option> to be enabled, when it is not
        if (DriverVersionUtils.getInstance().isHtmlUnitDriver(driver) && OPTION_TAG.equals(element.getTagName())) {
			WebElement optionParent = SelectorUtils.parent(element);
			if (OPTGROUP_TAG.equals(optionParent.getTagName()) && !optionParent.isEnabled()) {
				return false;
			}
		}
		return element.isEnabled() && ENABLEABLE_TAGS.contains(element.getTagName());
	}
	
	public static final String ENABLED_XPATH = "(not(@disabled) and " + DisabledPseudoClass.DISABLEABLE_TAGS_XPATH + ")";
	
	@Override
	public ConditionSimpleComponent pseudoClassToXPath(PseudoClassSelector pseudoClassSelector) {
		return new ConditionSimpleComponent("[" + ENABLED_XPATH + "]");
	}
	
}
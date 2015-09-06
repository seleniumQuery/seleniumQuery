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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;

/**
 * http://api.jquery.com/header-selector/
 * 
 * @author acdcjunior
 * @since 0.9.0
 */
public class HeaderPseudoClass implements PseudoClass<ConditionSimpleComponent> {
	
	private static final String HX_XPATH = "[(local-name() = 'h0' or "
											+ "local-name() = 'h1' or "
											+ "local-name() = 'h2' or "
											+ "local-name() = 'h3' or "
											+ "local-name() = 'h4' or "
											+ "local-name() = 'h5' or "
											+ "local-name() = 'h6' or "
											+ "local-name() = 'h7' or "
											+ "local-name() = 'h8' or "
											+ "local-name() = 'h9')]";

	private static final List<String> HEADER_TAGS = Arrays.asList("h0", "h1", "h2", "h3", "h4", "h5", "h6", "h7", "h8", "h9");
	
	private static final String HEADER = "header";
	
	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return HEADER.equals(pseudoClassValue);
	}
	
	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, PseudoClassSelector pseudoClassSelector) {
		return HEADER_TAGS.contains(element.getTagName());
	}
	
	@Override
	public ConditionSimpleComponent pseudoClassToXPath(PseudoClassSelector pseudoClassSelector) {
		return new ConditionSimpleComponent(HX_XPATH);
	}
	
}
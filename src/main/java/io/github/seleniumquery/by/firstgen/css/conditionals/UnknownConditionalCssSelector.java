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

package io.github.seleniumquery.by.firstgen.css.conditionals;

import io.github.seleniumquery.by.firstgen.css.CssConditionalSelector;
import io.github.seleniumquery.by.firstgen.filter.ElementFilter;
import io.github.seleniumquery.by.firstgen.preparser.ArgumentMap;
import io.github.seleniumquery.by.firstgen.xpath.component.ConditionSimpleComponent;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.Condition;
import org.w3c.css.sac.Selector;

/**
 * Represents an unknown condition type.
 */
public class UnknownConditionalCssSelector<T extends Condition> implements CssConditionalSelector<T, ConditionSimpleComponent> {
	
	private static final Log LOGGER = LogFactory.getLog(UnknownConditionalCssSelector.class);
	
	private short type;
	
	public UnknownConditionalCssSelector(short type) {
		this.type = type;
	}

	@Override
	public boolean isCondition(WebDriver driver, WebElement element, ArgumentMap argumentMap, Selector selectorUpToThisPoint, T condition) {
		throw new RuntimeException("CSS condition "+condition.getClass().getSimpleName()+" of type "+type+" is invalid or not supported!");
	}

	@Override
	public ConditionSimpleComponent conditionToXPath(ArgumentMap argumentMap, Selector simpleSelector, T condition) {
		// if it is unknown, we can't convert it, so we simply ignore it
		LOGGER.warn("CSS Selector Condition '"+condition+"' is unknown. Ignoring it.");
		return new ConditionSimpleComponent(ElementFilter.FILTER_NOTHING);
	}
	
}
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

import io.github.seleniumquery.by.css.CssConditionalSelector;
import io.github.seleniumquery.by.preparser.ArgumentMap;
import io.github.seleniumquery.by.xpath.component.ConditionComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.AttributeCondition;
import org.w3c.css.sac.Selector;

import java.util.Arrays;
import java.util.List;

/**
 * :pseudo-classes
 * :pseudo-classes([args])
 *
 * @author acdcjunior
 *
 * @since 0.9.0
 */
public class PseudoClassCssSelector implements CssConditionalSelector<AttributeCondition, ConditionComponent> {

	private final List<PseudoClass> pseudoClasses = Arrays.asList(new CheckedPseudoClass(),
			new SelectedPseudoClass(), new EqPseudoClass(), new OnlyChildPseudoClass(),
			new ContainsPseudoClass(), new FirstChildPseudoClass(), new NotPseudoClass(),
			new OnlyOfTypePseudoClass(), new RootPseudoClass(), new PresentPseudoClass(),
			new EnabledPseudoClass(), new DisabledPseudoClass(), new VisiblePseudoClass(),
			new HiddenPseudoClass(), new FirstPseudoClass(), new LastPseudoClass(),
			new CheckboxPseudoClass(), new RadioPseudoClass(), new ImagePseudoClass(),
			new PasswordPseudoClass(), new FilePseudoClass(), new SubmitPseudoClass(),
			new ResetPseudoClass(), new ButtonPseudoClass(), new InputPseudoClass(),
			new HeaderPseudoClass(), new LtPseudoClass(), new GtPseudoClass(),
			new FocusPseudoClass(), new FocusablePseudoClass(), new TabbablePseudoClass(),
			new HasPseudoClass(), new LangPseudoClass(), new ParentPseudoClass(),
			new EmptyPseudoClass(), new TextPseudoClass(), new EvenPseudoClass(),
			new OddPseudoClass(), new NthChildPseudoClass());

	@Override
	public boolean isCondition(WebDriver driver, WebElement element, ArgumentMap argumentMap, Selector selectorUpToThisPoint, AttributeCondition attributeCondition) {
		String pseudoClassValue = attributeCondition.getValue();
		for (PseudoClass pseudoClass : pseudoClasses) {
			if (pseudoClass.isApplicable(pseudoClassValue)) {
				return pseudoClass.isPseudoClass(driver, element, new PseudoClassSelector(argumentMap, selectorUpToThisPoint, pseudoClassValue));
			}
		}
		System.err.println("Warning: Unsupported pseudo-class: " + pseudoClassValue);
		return false;
	}
	
	@Override
	public ConditionComponent conditionToXPath(ArgumentMap argumentMap, Selector selectorUpToThisPoint, AttributeCondition attributeCondition) {
		String pseudoClassValue = attributeCondition.getValue();
		for (PseudoClass pseudoClass : pseudoClasses) {
			if (pseudoClass.isApplicable(pseudoClassValue)) {
				return pseudoClass.pseudoClassToXPath(new PseudoClassSelector(argumentMap, selectorUpToThisPoint, pseudoClassValue));
			}
		}
		PseudoClassSelector pseudoClassSelector = new PseudoClassSelector(argumentMap, selectorUpToThisPoint, pseudoClassValue);
		// right now we'll just exit, hoping to cause less problems
		throw new UnsupportedPseudoClassException(pseudoClassSelector.getOriginalPseudoClassSelector());
	}

}
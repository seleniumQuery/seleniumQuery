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

package io.github.seleniumquery.by.secondgen.parser.translator.condition;

import io.github.seleniumquery.by.common.preparser.ArgumentMap;
import io.github.seleniumquery.by.common.pseudoclass.PseudoClass;
import io.github.seleniumquery.by.firstgen.css.pseudoclasses.PseudoClassSelector;
import io.github.seleniumquery.by.firstgen.css.pseudoclasses.UnsupportedPseudoClassException;
import io.github.seleniumquery.by.secondgen.csstree.condition.CssCondition;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.CssFunctionalPseudoClassCondition;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.CssPseudoClassCondition;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.basicfilter.*;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.childfilter.*;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.contentfilter.CssContainsPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.contentfilter.CssEmptyPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.contentfilter.CssHasPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.contentfilter.CssParentPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.form.*;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.jqueryui.CssFocusablePseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.jqueryui.CssTabbablePseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.seleniumquery.CssBlankPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.seleniumquery.CssFilledPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.seleniumquery.CssPresentPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.seleniumquery.CssUncheckedPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.visibility.CssHiddenPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.visibility.CssVisiblePseudoClass;
import org.w3c.css.sac.AttributeCondition;
import org.w3c.css.sac.SimpleSelector;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * :pseudo-classes
 * :pseudo-classes([args])
 *
 * @author acdcjunior
 * @since 0.10.0
 */
class SQCssPseudoClassConditionTranslator {

	private Map<String, Class<? extends CssPseudoClassCondition>> pseudoClasses = new HashMap<>();

	SQCssPseudoClassConditionTranslator() {
		// form
		pseudoClasses.put(CssButtonPseudoClass.PSEUDO, CssButtonPseudoClass.class);
		pseudoClasses.put(CssCheckboxPseudoClass.PSEUDO, CssCheckboxPseudoClass.class);
		pseudoClasses.put(CssCheckedPseudoClass.PSEUDO, CssCheckedPseudoClass.class);
		pseudoClasses.put(CssEnabledPseudoClass.PSEUDO, CssEnabledPseudoClass.class);
		pseudoClasses.put(CssDisabledPseudoClass.PSEUDO, CssDisabledPseudoClass.class);
		pseudoClasses.put(CssFilePseudoClass.PSEUDO, CssFilePseudoClass.class);
		pseudoClasses.put(CssFocusPseudoClass.PSEUDO, CssFocusPseudoClass.class);
		pseudoClasses.put(CssImagePseudoClass.PSEUDO, CssImagePseudoClass.class);
		pseudoClasses.put(CssInputPseudoClass.PSEUDO, CssInputPseudoClass.class);
		pseudoClasses.put(CssPasswordPseudoClass.PSEUDO, CssPasswordPseudoClass.class);
		pseudoClasses.put(CssRadioPseudoClass.PSEUDO, CssRadioPseudoClass.class);
		pseudoClasses.put(CssResetPseudoClass.PSEUDO, CssResetPseudoClass.class);
		pseudoClasses.put(CssSelectedPseudoClass.PSEUDO, CssSelectedPseudoClass.class);
		pseudoClasses.put(CssSubmitPseudoClass.PSEUDO, CssSubmitPseudoClass.class);
		pseudoClasses.put(CssTextPseudoClass.PSEUDO, CssTextPseudoClass.class);

		// basic filter
		pseudoClasses.put(CssAnimatedPseudoClass.PSEUDO, CssAnimatedPseudoClass.class);
		pseudoClasses.put(CssEqPseudoClass.PSEUDO, CssEqPseudoClass.class);
		pseudoClasses.put(CssEvenPseudoClass.PSEUDO, CssEvenPseudoClass.class);
		pseudoClasses.put(CssFirstPseudoClass.PSEUDO, CssFirstPseudoClass.class);
		pseudoClasses.put(CssGtPseudoClass.PSEUDO, CssGtPseudoClass.class);
		pseudoClasses.put(CssHeaderPseudoClass.PSEUDO, CssHeaderPseudoClass.class);
		pseudoClasses.put(CssLangPseudoClass.PSEUDO, CssLangPseudoClass.class);
		pseudoClasses.put(CssLangPseudoClass.PSEUDO_PURE_LANG, CssLangPseudoClass.class);
		pseudoClasses.put(CssLastPseudoClass.PSEUDO, CssLastPseudoClass.class);
		pseudoClasses.put(CssLtPseudoClass.PSEUDO, CssLtPseudoClass.class);
		pseudoClasses.put(CssNotPseudoClass.PSEUDO, CssNotPseudoClass.class);
		pseudoClasses.put(CssNotPseudoClass.PSEUDO_PURE_NOT, CssNotPseudoClass.class);
		pseudoClasses.put(CssNthPseudoClass.PSEUDO, CssNthPseudoClass.class);
		pseudoClasses.put(CssOddPseudoClass.PSEUDO, CssOddPseudoClass.class);
		pseudoClasses.put(CssRootPseudoClass.PSEUDO, CssRootPseudoClass.class);
		pseudoClasses.put(CssTargetPseudoClass.PSEUDO, CssTargetPseudoClass.class);

		// child filter
		pseudoClasses.put(CssFirstChildPseudoClass.PSEUDO, CssFirstChildPseudoClass.class);
		pseudoClasses.put(CssFirstOfTypePseudoClass.PSEUDO, CssFirstOfTypePseudoClass.class);
		pseudoClasses.put(CssLastChildPseudoClass.PSEUDO, CssLastChildPseudoClass.class);
		pseudoClasses.put(CssLastOfTypePseudoClass.PSEUDO, CssLastOfTypePseudoClass.class);
		pseudoClasses.put(CssNthChildPseudoClass.PSEUDO, CssNthChildPseudoClass.class);
		pseudoClasses.put(CssNthLastChildPseudoClass.PSEUDO, CssNthLastChildPseudoClass.class);
		pseudoClasses.put(CssNthLastOfTypePseudoClass.PSEUDO, CssNthLastOfTypePseudoClass.class);
		pseudoClasses.put(CssNthOfTypePseudoClass.PSEUDO, CssNthOfTypePseudoClass.class);
		pseudoClasses.put(CssOnlyChildPseudoClass.PSEUDO, CssOnlyChildPseudoClass.class);
		pseudoClasses.put(CssOnlyOfTypePseudoClass.PSEUDO, CssOnlyOfTypePseudoClass.class);

		// content filter
		pseudoClasses.put(CssContainsPseudoClass.PSEUDO, CssContainsPseudoClass.class);
		pseudoClasses.put(CssEmptyPseudoClass.PSEUDO, CssEmptyPseudoClass.class);
		pseudoClasses.put(CssHasPseudoClass.PSEUDO, CssHasPseudoClass.class);
		pseudoClasses.put(CssParentPseudoClass.PSEUDO, CssParentPseudoClass.class);

		// jquery-ui
		pseudoClasses.put(CssFocusablePseudoClass.PSEUDO, CssFocusablePseudoClass.class);
		pseudoClasses.put(CssTabbablePseudoClass.PSEUDO, CssTabbablePseudoClass.class);

		// visibility
		pseudoClasses.put(CssHiddenPseudoClass.PSEUDO, CssHiddenPseudoClass.class);
		pseudoClasses.put(CssVisiblePseudoClass.PSEUDO, CssVisiblePseudoClass.class);

		// seleniumQuery additions
		pseudoClasses.put(CssPresentPseudoClass.PSEUDO, CssPresentPseudoClass.class);
		pseudoClasses.put(CssBlankPseudoClass.PSEUDO, CssBlankPseudoClass.class);
		pseudoClasses.put(CssFilledPseudoClass.PSEUDO, CssFilledPseudoClass.class);
		pseudoClasses.put(CssUncheckedPseudoClass.PSEUDO, CssUncheckedPseudoClass.class);
	}

	public CssCondition translate(SimpleSelector selectorUpToThisPoint, ArgumentMap argumentMap, AttributeCondition attributeCondition) {
		String pseudoClassName = getPseudoClassName(attributeCondition);
		Class<? extends CssPseudoClassCondition> pseudoClass = pseudoClasses.get(pseudoClassName);
		if (pseudoClass != null) {
			return instantiatePseudoClass(pseudoClass, new PseudoClassSelector(argumentMap, selectorUpToThisPoint, attributeCondition.getValue()));
		}
		throw new UnsupportedPseudoClassException(argumentMap, selectorUpToThisPoint, pseudoClassName);
	}

	private String getPseudoClassName(AttributeCondition attributeCondition) {
		return attributeCondition.getValue().replaceAll("\\(.*", "");
	}

	private <T extends CssPseudoClassCondition> T instantiatePseudoClass(Class<T> pseudoClass, PseudoClassSelector pseudoClassSelectorConstructorArgument) {
		try {
			// instantiates CssFunctionalPseudoClassCondition using pseudoClassSelectorConstructorArgument arg
			if (CssFunctionalPseudoClassCondition.class.isAssignableFrom(pseudoClass)) {
				return pseudoClass.getConstructor(PseudoClass.class).newInstance(pseudoClassSelectorConstructorArgument);
			}
			// instantiates non-functional CssPseudoClassCondition, which should not need an arg.
			return pseudoClass.getConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			throw new RuntimeException(e);
		}
	}

}

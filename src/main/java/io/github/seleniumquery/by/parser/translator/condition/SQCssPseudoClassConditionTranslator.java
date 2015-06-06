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

package io.github.seleniumquery.by.parser.translator.condition;

import io.github.seleniumquery.by.css.pseudoclasses.PseudoClassSelector;
import io.github.seleniumquery.by.css.pseudoclasses.UnsupportedPseudoClassException;
import io.github.seleniumquery.by.csstree.condition.SQCssCondition;
import io.github.seleniumquery.by.csstree.condition.pseudoclass.SQCssFunctionalPseudoClassCondition;
import io.github.seleniumquery.by.csstree.condition.pseudoclass.SQCssPseudoClassCondition;
import io.github.seleniumquery.by.csstree.condition.pseudoclass.basicfilter.*;
import io.github.seleniumquery.by.csstree.condition.pseudoclass.childfilter.*;
import io.github.seleniumquery.by.csstree.condition.pseudoclass.contentfilter.SQCssContainsPseudoClass;
import io.github.seleniumquery.by.csstree.condition.pseudoclass.contentfilter.SQCssEmptyPseudoClass;
import io.github.seleniumquery.by.csstree.condition.pseudoclass.contentfilter.SQCssHasPseudoClass;
import io.github.seleniumquery.by.csstree.condition.pseudoclass.contentfilter.SQCssParentPseudoClass;
import io.github.seleniumquery.by.csstree.condition.pseudoclass.form.*;
import io.github.seleniumquery.by.csstree.condition.pseudoclass.jqueryui.SQCssFocusablePseudoClass;
import io.github.seleniumquery.by.csstree.condition.pseudoclass.jqueryui.SQCssTabbablePseudoClass;
import io.github.seleniumquery.by.csstree.condition.pseudoclass.seleniumquery.SQCssBlankPseudoClass;
import io.github.seleniumquery.by.csstree.condition.pseudoclass.seleniumquery.SQCssFilledPseudoClass;
import io.github.seleniumquery.by.csstree.condition.pseudoclass.seleniumquery.SQCssPresentPseudoClass;
import io.github.seleniumquery.by.csstree.condition.pseudoclass.seleniumquery.SQCssUncheckedPseudoClass;
import io.github.seleniumquery.by.csstree.condition.pseudoclass.visibility.SQCssHiddenPseudoClass;
import io.github.seleniumquery.by.csstree.condition.pseudoclass.visibility.SQCssVisiblePseudoClass;
import io.github.seleniumquery.by.preparser.ArgumentMap;
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
public class SQCssPseudoClassConditionTranslator {

	private Map<String, Class<? extends SQCssPseudoClassCondition>> pseudoClasses = new HashMap<String, Class<? extends SQCssPseudoClassCondition>>();

	public SQCssPseudoClassConditionTranslator() {
		// form
		pseudoClasses.put(SQCssButtonPseudoClass.PSEUDO, SQCssButtonPseudoClass.class);
		pseudoClasses.put(SQCssCheckboxPseudoClass.PSEUDO, SQCssCheckboxPseudoClass.class);
		pseudoClasses.put(SQCssCheckedPseudoClass.PSEUDO, SQCssCheckedPseudoClass.class);
		pseudoClasses.put(SQCssEnabledPseudoClass.PSEUDO, SQCssEnabledPseudoClass.class);
		pseudoClasses.put(SQCssDisabledPseudoClass.PSEUDO, SQCssDisabledPseudoClass.class);
		pseudoClasses.put(SQCssFilePseudoClass.PSEUDO, SQCssFilePseudoClass.class);
		pseudoClasses.put(SQCssFocusPseudoClass.PSEUDO, SQCssFocusPseudoClass.class);
		pseudoClasses.put(SQCssImagePseudoClass.PSEUDO, SQCssImagePseudoClass.class);
		pseudoClasses.put(SQCssInputPseudoClass.PSEUDO, SQCssInputPseudoClass.class);
		pseudoClasses.put(SQCssPasswordPseudoClass.PSEUDO, SQCssPasswordPseudoClass.class);
		pseudoClasses.put(SQCssRadioPseudoClass.PSEUDO, SQCssRadioPseudoClass.class);
		pseudoClasses.put(SQCssResetPseudoClass.PSEUDO, SQCssResetPseudoClass.class);
		pseudoClasses.put(SQCssSelectedPseudoClass.PSEUDO, SQCssSelectedPseudoClass.class);
		pseudoClasses.put(SQCssSubmitPseudoClass.PSEUDO, SQCssSubmitPseudoClass.class);
		pseudoClasses.put(SQCssTextPseudoClass.PSEUDO, SQCssTextPseudoClass.class);

		// basic filter
		pseudoClasses.put(SQCssAnimatedPseudoClass.PSEUDO, SQCssAnimatedPseudoClass.class);
		pseudoClasses.put(SQCssEqPseudoClass.PSEUDO, SQCssEqPseudoClass.class);
		pseudoClasses.put(SQCssEvenPseudoClass.PSEUDO, SQCssEvenPseudoClass.class);
		pseudoClasses.put(SQCssFirstPseudoClass.PSEUDO, SQCssFirstPseudoClass.class);
		pseudoClasses.put(SQCssGtPseudoClass.PSEUDO, SQCssGtPseudoClass.class);
		pseudoClasses.put(SQCssHeaderPseudoClass.PSEUDO, SQCssHeaderPseudoClass.class);
		pseudoClasses.put(SQCssLangPseudoClass.PSEUDO, SQCssLangPseudoClass.class);
		pseudoClasses.put(SQCssLangPseudoClass.PSEUDO_PURE_LANG, SQCssLangPseudoClass.class);
		pseudoClasses.put(SQCssLastPseudoClass.PSEUDO, SQCssLastPseudoClass.class);
		pseudoClasses.put(SQCssLtPseudoClass.PSEUDO, SQCssLtPseudoClass.class);
		pseudoClasses.put(SQCssNotPseudoClass.PSEUDO, SQCssNotPseudoClass.class);
		pseudoClasses.put(SQCssNotPseudoClass.PSEUDO_PURE_NOT, SQCssNotPseudoClass.class);
		pseudoClasses.put(SQCssNthPseudoClass.PSEUDO, SQCssNthPseudoClass.class);
		pseudoClasses.put(SQCssOddPseudoClass.PSEUDO, SQCssOddPseudoClass.class);
		pseudoClasses.put(SQCssRootPseudoClass.PSEUDO, SQCssRootPseudoClass.class);
		pseudoClasses.put(SQCssTargetPseudoClass.PSEUDO, SQCssTargetPseudoClass.class);

		// child filter
		pseudoClasses.put(SQCssFirstChildPseudoClass.PSEUDO, SQCssFirstChildPseudoClass.class);
		pseudoClasses.put(SQCssFirstOfTypePseudoClass.PSEUDO, SQCssFirstOfTypePseudoClass.class);
		pseudoClasses.put(SQCssLastChildPseudoClass.PSEUDO, SQCssLastChildPseudoClass.class);
		pseudoClasses.put(SQCssLastOfTypePseudoClass.PSEUDO, SQCssLastOfTypePseudoClass.class);
		pseudoClasses.put(SQCssNthChildPseudoClass.PSEUDO, SQCssNthChildPseudoClass.class);
		pseudoClasses.put(SQCssNthLastChildPseudoClass.PSEUDO, SQCssNthLastChildPseudoClass.class);
		pseudoClasses.put(SQCssNthLastOfTypePseudoClass.PSEUDO, SQCssNthLastOfTypePseudoClass.class);
		pseudoClasses.put(SQCssNthOfTypePseudoClass.PSEUDO, SQCssNthOfTypePseudoClass.class);
		pseudoClasses.put(SQCssOnlyChildPseudoClass.PSEUDO, SQCssOnlyChildPseudoClass.class);
		pseudoClasses.put(SQCssOnlyOfTypePseudoClass.PSEUDO, SQCssOnlyOfTypePseudoClass.class);

		// content filter
		pseudoClasses.put(SQCssContainsPseudoClass.PSEUDO, SQCssContainsPseudoClass.class);
		pseudoClasses.put(SQCssEmptyPseudoClass.PSEUDO, SQCssEmptyPseudoClass.class);
		pseudoClasses.put(SQCssHasPseudoClass.PSEUDO, SQCssHasPseudoClass.class);
		pseudoClasses.put(SQCssParentPseudoClass.PSEUDO, SQCssParentPseudoClass.class);

		// jquery-ui
		pseudoClasses.put(SQCssFocusablePseudoClass.PSEUDO, SQCssFocusablePseudoClass.class);
		pseudoClasses.put(SQCssTabbablePseudoClass.PSEUDO, SQCssTabbablePseudoClass.class);

		// visibility
		pseudoClasses.put(SQCssHiddenPseudoClass.PSEUDO, SQCssHiddenPseudoClass.class);
		pseudoClasses.put(SQCssVisiblePseudoClass.PSEUDO, SQCssVisiblePseudoClass.class);

		// seleniumQuery additions
		pseudoClasses.put(SQCssPresentPseudoClass.PSEUDO, SQCssPresentPseudoClass.class);
		pseudoClasses.put(SQCssBlankPseudoClass.PSEUDO, SQCssBlankPseudoClass.class);
		pseudoClasses.put(SQCssFilledPseudoClass.PSEUDO, SQCssFilledPseudoClass.class);
		pseudoClasses.put(SQCssUncheckedPseudoClass.PSEUDO, SQCssUncheckedPseudoClass.class);
	}

	public SQCssCondition translate(SimpleSelector selectorUpToThisPoint, ArgumentMap stringMap, AttributeCondition attributeCondition) {
		String pseudoClassName = getPseudoClassName(attributeCondition);
		Class<? extends SQCssPseudoClassCondition> pseudoClass = pseudoClasses.get(pseudoClassName);
		if (pseudoClass != null) {
			return instantiate(pseudoClass, new PseudoClassSelector(stringMap, selectorUpToThisPoint, attributeCondition.getValue()));
		}
		throw new UnsupportedPseudoClassException(stringMap, selectorUpToThisPoint, pseudoClassName);
	}

	private String getPseudoClassName(AttributeCondition attributeCondition) {
		return attributeCondition.getValue().replaceAll("\\(.*", "");
	}

	private <T extends SQCssPseudoClassCondition> T instantiate(Class<T> pseudoClass, PseudoClassSelector pseudoClassSelector) {
		try {
			// instantiates SQCssFunctionalPseudoClassCondition using pseudoClassSelector arg
			if (SQCssFunctionalPseudoClassCondition.class.isAssignableFrom(pseudoClass)) {
				return pseudoClass.getConstructor(new Class[]{PseudoClassSelector.class}).newInstance(pseudoClassSelector);
			}
			// instantiates non-functional SQCssPseudoClassCondition, which should not need an arg.
			return pseudoClass.getConstructor().newInstance();
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		}
	}

}
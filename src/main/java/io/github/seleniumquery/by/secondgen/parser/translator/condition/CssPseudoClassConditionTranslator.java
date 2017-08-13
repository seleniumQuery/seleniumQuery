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

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.w3c.css.sac.AttributeCondition;
import org.w3c.css.sac.SimpleSelector;

import io.github.seleniumquery.by.common.preparser.ArgumentMap;
import io.github.seleniumquery.by.firstgen.css.pseudoclasses.UnsupportedPseudoClassException;
import io.github.seleniumquery.by.secondgen.csstree.condition.CssCondition;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.CssFunctionalPseudoClassHasNoArgumentsException;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.CssPseudoClassCondition;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.basicfilter.CssAnimatedPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.basicfilter.CssEqPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.basicfilter.CssEvenPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.basicfilter.CssFirstPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.basicfilter.CssGtPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.basicfilter.CssHeaderPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.basicfilter.CssLangPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.basicfilter.CssLastPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.basicfilter.CssLtPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.basicfilter.CssNotPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.basicfilter.CssNthPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.basicfilter.CssOddPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.basicfilter.CssRootPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.basicfilter.CssTargetPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.childfilter.CssFirstChildPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.childfilter.CssFirstOfTypePseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.childfilter.CssLastChildPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.childfilter.CssLastOfTypePseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.childfilter.CssNthChildPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.childfilter.CssNthLastChildPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.childfilter.CssNthLastOfTypePseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.childfilter.CssNthOfTypePseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.childfilter.CssOnlyChildPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.childfilter.CssOnlyOfTypePseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.contentfilter.CssContainsPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.contentfilter.CssEmptyPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.contentfilter.CssHasPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.contentfilter.CssParentPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.form.CssButtonPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.form.CssCheckboxPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.form.CssCheckedPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.form.CssDisabledPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.form.CssEnabledPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.form.CssFilePseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.form.CssFocusPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.form.CssImagePseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.form.CssInputPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.form.CssPasswordPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.form.CssRadioPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.form.CssResetPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.form.CssSelectedPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.form.CssSubmitPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.form.CssTextPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.jqueryui.CssFocusablePseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.jqueryui.CssTabbablePseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.seleniumquery.CssBlankPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.seleniumquery.CssFilledPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.seleniumquery.CssPresentPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.seleniumquery.CssUncheckedPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.visibility.CssHiddenPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.visibility.CssVisiblePseudoClass;

/**
 * :pseudo-classes
 * :pseudo-classes([args])
 *
 * @author acdcjunior
 * @since 0.10.0
 */
class CssPseudoClassConditionTranslator {

    private Map<String, Function<String, ? extends CssPseudoClassCondition>> pseudoClassesF = new HashMap<>();

	CssPseudoClassConditionTranslator() {
		// form
		pseudoClassesF.put(CssButtonPseudoClass.PSEUDO, (a) -> new CssButtonPseudoClass());
		pseudoClassesF.put(CssCheckboxPseudoClass.PSEUDO, (a) -> new CssCheckboxPseudoClass());
		pseudoClassesF.put(CssCheckedPseudoClass.PSEUDO, (a) -> new CssCheckedPseudoClass());
		pseudoClassesF.put(CssEnabledPseudoClass.PSEUDO, (a) -> new CssEnabledPseudoClass());
		pseudoClassesF.put(CssDisabledPseudoClass.PSEUDO, (a) -> new CssDisabledPseudoClass());
		pseudoClassesF.put(CssFilePseudoClass.PSEUDO, (a) -> new CssFilePseudoClass());
		pseudoClassesF.put(CssFocusPseudoClass.PSEUDO, (a) -> new CssFocusPseudoClass());
		pseudoClassesF.put(CssImagePseudoClass.PSEUDO, (a) -> new CssImagePseudoClass());
		pseudoClassesF.put(CssInputPseudoClass.PSEUDO, (a) -> new CssInputPseudoClass());
		pseudoClassesF.put(CssPasswordPseudoClass.PSEUDO, (a) -> new CssPasswordPseudoClass());
		pseudoClassesF.put(CssRadioPseudoClass.PSEUDO, (a) -> new CssRadioPseudoClass());
		pseudoClassesF.put(CssResetPseudoClass.PSEUDO, (a) -> new CssResetPseudoClass());
		pseudoClassesF.put(CssSelectedPseudoClass.PSEUDO, (a) -> new CssSelectedPseudoClass());
		pseudoClassesF.put(CssSubmitPseudoClass.PSEUDO, (a) -> new CssSubmitPseudoClass());
		pseudoClassesF.put(CssTextPseudoClass.PSEUDO, (a) -> new CssTextPseudoClass());

		// basic filter
		pseudoClassesF.put(CssAnimatedPseudoClass.PSEUDO, (a) -> new CssAnimatedPseudoClass());
		pseudoClassesF.put(CssEqPseudoClass.PSEUDO, CssEqPseudoClass::new);
		pseudoClassesF.put(CssEvenPseudoClass.PSEUDO, (a) -> new CssEvenPseudoClass());
		pseudoClassesF.put(CssFirstPseudoClass.PSEUDO, (a) -> new CssFirstPseudoClass());
		pseudoClassesF.put(CssGtPseudoClass.PSEUDO, CssGtPseudoClass::new);
		pseudoClassesF.put(CssHeaderPseudoClass.PSEUDO, (a) -> new CssHeaderPseudoClass());
		pseudoClassesF.put(CssLangPseudoClass.PSEUDO, CssLangPseudoClass::new);
		pseudoClassesF.put(CssLangPseudoClass.PSEUDO_PURE_LANG, CssLangPseudoClass::new);
		pseudoClassesF.put(CssLastPseudoClass.PSEUDO, (a) -> new CssLastPseudoClass());
		pseudoClassesF.put(CssLtPseudoClass.PSEUDO, CssLtPseudoClass::new);
		pseudoClassesF.put(CssNotPseudoClass.PSEUDO, CssNotPseudoClass::new);
		pseudoClassesF.put(CssNotPseudoClass.PSEUDO_PURE_NOT, CssNotPseudoClass::new);
		pseudoClassesF.put(CssNthPseudoClass.PSEUDO, CssNthPseudoClass::new);
		pseudoClassesF.put(CssOddPseudoClass.PSEUDO, (a) -> new CssOddPseudoClass());
		pseudoClassesF.put(CssRootPseudoClass.PSEUDO, (a) -> new CssRootPseudoClass());
		pseudoClassesF.put(CssTargetPseudoClass.PSEUDO, (a) -> new CssTargetPseudoClass());

		// child filter
		pseudoClassesF.put(CssFirstChildPseudoClass.PSEUDO, (a) -> new CssFirstChildPseudoClass());
		pseudoClassesF.put(CssFirstOfTypePseudoClass.PSEUDO, (a) -> new CssFirstOfTypePseudoClass());
		pseudoClassesF.put(CssLastChildPseudoClass.PSEUDO, (a) -> new CssLastChildPseudoClass());
		pseudoClassesF.put(CssLastOfTypePseudoClass.PSEUDO, (a) -> new CssLastOfTypePseudoClass());
		pseudoClassesF.put(CssNthChildPseudoClass.PSEUDO, CssNthChildPseudoClass::new);
		pseudoClassesF.put(CssNthLastChildPseudoClass.PSEUDO, CssNthLastChildPseudoClass::new);
		pseudoClassesF.put(CssNthLastOfTypePseudoClass.PSEUDO, CssNthLastOfTypePseudoClass::new);
		pseudoClassesF.put(CssNthOfTypePseudoClass.PSEUDO, CssNthOfTypePseudoClass::new);
		pseudoClassesF.put(CssOnlyChildPseudoClass.PSEUDO, (a) -> new CssOnlyChildPseudoClass());
		pseudoClassesF.put(CssOnlyOfTypePseudoClass.PSEUDO, (a) -> new CssOnlyOfTypePseudoClass());

		// content filter
		pseudoClassesF.put(CssContainsPseudoClass.PSEUDO, CssContainsPseudoClass::new);
		pseudoClassesF.put(CssEmptyPseudoClass.PSEUDO, (a) -> new CssEmptyPseudoClass());
		pseudoClassesF.put(CssHasPseudoClass.PSEUDO, CssHasPseudoClass::new);
		pseudoClassesF.put(CssParentPseudoClass.PSEUDO, (a) -> new CssParentPseudoClass());

		// jquery-ui
		pseudoClassesF.put(CssFocusablePseudoClass.PSEUDO, (a) -> new CssFocusablePseudoClass());
		pseudoClassesF.put(CssTabbablePseudoClass.PSEUDO, (a) -> new CssTabbablePseudoClass());

		// visibility
		pseudoClassesF.put(CssHiddenPseudoClass.PSEUDO, (a) -> new CssHiddenPseudoClass());
		pseudoClassesF.put(CssVisiblePseudoClass.PSEUDO, (a) -> new CssVisiblePseudoClass());

		// seleniumQuery additions
		pseudoClassesF.put(CssPresentPseudoClass.PSEUDO, (a) -> new CssPresentPseudoClass());
		pseudoClassesF.put(CssBlankPseudoClass.PSEUDO, (a) -> new CssBlankPseudoClass());
		pseudoClassesF.put(CssFilledPseudoClass.PSEUDO, (a) -> new CssFilledPseudoClass());
		pseudoClassesF.put(CssUncheckedPseudoClass.PSEUDO, (a) -> new CssUncheckedPseudoClass());
	}

    public CssCondition translate(SimpleSelector selectorUpToThisPoint, ArgumentMap argumentMap, AttributeCondition attributeCondition) {
        String pseudoClassName = getPseudoClassName(attributeCondition);
        Function<String, ? extends CssPseudoClassCondition> pseudoClass = pseudoClassesF.get(pseudoClassName);
        if (pseudoClass == null) {
            throw new UnsupportedPseudoClassException(argumentMap, selectorUpToThisPoint, pseudoClassName);
        }
        String argumentValue = calculateFunctionalPseudoClassArgument(argumentMap, attributeCondition.getValue());
        try {
            return pseudoClass.apply(argumentValue);
        } catch (CssFunctionalPseudoClassHasNoArgumentsException e) {
            throw new IllegalArgumentException("Functional pseudo-class has no parenthesis/arguments: " + attributeCondition.getValue(), e);
        }
    }

	private String getPseudoClassName(AttributeCondition attributeCondition) {
		return attributeCondition.getValue().replaceAll("\\(.*", "");
	}

    private String calculateFunctionalPseudoClassArgument(ArgumentMap argumentMap, String pseudoClassValue) {
        if (!pseudoClassValue.contains("(")) {
            return null;
        }
        String index = pseudoClassValue.substring(pseudoClassValue.indexOf('(')+1, pseudoClassValue.length()-1);
        return argumentMap.get(index);
    }


}

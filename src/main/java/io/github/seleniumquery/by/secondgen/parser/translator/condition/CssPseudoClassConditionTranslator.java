/*
 * Copyright (c) 2017 seleniumQuery authors
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.w3c.css.sac.AttributeCondition;
import org.w3c.css.sac.SimpleSelector;

import com.google.common.annotations.VisibleForTesting;
import io.github.seleniumquery.SeleniumQueryException;
import io.github.seleniumquery.by.common.preparser.ArgumentMap;
import io.github.seleniumquery.by.firstgen.css.pseudoclasses.UnsupportedPseudoClassException;
import io.github.seleniumquery.by.secondgen.csstree.CssSelectorList;
import io.github.seleniumquery.by.secondgen.csstree.condition.CssCondition;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.CssFunctionalPseudoClassHasNoArgumentsException;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.CssPseudoClassCondition;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.InvalidPseudoClassSelectorException;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.basicfilter.AstCssAnimatedPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.basicfilter.AstCssEqPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.basicfilter.AstCssEvenPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.basicfilter.AstCssFirstPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.basicfilter.AstCssGtPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.basicfilter.AstCssHeaderPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.basicfilter.AstCssLangPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.basicfilter.AstCssLastPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.basicfilter.AstCssLtPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.basicfilter.AstCssNotPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.basicfilter.AstCssNthPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.basicfilter.AstCssOddPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.basicfilter.AstCssRootPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.basicfilter.AstCssTargetPseudoClass;
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
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.childfilter.AstCssFirstChildPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.childfilter.AstCssFirstOfTypePseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.childfilter.AstCssLastChildPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.childfilter.AstCssLastOfTypePseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.childfilter.AstCssNthChildPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.childfilter.AstCssNthLastChildPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.childfilter.AstCssNthLastOfTypePseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.childfilter.AstCssNthOfTypePseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.childfilter.AstCssOnlyChildPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.childfilter.AstCssOnlyOfTypePseudoClass;
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
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.contentfilter.AstCssContainsPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.contentfilter.AstCssEmptyPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.contentfilter.AstCssHasPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.contentfilter.AstCssParentPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.contentfilter.CssContainsPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.contentfilter.CssEmptyPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.contentfilter.CssHasPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.contentfilter.CssParentPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.form.AstCssButtonPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.form.AstCssCheckboxPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.form.AstCssCheckedPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.form.AstCssDisabledPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.form.AstCssEnabledPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.form.AstCssFilePseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.form.AstCssFocusPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.form.AstCssImagePseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.form.AstCssInputPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.form.AstCssPasswordPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.form.AstCssRadioPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.form.AstCssResetPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.form.AstCssSelectedPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.form.AstCssSubmitPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.form.AstCssTextPseudoClass;
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
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.jqueryui.AstCssFocusablePseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.jqueryui.AstCssTabbablePseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.jqueryui.CssFocusablePseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.jqueryui.CssTabbablePseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.seleniumquery.AstCssBlankPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.seleniumquery.AstCssFilledPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.seleniumquery.AstCssPresentPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.seleniumquery.AstCssUncheckedPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.seleniumquery.CssBlankPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.seleniumquery.CssFilledPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.seleniumquery.CssPresentPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.seleniumquery.CssUncheckedPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.visibility.AstCssHiddenPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.visibility.AstCssVisiblePseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.visibility.CssHiddenPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.visibility.CssVisiblePseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ParseTreeBuilder;

/**
 * :pseudo-classes
 * :pseudo-classes([args])
 *
 * @author acdcjunior
 * @since 0.10.0
 */
class CssPseudoClassConditionTranslator {

    private static final Pattern INDEX_REGEX = Pattern.compile("^\\s*([+-]?\\d+)\\s*$");
    private Map<String, Function<String, ? extends CssPseudoClassCondition>> pseudoClassesF = new HashMap<>();

	CssPseudoClassConditionTranslator() {
		// form
		pseudoClassesF.put(AstCssButtonPseudoClass.PSEUDO, a -> new CssButtonPseudoClass());
		pseudoClassesF.put(AstCssCheckboxPseudoClass.PSEUDO, a -> new CssCheckboxPseudoClass());
		pseudoClassesF.put(AstCssCheckedPseudoClass.PSEUDO, a -> new CssCheckedPseudoClass());
		pseudoClassesF.put(AstCssEnabledPseudoClass.PSEUDO, a -> new CssEnabledPseudoClass());
		pseudoClassesF.put(AstCssDisabledPseudoClass.PSEUDO, a -> new CssDisabledPseudoClass());
		pseudoClassesF.put(AstCssFilePseudoClass.PSEUDO, a -> new CssFilePseudoClass());
		pseudoClassesF.put(AstCssFocusPseudoClass.PSEUDO, a -> new CssFocusPseudoClass());
		pseudoClassesF.put(AstCssImagePseudoClass.PSEUDO, a -> new CssImagePseudoClass());
		pseudoClassesF.put(AstCssInputPseudoClass.PSEUDO, a -> new CssInputPseudoClass());
		pseudoClassesF.put(AstCssPasswordPseudoClass.PSEUDO, a -> new CssPasswordPseudoClass());
		pseudoClassesF.put(AstCssRadioPseudoClass.PSEUDO, a -> new CssRadioPseudoClass());
		pseudoClassesF.put(AstCssResetPseudoClass.PSEUDO, a -> new CssResetPseudoClass());
		pseudoClassesF.put(AstCssSelectedPseudoClass.PSEUDO, a -> new CssSelectedPseudoClass());
		pseudoClassesF.put(AstCssSubmitPseudoClass.PSEUDO, a -> new CssSubmitPseudoClass());
		pseudoClassesF.put(AstCssTextPseudoClass.PSEUDO, a -> new CssTextPseudoClass());

		// basic filter
		pseudoClassesF.put(AstCssAnimatedPseudoClass.PSEUDO, a -> new CssAnimatedPseudoClass());
		pseudoClassesF.put(AstCssEqPseudoClass.PSEUDO, a -> new CssEqPseudoClass(new AstCssEqPseudoClass(extractIndexArgument(a, AstCssEqPseudoClass.PSEUDO))));
		pseudoClassesF.put(AstCssEvenPseudoClass.PSEUDO, a -> new CssEvenPseudoClass());
		pseudoClassesF.put(AstCssFirstPseudoClass.PSEUDO, a -> new CssFirstPseudoClass());
		pseudoClassesF.put(AstCssGtPseudoClass.PSEUDO, a -> new CssGtPseudoClass(extractIndexArgument(a, AstCssGtPseudoClass.PSEUDO)));
		pseudoClassesF.put(AstCssHeaderPseudoClass.PSEUDO, a -> new CssHeaderPseudoClass());
		pseudoClassesF.put(AstCssLangPseudoClass.PSEUDO, CssLangPseudoClass::new);
		pseudoClassesF.put(AstCssLangPseudoClass.PSEUDO_PURE_LANG, CssLangPseudoClass::new);
		pseudoClassesF.put(AstCssLastPseudoClass.PSEUDO, a -> new CssLastPseudoClass());
		pseudoClassesF.put(AstCssLtPseudoClass.PSEUDO, a -> new CssLtPseudoClass(extractIndexArgument(a, AstCssLtPseudoClass.PSEUDO)));
		pseudoClassesF.put(AstCssNotPseudoClass.PSEUDO, a -> new CssNotPseudoClass(new AstCssNotPseudoClass(parseFunctionalPseudoClassSelectorArgument(AstCssNotPseudoClass.PSEUDO_PURE_NOT, a))));
		pseudoClassesF.put(AstCssNotPseudoClass.PSEUDO_PURE_NOT, a -> new CssNotPseudoClass(new AstCssNotPseudoClass(parseFunctionalPseudoClassSelectorArgument(AstCssNotPseudoClass.PSEUDO_PURE_NOT, a))));
		pseudoClassesF.put(AstCssNthPseudoClass.PSEUDO, a -> new CssNthPseudoClass(extractIndexArgument(a, AstCssNthPseudoClass.PSEUDO)));
		pseudoClassesF.put(AstCssOddPseudoClass.PSEUDO, a -> new CssOddPseudoClass());
		pseudoClassesF.put(AstCssRootPseudoClass.PSEUDO, a -> new CssRootPseudoClass());
		pseudoClassesF.put(AstCssTargetPseudoClass.PSEUDO, a -> new CssTargetPseudoClass());

		// child filter
		pseudoClassesF.put(AstCssFirstChildPseudoClass.PSEUDO, a -> new CssFirstChildPseudoClass());
		pseudoClassesF.put(AstCssFirstOfTypePseudoClass.PSEUDO, a -> new CssFirstOfTypePseudoClass());
		pseudoClassesF.put(AstCssLastChildPseudoClass.PSEUDO, a -> new CssLastChildPseudoClass());
		pseudoClassesF.put(AstCssLastOfTypePseudoClass.PSEUDO, a -> new CssLastOfTypePseudoClass());
		pseudoClassesF.put(AstCssNthChildPseudoClass.PSEUDO, CssNthChildPseudoClass::new);
		pseudoClassesF.put(AstCssNthLastChildPseudoClass.PSEUDO, CssNthLastChildPseudoClass::new);
		pseudoClassesF.put(AstCssNthLastOfTypePseudoClass.PSEUDO, CssNthLastOfTypePseudoClass::new);
		pseudoClassesF.put(AstCssNthOfTypePseudoClass.PSEUDO, CssNthOfTypePseudoClass::new);
		pseudoClassesF.put(AstCssOnlyChildPseudoClass.PSEUDO, a -> new CssOnlyChildPseudoClass());
		pseudoClassesF.put(AstCssOnlyOfTypePseudoClass.PSEUDO, a -> new CssOnlyOfTypePseudoClass());

		// content filter
		pseudoClassesF.put(AstCssContainsPseudoClass.PSEUDO, a -> new CssContainsPseudoClass(new AstCssContainsPseudoClass(a)));
		pseudoClassesF.put(AstCssEmptyPseudoClass.PSEUDO, a -> new CssEmptyPseudoClass());
		pseudoClassesF.put(AstCssHasPseudoClass.PSEUDO, a -> new CssHasPseudoClass(new AstCssHasPseudoClass(parseFunctionalPseudoClassSelectorArgument(AstCssHasPseudoClass.PSEUDO, a))));
		pseudoClassesF.put(AstCssParentPseudoClass.PSEUDO, a -> new CssParentPseudoClass());

		// jquery-ui
		pseudoClassesF.put(AstCssFocusablePseudoClass.PSEUDO, a -> new CssFocusablePseudoClass());
		pseudoClassesF.put(AstCssTabbablePseudoClass.PSEUDO, a -> new CssTabbablePseudoClass());

		// visibility
		pseudoClassesF.put(AstCssHiddenPseudoClass.PSEUDO, a -> new CssHiddenPseudoClass());
		pseudoClassesF.put(AstCssVisiblePseudoClass.PSEUDO, a -> new CssVisiblePseudoClass());

		// seleniumQuery additions
		pseudoClassesF.put(AstCssPresentPseudoClass.PSEUDO, a -> new CssPresentPseudoClass());
		pseudoClassesF.put(AstCssBlankPseudoClass.PSEUDO, a -> new CssBlankPseudoClass());
		pseudoClassesF.put(AstCssFilledPseudoClass.PSEUDO, a -> new CssFilledPseudoClass());
		pseudoClassesF.put(AstCssUncheckedPseudoClass.PSEUDO, a -> new CssUncheckedPseudoClass());
	}

    // This is visible until we find a way to test it indirectly.
	@VisibleForTesting
    static int extractIndexArgument(String indexPseudoClassArgument, String pseudoClassName) {
        if (indexPseudoClassArgument == null) {
            throw new CssFunctionalPseudoClassHasNoArgumentsException();
        }
        Matcher m = INDEX_REGEX.matcher(indexPseudoClassArgument);
        boolean isArgumentAnInteger = m.find();
        if (!isArgumentAnInteger) {
            String reason = String.format("The :%s() pseudo-class requires an integer as argument but got: \"%s\".",
                pseudoClassName, indexPseudoClassArgument);
            throw new InvalidPseudoClassSelectorException(reason);
        }
        String integerIndex = m.group(1);
        if (integerIndex.startsWith("+")) {
            integerIndex = integerIndex.substring(1);
        }
        return Integer.valueOf(integerIndex);
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

    private CssSelectorList parseFunctionalPseudoClassSelectorArgument(String pseudoClassName, String selector) {
        if (selector == null) {
            throw new CssFunctionalPseudoClassHasNoArgumentsException();
        }
        try {
            return ParseTreeBuilder.parse(selector);
        } catch (SeleniumQueryException e) {
            throw new SeleniumQueryException("Error while parsing pseudo-class `:" + pseudoClassName + "(" +selector+")`: " + e.getLocalizedMessage(), e);
        }
    }

}

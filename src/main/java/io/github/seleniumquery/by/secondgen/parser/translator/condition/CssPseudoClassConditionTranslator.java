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
import io.github.seleniumquery.by.secondgen.parser.ParseTreeBuilder;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.AstCssFunctionalPseudoClassHasNoArgumentsException;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.AstCssPseudoClassCondition;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.InvalidPseudoClassSelectorException;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.basicfilter.AstCssAnimatedPseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.basicfilter.AstCssEqPseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.basicfilter.AstCssEvenPseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.basicfilter.AstCssFirstPseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.basicfilter.AstCssGtPseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.basicfilter.AstCssHeaderPseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.basicfilter.AstCssLangPseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.basicfilter.AstCssLastPseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.basicfilter.AstCssLtPseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.basicfilter.AstCssNotPseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.basicfilter.AstCssNthPseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.basicfilter.AstCssOddPseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.basicfilter.AstCssRootPseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.basicfilter.AstCssTargetPseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.childfilter.AstCssFirstChildPseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.childfilter.AstCssFirstOfTypePseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.childfilter.AstCssLastChildPseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.childfilter.AstCssLastOfTypePseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.childfilter.AstCssNthChildPseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.childfilter.AstCssNthLastChildPseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.childfilter.AstCssNthLastOfTypePseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.childfilter.AstCssNthOfTypePseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.childfilter.AstCssOnlyChildPseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.childfilter.AstCssOnlyOfTypePseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.contentfilter.AstCssContainsPseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.contentfilter.AstCssEmptyPseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.contentfilter.AstCssHasPseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.contentfilter.AstCssParentPseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.form.AstCssButtonPseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.form.AstCssCheckboxPseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.form.AstCssCheckedPseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.form.AstCssDisabledPseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.form.AstCssEnabledPseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.form.AstCssFilePseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.form.AstCssFocusPseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.form.AstCssImagePseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.form.AstCssInputPseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.form.AstCssPasswordPseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.form.AstCssRadioPseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.form.AstCssResetPseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.form.AstCssSelectedPseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.form.AstCssSubmitPseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.form.AstCssTextPseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.jqueryui.AstCssFocusablePseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.jqueryui.AstCssTabbablePseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.seleniumquery.AstCssBlankPseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.seleniumquery.AstCssFilledPseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.seleniumquery.AstCssPresentPseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.seleniumquery.AstCssUncheckedPseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.visibility.AstCssHiddenPseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.visibility.AstCssVisiblePseudoClass;

/**
 * :pseudo-classes
 * :pseudo-classes([args])
 *
 * @author acdcjunior
 * @since 0.10.0
 */
class CssPseudoClassConditionTranslator {

    private static final Pattern INDEX_REGEX = Pattern.compile("^\\s*([+-]?\\d+)\\s*$");

    private Map<String, Function<String, ? extends AstCssPseudoClassCondition>> pseudoClassesF = new HashMap<>();

	@SuppressWarnings("Convert2MethodRef")
    CssPseudoClassConditionTranslator() {
		// form
		pseudoClassesF.put(AstCssButtonPseudoClass.PSEUDO, a -> new AstCssButtonPseudoClass());
		pseudoClassesF.put(AstCssCheckboxPseudoClass.PSEUDO, a -> new AstCssCheckboxPseudoClass());
		pseudoClassesF.put(AstCssCheckedPseudoClass.PSEUDO, a -> new AstCssCheckedPseudoClass());
		pseudoClassesF.put(AstCssEnabledPseudoClass.PSEUDO, a -> new AstCssEnabledPseudoClass());
		pseudoClassesF.put(AstCssDisabledPseudoClass.PSEUDO, a -> new AstCssDisabledPseudoClass());
		pseudoClassesF.put(AstCssFilePseudoClass.PSEUDO, a -> new AstCssFilePseudoClass());
		pseudoClassesF.put(AstCssFocusPseudoClass.PSEUDO, a -> new AstCssFocusPseudoClass());
		pseudoClassesF.put(AstCssImagePseudoClass.PSEUDO, a -> new AstCssImagePseudoClass());
		pseudoClassesF.put(AstCssInputPseudoClass.PSEUDO, a -> new AstCssInputPseudoClass());
		pseudoClassesF.put(AstCssPasswordPseudoClass.PSEUDO, a -> new AstCssPasswordPseudoClass());
		pseudoClassesF.put(AstCssRadioPseudoClass.PSEUDO, a -> new AstCssRadioPseudoClass());
		pseudoClassesF.put(AstCssResetPseudoClass.PSEUDO, a -> new AstCssResetPseudoClass());
		pseudoClassesF.put(AstCssSelectedPseudoClass.PSEUDO, a -> new AstCssSelectedPseudoClass());
		pseudoClassesF.put(AstCssSubmitPseudoClass.PSEUDO, a -> new AstCssSubmitPseudoClass());
		pseudoClassesF.put(AstCssTextPseudoClass.PSEUDO, a -> new AstCssTextPseudoClass());

		// basic filter
		pseudoClassesF.put(AstCssAnimatedPseudoClass.PSEUDO, a -> new AstCssAnimatedPseudoClass());
		pseudoClassesF.put(AstCssEqPseudoClass.PSEUDO, a -> new AstCssEqPseudoClass(extractIndexArgument(a, AstCssEqPseudoClass.PSEUDO)));
		pseudoClassesF.put(AstCssEvenPseudoClass.PSEUDO, a -> new AstCssEvenPseudoClass());
		pseudoClassesF.put(AstCssFirstPseudoClass.PSEUDO, a -> new AstCssFirstPseudoClass());
		pseudoClassesF.put(AstCssGtPseudoClass.PSEUDO, a -> new AstCssGtPseudoClass(extractIndexArgument(a, AstCssGtPseudoClass.PSEUDO)));
		pseudoClassesF.put(AstCssHeaderPseudoClass.PSEUDO, a -> new AstCssHeaderPseudoClass());
		pseudoClassesF.put(AstCssLangPseudoClass.PSEUDO, a -> new AstCssLangPseudoClass(a));
		pseudoClassesF.put(AstCssLangPseudoClass.PSEUDO_PURE_LANG, a -> new AstCssLangPseudoClass(a));
		pseudoClassesF.put(AstCssLastPseudoClass.PSEUDO, a -> new AstCssLastPseudoClass());
		pseudoClassesF.put(AstCssLtPseudoClass.PSEUDO, a -> new AstCssLtPseudoClass(extractIndexArgument(a, AstCssLtPseudoClass.PSEUDO)));
		pseudoClassesF.put(AstCssNotPseudoClass.PSEUDO, a -> new AstCssNotPseudoClass(parseFunctionalPseudoClassSelectorArgument(AstCssNotPseudoClass.PSEUDO_PURE_NOT, a)));
		pseudoClassesF.put(AstCssNotPseudoClass.PSEUDO_PURE_NOT, a -> new AstCssNotPseudoClass(parseFunctionalPseudoClassSelectorArgument(AstCssNotPseudoClass.PSEUDO_PURE_NOT, a)));
		pseudoClassesF.put(AstCssNthPseudoClass.PSEUDO, a -> new AstCssNthPseudoClass(extractIndexArgument(a, AstCssNthPseudoClass.PSEUDO)));
		pseudoClassesF.put(AstCssOddPseudoClass.PSEUDO, a -> new AstCssOddPseudoClass());
		pseudoClassesF.put(AstCssRootPseudoClass.PSEUDO, a -> new AstCssRootPseudoClass());
		pseudoClassesF.put(AstCssTargetPseudoClass.PSEUDO, a -> new AstCssTargetPseudoClass());

		// child filter
		pseudoClassesF.put(AstCssFirstChildPseudoClass.PSEUDO, a -> new AstCssFirstChildPseudoClass());
		pseudoClassesF.put(AstCssFirstOfTypePseudoClass.PSEUDO, a -> new AstCssFirstOfTypePseudoClass());
		pseudoClassesF.put(AstCssLastChildPseudoClass.PSEUDO, a -> new AstCssLastChildPseudoClass());
		pseudoClassesF.put(AstCssLastOfTypePseudoClass.PSEUDO, a -> new AstCssLastOfTypePseudoClass());
		pseudoClassesF.put(AstCssNthChildPseudoClass.PSEUDO, a -> new AstCssNthChildPseudoClass(a));
		pseudoClassesF.put(AstCssNthLastChildPseudoClass.PSEUDO, a -> new AstCssNthLastChildPseudoClass(a));
		pseudoClassesF.put(AstCssNthLastOfTypePseudoClass.PSEUDO, a -> new AstCssNthLastOfTypePseudoClass(a));
		pseudoClassesF.put(AstCssNthOfTypePseudoClass.PSEUDO, a -> new AstCssNthOfTypePseudoClass(a));
		pseudoClassesF.put(AstCssOnlyChildPseudoClass.PSEUDO, a -> new AstCssOnlyChildPseudoClass());
		pseudoClassesF.put(AstCssOnlyOfTypePseudoClass.PSEUDO, a -> new AstCssOnlyOfTypePseudoClass());

		// content filter
		pseudoClassesF.put(AstCssContainsPseudoClass.PSEUDO, a -> new AstCssContainsPseudoClass(a));
		pseudoClassesF.put(AstCssEmptyPseudoClass.PSEUDO, a -> new AstCssEmptyPseudoClass());
		pseudoClassesF.put(AstCssHasPseudoClass.PSEUDO, a -> new AstCssHasPseudoClass(parseFunctionalPseudoClassSelectorArgument(AstCssHasPseudoClass.PSEUDO, a)));
		pseudoClassesF.put(AstCssParentPseudoClass.PSEUDO, a -> new AstCssParentPseudoClass());

		// jquery-ui
		pseudoClassesF.put(AstCssFocusablePseudoClass.PSEUDO, a -> new AstCssFocusablePseudoClass());
		pseudoClassesF.put(AstCssTabbablePseudoClass.PSEUDO, a -> new AstCssTabbablePseudoClass());

		// visibility
		pseudoClassesF.put(AstCssHiddenPseudoClass.PSEUDO, a -> new AstCssHiddenPseudoClass());
		pseudoClassesF.put(AstCssVisiblePseudoClass.PSEUDO, a -> new AstCssVisiblePseudoClass());

		// seleniumQuery additions
		pseudoClassesF.put(AstCssPresentPseudoClass.PSEUDO, a -> new AstCssPresentPseudoClass());
		pseudoClassesF.put(AstCssBlankPseudoClass.PSEUDO, a -> new AstCssBlankPseudoClass());
		pseudoClassesF.put(AstCssFilledPseudoClass.PSEUDO, a -> new AstCssFilledPseudoClass());
		pseudoClassesF.put(AstCssUncheckedPseudoClass.PSEUDO, a -> new AstCssUncheckedPseudoClass());
	}

    // This is visible until we find a way to test it indirectly.
	@VisibleForTesting
    static int extractIndexArgument(String indexPseudoClassArgument, String pseudoClassName) {
        if (indexPseudoClassArgument == null) {
            throw new AstCssFunctionalPseudoClassHasNoArgumentsException();
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

    public AstCssPseudoClassCondition translate(SimpleSelector selectorUpToThisPoint, ArgumentMap argumentMap, AttributeCondition attributeCondition) {
        String pseudoClassName = getPseudoClassName(attributeCondition);
        Function<String, ? extends AstCssPseudoClassCondition> pseudoClass = pseudoClassesF.get(pseudoClassName);
        if (pseudoClass == null) {
            throw new UnsupportedPseudoClassException(argumentMap, selectorUpToThisPoint, pseudoClassName);
        }
        String argumentValue = calculateFunctionalPseudoClassArgument(argumentMap, attributeCondition.getValue());
        try {
            return pseudoClass.apply(argumentValue);
        } catch (AstCssFunctionalPseudoClassHasNoArgumentsException e) {
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
            throw new AstCssFunctionalPseudoClassHasNoArgumentsException();
        }
        try {
            return ParseTreeBuilder.parse(selector);
        } catch (SeleniumQueryException e) {
            throw new SeleniumQueryException("Error while parsing pseudo-class `:" + pseudoClassName + "(" +selector+")`: " + e.getLocalizedMessage(), e);
        }
    }

}

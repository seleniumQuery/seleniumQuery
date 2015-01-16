package io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.basicfilter;

import io.github.seleniumquery.by.css.pseudoclasses.PseudoClassSelector;
import io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.SQCssFunctionalPseudoClassCondition;

/**
 * https://developer.mozilla.org/en-US/docs/Web/CSS/:lang
 *
 * @author acdcjunior
 * @since 0.10.0
 */
public class SQCssLangPseudoClass extends SQCssFunctionalPseudoClassCondition {

    // :lang(), similar to :not(), gets translated into :lang-sq() by the pre-parser
    public static final String PSEUDO = "lang-sq";

    /* when used without args, such as "div:lang", the pre-parser does not translate it. it is invalid,
      but we still match it, so we can return a proper error message */
    public static final String PSEUDO_PURE_LANG = "lang";

    public SQCssLangPseudoClass(PseudoClassSelector pseudoClassSelector) {
        super(pseudoClassSelector);
    }

}
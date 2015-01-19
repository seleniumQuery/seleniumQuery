package io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass;

import io.github.seleniumquery.by.csstree.condition.SQCssCondition;

import static io.github.seleniumquery.by.parser.translator.condition.attribute.TranslatorsTestUtils.parseFirstCssCondition;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class PseudoClassTestUtils {

    public static <T extends SQCssCondition> void assertPseudo(String selector, Class<T> pseudoClassClass) {
        // given
        // selector
        // when
        T cssCondition = parseFirstCssCondition(selector, pseudoClassClass);
        // then
        assertThat(cssCondition, is(notNullValue()));
    }

    public static <T extends SQCssFunctionalPseudoClassCondition> void assertFunctionalPseudo(String selector,
                                                                                              Class<T> pseudoClassClass) {
        assertSelectorTranslatesArgument(selector, pseudoClassClass, "(0)", "0");
        assertSelectorTranslatesArgument(selector, pseudoClassClass, "(-0)", "-0");
        assertSelectorTranslatesArgument(selector, pseudoClassClass, "(+0)", "+0");
        assertSelectorTranslatesArgument(selector, pseudoClassClass, "(1)", "1");
        assertSelectorTranslatesArgument(selector, pseudoClassClass, "(-1)", "-1");
        assertSelectorTranslatesArgument(selector, pseudoClassClass, "(+1)", "+1");
        assertSelectorTranslatesArgument(selector, pseudoClassClass, "(999999)", "999999");
        assertSelectorTranslatesArgument(selector, pseudoClassClass, "(-999999)", "-999999");

        assertSelectorTranslatesArgument(selector, pseudoClassClass, "", null);
        assertSelectorTranslatesArgument(selector, pseudoClassClass, "()", "");
        assertSelectorTranslatesArgument(selector, pseudoClassClass, "( )", " ");
        assertSelectorTranslatesArgument(selector, pseudoClassClass, "(     )", "     ");

        assertSelectorTranslatesArgument(selector, pseudoClassClass, "(a)", "a");
        assertSelectorTranslatesArgument(selector, pseudoClassClass, "('a')", "'a'");
        assertSelectorTranslatesArgument(selector, pseudoClassClass, "(\"a\")", "\"a\"");

        assertSelectorTranslatesArgument(selector, pseudoClassClass, "(a b c d e)", "a b c d e");
        assertSelectorTranslatesArgument(selector, pseudoClassClass, "(\"a b c d e\")", "\"a b c d e\"");
    }

    private static <T extends SQCssFunctionalPseudoClassCondition> void assertSelectorTranslatesArgument(String selector,
                                                                                                         Class<T> pseudoClassClass,
                                                                                                         String selectorSuffix,
                                                                                                         String expectedArgument) {
        // given
        // selector
        // when
        T cssCondition = parseFirstCssCondition(selector+selectorSuffix, pseudoClassClass);
        // then
        if (expectedArgument != null) {
            assertThat(cssCondition.getArgument(), is(expectedArgument));
        } else {
            assertThat(cssCondition.getArgument(), is(nullValue()));
        }
    }

}
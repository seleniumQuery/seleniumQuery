package io.github.seleniumquery.internal.fluentfunctions.evaluators.matches;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static testinfrastructure.testdouble.io.github.seleniumquery.SeleniumQueryObjectMother
    .createStubSeleniumQueryObjectWithElements;

import java.util.regex.Pattern;

import org.junit.Test;

import io.github.seleniumquery.SeleniumQueryObject;
import io.github.seleniumquery.functions.jquery.manipulation.TextFunctionTest;
import io.github.seleniumquery.internal.fluentfunctions.FluentBehaviorModifier;
import io.github.seleniumquery.internal.fluentfunctions.getters.TextGetter;

public class MatchesPatternEvaluatorTest {

    private final MatchesPatternEvaluator matchesPatternEvaluator = new MatchesPatternEvaluator(TextGetter.TEXT_GETTER);
    private final Pattern caseInsensitivePattern = Pattern.compile("A{3} B{3}", Pattern.CASE_INSENSITIVE);

    @Test
    public void evaluates__success() {
        // given
        SeleniumQueryObject elements = createStubSeleniumQueryObjectWithElements(new TextFunctionTest.WebElementText("aaa"), new TextFunctionTest.WebElementText("bbb"));
        // when
        boolean evaluate = matchesPatternEvaluator.evaluate(elements, caseInsensitivePattern);
        // then
        assertTrue(evaluate);
    }

    @Test
    public void evaluates__fail() {
        // given
        SeleniumQueryObject elements = createStubSeleniumQueryObjectWithElements(new TextFunctionTest.WebElementText("zzz"), new TextFunctionTest.WebElementText("bbb"));
        // when
        boolean evaluate = matchesPatternEvaluator.evaluate(elements, caseInsensitivePattern);
        // then
        assertFalse(evaluate);
    }

    @Test
    public void stringFor() {
        // when
        String stringFor = matchesPatternEvaluator.stringFor(caseInsensitivePattern, FluentBehaviorModifier.NEGATED_BEHAVIOR);
        // then
        assertEquals("text().not().matches(\"A{3} B{3}\")", stringFor);
    }

}

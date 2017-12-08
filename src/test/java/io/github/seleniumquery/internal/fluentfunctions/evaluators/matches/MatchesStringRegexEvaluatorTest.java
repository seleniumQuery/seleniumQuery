package io.github.seleniumquery.internal.fluentfunctions.evaluators.matches;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static testinfrastructure.testdouble.io.github.seleniumquery.SeleniumQueryObjectMother
    .createStubSeleniumQueryObjectWithElements;

import org.junit.Test;

import io.github.seleniumquery.SeleniumQueryObject;
import io.github.seleniumquery.functions.jquery.manipulation.TextFunctionTest;
import io.github.seleniumquery.internal.fluentfunctions.FluentBehaviorModifier;
import io.github.seleniumquery.internal.fluentfunctions.evaluators.EvaluationReport;
import io.github.seleniumquery.internal.fluentfunctions.getters.TextGetter;

public class MatchesStringRegexEvaluatorTest {

    private final MatchesStringRegexEvaluator matchesStringRegexEvaluator = new MatchesStringRegexEvaluator(TextGetter.TEXT_GETTER);

    @Test
    public void evaluates__success() {
        // given
        SeleniumQueryObject elements = createStubSeleniumQueryObjectWithElements(new TextFunctionTest.WebElementText("aaa"), new TextFunctionTest.WebElementText("bbb"));
        // when
        EvaluationReport evaluate = matchesStringRegexEvaluator.evaluate(elements, "a{3} b{3}");
        // then
        assertTrue(evaluate.isSatisfiesConstraints());
    }

    @Test
    public void evaluates__fails() {
        // given
        SeleniumQueryObject elements = createStubSeleniumQueryObjectWithElements(new TextFunctionTest.WebElementText("zzz"), new TextFunctionTest.WebElementText("bbb"));
        // when
        EvaluationReport evaluate = matchesStringRegexEvaluator.evaluate(elements, "a{3} b{3}");
        // then
        assertFalse(evaluate.isSatisfiesConstraints());
    }

    @Test
    public void stringFor() {
        // when
        String stringFor = matchesStringRegexEvaluator.describeEvaluatorFunction("a{3} b{3}", FluentBehaviorModifier.NEGATED_BEHAVIOR);
        // then
        assertEquals("text().not().matches(\"a{3} b{3}\")", stringFor);
    }

}

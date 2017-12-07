package io.github.seleniumquery.internal.fluentfunctions.evaluators.matches;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.github.seleniumquery.functions.jquery.manipulation.TextFunctionTest;
import io.github.seleniumquery.internal.fluentfunctions.FluentBehaviorModifier;
import io.github.seleniumquery.internal.fluentfunctions.getters.TextGetter;

public class MatchesStringRegexEvaluatorTest {

    private static final WebDriver UNUSED_DRIVER = null;
    private final MatchesStringRegexEvaluator matchesStringRegexEvaluator = new MatchesStringRegexEvaluator(TextGetter.TEXT_GETTER);

    @Test
    public void evaluates__success() {
        // given
        List<WebElement> elements = asList(new TextFunctionTest.WebElementText("aaa"), new TextFunctionTest.WebElementText("bbb"));
        // when
        boolean evaluate = matchesStringRegexEvaluator.evaluate(UNUSED_DRIVER, elements, "a{3} b{3}");
        // then
        assertTrue(evaluate);
    }

    @Test
    public void evaluates__fails() {
        // given
        List<WebElement> elements = asList(new TextFunctionTest.WebElementText("zzz"), new TextFunctionTest.WebElementText("bbb"));
        // when
        boolean evaluate = matchesStringRegexEvaluator.evaluate(UNUSED_DRIVER, elements, "a{3} b{3}");
        // then
        assertFalse(evaluate);
    }

    @Test
    public void stringFor() {
        // when
        String stringFor = matchesStringRegexEvaluator.stringFor("a{3} b{3}", FluentBehaviorModifier.NEGATED_BEHAVIOR);
        // then
        assertEquals("text().not().matches(\"a{3} b{3}\")", stringFor);
    }

}

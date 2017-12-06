package io.github.seleniumquery.internal.fluentfunctions.evaluators;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

import java.util.List;
import java.util.regex.Pattern;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.github.seleniumquery.functions.jquery.manipulation.TextFunctionTest;
import io.github.seleniumquery.internal.fluentfunctions.FluentBehaviorModifier;
import io.github.seleniumquery.internal.fluentfunctions.getters.TextGetter;

public class MatchesPatternEvaluatorTest {

    private static final WebDriver UNUSED_DRIVER = null;
    private final MatchesPatternEvaluator matchesPatternEvaluator = new MatchesPatternEvaluator(TextGetter.TEXT_GETTER);
    private final Pattern caseInsensitivePattern = Pattern.compile("A{3} B{3}", Pattern
        .CASE_INSENSITIVE);

    @Test
    public void evaluates__success() {
        // given
        List<WebElement> elements = asList(new TextFunctionTest.WebElementText("aaa"), new TextFunctionTest.WebElementText("bbb"));
        // when
        boolean evaluate = matchesPatternEvaluator.evaluate(UNUSED_DRIVER, elements, caseInsensitivePattern);
        // then
        assertTrue(evaluate);
    }

    @Test
    public void evaluates__fail() {
        // given
        List<WebElement> elements = asList(new TextFunctionTest.WebElementText("zzz"), new TextFunctionTest.WebElementText("bbb"));
        // when
        boolean evaluate = matchesPatternEvaluator.evaluate(UNUSED_DRIVER, elements, caseInsensitivePattern);
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

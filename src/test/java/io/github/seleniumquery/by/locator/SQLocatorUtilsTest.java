package io.github.seleniumquery.by.locator;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SQLocatorUtilsTest {

    public static final SQLocator TAG_ASTERISK = new SQLocator("*", ".//*[true()]");

    @Test
    public void conditionalSimpleXPathMerge__should_merge_XPath_condition_adding_and() {
        String mergedXPath = SQLocatorUtils.conditionalSimpleXPathMerge(".//*[previousStuff]", "newStuff");
        assertThat(mergedXPath, is(".//*[previousStuff and newStuff]"));
    }

    @Test
    public void conditionalSimpleXPathMerge__should_remove_last_previous_condition_if_it_was_just_true() {
        String mergedXPath = SQLocatorUtils.conditionalSimpleXPathMerge(".//*[true()]", "newStuff");
        assertThat(mergedXPath, is(".//*[newStuff]"));
    }

    @Test
    public void conditionalSimpleXPathMerge__should_remove_last_previous_condition_if_it_was_just_true_ALTERNATE() {
        String mergedXPath = SQLocatorUtils.conditionalSimpleXPathMerge(".//*[self::a]/*[true()]", "newStuff");
        assertThat(mergedXPath, is(".//*[self::a]/*[newStuff]"));
    }

    @Test
    public void conditionalSimpleXPathMerge__should_remove_last_previous_condition_if_it_was_just_true_even_if_there_was_something_else() {
        String mergedXPath = SQLocatorUtils.conditionalSimpleXPathMerge(".//*[previousStuff and true()]", "newStuff");
        assertThat(mergedXPath, is(".//*[previousStuff and newStuff]"));
    }

    @Test
    public void conditionalSimpleXPathMerge__should_remove_last_previous_condition_if_it_was_just_true_but_with_care() {
        String mergedXPath = SQLocatorUtils.conditionalSimpleXPathMerge(".//*[xtrue()]", "newStuff");
        assertThat(mergedXPath, is(".//*[xtrue() and newStuff]"));
    }

    @Test
    public void conditionalSimpleXPathMerge__should_remove_last_previous_condition_if_it_was_just_true_but_with_care_even_if_there_was_something_else() {
        String mergedXPath = SQLocatorUtils.conditionalSimpleXPathMerge("[previousStuff and xtrue()]", "newStuff");
        assertThat(mergedXPath, is("[previousStuff and xtrue() and newStuff]"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void conditionalSimpleXPathMerge__should_validate_the_left_expression() {
        SQLocatorUtils.conditionalSimpleXPathMerge("true()", "newStuff");
    }

    @Test(expected = IllegalArgumentException.class)
    public void conditionalSimpleXPathMerge__should_validate_the_left_expression_for_nullity() {
        SQLocatorUtils.conditionalSimpleXPathMerge(null, "newStuff");
    }

    @Test
    public void cssMerge__should_merge() {
        String mergedCss = SQLocatorUtils.cssMerge("div", ".clz");
        assertThat(mergedCss, is("div.clz"));
    }

    @Test
    public void cssMerge__should_remove_asterisk() {
        String mergedCss = SQLocatorUtils.cssMerge("*", ".clz");
        assertThat(mergedCss, is(".clz"));
    }

}
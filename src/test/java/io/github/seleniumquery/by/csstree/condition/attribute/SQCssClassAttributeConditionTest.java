package io.github.seleniumquery.by.csstree.condition.attribute;

import io.github.seleniumquery.by.filter.ElementFilter;
import io.github.seleniumquery.by.locator.SQLocator;
import io.github.seleniumquery.by.locator.SQLocatorUtilsTest;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.junit.Assert.assertThat;

public class SQCssClassAttributeConditionTest {

    @Test
    public void toSQLocator() {
        // given
        SQCssClassAttributeCondition tagNameSelector = new SQCssClassAttributeCondition("clazz");
        SQLocator previous = SQLocatorUtilsTest.TAG_ASTERISK;
        // when
        SQLocator locator = tagNameSelector.toSQLocator(previous);
        // then
        assertThat(locator.getCssSelector(), is(".clazz"));
        assertThat(locator.getXPathExpression(), is(".//*[contains(concat(' ', normalize-space(@class), ' '), ' clazz ')]"));
        assertThat(locator.canPureCss(), is(true));
        assertThat(locator.canPureXPath(), is(true));
        assertThat(locator.getElementFilterList().getElementFilters(), contains(ElementFilter.FILTER_NOTHING));
    }

}
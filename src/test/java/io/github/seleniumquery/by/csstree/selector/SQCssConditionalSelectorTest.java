package io.github.seleniumquery.by.csstree.selector;

import io.github.seleniumquery.by.csstree.condition.attribute.SQCssClassAttributeCondition;
import io.github.seleniumquery.by.filter.ElementFilter;
import io.github.seleniumquery.by.locator.SQLocator;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.junit.Assert.assertThat;

public class SQCssConditionalSelectorTest {

    @Test
    public void toSQLocator() {
        // given
        SQCssTagNameSelector tagNameSelector = new SQCssTagNameSelector("tagg");
        SQCssClassAttributeCondition classAttributeCondition = new SQCssClassAttributeCondition("clz");
        SQCssConditionalSelector conditionalSelector = new SQCssConditionalSelector(tagNameSelector, classAttributeCondition);
        // when
        SQLocator xPath = conditionalSelector.toSQLocator();
        // then
        assertThat(xPath.getCssSelector(), is("tagg.clz"));
        assertThat(xPath.getXPathExpression(), is(".//*[self::tagg and contains(concat(' ', normalize-space(@class), ' '), ' clz ')]"));
        assertThat(xPath.canPureCss(), is(true));
        assertThat(xPath.canPureXPath(), is(true));
        assertThat(xPath.getElementFilterList().getElementFilters(), contains(ElementFilter.FILTER_NOTHING));
    }

}
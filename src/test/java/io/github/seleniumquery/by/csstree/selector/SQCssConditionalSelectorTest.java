package io.github.seleniumquery.by.csstree.selector;

import io.github.seleniumquery.by.csstree.condition.attribute.SQCssClassAttributeCondition;
import io.github.seleniumquery.by.csstree.selector.combinator.SQCssDescendantSelector;
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
        // tagg.clz
        SQCssConditionalSelector conditionalSelector = new SQCssConditionalSelector(tagNameSelector, classAttributeCondition);
        // when
        SQLocator locator = conditionalSelector.toSQLocator();
        // then
        assertThat(locator.getCssSelector(), is("tagg.clz"));
        assertThat(locator.getXPathExpression(), is(".//*[self::tagg and contains(concat(' ', normalize-space(@class), ' '), ' clz ')]"));
        assertThat(locator.canPureCss(), is(true));
        assertThat(locator.canPureXPath(), is(true));
        assertThat(locator.getElementFilterList().getElementFilters(), contains(ElementFilter.FILTER_NOTHING));
    }

    @Test
    public void toSQLocator__with_SQLocator_arg() {
        // given
        SQCssTagNameSelector aTagSelector = new SQCssTagNameSelector("a");
        SQCssTagNameSelector bTagSelector = new SQCssTagNameSelector("b");
        SQCssClassAttributeCondition classAttributeCondition = new SQCssClassAttributeCondition("condition");
        SQCssConditionalSelector conditionalSelector = new SQCssConditionalSelector(bTagSelector, classAttributeCondition);
        // a b.condition
        SQCssDescendantSelector descendantSelector = new SQCssDescendantSelector(aTagSelector, conditionalSelector);
        // when
        SQLocator locator = descendantSelector.toSQLocator();
        // then
        assertThat(locator.getCssSelector(), is("a b.condition"));
        assertThat(locator.getXPathExpression(), is(".//*[self::a]//*[self::b and contains(concat(' ', normalize-space(@class), ' '), ' condition ')]"));
        assertThat(locator.canPureCss(), is(true));
        assertThat(locator.canPureXPath(), is(true));
        assertThat(locator.getElementFilterList().getElementFilters(), contains(ElementFilter.FILTER_NOTHING));
    }

}
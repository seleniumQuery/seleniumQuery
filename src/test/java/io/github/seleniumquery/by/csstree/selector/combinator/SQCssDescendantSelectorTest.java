package io.github.seleniumquery.by.csstree.selector.combinator;

import io.github.seleniumquery.by.csstree.selector.SQCssTagNameSelector;
import io.github.seleniumquery.by.filter.ElementFilter;
import io.github.seleniumquery.by.locator.SQLocator;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.junit.Assert.assertThat;

public class SQCssDescendantSelectorTest {

    @Test
    public void toSQLocator() {
        // given
        SQCssTagNameSelector aTagSelector = new SQCssTagNameSelector("a");
        SQCssTagNameSelector bTagSelector = new SQCssTagNameSelector("b");
        SQCssDescendantSelector descendantSelector = new SQCssDescendantSelector(aTagSelector, bTagSelector);
        // when
        SQLocator locator = descendantSelector.toSQLocator();
        // then
        assertThat(locator.getCssSelector(), is("a b"));
        assertThat(locator.getXPathExpression(), is(".//*[self::a]//*[self::b]"));
        assertThat(locator.canPureCss(), is(true));
        assertThat(locator.canPureXPath(), is(true));
        assertThat(locator.getElementFilterList().getElementFilters(), contains(ElementFilter.FILTER_NOTHING));
    }

    @Test
    public void toSQLocator_multiple() {
        // given
        SQCssTagNameSelector firstSelector = new SQCssTagNameSelector("a");
        SQCssTagNameSelector secondSelector = new SQCssTagNameSelector("b");
        SQCssDescendantSelector firstAndSecondSelectors = new SQCssDescendantSelector(firstSelector, secondSelector);
        SQCssTagNameSelector thirdSelector = new SQCssTagNameSelector("c");
        SQCssDescendantSelector descendantSelector = new SQCssDescendantSelector(firstAndSecondSelectors, thirdSelector);
        // when
        SQLocator locator = descendantSelector.toSQLocator();
        // then
        assertThat(locator.getCssSelector(), is("a b c"));
        assertThat(locator.getXPathExpression(), is(".//*[self::a]//*[self::b]//*[self::c]"));
        assertThat(locator.canPureCss(), is(true));
        assertThat(locator.canPureXPath(), is(true));
        assertThat(locator.getElementFilterList().getElementFilters(), contains(ElementFilter.FILTER_NOTHING));
    }

}
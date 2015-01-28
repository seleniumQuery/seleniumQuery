package io.github.seleniumquery.by.csstree.selector.combinator;

import io.github.seleniumquery.by.csstree.selector.SQCssTagNameSelector;
import io.github.seleniumquery.by.filter.ElementFilter;
import io.github.seleniumquery.by.locator.SQLocator;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.junit.Assert.assertThat;

public class SQCssDirectAdjacentSelectorTest {

    @Test
    public void toSQLocator() {
        // given
        SQCssTagNameSelector aTagSelector = new SQCssTagNameSelector("a");
        SQCssTagNameSelector bTagSelector = new SQCssTagNameSelector("b");
        SQCssDirectAdjacentSelector directAdjacentSelector = new SQCssDirectAdjacentSelector(aTagSelector, bTagSelector);
        // when
        SQLocator locator = directAdjacentSelector.toSQLocator();
        // then
        assertThat(locator.getCssSelector(), is("a+b"));
        assertThat(locator.getXPathExpression(), is(".//*[self::a]/following-sibling::*[position() = 1 and self::b]"));
        assertThat(locator.canPureCss(), is(true));
        assertThat(locator.canPureXPath(), is(true));
        assertThat(locator.getElementFilterList().getElementFilters(), contains(ElementFilter.FILTER_NOTHING));
    }

}
package io.github.seleniumquery.by.csstree.selector;

import io.github.seleniumquery.by.filter.ElementFilter;
import io.github.seleniumquery.by.locator.SQLocator;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.junit.Assert.assertThat;

public class SQCssTagNameSelectorTest {

    @Test
    public void toSQLocator() {
        // given
        SQCssTagNameSelector tagNameSelector = new SQCssTagNameSelector("myTag");
        // when
        SQLocator xPath = tagNameSelector.toSQLocator();
        // then
        assertThat(xPath.getCssSelector(), is("myTag"));
        assertThat(xPath.getXPathExpression(), is(".//*[self::myTag]"));
        assertThat(xPath.canPureCss(), is(true));
        assertThat(xPath.canPureXPath(), is(true));
        assertThat(xPath.getElementFilterList().getElementFilters(), contains(ElementFilter.FILTER_NOTHING));
    }

    @Test
    public void toSQLocator__should_return_true_for_XPath_if_tag_is_all() {
        // given
        SQCssTagNameSelector tagNameSelector = new SQCssTagNameSelector("*");
        // when
        SQLocator xPath = tagNameSelector.toSQLocator();
        // then
        assertThat(xPath.getXPathExpression(), is(".//*[true()]"));
    }

}
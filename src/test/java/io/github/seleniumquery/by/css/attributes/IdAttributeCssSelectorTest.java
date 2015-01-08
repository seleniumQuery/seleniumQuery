package io.github.seleniumquery.by.css.attributes;

import io.github.seleniumquery.by.css.CssSelector;
import io.github.seleniumquery.by.css.CssSelectorFactory;
import io.github.seleniumquery.by.preparser.CSSParsedSelectorList;
import io.github.seleniumquery.by.preparser.CSSSelectorParser;
import io.github.seleniumquery.by.xpath.component.TagComponent;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.w3c.css.sac.Selector;
import org.w3c.css.sac.SelectorList;

import static org.junit.Assert.assertThat;

public class IdAttributeCssSelectorTest {

    @Test
    public void testConditionToXPath() {
        CSSParsedSelectorList cssParsedSelectorList = CSSSelectorParser.parseSelector("#idz");
        SelectorList selectorList = cssParsedSelectorList.getSelectorList();
        Selector selector = selectorList.item(0);

        CssSelector<Selector, TagComponent> cssSelector = CssSelectorFactory.parsedSelectorToCssSelector(selector);
        TagComponent tagComponent = cssSelector.toXPath(cssParsedSelectorList.getStringMap(), selector);

        assertThat(tagComponent.toXPath(), Matchers.is("(.//*[@id = 'idz'])"));
        assertThat(tagComponent.toXPathCondition(), Matchers.is("@id = 'idz'"));
    }

}
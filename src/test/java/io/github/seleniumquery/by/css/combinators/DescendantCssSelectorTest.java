package io.github.seleniumquery.by.css.combinators;

import io.github.seleniumquery.by.preparser.ParsedSelectorList;
import io.github.seleniumquery.by.preparser.SelectorParser;
import io.github.seleniumquery.by.xpath.component.TagComponent;
import org.junit.Test;
import org.w3c.css.sac.DescendantSelector;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DescendantCssSelectorTest {

    DescendantCssSelector descendantCssSelector = new DescendantCssSelector();

    @Test
    public void testToXPath() throws Exception {
        // given
        ParsedSelectorList parsedSelectorList = SelectorParser.parseSelector("a b");
        DescendantSelector descendantSelector = (DescendantSelector) parsedSelectorList.getSelectorList().item(0);
        // when
        TagComponent xPathComponent = descendantCssSelector.toXPath(parsedSelectorList.getStringMap(), descendantSelector);
        // then
        String xPath = xPathComponent.toXPath();
        assertThat(xPath, is("(.//*[self::a]//*[self::b])"));
    }

}
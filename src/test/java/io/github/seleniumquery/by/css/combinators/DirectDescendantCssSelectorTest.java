package io.github.seleniumquery.by.css.combinators;

import io.github.seleniumquery.by.preparser.ParsedSelector;
import io.github.seleniumquery.by.preparser.SelectorParser;
import io.github.seleniumquery.by.xpath.XPathComponent;
import org.junit.Test;
import org.w3c.css.sac.DescendantSelector;
import org.w3c.css.sac.SelectorList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DirectDescendantCssSelectorTest {

    DirectDescendantCssSelector directDescendantCssSelector = new DirectDescendantCssSelector();

    @Test
    public void testToXPath() throws Exception {
        // given
        ParsedSelector<SelectorList> parsedSelector = SelectorParser.parseSelector("a > b");
        DescendantSelector descendantSelector = (DescendantSelector) parsedSelector.getSelector().item(0);
        // when
        XPathComponent xPathComponent = directDescendantCssSelector.toXPath(parsedSelector.getStringMap(), descendantSelector);
        // then
        String xPath = xPathComponent.toXPath();
        assertThat(xPath, is("(.//*[self::a]/*[self::b])"));
    }

}
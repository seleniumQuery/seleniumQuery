package io.github.seleniumquery.by.css.combinators;

import io.github.seleniumquery.by.preparser.CSSParsedSelectorList;
import io.github.seleniumquery.by.preparser.CSSSelectorParser;
import io.github.seleniumquery.by.xpath.component.TagComponent;
import org.junit.Test;
import org.w3c.css.sac.SiblingSelector;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DirectAdjacentCssSelectorTest {

    DirectAdjacentCssSelector directAdjacentCssSelector = new DirectAdjacentCssSelector();

    @Test
    public void testToXPath() throws Exception {
        // given
        CSSParsedSelectorList CSSParsedSelectorList = CSSSelectorParser.parseSelector("a + b");
        SiblingSelector siblingSelector = (SiblingSelector) CSSParsedSelectorList.getSelectorList().item(0);
        // when
        TagComponent xPathComponent = directAdjacentCssSelector.toXPath(CSSParsedSelectorList.getStringMap(), siblingSelector);
        // then
        String xPath = xPathComponent.toXPath();
        assertThat(xPath, is("(.//*[self::a]/following-sibling::b[position() = 1])"));
    }

}
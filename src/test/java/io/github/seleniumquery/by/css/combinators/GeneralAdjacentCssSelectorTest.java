package io.github.seleniumquery.by.css.combinators;

import io.github.seleniumquery.by.preparser.ParsedSelector;
import io.github.seleniumquery.by.preparser.SelectorParser;
import io.github.seleniumquery.by.xpath.component.XPathComponent;
import org.junit.Test;
import org.w3c.css.sac.SelectorList;
import org.w3c.css.sac.SiblingSelector;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class GeneralAdjacentCssSelectorTest {

    GeneralAdjacentCssSelector generalAdjacentCssSelector = new GeneralAdjacentCssSelector();

    @Test
    public void testToXPath() throws Exception {
        // given
        ParsedSelector<SelectorList> parsedSelector = SelectorParser.parseSelector("a ~ b");
        SiblingSelector siblingSelector = (SiblingSelector) parsedSelector.getSelector().item(0);
        // when
        XPathComponent xPathComponent = generalAdjacentCssSelector.toXPath(parsedSelector.getStringMap(), siblingSelector);
        // then
        String xPath = xPathComponent.toXPath();
        assertThat(xPath, is("(.//*[self::a]/following-sibling::b)"));
    }

}
package io.github.seleniumquery.by.css.conditionals;

import io.github.seleniumquery.by.preparser.CSSParsedSelectorList;
import io.github.seleniumquery.by.preparser.SelectorParser;
import io.github.seleniumquery.by.xpath.XPathSelectorCompilerService;
import io.github.seleniumquery.by.xpath.component.ComponentUtils;
import io.github.seleniumquery.by.xpath.component.TagComponent;
import io.github.seleniumquery.by.xpath.component.XPathComponent;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.w3c.css.sac.CombinatorCondition;
import org.w3c.css.sac.ConditionalSelector;
import org.w3c.css.sac.SelectorList;
import org.w3c.css.sac.SimpleSelector;

import java.util.Map;

import static org.junit.Assert.assertThat;

public class AndConditionalCssSelectorTest {

    AndConditionalCssSelector andConditionalCssSelector = new AndConditionalCssSelector(new ConditionalCssSelector());

    @Test
    public void testConditionToXPath() {
        CSSParsedSelectorList CSSParsedSelectorList = SelectorParser.parseSelector("span.a.b");
        SelectorList selectorList = CSSParsedSelectorList.getSelectorList();
        ConditionalSelector selector = (ConditionalSelector) selectorList.item(0);

//        XPathComponent cs = conditionalCssSelector.toXPath(parsedSelector.getStringMap(), selector);
        Map<String,String> stringMap = CSSParsedSelectorList.getStringMap();
        SimpleSelector simpleSelector = selector.getSimpleSelector();
        TagComponent spanTagComponent = XPathSelectorCompilerService.compileSelector(stringMap, simpleSelector);

//        XPathComponent compiledCondition = conditionalCssSelector.conditionToXPath(stringMap, selector.getSimpleSelector(), selector.getCondition());
        CombinatorCondition combinatorCondition = (CombinatorCondition) selector.getCondition();
        XPathComponent compiledCondition = andConditionalCssSelector.conditionToXPath(stringMap, simpleSelector, combinatorCondition);

        TagComponent cs = ComponentUtils.combineKeepingTypeOfFirstArg(spanTagComponent, compiledCondition);
        assertThat(cs.toXPath(), Matchers.is("(.//*[self::span and contains(concat(' ', normalize-space(@class), ' '), ' a ') and contains(concat(' ', normalize-space(@class), ' '), ' b ')])"));
        assertThat(cs.toXPathCondition(), Matchers.is("local-name() = 'span' and contains(concat(' ', normalize-space(@class), ' '), ' a ') and contains(concat(' ', normalize-space(@class), ' '), ' b ')"));
    }

}
package io.github.seleniumquery.by.css.conditionals;

import io.github.seleniumquery.by.preparser.ParsedSelector;
import io.github.seleniumquery.by.preparser.SelectorParser;
import io.github.seleniumquery.by.xpath.XPathSelectorCompilerService;
import io.github.seleniumquery.by.xpath.component.KeepTypeComponent;
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
        ParsedSelector<SelectorList> parsedSelector = SelectorParser.parseSelector("span.a.b");
        SelectorList selectorList = parsedSelector.getSelector();
        ConditionalSelector selector = (ConditionalSelector) selectorList.item(0);

//        XPathComponent cs = conditionalCssSelector.toXPath(parsedSelector.getStringMap(), selector);
        Map<String,String> stringMap = parsedSelector.getStringMap();
        SimpleSelector simpleSelector = selector.getSimpleSelector();
        XPathComponent spanTagComponent = XPathSelectorCompilerService.compileSelector(stringMap, simpleSelector);

//        XPathComponent compiledCondition = conditionalCssSelector.conditionToXPath(stringMap, selector.getSimpleSelector(), selector.getCondition());
        CombinatorCondition combinatorCondition = (CombinatorCondition) selector.getCondition();
        XPathComponent compiledCondition = andConditionalCssSelector.conditionToXPath(stringMap, simpleSelector, combinatorCondition);

        XPathComponent cs = KeepTypeComponent.combineKeepingType(spanTagComponent, compiledCondition);
        assertThat(cs.toXPath(), Matchers.is("(.//*[self::span and contains(concat(' ', normalize-space(@class), ' '), ' a ') and contains(concat(' ', normalize-space(@class), ' '), ' b ')])"));
        assertThat(cs.toXPathCondition(), Matchers.is("local-name() = 'span' and contains(concat(' ', normalize-space(@class), ' '), ' a ') and contains(concat(' ', normalize-space(@class), ' '), ' b ')"));
    }

}
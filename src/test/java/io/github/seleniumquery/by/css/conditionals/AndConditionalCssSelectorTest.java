package io.github.seleniumquery.by.css.conditionals;

import io.github.seleniumquery.by.preparser.CSSParsedSelectorList;
import io.github.seleniumquery.by.preparser.CSSSelectorParser;
import io.github.seleniumquery.by.xpath.XPathComponentCompilerService;
import io.github.seleniumquery.by.xpath.component.ConditionComponent;
import io.github.seleniumquery.by.xpath.component.TagComponent;
import org.junit.Test;
import org.w3c.css.sac.CombinatorCondition;
import org.w3c.css.sac.ConditionalSelector;
import org.w3c.css.sac.SelectorList;
import org.w3c.css.sac.SimpleSelector;

import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class AndConditionalCssSelectorTest {

    AndConditionalCssSelector andConditionalCssSelector = new AndConditionalCssSelector(new ConditionalCssSelector());

    @Test
    public void testConditionToXPath() {
        CSSParsedSelectorList CSSParsedSelectorList = CSSSelectorParser.parseSelector("span.a.b");
        SelectorList selectorList = CSSParsedSelectorList.getSelectorList();
        ConditionalSelector selector = (ConditionalSelector) selectorList.item(0);

//        XPathComponent cs = conditionalCssSelector.toXPath(parsedSelector.getStringMap(), selector);
        Map<String,String> stringMap = CSSParsedSelectorList.getStringMap();
        SimpleSelector simpleSelector = selector.getSimpleSelector();
        TagComponent spanTagComponent = XPathComponentCompilerService.compileSelector(stringMap, simpleSelector);

//        XPathComponent compiledCondition = conditionalCssSelector.conditionToXPath(stringMap, selector.getSimpleSelector(), selector.getCondition());
        CombinatorCondition combinatorCondition = (CombinatorCondition) selector.getCondition();
        ConditionComponent compiledCondition = andConditionalCssSelector.conditionToXPath(stringMap, simpleSelector, combinatorCondition);

        TagComponent cs = spanTagComponent.cloneAndCombineTo(compiledCondition);
        assertThat(cs.toXPath(), is("(.//*[self::span and contains(concat(' ', normalize-space(@class), ' '), ' a ') and contains(concat(' ', normalize-space(@class), ' '), ' b ')])"));
        assertThat(cs.toXPathCondition(), is("local-name() = 'span' and contains(concat(' ', normalize-space(@class), ' '), ' a ') and contains(concat(' ', normalize-space(@class), ' '), ' b ')"));
    }

}
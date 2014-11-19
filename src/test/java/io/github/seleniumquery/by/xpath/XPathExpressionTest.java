package io.github.seleniumquery.by.xpath;

import io.github.seleniumquery.by.preparser.ParsedSelector;
import io.github.seleniumquery.by.preparser.SelectorParser;
import org.junit.Test;
import org.w3c.css.sac.SelectorList;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class XPathExpressionTest {

    @Test
    public void toXPath__id() {
        XPathExpressionList compileSelectorList = XPathSelectorCompilerService.compileSelectorList("#ball");
        assertThat(compileSelectorList.toXPath(), is("(.//*[@id = 'ball'])"));
    }

    @Test(expected = UnsupportedConditionalSelector.class)
    public void toXPath__not_body_div_span() {
        XPathExpression xPathExpr = selectorToExpression("span:not(body div span)");
        String xPath = xPathExpr.toXPath();
        assertThat(xPath, is("(.//span[not(local-name() = 'span' and ancestor::div[ancestor::body])])"));
    }

    @Test
    public void toXPath__id_text() {
        XPathExpression xPathExpr = selectorToExpression("#myID :text");
        String xPath = xPathExpr.toXPath();
        assertThat(xPath, is("(.//*[@id = 'myID']//*[self::input and (translate(@type,'TEXT','text') = 'text' or not(@type))])"));
    }

    @Test
    public void toXPath__only_child() {
        XPathExpression xPathExpr = selectorToExpression("#idz a:only-child");
        String xPath = xPathExpr.toXPath();
        assertThat(xPath, is("(.//*[@id = 'idz']//*[self::a and ../*[last() = 1]])"));
    }

    public static XPathExpression selectorToExpression(String selector) {
        ParsedSelector<SelectorList> parsedSelector = SelectorParser.parseSelector(selector);
        SelectorList selectorList = parsedSelector.getSelector();
        return XPathSelectorCompilerService.compileSelector(parsedSelector.getStringMap(), selectorList.item(0));
    }

    @Test
    public void toXPathCondition__first() {
        XPathExpression xPathExpr = selectorToExpression(":first");
        String xPathCondition = xPathExpr.toXPathCondition();
        assertThat(xPathCondition, is("position() = 1"));
    }

    @Test
    public void toXPathCondition__div_first() {
        XPathExpression xPathExpr = selectorToExpression("div:first");
        String xPathCondition = xPathExpr.toXPathCondition();
        assertThat(xPathCondition, is("local-name() = 'div' and position() = 1"));
    }

    @Test
    public void toXPathCondition__class() {
        XPathExpression xPathExpr = selectorToExpression(".cls");
        String xPathCondition = xPathExpr.toXPathCondition();
        assertThat(xPathCondition, is("contains(concat(' ', normalize-space(@class), ' '), ' cls ')"));
    }

    @Test
    public void toXPathCondition__escaped_class() {
        XPathExpression xPathExpr = selectorToExpression(".foo\\:bar");
        String xPathCondition = xPathExpr.toXPathCondition();
        assertThat(xPathCondition, is("contains(concat(' ', normalize-space(@class), ' '), ' foo:bar ')"));
    }

}
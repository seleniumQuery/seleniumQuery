package io.github.seleniumquery.selector_old_should_move.xpath;

import io.github.seleniumquery.by.selector.UnsupportedConditionalSelector;
import io.github.seleniumquery.by.preparser.ParsedSelector;
import io.github.seleniumquery.by.preparser.SelectorParser;
import io.github.seleniumquery.by.selector.xpath.XPathExpression;
import io.github.seleniumquery.by.selector.xpath.XPathExpressionList;
import io.github.seleniumquery.by.selector.xpath.XPathSelectorCompilerService;
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
    @Test
    public void toXPath__id_with_odd_chars() {
        XPathExpressionList compileSelectorList = XPathSelectorCompilerService.compileSelectorList("#a-b\\.c\\:d");
        assertThat(compileSelectorList.toXPath(), is("(.//*[@id = 'a-b.c:d'])"));
    }

    @Test
    public void toXPath__two_ids() {
        XPathExpressionList compileSelectorList = XPathSelectorCompilerService.compileSelectorList("#ball,#bell");
        assertThat(compileSelectorList.toXPath(), is("(.//*[@id = 'ball']) | (.//*[@id = 'bell'])"));
    }

    @Test
    public void toXPath__by_class() {
        XPathExpressionList compileSelectorList = XPathSelectorCompilerService.compileSelectorList(".bozo");
        assertThat(compileSelectorList.toXPath(), is("(.//*[contains(concat(' ', normalize-space(@class), ' '), ' bozo ')])"));
    }

    @Test
    public void toXPath__by_id_and_class() {
        XPathExpressionList compileSelectorList = XPathSelectorCompilerService.compileSelectorList("#ball.bozo");
        assertThat(compileSelectorList.toXPath(), is("(.//*[@id = 'ball' and contains(concat(' ', normalize-space(@class), ' '), ' bozo ')])"));
    }

    @Test
    public void toXPath__by_id_and_DESCENDANT_class() {
        XPathExpressionList compileSelectorList = XPathSelectorCompilerService.compileSelectorList("#ball .bozo");
        assertThat(compileSelectorList.toXPath(), is("(.//*[@id = 'ball']//*[contains(concat(' ', normalize-space(@class), ' '), ' bozo ')])"));
    }

    @Test
    public void toXPath__descendant() {
        XPathExpressionList compileSelectorList = XPathSelectorCompilerService.compileSelectorList("span div");
        assertThat(compileSelectorList.toXPath(), is("(.//span//div)"));
    }

    @Test
    public void toXPath__direct_descendant() {
        XPathExpressionList compileSelectorList = XPathSelectorCompilerService.compileSelectorList("span>div");
        assertThat(compileSelectorList.toXPath(), is("(.//span/div)"));
    }

    @Test
    public void toXPath__general_adjacent() {
        XPathExpressionList compileSelectorList = XPathSelectorCompilerService.compileSelectorList("span~div");
        assertThat(compileSelectorList.toXPath(), is("(.//span/following-sibling::div)"));
    }

    @Test
    public void toXPath__direct_adjacent() {
        XPathExpressionList compileSelectorList = XPathSelectorCompilerService.compileSelectorList("span+div");
        assertThat(compileSelectorList.toXPath(), is("(.//span/following-sibling::div[position() = 1])"));
    }

    @Test
    public void toXPath__direct_adjacent_with_class() {
        XPathExpressionList compileSelectorList = XPathSelectorCompilerService.compileSelectorList("span+div.ball");
        assertThat(compileSelectorList.toXPath(), is("(.//span/following-sibling::div[contains(concat(' ', normalize-space(@class), ' '), ' ball ') and position() = 1])"));
    }

    @Test
    public void toXPath__attribute_contains_prefix() {
        XPathExpressionList compileSelectorList = XPathSelectorCompilerService.compileSelectorList("[attr|=prefix]");
        assertThat(compileSelectorList.toXPath(), is("(.//*[(@attr = 'prefix' or starts-with(@attr, 'prefix-'))])"));
    }

    @Test
    public void toXPath__attribute_contains_substring() {
        XPathExpressionList compileSelectorList = XPathSelectorCompilerService.compileSelectorList("[attr*=substring]");
        assertThat(compileSelectorList.toXPath(), is("(.//*[contains(@attr, 'substring')])"));
    }

    @Test
    public void toXPath__attribute_contains_word() {
        XPathExpressionList compileSelectorList = XPathSelectorCompilerService.compileSelectorList("[attr~=word]");
        assertThat(compileSelectorList.toXPath(), is("(.//*[contains(concat(' ', normalize-space(@attr), ' '), ' word ')])"));
    }

    @Test
    public void toXPath__attribute_ends_with_string() {
        XPathExpressionList compileSelectorList = XPathSelectorCompilerService.compileSelectorList("[attr$=string]");
        assertThat(compileSelectorList.toXPath(), is("(.//*[substring(@attr, string-length(@attr)-5) = 'string'])"));
    }

    @Test
    public void toXPath__attribute_starts_with_string() {
        XPathExpressionList compileSelectorList = XPathSelectorCompilerService.compileSelectorList("[attr^=string]");
        assertThat(compileSelectorList.toXPath(), is("(.//*[starts-with(@attr, 'string')])"));
    }

    @Test
    public void toXPath__attribute_equals_string() {
        XPathExpressionList compileSelectorList = XPathSelectorCompilerService.compileSelectorList("[attr=string]");
        assertThat(compileSelectorList.toXPath(), is("(.//*[@attr = 'string'])"));
    }

    @Test
    public void toXPath__attribute_exists() {
        XPathExpressionList compileSelectorList = XPathSelectorCompilerService.compileSelectorList("[attr]");
        assertThat(compileSelectorList.toXPath(), is("(.//*[@attr])"));
    }

    @Test
    public void toXPath__descendant_eq() {
        XPathExpressionList compileSelectorList = XPathSelectorCompilerService.compileSelectorList("div a:eq(0)");
        assertThat(compileSelectorList.toXPath(), is("((.//div//a)[position() = 1])"));
    }

    @Test
    public void toXPath__tag_lt() {
        XPathExpressionList compileSelectorList = XPathSelectorCompilerService.compileSelectorList("div:lt(1)");
        assertThat(compileSelectorList.toXPath(), is("((.//div)[position() < 2])"));
    }

    @Test
    public void toXPath__tag_lt_negative() {
        XPathExpressionList compileSelectorList = XPathSelectorCompilerService.compileSelectorList("div:lt(-1)");
        assertThat(compileSelectorList.toXPath(), is("((.//div)[position() < (last()-0)])"));
    }

    @Test
    public void toXPath__not_all() {
        XPathExpressionList compileSelectorList = XPathSelectorCompilerService.compileSelectorList("*:not(*)");
        assertThat(compileSelectorList.toXPath(), is("(.//*[not((local-name()))])"));
    }

    @Test
    public void toXPath__not_with_selector_list() {
        XPathExpressionList compileSelectorList = XPathSelectorCompilerService.compileSelectorList("div:not(.c3,.c1)");
        assertThat(compileSelectorList.toXPath(), is(
                "("
                        + ".//div["
                        + "not("
                        + "(local-name() and contains(concat(' ', normalize-space(@class), ' '), ' c3 ')) or (local-name() and contains(concat(' ', normalize-space(@class), ' '), ' c1 '))"
                        + ")"
                        + "]"
                        + ")"));
    }

    @Test
    public void toXPath__has_tag() {
        XPathExpressionList compileSelectorList = XPathSelectorCompilerService.compileSelectorList("div:has(p)");
        assertThat(compileSelectorList.toXPath(), is("(.//div[.//p])"));
    }

    @Test
    public void toXPath__first() {
        XPathExpression xPathExpression = selectorToExpression("p:first");
        assertThat(xPathExpression.toXPath(), is("((.//p)[position() = 1])"));
    }

    @Test
    public void toXPath__not() {
        XPathExpressionList compileSelectorList = XPathSelectorCompilerService.compileSelectorList("#foo p:not(:first) .link");
        assertThat(compileSelectorList.toXPath(),
                is("(" +
                                ".//*[@id = 'foo']" +
                                "//p[not((position() = 1))]" +
                                "//*[contains(concat(' ', normalize-space(@class), ' '), ' link ')]" +
                                ")"
                )
        );
    }

    @Test
    public void toXPath__not_first() {
        XPathExpression xPathExpr = selectorToExpression(":not(:first)");
        String xPath = xPathExpr.toXPath();
        assertThat(xPath, is("(.//*[not((position() = 1))])"));
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
        assertThat(xPath, is("(.//*[@id = 'myID']//*[local-name() = 'input' and (@type = 'text' or not(@type))])"));
    }

    @Test
    public void toXPath__only_child() {
        XPathExpression xPathExpr = selectorToExpression("#idz a:only-child");
        String xPath = xPathExpr.toXPath();
        assertThat(xPath, is("(.//*[@id = 'idz']//a[../*[last() = 1]])"));
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
        assertThat(xPathCondition, is("local-name() and contains(concat(' ', normalize-space(@class), ' '), ' cls ')"));
    }

    @Test
    public void toXPathCondition__escaped_class() {
        XPathExpression xPathExpr = selectorToExpression(".foo\\:bar");
        String xPathCondition = xPathExpr.toXPathCondition();
        assertThat(xPathCondition, is("local-name() and contains(concat(' ', normalize-space(@class), ' '), ' foo:bar ')"));
    }

}
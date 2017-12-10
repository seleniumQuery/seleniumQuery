package io.github.seleniumquery.utils;

import static io.github.seleniumquery.SeleniumQuery.$;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import testinfrastructure.junitrule.SetUpAndTearDownDriver;

public class SelectorUtilsTest {

    @ClassRule @Rule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();

    @Test
    public void parent() {
        WebElement html = $("html").get(0);
        WebElement body = $("body").get(0);
        WebElement a = $("#a").get(0);
        WebElement b = $("#b").get(0);
        WebElement c = $("#c").get(0);

        assertEquals(b, SelectorUtils.parent(c));
        assertEquals(a, SelectorUtils.parent(b));
        assertEquals(body, SelectorUtils.parent(a));
        assertEquals(html, SelectorUtils.parent(body));
        assertEquals(null, SelectorUtils.parent(html));
    }

    @Test
    @SuppressWarnings("ArraysAsListWithZeroOrOneArgument")
    public void parents() {
        WebElement html = $("html").get(0);
        WebElement body = $("body").get(0);
        WebElement a = $("#a").get(0);
        WebElement b = $("#b").get(0);
        WebElement c = $("#c").get(0);

        assertEquals(asList("html", "body", "a", "b"), asStringList(SelectorUtils.parents(c)));
        assertEquals(asList("html", "body", "a"), asStringList(SelectorUtils.parents(b)));
        assertEquals(asList("html", "body"), asStringList(SelectorUtils.parents(a)));
        assertEquals(asList("html"), asStringList(SelectorUtils.parents(body)));
        assertEquals(asList(), asStringList(SelectorUtils.parents(html)));
    }

    private List<String> asStringList(List<WebElement> webElements) {
        return webElements.stream().map(we -> we.getAttribute("id")).collect(Collectors.toList());
    }

}

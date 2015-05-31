package endtoend.sizzle;

import infrastructure.junitrule.SetUpAndTearDownDriver;
import io.github.seleniumquery.by.SelectorUtils;
import org.junit.Rule;
import org.junit.Test;

import static infrastructure.IntegrationTestUtils.equal;

public class SizzleID extends SizzleTest {

    @Rule
    public SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver(SizzleTest.class);

    @Test
    public void id_selectors() throws Exception {
        t("ID Selector", "#body", new String[]{"body"});
        t("ID Selector w/ Element", "body#body", new String[]{"body"});
        t("ID Selector w/ Element", "ul#first", new String[]{});
        t("ID selector with existing ID descendant", "#firstp #simon1", new String[]{"simon1"});
        t("ID selector with non-existant descendant", "#firstp #foobar", new String[]{});
//        t("ID selector using UTF8", "#台北Táiběi", new String[]{"台北Táiběi"});
//        t("Multiple ID selectors using UTF8", "#台北Táiběi, #台北", new String[]{"台北Táiběi", "台北"});
//        t("Descendant ID selector using UTF8", "div #台北", new String[]{"台北"});
//        t("Child ID selector using UTF8", "form > #台北", new String[]{"台北"});

        t("Escaped ID", "#foo\\:bar", new String[]{"foo:bar"});
        t("Escaped ID with descendent", "#foo\\:bar span:not(:input)", new String[]{"foo_descendent"});
        t("Escaped ID", "#test\\.foo\\[5\\]bar", new String[]{"test.foo[5]bar"});
        t("Descendant escaped ID", "div #foo\\:bar", new String[]{"foo:bar"});
        t("Descendant escaped ID", "div #test\\.foo\\[5\\]bar", new String[]{"test.foo[5]bar"});
        t("Child escaped ID", "form > #foo\\:bar", new String[]{"foo:bar"});
        t("Child escaped ID", "form > #test\\.foo\\[5\\]bar", new String[]{"test.foo[5]bar"});

/*
        Object fiddle = jQuery("<div id='fiddle\\Foo'><span id='fiddleSpan'></span></div>").appendTo("#qunit-fixture");
        deepEqual(Sizzle("> span", Sizzle("#fiddle\\\\Foo").get(0)), q(new String[]{"fiddleSpan"}), "Escaped ID as context");
        fiddle.remove();
*/

        t("ID Selector, child ID present", "#form > #radio1", new String[]{"radio1"}); // bug #267
        t("ID Selector, not an ancestor ID", "#form #first", new String[]{});
        t("ID Selector, not a child ID", "#form > #option1a", new String[]{});

        t("All Children of ID", "#foo > *", new String[]{"sndp", "en", "sap"});
        t("All Children of ID with no children", "#firstUL > *", new String[]{});

        equal(Sizzle("#tName1").get(0).getAttribute("id"), "tName1", "ID selector with same value for a name attribute");
        t("ID selector non-existing but name attribute on an A tag", "#tName2", new String[]{});
        t("Leading ID selector non-existing but name attribute on an A tag", "#tName2 span", new String[]{});
        t("Leading ID selector existing, retrieving the child", "#tName1 span", new String[]{"tName1-span"});
        equal(Sizzle("div > div #tName1").get(0).getAttribute("id"), SelectorUtils.parent(Sizzle("#tName1-span").get(0)).getAttribute("id"), "Ending with ID");

/*
        jQuery("<a id='backslash\\foo'></a>").appendTo("#qunit-fixture");
        t("ID Selector contains backslash", "#backslash\\\\foo", new String[]{"backslash\\foo"});
*/

        t("ID Selector on Form with an input that has a name of 'id'", "#lengthtest", new String[]{"lengthtest"});

        t("ID selector with non-existant ancestor", "#asdfasdf #foobar", new String[]{}); // bug #986

//        deepEqual(Sizzle("div#form", document.body), new String[]{}, "ID selector within the context of another element");

        t("Underscore ID", "#types_all", new String[]{"types_all"});
        t("Dash ID", "#qunit-fixture", new String[]{"qunit-fixture"});

        t("ID with weird characters in it", "#name\\+value", new String[]{"name+value"});
    }

}
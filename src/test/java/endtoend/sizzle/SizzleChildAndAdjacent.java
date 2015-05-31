package endtoend.sizzle;

import infrastructure.junitrule.SetUpAndTearDownDriver;
import org.junit.Rule;
import org.junit.Test;

import static infrastructure.IntegrationTestUtils.equal;

public class SizzleChildAndAdjacent extends SizzleTest {

    @Rule
    public SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver(SizzleTest.class);

    @Test
    public void child_and_adjacent_selectors() throws Exception {
        t("Child", "p > a", new String[]{"simon1", "google", "groups", "mark", "yahoo", "simon"});
        t("Child", "p> a", new String[]{"simon1", "google", "groups", "mark", "yahoo", "simon"});
        t("Child", "p >a", new String[]{"simon1", "google", "groups", "mark", "yahoo", "simon"});
        t("Child", "p>a", new String[]{"simon1", "google", "groups", "mark", "yahoo", "simon"});
        t("Child w/ Class", "p > a.blog", new String[]{"mark", "simon"});
        t("All Children", "code > *", new String[]{"anchor1", "anchor2"});
        t("All Grandchildren", "p > * > *", new String[]{"anchor1", "anchor2"});
// TODO(issue#55)
//        t("Adjacent", "#qunit-fixture a + a", new String[]{"groups", "tName2ID"});
//        t("Adjacent", "#qunit-fixture a +a", new String[]{"groups", "tName2ID"});
//        t("Adjacent", "#qunit-fixture a+ a", new String[]{"groups", "tName2ID"});
//        t("Adjacent", "#qunit-fixture a+a", new String[]{"groups", "tName2ID"});
//        t("Adjacent", "p + p", new String[]{"ap", "en", "sap"});
        t("Adjacent", "p#firstp + p", new String[]{"ap"});
        t("Adjacent", "p[lang=en] + p", new String[]{"sap"});
        t("Adjacent", "a.GROUPS + code + a", new String[]{"mark"});
//        t("Comma, Child, and Adjacent", "#qunit-fixture a + a, code > a", new String[]{"groups", "anchor1", "anchor2", "tName2ID"});
        t("Element Preceded By", "#qunit-fixture p ~ div", new String[]{"foo", "nothiddendiv", "moretests", "tabindex-tests", "liveHandlerOrder", "siblingTest"});
        t("Element Preceded By", "#first ~ div", new String[]{"moretests", "tabindex-tests", "liveHandlerOrder", "siblingTest"});
        t("Element Preceded By", "#groups ~ a", new String[]{"mark"});
        t("Element Preceded By", "#length ~ input", new String[]{"idTest"});
        t("Element Preceded By", "#siblingfirst ~ em", new String[]{"siblingnext", "siblingthird"});
        t("Element Preceded By (multiple)", "#siblingTest em ~ em ~ em ~ span", new String[]{"siblingspan"});
        t("Element Preceded By, Containing", "#liveHandlerOrder ~ div em:contains('1')", new String[]{"siblingfirst"});
/*
        WebElement siblingFirst = id("siblingfirst");
        deepEqual(Sizzle("~ em", siblingFirst), q("siblingnext", "siblingthird"), "Element Preceded By with a context.");
        deepEqual(Sizzle("+ em", siblingFirst), q("siblingnext"), "Element Directly Preceded By with a context.");
        deepEqual(Sizzle("~ em:first", siblingFirst), q("siblingnext"), "Element Preceded By positional with a context.");
*/
        t("Element Preceded By with a context.", "#siblingfirst ~ em", new String[]{"siblingnext", "siblingthird"});
        t("Element Directly Preceded By with a context.", "#siblingfirst + em", new String[]{"siblingnext"});
        t("Element Preceded By positional with a context.", "#siblingfirst ~ em:first", new String[]{"siblingnext"});
/*
        WebElement en = id("en");
        deepEqual(Sizzle("+ p, a", en), q("yahoo", "sap"), "Compound selector with context, beginning with sibling test.");
        deepEqual(Sizzle("a, + p", en), q("yahoo", "sap"), "Compound selector with context, containing sibling test.");
*/
//        t("Compound selector with context, beginning with sibling test.", "#en + p, a", new String[]{"yahoo", "sap"});
        t("Compound selector with context, containing sibling test.", "#en a,#en + p", new String[]{"yahoo", "sap"});

        t("Multiple combinators selects all levels", "#siblingTest em *", new String[]{"siblingchild", "siblinggrandchild", "siblinggreatgrandchild"});
        t("Multiple combinators selects all levels", "#siblingTest > em *", new String[]{"siblingchild", "siblinggrandchild", "siblinggreatgrandchild"});
        t("Multiple sibling combinators doesn't miss general siblings", "#siblingTest > em:first-child + em ~ span", new String[]{"siblingspan"});
        t("Combinators are not skipped when mixing general and specific", "#siblingTest > em:contains('x') + em ~ span", new String[]{});

        equal(Sizzle("#listWithTabIndex").size(), 1, "Parent div for next test is found via ID (#8310)");
        equal(Sizzle("#listWithTabIndex li:eq(2) ~ li").size(), 1, "Find by general sibling combinator (#8310)");
        equal(Sizzle("#__sizzle__").size(), 0, "Make sure the temporary id assigned by sizzle is cleared out (#8310)");
        equal(Sizzle("#listWithTabIndex").size(), 1, "Parent div for previous test is still found via ID (#8310)");

        t("Verify deep class selector", "div.blah > p > a", new String[]{});

        t("No element deep selector", "div.foo > span > a", new String[]{});
/*
        WebElement nothiddendiv = id("nothiddendiv");
        deepEqual(Sizzle("> :first", nothiddendiv), q("nothiddendivchild"), "Verify child context positional selector");
        deepEqual(Sizzle("> :eq(0)", nothiddendiv), q("nothiddendivchild"), "Verify child context positional selector");
        deepEqual(Sizzle("> *:first", nothiddendiv), q("nothiddendivchild"), "Verify child context positional selector");
*/
        t("Verify child context positional selector", "#nothiddendiv > :first", new String[]{"nothiddendivchild"});
        t("Verify child context positional selector", "#nothiddendiv > :eq(0)", new String[]{"nothiddendivchild"});
        t("Verify child context positional selector", "#nothiddendiv > *:first", new String[]{"nothiddendivchild"});

        t("Non-existant ancestors", ".fototab > .thumbnails > a", new String[]{});
    }

}
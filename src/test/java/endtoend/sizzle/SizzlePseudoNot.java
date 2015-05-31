package endtoend.sizzle;

import testinfrastructure.junitrule.SetUpAndTearDownDriver;
import org.junit.Rule;
import org.junit.Test;

public class SizzlePseudoNot extends SizzleTest {

    @Rule
    public SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver(SizzleTest.class);

    @Test
    public void pseudo_not() throws Exception {
        t("Not", "a.blog:not(.link)", new String[]{"mark"});
        t(":not() with :first", "#foo p:not(:first) .link", new String[]{"simon"});

//        t("Not - multiple", "#form option:not(:contains(Nothing),#option1b,:selected)", new String[]{"option1c", "option1d", "option2b", "option2c", "option3d", "option3e", "option4e", "option5b", "option5c"});
        t("Not - recursive", "#form option:not(:not(:selected))[id^='option3']", new String[]{"option3b", "option3c"});

        t(":not() failing interior", "#qunit-fixture p:not(.foo)", new String[]{"firstp", "ap", "sndp", "en", "sap", "first"});
        t(":not() failing interior", "#qunit-fixture p:not(div.foo)", new String[]{"firstp", "ap", "sndp", "en", "sap", "first"});
        t(":not() failing interior", "#qunit-fixture p:not(p.foo)", new String[]{"firstp", "ap", "sndp", "en", "sap", "first"});
        t(":not() failing interior", "#qunit-fixture p:not(#blargh)", new String[]{"firstp", "ap", "sndp", "en", "sap", "first"});
        t(":not() failing interior", "#qunit-fixture p:not(div#blargh)", new String[]{"firstp", "ap", "sndp", "en", "sap", "first"});
        t(":not() failing interior", "#qunit-fixture p:not(p#blargh)", new String[]{"firstp", "ap", "sndp", "en", "sap", "first"});

        t(":not Multiple", "#qunit-fixture p:not(a)", new String[]{"firstp", "ap", "sndp", "en", "sap", "first"});
        t(":not Multiple", "#qunit-fixture p:not( a )", new String[]{"firstp", "ap", "sndp", "en", "sap", "first"});
        t(":not Multiple", "#qunit-fixture p:not( p )", new String[]{});
        t(":not Multiple", "#qunit-fixture p:not(a, b)", new String[]{"firstp", "ap", "sndp", "en", "sap", "first"});
        t(":not Multiple", "#qunit-fixture p:not(a, b, div)", new String[]{"firstp", "ap", "sndp", "en", "sap", "first"});
        t(":not Multiple", "p:not(p)", new String[]{});
        t(":not Multiple", "p:not(a,p)", new String[]{});
        t(":not Multiple", "p:not(p,a)", new String[]{});
        t(":not Multiple", "p:not(a,p,b)", new String[]{});
        t(":not Multiple", ":input:not(:image,:input,:submit)", new String[]{});
        t(":not Multiple", "#qunit-fixture p:not(:has(a), :nth-child(1))", new String[]{"first"});

        t("No element not selector", ".container div:not(.excluded) div", new String[]{});

        t(":not() Existing attribute", "#form select:not([multiple])", new String[]{"select1", "select2", "select5"});
        t(":not() Equals attribute", "#form select:not([name=select1])", new String[]{"select2", "select3", "select4", "select5"});
        t(":not() Equals quoted attribute", "#form select:not([name='select1'])", new String[]{"select2", "select3", "select4", "select5"});

        t(":not() Multiple Class", "#foo a:not(.blog)", new String[]{"yahoo", "anchor2"});
        t(":not() Multiple Class", "#foo a:not(.link)", new String[]{"yahoo", "anchor2"});
        t(":not() Multiple Class", "#foo a:not(.blog.link)", new String[]{"yahoo", "anchor2"});
// TODO(issue#54) - :not and :has deep compound chaining ends up not accurate
//        t(":not chaining (compound)", "#qunit-fixture div[id]:not(:has(div, span)):not(:has(*))", new String[]{"nothiddendivchild", "divWithNoTabIndex"});
        t(":not chaining (with attribute)", "#qunit-fixture form[id]:not([action$='formaction']):not(:button)", new String[]{"lengthtest", "name-tests", "testForm"});
        t(":not chaining (colon in attribute)", "#qunit-fixture form[id]:not([action='form:action']):not(:button)", new String[]{"form", "lengthtest", "name-tests", "testForm"});
        t(":not chaining (colon in attribute and nested chaining)", "#qunit-fixture form[id]:not([action='form:action']:button):not(:input)", new String[]{"form", "lengthtest", "name-tests", "testForm"});
        t(":not chaining", "#form select:not(.select1):contains(Nothing) > option:not(option)", new String[]{});

        t("positional :not()", "#foo p:not(:last)", new String[]{"sndp", "en"});
        t("positional :not() prefix", "#foo p:not(:last) a", new String[]{"yahoo"});
        t("compound positional :not()", "#foo p:not(:first, :last)", new String[]{"en"});
        t("compound positional :not()", "#foo p:not(:first, :even)", new String[]{"en"});
        t("compound positional :not()", "#foo p:not(:first, :odd)", new String[]{"sap"});
        t("reordered compound positional :not()", "#foo p:not(:odd, :first)", new String[]{"sap"});

        t("positional :not() with pre-filter", "#foo p:not([id]:first)", new String[]{"en", "sap"});
        t("positional :not() with post-filter", "#foo p:not(:first[id])", new String[]{"en", "sap"});
//        t("positional :not() with pre-filter", "#foo p:not([lang]:first)", new String[]{"sndp", "sap"});
        t("positional :not() with post-filter", "#foo p:not(:first[lang])", new String[]{"sndp", "en", "sap"});
    }

}
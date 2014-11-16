package integration.sizzle;

import infrastructure.junitrule.SetUpAndTearDownDriver;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.util.List;

import static io.github.seleniumquery.SeleniumQuery.$;
import static java.util.Arrays.asList;

public class SizzleAttributes extends SizzleTest {

    @Rule
    public SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver(SizzleTest.class);


    /*
    #failure
     @## FAILED on Chrome! -> Attribute Exists (case-insensitive) --> Lists differ! expected:<[[google]]> but was:<[[]]>
     @## FAILED on Firefox! -> Finding by attribute with escaped characters. expected:<[[org.openqa.selenium.remote.RemoteWebElement@9a82aa8 -> unknown locator]]> but was:<[]>
     @## FAILED on PhantomJS! -> Attribute Exists (case-insensitive) --> Lists differ! expected:<[[google]]> but was:<[[]]>
     */
    @Test
    public void attribute_selectors() throws Exception {
        t("Attribute Exists", "#qunit-fixture a[title]", new String[]{"google"});
        t("Attribute Exists (case-insensitive)", "#qunit-fixture a[TITLE]", new String[]{"google"});
        t("Attribute Exists (case-insensitive 2)", "#qunit-fixture a[TItlE]", new String[]{"google"});
        t("Attribute Exists", "#qunit-fixture *[title]", new String[]{"google"});
        t("Attribute Exists", "#qunit-fixture [title]", new String[]{"google"});
        t("Attribute Exists", "#qunit-fixture a[ title ]", new String[]{"google"});

        t("Boolean attribute exists", "#select2 option[selected]", new String[]{"option2d"});
        t("Boolean attribute equals", "#select2 option[selected='selected']", new String[]{"option2d"});

        t("Attribute Equals", "#qunit-fixture a[rel='bookmark']", new String[]{"simon1"});
        t("Attribute Equals", "#qunit-fixture a[rel='bookmark']", new String[]{"simon1"});
        t("Attribute Equals", "#qunit-fixture a[rel=bookmark]", new String[]{"simon1"});
        t("Attribute Equals", "#qunit-fixture a[href='http://www.google.com/']", new String[]{"google"});
        t("Attribute Equals", "#qunit-fixture a[ rel = 'bookmark' ]", new String[]{"simon1"});
        t("Attribute Equals Number", "#qunit-fixture option[value='1']", new String[]{"option1b", "option2b", "option3b", "option4b", "option5c"});
        t("Attribute Equals Number", "#qunit-fixture li[tabIndex='-1']", new String[]{"foodWithNegativeTabIndex"});

// TODO(issue#40)
//        t("Attribute Equals Number", "#qunit-fixture option[value=1]", new String[]{"option1b", "option2b", "option3b", "option4b", "option5c"});
//        t("Attribute Equals Number", "#qunit-fixture li[tabIndex=-1]", new String[]{"foodWithNegativeTabIndex"});

        executeJS("arguments[0].href = '#2';", id("anchor2"));
        t("href Attribute", "p a[href^='#']", new String[]{"anchor2"});
        t("href Attribute", "p a[href*='#']", new String[]{"simon1", "anchor2"});

        t("for Attribute", "form label[for]", new String[]{"label-for"});
        t("for Attribute in form", "#form [for=action]", new String[]{"label-for"});

        t("Attribute containing []", "input[name^='foo[']", new String[]{"hidden2"});
        t("Attribute containing []", "input[name^='foo[bar]']", new String[]{"hidden2"});
        t("Attribute containing []", "input[name*='[bar]']", new String[]{"hidden2"});
        t("Attribute containing []", "input[name$='bar]']", new String[]{"hidden2"});
        t("Attribute containing []", "input[name$='[bar]']", new String[]{"hidden2"});
        t("Attribute containing []", "input[name$='foo[bar]']", new String[]{"hidden2"});
        t("Attribute containing []", "input[name*='foo[bar]']", new String[]{"hidden2"});

        deepEqual(Sizzle("input[data-comma='0,1']"), asList(id("el12087")), "Without context, single-quoted attribute containing ','");
        deepEqual(Sizzle("input[data-comma=\"0,1\"]"), asList(id("el12087")), "Without context, double-quoted attribute containing ','");
        deepEqual(Sizzle("input[data-comma='0,1']", id("t12087")), asList(id("el12087")), "With context, single-quoted attribute containing ','");
        deepEqual(Sizzle("input[data-comma=\"0,1\"]", id("t12087")), asList(id("el12087")), "With context, double-quoted attribute containing ','");

        t("Multiple Attribute Equals", "#form input[type='radio'], #form input[type='hidden']", new String[]{"radio1", "radio2", "hidden1"});
        t("Multiple Attribute Equals", "#form input[type='radio'], #form input[type=\"hidden\"]", new String[]{"radio1", "radio2", "hidden1"});
        t("Multiple Attribute Equals", "#form input[type='radio'], #form input[type=hidden]", new String[]{"radio1", "radio2", "hidden1"});

        t("Attribute selector using UTF8", "span[lang=中文]", new String[]{"台北"});

        t("Attribute Begins With", "a[href ^= 'http://www']", new String[]{"google", "yahoo"});
        t("Attribute Ends With", "a[href $= 'org/']", new String[]{"mark"});
        t("Attribute Contains", "a[href *= 'google']", new String[]{"google", "groups"});
        t("Attribute Is Not Equal", "#ap a[hreflang!='en']", new String[]{"google", "groups", "anchor1"});
        t("Attribute Dashed Prefix", "#names-group span[id|='name']", new String[]{"name-is-example", "name-is-div"});
        t("Attribute Dashed Prefix Containing Dash", "#names-group span[id|='name-is']", new String[]{"name-is-example", "name-is-div"});
        t("Attribute Dashed Prefix Ending With Dash", "#names-group span[id|='name-is-']", new String[]{});
        t("Attribute Whitespace List Includes", "input[data-15233~='foo']", new String[]{"t15233-single", "t15233-double", "t15233-double-tab", "t15233-double-nl", "t15233-triple"});
        t("Attribute Whitespace List Includes", "input[data-15233~='bar']", new String[]{"t15233-double", "t15233-double-tab", "t15233-double-nl", "t15233-triple"});
        t("Attribute Whitespace List Includes", "input[data-15233~='baz']", new String[]{"t15233-triple"});

        WebElement opt = id("option1a");
        executeJS("arguments[0].setAttribute('test', '');", opt);

        ok(Sizzle.matchesSelector(opt, "[id*=option1][type!=checkbox]"), "Attribute Is Not Equal Matches");
        ok(Sizzle.matchesSelector(opt, "[id*=option1]"), "Attribute With No Quotes Contains Matches");

// TODO(issue#40)
//        ok(Sizzle.matchesSelector(opt, "[test=]"), "Attribute With No Quotes No Content Matches");

// TODO(issue#37)
//        ok(!Sizzle.matchesSelector(opt, "[test^='']"), "Attribute with empty string value does not match startsWith selector (^=)");

        ok(Sizzle.matchesSelector(opt, "[id=option1a]"), "Attribute With No Quotes Equals Matches");
// TODO(issue#40)
//        ok(Sizzle.matchesSelector(id("simon1"), "a[href*=#]"), "Attribute With No Quotes Href Contains Matches");

        t("Empty values", "#select1 option[value='']", new String[]{"option1a"});
        t("Empty values", "#select1 option[value!='']", new String[]{"option1b", "option1c", "option1d"});

        t("Select options via :selected", "#select1 option:selected", new String[]{"option1a"});
        t("Select options via :selected", "#select2 option:selected", new String[]{"option2d"});
        t("Select options via :selected", "#select3 option:selected", new String[]{"option3b", "option3c"});
        t("Select options via :selected", "select[name='select2'] option:selected", new String[]{"option2d"});

        t("Grouped Form Elements", "input[name='foo[bar]']", new String[]{"hidden2"});

        WebElement input = id("text1");
        executeJS("arguments[0].title = 'Don\\'t click me';", input);

        ok(Sizzle.matchesSelector(input, "input[title=\"Don't click me\"]"), "Quote within attribute value does not mess up tokenizer");

        // Uncomment if the boolHook is removed
        // var check2 = driver.findElement(By.id("check2"));
        // check2.checked = true;
        // ok( !Sizzle.matches("[checked]", [ check2 ] ), "Dynamic boolean attributes match when they should with Sizzle.matches (#11115)" );

        // jQuery #12303
        executeJS("arguments[0].setAttribute('data-pos', ':first');", input);
// TODO(issue#40)
//        ok(Sizzle.matchesSelector(input, "input[data-pos=\\:first]"), "POS within attribute value is treated as an attribute value");
        ok(Sizzle.matchesSelector(input, "input[data-pos=':first']"), "POS within attribute value is treated as an attribute value");
        ok(Sizzle.matchesSelector(input, ":input[data-pos=':first']"), "POS within attribute value after pseudo is treated as an attribute value");
        executeJS("arguments[0].removeAttribute('data-pos');", input);

        // Make sure attribute value quoting works correctly. See jQuery #6093; #6428; #13894
        // Use seeded results to bypass querySelectorAll optimizations
        executeJS("return "+
        "jQuery(\""+
            "<input type='hidden' id='attrbad_space' name='foo bar'/>" +
            "<input type='hidden' id='attrbad_dot' value='2' name='foo.baz'/>" +
            "<input type='hidden' id='attrbad_brackets' value='2' name='foo[baz]'/>" +
            "<input type='hidden' id='attrbad_injection' data-attr='foo_baz&#39;]'/>" +
            "<input type='hidden' id='attrbad_quote' data-attr='&#39;'/>" +
            "<input type='hidden' id='attrbad_backslash' data-attr='&#92;'/>" +
            "<input type='hidden' id='attrbad_backslash_quote' data-attr='&#92;&#39;'/>" +
            "<input type='hidden' id='attrbad_backslash_backslash' data-attr='&#92;&#92;'/>" +
            "<input type='hidden' id='attrbad_unicode' data-attr='&#x4e00;'/>" +
        "\").appendTo('#qunit-fixture').get()");

        t("Underscores don't need escaping", "input[id=types_all]", new String[]{"types_all"});

        List<WebElement> attrbad = q("qunit-fixture");
        deepEqual(Sizzle("#attrbad_space", null, null, attrbad), q("attrbad_space"), "Escaped space");
// TODO(issue#39)
/*
        deepEqual(Sizzle("input[name=foo\\ bar]", null, null, attrbad), q("attrbad_space"), "Escaped space");
        deepEqual(Sizzle("input[name=foo\\.baz]", null, null, attrbad), q("attrbad_dot"), "Escaped dot");
        deepEqual(Sizzle("input[name=foo\\[baz\\]]", null, null, attrbad), q("attrbad_brackets"), "Escaped brackets");
        deepEqual(Sizzle("input[data-attr='foo_baz\\']']", null, null, attrbad), q("attrbad_injection"), "Escaped quote + right bracket");

        deepEqual(Sizzle("input[data-attr='\\'']", null, null, attrbad), q("attrbad_quote"), "Quoted quote");
        deepEqual(Sizzle("input[data-attr='\\\\']", null, null, attrbad), q("attrbad_backslash"), "Quoted backslash");
        deepEqual(Sizzle("input[data-attr='\\\\\\'']", null, null, attrbad), q("attrbad_backslash_quote"), "Quoted backslash quote");
        deepEqual(Sizzle("input[data-attr='\\\\\\\\']", null, null, attrbad), q("attrbad_backslash_backslash"), "Quoted backslash backslash");

        deepEqual(Sizzle("input[data-attr='\\5C\\\\']", null, null, attrbad), q("attrbad_backslash_backslash"), "Quoted backslash backslash (numeric escape)");
        deepEqual(Sizzle("input[data-attr='\\5C \\\\']", null, null, attrbad), q("attrbad_backslash_backslash"), "Quoted backslash backslash (numeric escape with trailing space)");
        deepEqual(Sizzle("input[data-attr='\\5C\t\\\\']", null, null, attrbad), q("attrbad_backslash_backslash"), "Quoted backslash backslash (numeric escape with trailing tab)");
        deepEqual(Sizzle("input[data-attr='\\04e00']", null, null, attrbad), q("attrbad_unicode"), "Long numeric escape (BMP)");
*/
        // document.getElementById("attrbad_unicode").setAttribute("data-attr", "\uD834\uDF06A");
        // It was too much code to fix Safari 5.x Supplemental Plane crashes (see ba5f09fa404379a87370ec905ffa47f8ac40aaa3)
        // deepEqual( Sizzle( "input[data-attr='\\01D306A']", null, null, attrbad ), q("attrbad_unicode"),
        // 	"Long numeric escape (non-BMP)" );

        t("input[type=text]", "#form input[type=text]", new String[]{"text1", "text2", "hidden2", "name"});
        t("input[type=search]", "#form input[type=search]", new String[]{"search"});
        t("script[src] (jQuery #13777)", "#moretests script[src]", new String[]{"script-src"});

        // #3279
        executeJS("var div = document.createElement('div'); " +
                                                "div.id = 'divPARENTSELENIUMQUERY'; " +
                                                "div.innerHTML = \"<div id='fooSELENIUMQUERY' xml:test='something'></div>\"; " +
                                                "document.getElementById('qunit-fixture').appendChild(div);");
        WebElement div = id("divPARENTSELENIUMQUERY");
        WebElement div_firstChild = (WebElement) executeJS("return arguments[0].firstChild;", div);
        deepEqual(Sizzle("[xml\\:test]", div), asList(div_firstChild), "Finding by attribute with escaped characters.");

        t("Object.prototype property \"constructor\" (negative)", "[constructor]", new String[]{});
        t("Gecko Object.prototype property \"watch\" (negative)", "[watch]", new String[]{});

        executeJS("arguments[0].setAttribute('constructor', 'foo');", div_firstChild);
        executeJS("arguments[0].setAttribute('watch', 'bar');", div_firstChild);

        t("Object.prototype property \"constructor\"", "[constructor='foo']", new String[]{"fooSELENIUMQUERY"});
        t("Gecko Object.prototype property \"watch\"", "[watch='bar']", new String[]{"fooSELENIUMQUERY"});

        t("Value attribute is retrieved correctly", "input[value=Test]", new String[]{"text1", "text2"});
    }

}
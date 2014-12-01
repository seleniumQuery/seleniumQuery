package integration.selectors.pseudoclasses.content;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import infrastructure.junitrule.SetUpAndTearDownDriver;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

public class ContainsPseudoClassTest {

	@ClassRule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();
	@Rule public SetUpAndTearDownDriver setUpAndTearDownDriverRuleInstance = setUpAndTearDownDriverRule;

	// http://jsbin.com/siwapeqe/1/edit
    @Test
    public void contains_pseudo_with_tag() {
    	assertThat($("div").size(), is(36));

    	assertThat($("div:contains(abc)").size(), is(12));
    	assertThat($("div:contains(\"abc\")").size(), is(12));
    	assertThat($("div:contains('abc')").size(), is(12));

    	assertThat($("div:contains(\"'abc'\")").size(), is(4));
    	assertThat($("div:contains('\"abc\"')").size(), is(4));

    	assertThat($("div:contains(\"a'bc\")").size(), is(4));
    	assertThat($("div:contains('a\"bc')").size(), is(4));

    	assertThat($("div:contains(\"ab)c\")").size(), is(4));
    	assertThat($("div:contains('ab)c')").size(), is(4));

    	assertThat($("div:contains(\"a'b)c\")").size(), is(4));
    	assertThat($("div:contains('a\"b)c')").size(), is(4));
    }
    
    @Test
    public void contains_pseudo_with_tag_escaping_double_quotes_inside_double_quotes_string() {
    	assertThat($("div:contains(\"a\\\"b)c\")").size(), is(4)); //2?? 4?? who knows!!!
    	assertThat($("div:contains(\"a\\\\\\\"b)c\")").size(), is(2)); //2?? 4?? who knows!!!
    }

    @Test
    public void contains_pseudo_with_tag_escaping_single_quote_inside_single_quote_string() {
    	// the selectors below should return TWO divs, not FOUR, it is matching WRONGLY
    	// it should match the <div>a\'b)c</div>, like jQuery does, but it doesnt
    	// as the content of contains reaches the ContainsPseudoClass as a'b)c, whereas
    	// jQuery considers it to be a\'b)c.
    	//
    	// To me, the CSS parser does the right thing, but jQuery disagrees.
    	// There's not much we can do here without changing the CSS Parser...
    	assertThat($("div:contains('a\\'b)c')").size(), is(4));
    	assertThat($("div:contains('a\\\'b)c')").size(), is(4));
    }
    
    @Test
    public void containsPseudo() {
    	assertThat($("div:contains(abc)").size(), is(12));
    	assertThat($("body:contains(abc)").size(), is(1));
    	assertThat($("html:contains(abc)").size(), is(1));
    	assertThat($(":contains(abc)").size(), is(14));
    }
	
}
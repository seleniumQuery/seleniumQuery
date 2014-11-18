package integration.selectors.pseudoclasses.content;

import infrastructure.junitrule.SetUpAndTearDownDriver;
import org.junit.ClassRule;
import org.junit.Test;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ContainsPseudoClass2Test {
	
	@ClassRule
	public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver(ContainsPseudoClass2Test.class);
	
	// http://jsbin.com/tapol/6/edit
    @Test
    public void containsPseudo() {
    	assertThat($("div").size(), is(38));
    	assertThat($("div:contains(abc)").size(), is(12));
    	assertThat($("div:contains(\"abc\")").size(), is(12));
    	assertThat($("div:contains('abc')").size(), is(12));
    }
    @Test
    public void containsPseudo2() {
    	assertThat($("div:contains('\"abc\"')").size(), is(4));
    	assertThat($("#d5").is("div:contains('\"abc\"')"), is(true));
    }
    @Test
    public void containsPseudo23() {
    	assertThat($("div:contains(\"'abc'\")").size(), is(4));
    	assertThat($("#d9").is("div:contains(\"'abc'\")"), is(true));
    }
//    @Test
    public void containsPseudo24() {
    	assertThat($("div:contains(\"\\\"abc\\\"\")").size(), is(0));
    	assertThat($("div:contains('\\'abc\\'')").size(), is(0));
    }
//    @Test
    public void containsPseudo25() {
    	assertThat($("div:contains(\"a\\\"bc\")").size(), is(0));
    	assertThat($("div:contains('a\"bc')").size(), is(4));
    	assertThat($("#d12").is("div:contains('a\"bc')"), is(true));
    }
    @Test
    public void containsPseudo26() {
    	assertThat($("div:contains(\"a'bc\")").size(), is(4));
    	assertThat($("#d16").is("div:contains(\"a'bc\")"), is(true));
    }
    @Test
    public void containsPseudo27() {
    	assertThat($("div:contains('a\\'bc')").size(), is(4));// not in jsbin
    	assertThat($("#d16").is("div:contains('a\\'bc')"), is(true)); // not in jsbin
//    	assertThat($("div:contains('a\'bc')").size(), is(4));
//    	assertThat($("#d16").is("div:contains('a\\'bc')"), is(false));
//    	assertThat($("#d16").is("div:contains('a\'bc')"), is(true));
//    	assertThat($("#d16").is("div:contains('a'bc')"), is(true));
    }
    @Test
    public void containsPseudo28() {
    	assertThat($("div:contains(\"ab)c\")").size(), is(4));
    	assertThat($("#d20").is("div:contains(\"ab)c\")"), is(true));
    	assertThat($("div:contains('ab)c')").size(), is(4));
    	assertThat($("#d20").is("div:contains(\"ab)c\")"), is(true));
    }
    @Test
    public void containsPseudo29() {
    	assertThat($("div:contains('a\"b)c')").size(), is(4));
    	assertThat($("#d24").is("div:contains('a\"b)c')"), is(true));
    }
    @Test
    public void containsPseudo30() {
    	assertThat($("div:contains(\"a'b)c\")").size(), is(4));
    	assertThat($("#d28").is("div:contains(\"a'b)c\")"), is(true));
    }
//    @Test
    public void containsPseudo31() {
    	assertThat($("div:contains('a\\'b)c')").size(), is(2));
    	assertThat($("#d32").is("div:contains('a\\'b)c')"), is(true));
    	assertThat($("#d33").is("div:contains('a\\'b)c')"), is(true));
    }
//    @Test
    public void containsPseudo32() {
    	assertThat($("div:contains(\"a\\\"b)c\")").size(), is(2));
    	assertThat($("#d34").is("div:contains(\"a\\\"b)c\")"), is(true));
    	assertThat($("#d35").is("div:contains(\"a\\\"b)c\")"), is(true));
    }
    
}
package integration.selectors.pseudoclasses;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import infrastructure.junitrule.SetUpAndTearDownGivenDriver;

import org.junit.Rule;
import org.junit.Test;

public class EqPseudoClassTest {
	
	@Rule
	public SetUpAndTearDownGivenDriver setUpAndTearDownGivenDriverRule = new SetUpAndTearDownGivenDriver(getClass());
	
	@Test
	public void eqPseudo_with_tag_div() {
		assertThat($("div:eq(0)").size(), is(1));
		assertThat($("div:eq(4)").size(), is(0));
		assertThat($("div:eq(99)").size(), is(0));
	}
	
	@Test
	public void eqPseudo_with_class() {
		assertThat($(".c1:eq(0)").size(), is(1));
		assertThat($(".c1:eq(1)").size(), is(0));
		assertThat($(".c1:eq(99)").size(), is(0));
		
		assertThat($(".w00t:eq(0)").size(), is(1));
		assertThat($(".w00t:eq(2)").size(), is(1));
		assertThat($(".w00t:eq(3)").size(), is(0));
	}
	
	@Test
	public void eqPseudo_with_tag__INDIVIDUAL_CHECK() {
//		assertThat($("div:eq(0)").get().get(0).getText(), is("Batman"));
//		assertThat($("div:eq(1)").get().get(0).getText(), is("Spider Man"));
//		assertThat($("div:eq(2)").get().get(0).getText(), is("Hulk"));
//		assertThat($("div:eq(3)").get().get(0).getText(), is("Bozo"));
//		
//		assertThat($("div:eq(+0)").get().get(0).getText(), is("Batman"));
//		assertThat($("div:eq(+1)").get().get(0).getText(), is("Spider Man"));
//		assertThat($("div:eq(+2)").get().get(0).getText(), is("Hulk"));
//		assertThat($("div:eq(+3)").get().get(0).getText(), is("Bozo"));
//		
//		assertThat($("div:eq(-0)").get().get(0).getText(), is("Batman"));
//		assertThat($("div:eq(-5)").size(), is(0));
		assertThat($("div:eq(-1)").get().get(0).getText(), is("Bozo"));
		assertThat($("div:eq(-4)").get().get(0).getText(), is("Batman"));
		assertThat($("div:eq(-3)").get().get(0).getText(), is("Spider Man"));
		assertThat($("div:eq(-2)").get().get(0).getText(), is("Hulk"));
	}
	
	@Test
	public void eqPseudo() {
		assertThat($(":eq(0)").size(), is(1));
		assertThat($(":eq(99)").size(), is(0));
	}
	
    @Test
    public void testEqSelector() {
        assertThat($("div.c1:eq(0)").text(), is("Batman"));
        assertThat($("div.c2:eq(0)").text(), is("Spider Man"));
        assertThat($("div.c3:eq(0)").text(), is("Hulk"));
        
        assertThat($("div.c1:eq(1)").text(), is(""));

        assertThat($("div:eq(0)").text(), is("Batman"));
        assertThat($("div:eq(1)").text(), is("Spider Man"));
        assertThat($("div:eq(2)").text(), is("Hulk"));
    }
    
	@Test
	public void eqFunction_with_tag_and_class__and_single_set_with_negative_index() {
        assertThat($("div.c1:eq(-1)").text(), is("Batman"));
        assertThat($("div.c2:eq(-1)").text(), is("Spider Man"));
        assertThat($("div.c3:eq(-1)").text(), is("Hulk"));
	}
	
	@Test
	public void eqPseudo_with_descendant() {
		assertThat($(".nesting a").size(), is(1));
		assertThat($(".nesting a:eq(0)").size(), is(1));
		assertThat($(".nesting a:eq(0)").text(), is("yo"));
	}
	
}
package integration.selectors.pseudoclasses.form;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import infrastructure.junitrule.SetUpAndTearDownGivenDriver;
import io.github.seleniumquery.selectors.pseudoclasses.UnsupportedXPathPseudoClassException;

import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebElement;

public class TabbablePseudoClassTest {
	
	@Rule
	public SetUpAndTearDownGivenDriver setUpAndTearDownGivenDriverRule = new SetUpAndTearDownGivenDriver(getClass());
	
	@Test(expected = UnsupportedXPathPseudoClassException.class)
	public void tabbablePseudoClass() {
		assertThat($("body > *").size(), is(3));
		for (WebElement el : $(":tabbable")) {
			System.out.println("@# El: "+el);
		}
		assertThat($(":tabbable").size(), is(2));
	}
	
}
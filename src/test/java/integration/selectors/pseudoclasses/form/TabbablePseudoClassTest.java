package integration.selectors.pseudoclasses.form;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import infrastructure.junitrule.SetUpAndTearDownDriver;
import io.github.seleniumquery.by.css.pseudoclasses.UnsupportedXPathPseudoClassException;

import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebElement;

public class TabbablePseudoClassTest {
	
	@Rule
	public SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver(getClass());
	
	@Test(expected = UnsupportedXPathPseudoClassException.class)
	public void tabbablePseudoClass() {
		assertThat($("body > *").size(), is(3));
		for (WebElement el : $(":tabbable")) {
			System.out.println("@# El: "+el);
		}
		assertThat($(":tabbable").size(), is(2));
	}
	
}
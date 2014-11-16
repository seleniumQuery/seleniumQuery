package integration.selectors.pseudoclasses;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import infrastructure.junitrule.SetUpAndTearDownDriver;
import io.github.seleniumquery.selector.css.CssSelectorMatcherService;

import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PseudoClassEvaluatorTest {
	
	@Rule
	public SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver(PseudoClassEvaluatorTest.class);

	WebDriver driver;
	
	@Before
	public void before() {
		driver = $.browser.getDefaultDriver();
	}
	
	@Test
	public void option() {
        WebElement optionElement = driver.findElement(By.cssSelector(".e4"));
			
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, optionElement, "option"), is(true));
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, optionElement, "*"), is(true));
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, optionElement, ":not(div)"), is(true));
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, optionElement, ":not(:not(div))"), is(false));
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, optionElement, ":not(:not(option.e4))"), is(true));
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, optionElement, ":only-of-type"), is(false));
	}
	
	@Test
	public void html() {
		WebElement htmlElement = driver.findElement(By.cssSelector("html"));
		
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, htmlElement, "option"), is(false));
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, htmlElement, "*"), is(true));
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, htmlElement, ":root"), is(true));
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, htmlElement, ":only-of-type"), is(true));
	}
	
	@Test
	public void body() {
		WebElement bodyElement = driver.findElement(By.cssSelector("body"));
		
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, bodyElement, "option"), is(false));
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, bodyElement, "*"), is(true));
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, bodyElement, ":root"), is(false));
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, bodyElement, ":only-of-type"), is(true));
	}
	
	// :lang()
	@Test
	public void lang() {
		WebElement brazilian_p = driver.findElement(By.id("brazilian-p"));
		WebElement french_p = driver.findElement(By.id("french-p"));
		WebElement hero_combo = driver.findElement(By.id("hero-combo"));
		
		WebElement htmlElement = driver.findElement(By.cssSelector("html"));
		WebElement bodyElement = driver.findElement(By.cssSelector("body"));
		
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, french_p, ":lang(fr)"), is(true));
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, brazilian_p, ":lang(fr)"), is(false));
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, hero_combo, ":lang(fr)"), is(false));
		
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, bodyElement, ":lang(fr)"), is(false));
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, htmlElement, ":lang(fr)"), is(false));
		
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, french_p, ":lang(pt-BR)"), is(false));
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, brazilian_p, ":lang(pt-BR)"), is(true));
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, hero_combo, ":lang(pt-BR)"), is(true));
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, bodyElement, ":lang(pt-BR)"), is(true));
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, htmlElement, ":lang(pt-BR)"), is(false));
	}
	
	// :only-child
	@Test
	public void only_child() {
		WebElement onlyChild = driver.findElement(By.id("onlyChild"));
		WebElement grandsonWithSiblings = driver.findElement(By.id("grandsonWithSiblings"));
		WebElement brazilian_p = driver.findElement(By.id("brazilian-p"));
		WebElement hero_combo = driver.findElement(By.id("hero-combo"));
		WebElement htmlElement = driver.findElement(By.cssSelector("html"));
		WebElement bodyElement = driver.findElement(By.cssSelector("body"));
		
		String selector = ":only-child";
		
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, onlyChild, selector), is(true));
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, grandsonWithSiblings, selector), is(false));
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, brazilian_p, selector), is(false));
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, hero_combo, selector), is(false));
		
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, bodyElement, selector), is(false));
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, htmlElement, selector), is(false));
	}
	
	// :contains()
    @Test
    public void contains_pseudo() throws Exception {
    	WebElement containsDiv = driver.findElement(By.id("containsDiv"));
    	List<WebElement> containsDivs = containsDiv.findElements(By.tagName("div"));
    	
    	// <div>abc</div>
    	WebElement div = containsDivs.get(0);
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, div, ":contains(abc)"), is(true));
    	assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, div, ":contains(ac)"), is(false));
    	assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, div, ":contains(\"abc\")"), is(true));
    	assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, div, ":contains('abc')"), is(true));
    	assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, div, ":contains('\"abc\"')"), is(false));
    	assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, div, ":contains(\"'abc'\")"), is(false));
    	
		// <div>"abc"</div>
    	WebElement div1 = containsDivs.get(1);
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, div1, ":contains(abc)"), is(true));
    	assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, div1, ":contains(ac)"), is(false));
    	assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, div1, ":contains(\"abc\")"), is(true));
    	assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, div1, ":contains('abc')"), is(true));
    	assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, div1, ":contains('\"abc\"')"), is(true)); // diferenca da 0
    	assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, div1, ":contains(\"'abc'\")"), is(false));
    	
    	// <div>'abc'</div>
    	WebElement div2 = containsDivs.get(2);
    	assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, div2, ":contains(abc)"), is(true));
    	assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, div2, ":contains(ac)"), is(false));
    	assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, div2, ":contains(\"abc\")"), is(true));
    	assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, div2, ":contains('abc')"), is(true));
    	assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, div2, ":contains('\"abc\"')"), is(false));
    	assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, div2, ":contains(\"'abc'\")"), is(true)); // diferenca da 0
    	
		// <div>a"bc</div>
    	WebElement div3 = containsDivs.get(3);
//    	assertThat(SelectorEvaluator.elementMatchesStringSelector(driver, div3, ":contains(a\\\"bc)"), is(false));
    	assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, div3, ":contains('a\"bc')"), is(true));

    	// <div>ab)c</div>
    	WebElement div4 = containsDivs.get(4);
    	assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, div4, ":contains(ab\\)c)"), is(true));
    	assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, div4, ":contains('ab)c')"), is(true));
    	assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, div4, ":contains(\"ab)c\")"), is(true));
    }
    
//    @Test
    public void bizarre_escaping() {
    	WebElement containsDiv = driver.findElement(By.id("containsDiv"));
    	List<WebElement> containsDivs = containsDiv.findElements(By.tagName("div"));
    	
    	// <div>a\"b)c</div>
    	WebElement div5 = containsDivs.get(5);
    	String slash = "\\\\"+"\\\\"; // after escaped by the java compiler: "\\\\" -- will become a \ after escaped by the css parser
    	String quote = "\\"+"\""; // after escaped by the java compiler: "\"" -- will become a " after escaped by the css parser
    	// TODO jQuery's quote escaping is messy!
    	assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, div5, ":contains(a"+ slash + quote + "b\\)c)"), is(true)); // should be true??
    	assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, div5, ":contains('a"+ slash + "\"b)c')"), is(true)); // ??
    	assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, div5, ":contains(\"a"+ slash + quote + "b)c\")"), is(true)); // ??
    }
    
    // :first-child
    @Test
    public void first_child() {
    	WebElement onlyChild = driver.findElement(By.id("onlyChild"));
    	WebElement grandsonWithSiblings = driver.findElement(By.id("grandsonWithSiblings"));
    	WebElement brazilian_p = driver.findElement(By.id("brazilian-p"));
    	WebElement hero_combo = driver.findElement(By.id("hero-combo"));
    	WebElement htmlElement = driver.findElement(By.cssSelector("html"));
    	WebElement headElement = driver.findElement(By.cssSelector("head"));
    	WebElement bodyElement = driver.findElement(By.cssSelector("body"));
    	
    	String selector = ":first-child";
    	
    	assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, onlyChild, selector), is(true));
    	assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, grandsonWithSiblings, selector), is(true));
    	assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, brazilian_p, selector), is(true));
    	assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, hero_combo, selector), is(false));
    	
    	assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, htmlElement, selector), is(false));
    	assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, headElement, selector), is(true));
    	assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, bodyElement, selector), is(false));
    }
    
    // :present
    @Test
    public void present() {
    	WebElement onlyChild = driver.findElement(By.id("onlyChild"));
    	
    	assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, onlyChild, ":present"), is(true));
    	assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, onlyChild, ":not(:present)"), is(false));
    }
    
    // :eq()
    @Test
    public void eq() {
		WebElement brazilian_p = driver.findElement(By.id("brazilian-p"));
		WebElement french_p = driver.findElement(By.id("french-p"));
    	
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, brazilian_p, "p:eq(0)"), is(true));
    	assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, brazilian_p, ":eq(0)"), is(false));
    	assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, french_p, "p:eq(1)"), is(true));
    	assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, french_p, ":eq(1)"), is(false));
		
		WebElement xidv = driver.findElement(By.id("xidv"));
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, xidv, ".ball.div:eq(1)"), is(false));
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, xidv, ".div:eq(1).ball"), is(true));
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, xidv, ".div.ball:eq(1)"), is(false));
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, xidv, ".div.ball:eq(0)"), is(true));
		
    }

}
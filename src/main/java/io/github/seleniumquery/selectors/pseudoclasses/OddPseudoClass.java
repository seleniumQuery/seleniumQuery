package io.github.seleniumquery.selectors.pseudoclasses;

import io.github.seleniumquery.locator.ElementFilter;
import io.github.seleniumquery.selectorcss.CompiledCssSelector;
import io.github.seleniumquery.selectorcss.CssSelectorCompilerService;
import io.github.seleniumquery.selectorxpath.SqXPathSelector;
import io.github.seleniumquery.selectorxpath.XPathSelectorFactory;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OddPseudoClass implements PseudoClass {

	private static final OddPseudoClass instance = new OddPseudoClass();
	public static OddPseudoClass getInstance() {
		return instance;
	}
	private OddPseudoClass() { }
	
	private static final String ODD_PSEUDO_CLASS_NO_COLON = "odd";
	
	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return ODD_PSEUDO_CLASS_NO_COLON.equals(pseudoClassValue);
	}

	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, PseudoClassSelector pseudoClassSelector) {
		CompiledCssSelector compileSelector = CssSelectorCompilerService.compileSelector(driver, pseudoClassSelector.getStringMap(), pseudoClassSelector.getSelector());
		List<WebElement> elements = compileSelector.execute(driver);
		return elements.indexOf(element) % 2 == 1;
	}
	
	@Override
	public CompiledCssSelector compilePseudoClass(WebDriver driver, PseudoClassSelector pseudoClassSelector) {
		// we never consider :odd to be supported natively
		ElementFilter evenPseudoClassFilter = new PseudoClassFilter(getInstance(), pseudoClassSelector);
		return CompiledCssSelector.createFilterOnlySelector(evenPseudoClassFilter);
	}
	
	@Override
	public SqXPathSelector pseudoClassToXPath(WebDriver driver, PseudoClassSelector pseudoClassSelector) {
		// notice that XPath is 1-based and :odd is not.
		return XPathSelectorFactory.createNoFilterSelectorAppliedToAll("[(position() mod 2) = 0]");
	}

}
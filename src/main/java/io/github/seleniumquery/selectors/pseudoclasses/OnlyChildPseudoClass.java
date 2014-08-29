package io.github.seleniumquery.selectors.pseudoclasses;

import io.github.seleniumquery.locator.ElementFilter;
import io.github.seleniumquery.selector.DriverSupportService;
import io.github.seleniumquery.selector.SelectorUtils;
import io.github.seleniumquery.selectorcss.CompiledCssSelector;
import io.github.seleniumquery.selectorxpath.XPathExpression;
import io.github.seleniumquery.selectorxpath.XPathSelectorFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OnlyChildPseudoClass implements PseudoClass {
	
	private static final OnlyChildPseudoClass instance = new OnlyChildPseudoClass();
	public static OnlyChildPseudoClass getInstance() {
		return instance;
	}
	private OnlyChildPseudoClass() { }
	
	private static final String ONLY_CHILD_PSEUDO_CLASS_NO_COLON = "only-child";
	private static final String ONLY_CHILD_PSEUDO_CLASS = ":"+ONLY_CHILD_PSEUDO_CLASS_NO_COLON;
	
	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return ONLY_CHILD_PSEUDO_CLASS_NO_COLON.equals(pseudoClassValue);
	}
	
	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, PseudoClassSelector pseudoClassSelector) {
		WebElement parent = SelectorUtils.parent(element);
		if (parent == null // parent is null when element is <HTML>
				|| parent.getTagName().equals("html")
				|| parent.getTagName().equals("body")
				|| parent.getTagName().equals("head")) {
			// I have tested and :only-child never worked direct children of those
			return false;
		}
		return SelectorUtils.itselfWithSiblings(element).size() == 1;
	}
	
	private static final ElementFilter onlyChildPseudoClassFilter = new PseudoClassFilter(getInstance());

	@Override
	public CompiledCssSelector compilePseudoClass(WebDriver driver, PseudoClassSelector pseudoClassSelector) {
		// https://developer.mozilla.org/en-US/docs/Web/CSS/:only-child
		if (DriverSupportService.getInstance().supportsNatively(driver, ONLY_CHILD_PSEUDO_CLASS)) {
			return CompiledCssSelector.createNoFilterSelector(ONLY_CHILD_PSEUDO_CLASS);
		}
		return CompiledCssSelector.createFilterOnlySelector(onlyChildPseudoClassFilter);
	}
	
	@Override
	public XPathExpression pseudoClassToXPath(WebDriver driver, PseudoClassSelector pseudoClassSelector) {
		return XPathSelectorFactory.createNoFilterSelector("[last() = 1]");
	}

}
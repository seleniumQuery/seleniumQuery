package io.github.seleniumquery.selectors.pseudoclasses;

import io.github.seleniumquery.selector.CompiledCssSelector;
import io.github.seleniumquery.selector.CssFilter;
import io.github.seleniumquery.selector.DriverSupportService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EmptyPseudoClass implements PseudoClass {
	
	private static final EmptyPseudoClass instance = new EmptyPseudoClass();
	public static EmptyPseudoClass getInstance() {
		return instance;
	}
	private EmptyPseudoClass() { }
	
	private static final Log LOGGER = LogFactory.getLog(EmptyPseudoClass.class);
	
	private static final String EMPTY_PSEUDO_CLASS_NO_COLON = "empty";
	
	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return EMPTY_PSEUDO_CLASS_NO_COLON.equals(pseudoClassValue);
	}
	
	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, PseudoClassSelector pseudoClassSelector) {
		boolean isEmpty = !ParentPseudoClass.getInstance().isParent(element);
		// #Cross-Driver
		if (isEmpty && DriverSupportService.isHtmlUnitDriverEmulatingIE(driver)) {
			LOGGER.warn("The outcome of the selector with the pseudo-class \":empty\" could be affected:" +
					" HtmlUnidDriver emulating IE considers elements " +
					" with space-only content (e.g. \"<div> </div>\") to be empty, while for other browsers" +
					" they are not! There is no workaround for this, as HtmlUnitDriver ignored the spaces during" +
					" the DOM parsing phase, and we have no means to know now if the elements had spaces (that" +
					" were ignored) or if they were just empty.");
		}
		return isEmpty;
	}
	
	private static final CssFilter emptyPseudoClassFilter = new PseudoClassFilter(getInstance());
	@Override
	public CompiledCssSelector compilePseudoClass(WebDriver driver, PseudoClassSelector pseudoClassSelector) {
		// we never consider :empty to be supported natively
		return CompiledCssSelector.createFilterOnlySelector(emptyPseudoClassFilter);
	}
	
}
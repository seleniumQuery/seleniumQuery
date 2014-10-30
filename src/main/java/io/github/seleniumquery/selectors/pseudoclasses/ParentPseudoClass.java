package io.github.seleniumquery.selectors.pseudoclasses;

import io.github.seleniumquery.selector.DriverSupportService;
import io.github.seleniumquery.selector.xpath.XPathExpression;
import io.github.seleniumquery.selector.xpath.XPathSelectorFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ParentPseudoClass implements PseudoClass {
	
	private static final ParentPseudoClass instance = new ParentPseudoClass();
	public static ParentPseudoClass getInstance() {
		return instance;
	}
	private ParentPseudoClass() { }
	
	private static final Log LOGGER = LogFactory.getLog(ParentPseudoClass.class);
	
	private static final String PARENT_PSEUDO_CLASS_NO_COLON = "parent";
	
	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return PARENT_PSEUDO_CLASS_NO_COLON.equals(pseudoClassValue);
	}
	
	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, PseudoClassSelector pseudoClassSelector) {
		boolean isParent = isParent(element);
		// #Cross-Driver
		if (!isParent && DriverSupportService.isHtmlUnitDriverEmulatingIE(driver)) {
			LOGGER.warn("The outcome of the selector with the pseudo-class \":parent\" could be affected:" +
					" HtmlUnidDriver emulating IE considers elements " +
					" with space-only content (e.g. \"<div> </div>\") to be empty, while for other browsers" +
					" they are not! There is no workaround for this, as HtmlUnitDriver ignored the spaces during" +
					" the DOM parsing phase, and we have no means to know now if the elements had spaces (that" +
					" were ignored) or if they were just empty.");
		}
		return isParent;
	}
	
	boolean isParent(WebElement element) {
		return !element.findElements(By.xpath("self::node()[count(node()) > 0]")).isEmpty();
	}
	
	@Override
	public XPathExpression pseudoClassToXPath(PseudoClassSelector pseudoClassSelector) {
		return XPathSelectorFactory.createNoFilterSelector("[count(.//*) > 0]");
	}
	
}
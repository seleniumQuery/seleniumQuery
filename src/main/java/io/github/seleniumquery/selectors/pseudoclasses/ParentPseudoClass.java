package io.github.seleniumquery.selectors.pseudoclasses;

import io.github.seleniumquery.selector.DriverVersionUtils;
import io.github.seleniumquery.selector.xpath.XPathExpression;
import io.github.seleniumquery.selector.xpath.XPathExpressionFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * :parent
 *
 * @author acdcjunior
 * @since 1.0.0
 */
public class ParentPseudoClass implements PseudoClass {
	
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
		if (!isParent && DriverVersionUtils.isHtmlUnitDriverEmulatingIEBelow11(driver)) {
			LOGGER.warn("The outcome of the selector with the pseudo-class \":parent\" could be affected:" +
					" HtmlUnidDriver emulating IE below 11 considers elements " +
					" with space-only content (e.g. \"<div> </div>\") to be empty, while for other browsers" +
					" they are not! There is no workaround for this, as HtmlUnitDriver ignored the spaces during" +
					" the DOM parsing phase, and we have no means to know now if the elements had spaces (that" +
					" were ignored) or if they were just empty.");
		}
		return isParent;
	}

    /**
     * Tests if the element has any children.
     * @param element The element to be checked if it is a parent.
     * @return true if the element is a parent (has children), false otherwise.
     */
	boolean isParent(WebElement element) {
		return !element.findElements(By.xpath("self::node()[count(node()) > 0]")).isEmpty();
	}
	
	@Override
	public XPathExpression pseudoClassToXPath(PseudoClassSelector pseudoClassSelector) {
		return XPathExpressionFactory.createNoFilterSelector("[count(node()) > 0]");
	}
	
}
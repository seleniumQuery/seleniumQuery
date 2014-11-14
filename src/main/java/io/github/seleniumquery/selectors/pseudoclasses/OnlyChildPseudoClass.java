package io.github.seleniumquery.selectors.pseudoclasses;

import io.github.seleniumquery.selector.SelectorUtils;
import io.github.seleniumquery.selector.xpath.XPathExpression;
import io.github.seleniumquery.selector.xpath.XPathExpressionFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * https://developer.mozilla.org/en-US/docs/Web/CSS/:only-child
 * 
 * @author acdcjunior
 * @since 1.0.0
 */
public class OnlyChildPseudoClass implements PseudoClass {
	
	private static final String ONLY_CHILD_PSEUDO_CLASS_NO_COLON = "only-child";
	
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
	
	@Override
	public XPathExpression pseudoClassToXPath(PseudoClassSelector pseudoClassSelector) {
		// [last() = 1] will not suffice because it may be composed into an expression like //a[last() = 1] which will yield wrong results
		// So you have to go up and then down again: //a[../*[last() = 1]]
		return XPathExpressionFactory.createNoFilterSelector("[../*[last() = 1]]");
	}

}
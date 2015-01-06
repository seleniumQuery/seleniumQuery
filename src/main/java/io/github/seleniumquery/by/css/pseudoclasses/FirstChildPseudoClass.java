package io.github.seleniumquery.by.css.pseudoclasses;

import io.github.seleniumquery.by.SelectorUtils;
import io.github.seleniumquery.by.xpath.XPathComponent;
import io.github.seleniumquery.by.xpath.XPathComponentFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * https://developer.mozilla.org/en-US/docs/Web/CSS/:first-child
 *
 * @author acdcjunior
 * @since 0.9.0
 */
public class FirstChildPseudoClass implements PseudoClass {

	private static final String FIRST_CHILD_PSEUDO_CLASS_NO_COLON = "first-child";

	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return FIRST_CHILD_PSEUDO_CLASS_NO_COLON.equals(pseudoClassValue);
	}

	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, PseudoClassSelector pseudoClassSelector) {
		WebElement parent = SelectorUtils.parent(element);
		/* parent is null when element is <HTML> */
		//noinspection SimplifiableIfStatement
		if (parent == null) {
			return false;
		}
		return SelectorUtils.itselfWithSiblings(element).get(0).equals(element);
	}

	@Override
	public XPathComponent pseudoClassToXPath(PseudoClassSelector pseudoClassSelector) {
		return XPathComponentFactory.createNoFilter("[position() = 1]");
	}

}
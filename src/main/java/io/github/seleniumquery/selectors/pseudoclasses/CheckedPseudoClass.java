package io.github.seleniumquery.selectors.pseudoclasses;

import io.github.seleniumquery.selector.xpath.XPathExpression;
import io.github.seleniumquery.selector.xpath.XPathSelectorFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * https://developer.mozilla.org/en-US/docs/Web/CSS/:checked
 * 
 * #Cross-Driver
 * In HtmlUnitDriver, :checked is not consistent, so we consider it as not supported
 * In PhantomJSDriver, :checked does not work for <option> tags, so we consider it as not supported as well
 *
 * @author acdcjunior
 * @since 1.0.0
 */
public class CheckedPseudoClass implements PseudoClass {

	private static final String INPUT_TAG_NAME = "input";
	private static final String OPTION_TAG_NAME = "option";
	
	private static final String TYPE_ATTRIBUTE_NAME = "type";
	private static final String RADIO_ATTR_VALUE = "radio";
	private static final String CHECKBOX_ATTR_VALUE = "checkbox";

	private static final String CHECKED_PSEUDO_CLASS_NO_COLON = "checked";

	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return CHECKED_PSEUDO_CLASS_NO_COLON.equals(pseudoClassValue);
	}

	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, PseudoClassSelector pseudoClassSelector) {
		return isChecked(element);
	}

	public boolean isChecked(WebElement element) {
		String tagName = element.getTagName();
		String typeAttribute = element.getAttribute(TYPE_ATTRIBUTE_NAME);
		return element.isSelected() && (OPTION_TAG_NAME.equals(tagName) || isRadioOrCheckbox(tagName, typeAttribute));
	}
	
	private boolean isRadioOrCheckbox(String tagName, String typeAttribute) {
		return INPUT_TAG_NAME.equals(tagName) && (RADIO_ATTR_VALUE.equals(typeAttribute) || CHECKBOX_ATTR_VALUE.equals(typeAttribute));
	}

	@Override
	public XPathExpression pseudoClassToXPath(PseudoClassSelector pseudoClassSelector) {
		return XPathSelectorFactory.createNoFilterSelector("[((local-name() = 'input' and (@type = 'radio' or @type = 'checkbox') and @checked) or (local-name() = 'option' and @selected))]");
	}

}
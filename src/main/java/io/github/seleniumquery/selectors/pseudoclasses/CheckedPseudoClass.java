package io.github.seleniumquery.selectors.pseudoclasses;

import io.github.seleniumquery.locator.ElementFilter;
import io.github.seleniumquery.selector.DriverSupportService;
import io.github.seleniumquery.selectorcss.CompiledCssSelector;
import io.github.seleniumquery.selectorxpath.XPathExpression;
import io.github.seleniumquery.selectorxpath.XPathSelectorFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckedPseudoClass implements PseudoClass {

	private static final String INPUT_TAG_NAME = "input";
	private static final String OPTION_TAG_NAME = "option";
	
	private static final String TYPE_ATTRIBUTE_NAME = "type";
	private static final String RADIO_ATTR_VALUE = "radio";
	private static final String CHECKBOX_ATTR_VALUE = "checkbox";

	private static final CheckedPseudoClass instance = new CheckedPseudoClass();
	public static CheckedPseudoClass getInstance() {
		return instance;
	}
	private CheckedPseudoClass() { }

	private static final String CHECKED_PSEUDO_CLASS_NO_COLON = "checked";
	private static final String CHECKED_PSEUDO_CLASS = ":"+CHECKED_PSEUDO_CLASS_NO_COLON;

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
		return element.isSelected() && 
				(
						OPTION_TAG_NAME.equals(tagName)
						||
						(INPUT_TAG_NAME.equals(tagName) && (RADIO_ATTR_VALUE.equals(typeAttribute) || CHECKBOX_ATTR_VALUE.equals(typeAttribute)) )
				);
	}

	private static final ElementFilter checkedPseudoClassFilter = new PseudoClassFilter(getInstance());
	@Override
	public CompiledCssSelector compilePseudoClass(WebDriver driver, PseudoClassSelector pseudoClassSelector) {
		// https://developer.mozilla.org/en-US/docs/Web/CSS/:checked

		// #Cross-Driver
		// In HtmlUnitDriver, :checked is not consistent, so we consider it as not supported
		// In PhantomJSDriver, :checked does not work for <option> tags, so we consider it as not supported as well
		if (DriverSupportService.isNotHtmlUnitDriver(driver) &&
				DriverSupportService.isNotPhantomJsDriver(driver) &&
				DriverSupportService.getInstance().supportsNatively(driver, CHECKED_PSEUDO_CLASS)) {
			return CompiledCssSelector.createNoFilterSelector(CHECKED_PSEUDO_CLASS);
		}
		return CompiledCssSelector.createFilterOnlySelector(checkedPseudoClassFilter);
	}
	
	@Override
	public XPathExpression pseudoClassToXPath(WebDriver driver, PseudoClassSelector pseudoClassSelector) {
		return XPathSelectorFactory.createNoFilterSelector("[((local-name() = 'input' and (@type = 'radio' or @type = 'checkbox') and @checked) or (local-name() = 'option' and @selected))]");
	}

}
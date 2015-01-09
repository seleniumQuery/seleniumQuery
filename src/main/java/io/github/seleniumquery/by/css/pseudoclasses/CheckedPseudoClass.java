package io.github.seleniumquery.by.css.pseudoclasses;

import io.github.seleniumquery.by.xpath.component.ConditionSimpleComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static io.github.seleniumquery.by.WebElementUtils.*;
import static io.github.seleniumquery.by.css.pseudoclasses.SelectedPseudoClass.SELECTED_PSEUDO_CONDITION;

/**
 * https://developer.mozilla.org/en-US/docs/Web/CSS/:checked
 * 
 * #Cross-Driver
 * In HtmlUnitDriver, document.querySelectorAll(":checked") is not consistent, so we should consider it as
 * not supported;
 * In PhantomJSDriver, document.querySelectorAll(":checked") does not work for <option> tags, so we should
 * consider it as not supported as well!
 *
 * @author acdcjunior
 * @author ricardo-sc
 *
 * @since 0.9.0
 */
public class CheckedPseudoClass implements PseudoClass<ConditionSimpleComponent> {

	private static final String CHECKED_PSEUDO_CLASS_NO_COLON = "checked";

	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return CHECKED_PSEUDO_CLASS_NO_COLON.equalsIgnoreCase(pseudoClassValue);
	}

	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, PseudoClassSelector pseudoClassSelector) {
		return isChecked(element);
	}

	public boolean isChecked(WebElement element) {
		// #Cross-Driver
		// PhantomJS: When we call element.isSelected() on an element that is not selectable,
		// PhantomJS throws an exception, so we must check the element type before calling isSelected().
		return isCheckableTag(element) && element.isSelected();
	}

	private boolean isCheckableTag(WebElement element) {
		return isOptionTag(element) || isInputRadioTag(element) || isInputCheckboxTag(element);
	}

	@Override
	public ConditionSimpleComponent pseudoClassToXPath(PseudoClassSelector pseudoClassSelector) {
		return new ConditionSimpleComponent("[" +
				"(" +
				"(local-name() = 'input' and (@type = 'radio' or @type = 'checkbox') and @checked) " +
				"or " +
				"(" + SELECTED_PSEUDO_CONDITION + ")" +
				")" +
				"]");
	}

}
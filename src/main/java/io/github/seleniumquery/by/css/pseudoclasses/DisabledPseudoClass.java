package io.github.seleniumquery.by.css.pseudoclasses;

import io.github.seleniumquery.by.DriverVersionUtils;
import io.github.seleniumquery.by.SelectorUtils;
import io.github.seleniumquery.by.xpath.component.XPathComponent;
import io.github.seleniumquery.by.xpath.component.XPathComponentFactory;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * https://developer.mozilla.org/en-US/docs/Web/CSS/:disabled
 * 
 * #Cross-Driver
 * HtmlUnitDriver has problems with :disabled, so we consider it can never be handler by the browser
 * by "problems" we mean it is inconsistent, changing depending on what browser it is attempting to emulate
 *
 * @author acdcjunior
 *
 * @since 0.9.0
 */
public class DisabledPseudoClass implements PseudoClass {

	private static final String DISABLED_PSEUDO_CLASS_NO_COLON = "disabled";

	private static final String INPUT = "input";
	private static final String BUTTON = "button";
	private static final String OPTION = "option";
	private static final String OPTGROUP = "optgroup";
	private static final String SELECT = "select";
	private static final String TEXTAREA = "textarea";

	public static final List<String> DISABLEABLE_TAGS = Arrays.asList(INPUT, BUTTON, OPTGROUP, OPTION, SELECT, TEXTAREA);

	private static final String DISABLEABLE_TAGS_XPATH;
	static {
		String or = " or ";
		StringBuilder sb = new StringBuilder("(");
		for (String disableableTag : DISABLEABLE_TAGS) { sb.append("self::").append(disableableTag).append(or); }
		DISABLEABLE_TAGS_XPATH = sb.replace(sb.length()-or.length(), sb.length(),")").toString();
	}

	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return DISABLED_PSEUDO_CLASS_NO_COLON.equals(pseudoClassValue);
	}

	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, PseudoClassSelector pseudoClassSelector) {
		// #Cross-Driver
		// When there is a not disabled <option> under a disabled <optgroup>, HtmlUnitDriver considers
		// the <option> to be enabled, when it is not
		if (DriverVersionUtils.isHtmlUnitDriver(driver) && OPTION.equals(element.getTagName())) {
			WebElement optionParent = SelectorUtils.parent(element);
			if (OPTGROUP.equals(optionParent.getTagName()) && !optionParent.isEnabled()) {
				return true;
			}
		}
		return !element.isEnabled() && DISABLEABLE_TAGS.contains(element.getTagName());
	}

	@Override
	public XPathComponent pseudoClassToXPath(PseudoClassSelector pseudoClassSelector) {
		return XPathComponentFactory.createNoFilter(
				"[(" +
						"(@disabled and " + DISABLEABLE_TAGS_XPATH + ") " +
						"or " +
						"(self::option and ancestor::select[@disabled])" +
						")]");
	}

}
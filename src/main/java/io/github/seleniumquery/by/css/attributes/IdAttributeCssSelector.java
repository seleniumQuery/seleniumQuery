package io.github.seleniumquery.by.css.attributes;

import io.github.seleniumquery.by.SelectorUtils;
import io.github.seleniumquery.by.xpath.XPathComponent;
import io.github.seleniumquery.by.xpath.XPathComponentFactory;
import io.github.seleniumquery.by.css.CssConditionalSelector;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.AttributeCondition;
import org.w3c.css.sac.Selector;

/**
 * #id
 *
 * @author acdcjunior
 *
 * @since 0.9.0
 */
public class IdAttributeCssSelector implements CssConditionalSelector<AttributeCondition> {

	private static final String ID_ATTRIBUTE = "id";

	/**
	 * see {@link org.w3c.css.sac.Condition#SAC_ID_CONDITION}
	 * 
	 * This condition checks an id attribute. Example:
	 * 
	 * #myId
	 * 
	 * CASE SENSITIVE!
	 */
	@Override
	public boolean isCondition(WebDriver driver, WebElement element, Map<String, String> stringMap, Selector selectorUpToThisPoint, AttributeCondition attributeCondition) {
		if (!SelectorUtils.hasAttribute(element, ID_ATTRIBUTE)) {
			return false;
		}
		String wantedId = attributeCondition.getValue();
		String actualId = element.getAttribute(ID_ATTRIBUTE);
		return actualId.equals(wantedId);
	}

	@Override
	public XPathComponent conditionToXPath(Map<String, String> stringMap, Selector simpleSelector, AttributeCondition attributeCondition) {
		String wantedId = attributeCondition.getValue();
		return XPathComponentFactory.createNoFilter("[@id = '" + wantedId + "']");
	}

}
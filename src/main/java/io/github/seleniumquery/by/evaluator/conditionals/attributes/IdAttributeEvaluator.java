package io.github.seleniumquery.by.evaluator.conditionals.attributes;

import io.github.seleniumquery.by.evaluator.CSSCondition;
import io.github.seleniumquery.by.evaluator.SelectorUtils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.AttributeCondition;
import org.w3c.css.sac.Selector;

public class IdAttributeEvaluator implements CSSCondition<AttributeCondition> {

	private static final String ID_ATTRIBUTE = "id";
	private static final IdAttributeEvaluator instance = new IdAttributeEvaluator();

	public static IdAttributeEvaluator getInstance() {
		return instance;
	}
	
	private IdAttributeEvaluator() { }

	/**
	 * @see {@link org.w3c.css.sac.Condition#SAC_ID_CONDITION}
	 * 
	 * This condition checks an id attribute. Example:
	 * 
	 * #myId
	 * 
	 * CASE SENSITIVE!
	 */
	@Override
	public boolean is(WebDriver driver, WebElement element, Selector selectorUpToThisPoint, AttributeCondition attributeCondition) {
		if (!SelectorUtils.hasAttribute(element, ID_ATTRIBUTE)) {
			return false;
		}
		String wantedId = attributeCondition.getValue();
		String actualId = element.getAttribute(ID_ATTRIBUTE);
		return actualId.equals(wantedId);
	}

}
package io.github.seleniumquery.by.evaluator.conditionals.attributes;

import static org.apache.commons.lang3.StringUtils.endsWith;
import io.github.seleniumquery.by.evaluator.CSSCondition;
import io.github.seleniumquery.by.selector.CSSFilter;
import io.github.seleniumquery.by.selector.CompiledSelector;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.AttributeCondition;
import org.w3c.css.sac.Selector;

public class EndsWithAttributeEvaluator implements CSSCondition<AttributeCondition> {

	private static final EndsWithAttributeEvaluator instance = new EndsWithAttributeEvaluator();

	public static EndsWithAttributeEvaluator getInstance() {
		return instance;
	}
	
	private EndsWithAttributeEvaluator() { }

	/**
	 * Currently it is (mistakenly?) mapped to the type {@link org.w3c.css.sac.Condition#SAC_ATTRIBUTE_CONDITION}.
	 * The factory then inspects the actual type and redirects here.
	 * 
	 * This selector is:
	 * [attribute$=stringToEnd]
	 * 
	 * CASE SENSITIVE! http://api.jquery.com/attribute-ends-with-selector/
	 */
	@Override
	public boolean is(WebDriver driver, WebElement element, Selector selectorUpToThisPoint, AttributeCondition attributeCondition) {
		String wantedValue = attributeCondition.getValue();
		String actualValue = element.getAttribute(attributeCondition.getLocalName());
		return endsWith(actualValue, wantedValue);
	}

	@Override
	public CompiledSelector compile(WebDriver driver, Selector simpleSelector, AttributeCondition condition) {
		return new CompiledSelector(condition.toString(), CSSFilter.FILTER_NOTHING);
	}

}
package io.github.seleniumquery.by.evaluator.conditionals.attributes;

import io.github.seleniumquery.by.evaluator.CSSCondition;
import io.github.seleniumquery.by.evaluator.SelectorUtils;
import io.github.seleniumquery.by.selector.CompiledSelector;

import java.util.Arrays;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.AttributeCondition;
import org.w3c.css.sac.Selector;

public class ClassAttributeEvaluator implements CSSCondition<AttributeCondition> {

	private static final String CLASS_ATTRIBUTE = "class";
	private static final ClassAttributeEvaluator instance = new ClassAttributeEvaluator();

	public static ClassAttributeEvaluator getInstance() {
		return instance;
	}
	
	private ClassAttributeEvaluator() { }

	/**
	 * @see {@link org.w3c.css.sac.Condition#SAC_CLASS_CONDITION}
	 *
	 * This condition checks for a specified class. Example:
	 * .example
	 */
	@Override
	public boolean is(WebDriver driver, WebElement element, Selector selectorUpToThisPoint, AttributeCondition attributeCondition) {
		if (!SelectorUtils.hasAttribute(element, CLASS_ATTRIBUTE)) {
			return false;
		}
		String wantedClassName = attributeCondition.getValue();
		String classAttributeValue = element.getAttribute(CLASS_ATTRIBUTE);
		return Arrays.asList(classAttributeValue.split("\\s+")).contains(wantedClassName);
	}

	@Override
	public CompiledSelector compile(WebDriver driver, Selector simpleSelector, AttributeCondition attributeCondition) {
		String wantedClassName = attributeCondition.getValue();
		// nothing to do, everyone supports filtering by class
		return CompiledSelector.createNoFilterSelector("."+wantedClassName);
	}
	
}
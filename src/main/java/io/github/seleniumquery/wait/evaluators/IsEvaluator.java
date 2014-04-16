package io.github.seleniumquery.wait.evaluators;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import io.github.seleniumquery.functions.IsFunction;

public class IsEvaluator implements Evaluator<String> {

	public static IsEvaluator IS_EVALUATOR = new IsEvaluator();

	private IsEvaluator() {	}

	@Override
	public boolean evaluate(WebDriver driver, List<WebElement> elements, String selector) {
		return IsFunction.is(driver, elements, selector);
	}

	@Override
	public String stringFor(String selector) {
		return "is(\"" + selector + "\")";
	}

}
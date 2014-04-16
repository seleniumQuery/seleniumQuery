package io.github.seleniumquery.wait.evaluators;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import io.github.seleniumquery.wait.getters.Getter;

public class EqualsEvaluator<T> implements Evaluator<T> {

	private Getter<T> getter;

	public EqualsEvaluator(Getter<T> getter) {
		this.getter = getter;
	}

	@Override
	public boolean evaluate(WebDriver driver, List<WebElement> elements, T valueToEqual) {
		return getter.get(driver, elements).equals(valueToEqual);
	}

	@Override
	public String stringFor(T valueToEqual) {
		return "isEqualTo(\"" + valueToEqual + "\")";
	}

}
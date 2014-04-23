package io.github.seleniumquery.wait.evaluators;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import io.github.seleniumquery.wait.getters.Getter;

public class ContainsEvaluator implements Evaluator<String> {

	private Getter<?> getter;

	public ContainsEvaluator(Getter<?> getter) {
		this.getter = getter;
	}

	@Override
	public boolean evaluate(WebDriver driver, List<WebElement> elements, String valueToEqual) {
		Object propertyGot = getter.get(driver, elements);
		return propertyGot != null && propertyGot.toString().contains(valueToEqual);
	}

	@Override
	public String stringFor(String valueToEqual) {
		return "contains(\"" + valueToEqual + "\")";
	}

}
package io.github.seleniumquery.wait.evaluators;

import io.github.seleniumquery.wait.getters.Getter;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EqualsEvaluator<T> implements Evaluator<T> {
	
	private static final Log LOGGER = LogFactory.getLog(EqualsEvaluator.class);

	private Getter<T> getter;

	public EqualsEvaluator(Getter<T> getter) {
		this.getter = getter;
	}

	@Override
	public boolean evaluate(WebDriver driver, List<WebElement> elements, T valueToEqual) {
		LOGGER.debug("Evaluating isEqualTo()...");
		final T gotValue = getter.get(driver, elements);
		LOGGER.debug("Evaluating isEqualTo()... got "+getter+": \""+gotValue+"\". Wanted: \""+valueToEqual+"\".");
		return gotValue.equals(valueToEqual);
	}

	@Override
	public String stringFor(T valueToEqual) {
		return getter + " isEqualTo(\"" + valueToEqual + "\")";
	}

}
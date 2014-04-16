package io.github.seleniumquery.wait.evaluators;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import io.github.seleniumquery.wait.getters.Getter;

public class LessThanEvaluator implements Evaluator<Number> {

	private static final DecimalFormat DECIMAL_FORMAT; 
	static {
		DECIMAL_FORMAT = (DecimalFormat) NumberFormat.getNumberInstance();
		DECIMAL_FORMAT.setParseBigDecimal(true);
	}

	private Getter<?> getter;

	public LessThanEvaluator(Getter<?> getter) {
		this.getter = getter;
	}

	@Override
	public boolean evaluate(WebDriver driver, List<WebElement> elements, Number valueToCompare) {
		BigDecimal elementNumber = null, numberToCompare = null;
		Object elementValue = getter.get(driver, elements);
		try {
			elementNumber = (BigDecimal) DECIMAL_FORMAT.parse(elementValue.toString());
		} catch (ParseException e) {
			handleException(valueToCompare, e);
		}
		try {
			numberToCompare = (BigDecimal) DECIMAL_FORMAT.parse(valueToCompare.toString());
		} catch (ParseException e) {
			handleException(valueToCompare, e);
		}
		return elementNumber.compareTo(numberToCompare) < 0;
	}

	private void handleException(Number valueToCompare, ParseException e) {
		throw new RuntimeException("Error parsing "+valueToCompare+" into a number. The conversion function uses " +
				"the default Locale. If your number uses a diferent format, try changing the default Locale " +
				"through \"Locale.setDefaultLocale(Locale.<SOME_OTHER_LOCALE>);\".", e);
	}

	@Override
	public String stringFor(Number valueToCompare) {
		return "isGreaterThan(\"" + valueToCompare + "\")";
	}

}
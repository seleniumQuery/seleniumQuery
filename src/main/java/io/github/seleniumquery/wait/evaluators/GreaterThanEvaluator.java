/*
 * Copyright (c) 2016 seleniumQuery authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.seleniumquery.wait.evaluators;

import io.github.seleniumquery.wait.getters.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;

import static java.lang.String.format;

public class GreaterThanEvaluator implements Evaluator<Number> {

	private static final DecimalFormat DECIMAL_FORMAT; 
	static {
		DECIMAL_FORMAT = (DecimalFormat) NumberFormat.getNumberInstance();
		DECIMAL_FORMAT.setParseBigDecimal(true);
	}

	private Getter<?> getter;

	public GreaterThanEvaluator(Getter<?> getter) {
		this.getter = getter;
	}

	@Override
	public boolean evaluate(WebDriver driver, List<WebElement> elements, Number valueToCompare) {
        BigDecimal numberToCompare = parseNumber(valueToCompare);

		Object elementValue = getter.get(driver, elements);
        BigDecimal elementValueAsNumber = parseNumber(elementValue);

        return elementValueAsNumber.compareTo(numberToCompare) > 0;
	}

    private BigDecimal parseNumber(Object elementValue) {
        try {
            return (BigDecimal) DECIMAL_FORMAT.parse(elementValue.toString());
        } catch (ParseException e) {
            throw handleException(elementValue, e);
        }
    }

    private IllegalArgumentException handleException(Object failedParsingObject, ParseException e) {
		return new IllegalArgumentException(format(
				"Error parsing \"%s\" into a number. " +
                "The conversion function uses the default Locale. " +
                "If your number uses a diferent format, try changing the default Locale through \"Locale.setDefaultLocale(Locale.<SOME_OTHER_LOCALE>);\".",
                failedParsingObject), e);
	}

	@Override
	public String stringFor(Number valueToCompare) {
		return String.format("%s isGreaterThan(\"%s\")", getter, valueToCompare);
	}

}
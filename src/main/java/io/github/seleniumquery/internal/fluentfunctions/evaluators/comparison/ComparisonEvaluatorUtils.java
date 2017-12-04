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

package io.github.seleniumquery.internal.fluentfunctions.evaluators.comparison;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

import static java.lang.String.format;

/**
 * Some utility functions used by the comparison evaluators.
 *
 * @author acdcjunior
 * @since 0.13.0
 */
class ComparisonEvaluatorUtils {

    private static final DecimalFormat DECIMAL_FORMAT;
    static {
        DECIMAL_FORMAT = (DecimalFormat) NumberFormat.getNumberInstance();
        DECIMAL_FORMAT.setParseBigDecimal(true);
    }

    private ComparisonEvaluatorUtils() {}

    static BigDecimal parseNumber(Object elementValue) {
        String elementValueAsString = elementValue.toString();
        if (elementValueAsString.isEmpty()) {
            throw new IllegalArgumentException("Element's value is an empty string.");
        }
        try {
            return (BigDecimal) DECIMAL_FORMAT.parse(elementValueAsString);
        } catch (ParseException e) {
            throw handleException(elementValue, e);
        }
    }

    private static IllegalArgumentException handleException(Object failedParsingObject, ParseException e) {
		return new IllegalArgumentException(format(
				"Error parsing \"%s\" into a number. " +
                "The conversion function uses the default Locale. " +
                "If your number uses a diferent format, try changing the default Locale through \"Locale.setDefaultLocale(Locale.<SOME_OTHER_LOCALE>);\" before executing the comparison.",
                failedParsingObject), e);
	}

}

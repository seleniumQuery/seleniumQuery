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

package io.github.seleniumquery.wait.evaluators.comparison;

import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ComparisonEvaluatorUtilsTest {

    @Test
    public void zeroStringIsParsedIntoBigDecimalZERO() {
        BigDecimal bigDecimal = ComparisonEvaluatorUtils.parseNumber("0");
        assertThat(bigDecimal, is(BigDecimal.ZERO));
    }

    @Test(expected = IllegalArgumentException.class)
    public void unparseableNumberThrowsException() {
        ComparisonEvaluatorUtils.parseNumber("z100");
    }

    @Test(expected = IllegalArgumentException.class)
    public void emptyStringThrowsException() {
        ComparisonEvaluatorUtils.parseNumber("");
    }

}
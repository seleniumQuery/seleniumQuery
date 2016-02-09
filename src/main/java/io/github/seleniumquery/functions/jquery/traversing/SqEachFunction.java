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

package io.github.seleniumquery.functions.jquery.traversing;

import io.github.seleniumquery.SeleniumQueryObject;
import org.apache.commons.lang3.Validate;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * $().each(EachFunction);
 *
 * @author acdcjunior
 * @since 0.13.0
 */
public class SqEachFunction {

    public SeleniumQueryObject each(SeleniumQueryObject targetSQO, SeleniumQueryObject.EachFunction eachFunction) {
        Validate.notNull(eachFunction, "$().each(function): function argument must not be null.");
        List<WebElement> webElements = targetSQO.get();
        applyEachFunctionToAllElements(eachFunction, webElements);
        return targetSQO;
    }

    private void applyEachFunctionToAllElements(SeleniumQueryObject.EachFunction eachFunction, List<WebElement> webElements) {
        for (int i = 0; i < webElements.size(); i++) {
            boolean shouldContinue = eachFunction.apply(i, webElements.get(i));
            if (!shouldContinue) {
                break;
            }
        }
    }

}

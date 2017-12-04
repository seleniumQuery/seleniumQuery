/*
 * Copyright (c) 2017 seleniumQuery authors
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

import io.github.seleniumquery.wait.FluentBehaviorModifier;
import io.github.seleniumquery.wait.getters.Getter;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ContainsIgnoreCaseEvaluator implements Evaluator<String> {
    private Getter<?> getter;

    public ContainsIgnoreCaseEvaluator(Getter<?> getter) {
        this.getter = getter;
    }

    @Override
    public boolean evaluate(WebDriver driver, List<WebElement> elements, String valueToEqual) {
        Object propertyGot = getter.get(driver, elements);

        return propertyGot != null && StringUtils.containsIgnoreCase(propertyGot.toString(), valueToEqual);
    }

    @Override
    public String stringFor(String valueToEqual, FluentBehaviorModifier fluentBehaviorModifier) {
        return getter.toString() + fluentBehaviorModifier + ".containsIgnoreCase(\"" + valueToEqual + "\")";
    }

}

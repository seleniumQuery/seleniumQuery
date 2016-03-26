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

package io.github.seleniumquery.wait;

import com.google.common.base.Function;
import io.github.seleniumquery.wait.evaluators.Evaluator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static io.github.seleniumquery.wait.WaitingBehaviorModifier.USUAL_BEHAVIOR;

/**
 * @author acdcjunior
 * @since 0.9.0
 */
class FluentSqWaitFunction<T> implements Function<By, List<WebElement>> {

    private final WebDriver driver;
    private final T value;
    private final Evaluator<T> evaluator;
    private final By by;
    private final WaitingBehaviorModifier waitingBehaviorModifier;

    FluentSqWaitFunction(WebDriver driver, T value, Evaluator<T> evaluator, By by, WaitingBehaviorModifier waitingBehaviorModifier) {
        this.driver = driver;
        this.value = value;
        this.evaluator = evaluator;
        this.by = by;
        this.waitingBehaviorModifier = waitingBehaviorModifier;
    }

    @Override
    public List<WebElement> apply(By selector) {
        List<WebElement> elements = driver.findElements(by);
        final boolean evaluate = evaluator.evaluate(driver, elements, value);
        if (waitingBehaviorModifier == USUAL_BEHAVIOR) {
            if (!evaluate) {
                return null;
            }
        } else {
            if (evaluate) {
                return null;
            }
        }
        return elements;
    }

}

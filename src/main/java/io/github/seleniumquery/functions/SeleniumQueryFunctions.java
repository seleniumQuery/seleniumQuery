/*
 * Copyright (c) 2015 seleniumQuery authors
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

package io.github.seleniumquery.functions;

import com.google.common.base.Predicate;
import io.github.seleniumquery.SeleniumQueryObject;
import io.github.seleniumquery.functions.jquery.attributes.PropFunction;
import io.github.seleniumquery.functions.jquery.forms.ValFunction;
import io.github.seleniumquery.functions.jquery.traversing.filtering.FilterFunction;
import org.openqa.selenium.WebElement;

public class SeleniumQueryFunctions {

    public <T> T propRead(SeleniumQueryObject seleniumQueryObject, String propertyName) {
        return PropFunction.prop(seleniumQueryObject, propertyName);
    }

    public SeleniumQueryObject propWrite(SeleniumQueryObject seleniumQueryObject, String propertyName, Object value) {
        return PropFunction.prop(seleniumQueryObject, propertyName, value);
    }

    public String valRead(SeleniumQueryObject seleniumQueryObject) {
        return ValFunction.val(seleniumQueryObject.get());
    }

    public SeleniumQueryObject valWrite(SeleniumQueryObject seleniumQueryObject, String value) {
        return ValFunction.val(seleniumQueryObject, seleniumQueryObject.get(), value);
    }

    public SeleniumQueryObject valWrite(SeleniumQueryObject seleniumQueryObject, Number value) {
        return ValFunction.val(seleniumQueryObject, seleniumQueryObject.get(), value);
    }

    public SeleniumQueryObject filterFunction(SeleniumQueryObject seleniumQueryObject, Predicate<WebElement> filterFunction) {
        return new FilterFunction().filter(seleniumQueryObject, filterFunction);
    }

}
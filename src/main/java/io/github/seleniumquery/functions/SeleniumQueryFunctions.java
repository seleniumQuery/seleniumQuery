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

package io.github.seleniumquery.functions;

import com.google.common.base.Predicate;
import io.github.seleniumquery.SeleniumQueryObject;
import io.github.seleniumquery.functions.jquery.attributes.AttrFunction;
import io.github.seleniumquery.functions.jquery.attributes.HasClassFunction;
import io.github.seleniumquery.functions.jquery.attributes.PropFunction;
import io.github.seleniumquery.functions.jquery.attributes.RemoveAttrFunction;
import io.github.seleniumquery.functions.jquery.events.ClickFunction;
import io.github.seleniumquery.functions.jquery.forms.FocusFunction;
import io.github.seleniumquery.functions.jquery.forms.SubmitFunction;
import io.github.seleniumquery.functions.jquery.forms.ValFunction;
import io.github.seleniumquery.functions.jquery.manipulation.HtmlFunction;
import io.github.seleniumquery.functions.jquery.manipulation.TextFunction;
import io.github.seleniumquery.functions.jquery.miscellaneous.GetFunction;
import io.github.seleniumquery.functions.jquery.miscellaneous.ToArrayFunction;
import io.github.seleniumquery.functions.jquery.traversing.filtering.*;
import io.github.seleniumquery.functions.jquery.traversing.filtering.filterfunction.FilterPredicateFunction;
import io.github.seleniumquery.functions.jquery.traversing.filtering.filterfunction.FilterSelectorFunction;
import io.github.seleniumquery.functions.jquery.traversing.treetraversal.ChildrenFunction;
import io.github.seleniumquery.functions.jquery.traversing.treetraversal.ClosestFunction;
import io.github.seleniumquery.functions.jquery.traversing.treetraversal.FindFunction;
import io.github.seleniumquery.functions.jquery.traversing.treetraversal.ParentFunction;
import org.openqa.selenium.WebElement;

public class SeleniumQueryFunctions {

    public <T> T propertyRead(SeleniumQueryObject seleniumQueryObject, String propertyName) {
        return PropFunction.prop(seleniumQueryObject, propertyName);
    }

    public SeleniumQueryObject propertyWrite(SeleniumQueryObject seleniumQueryObject, String propertyName, Object value) {
        return PropFunction.prop(seleniumQueryObject, propertyName, value);
    }

    public String valueRead(SeleniumQueryObject seleniumQueryObject) {
        return ValFunction.val(seleniumQueryObject.get());
    }

    public SeleniumQueryObject valueWrite(SeleniumQueryObject seleniumQueryObject, String value) {
        return ValFunction.val(seleniumQueryObject, seleniumQueryObject.get(), value);
    }

    public SeleniumQueryObject valueWrite(SeleniumQueryObject seleniumQueryObject, Number value) {
        return ValFunction.val(seleniumQueryObject, seleniumQueryObject.get(), value);
    }

    public SeleniumQueryObject filterPredicate(SeleniumQueryObject seleniumQueryObject, Predicate<WebElement> filterFunction) {
        return new FilterPredicateFunction().filter(seleniumQueryObject, filterFunction);
    }

    public SeleniumQueryObject filterSelector(SeleniumQueryObject seleniumQueryObject, String selector) {
        return new FilterSelectorFunction().filter(seleniumQueryObject, selector);
    }

    public boolean isSelector(SeleniumQueryObject seleniumQueryObject, String selector) {
        return IsFunction.is(seleniumQueryObject, selector);
    }

    public SeleniumQueryObject notSelector(SeleniumQueryObject seleniumQueryObject, String selector) {
        return NotFunction.not(seleniumQueryObject, selector);
    }

    public SeleniumQueryObject first(SeleniumQueryObject seleniumQueryObject) {
        return FirstFunction.first(seleniumQueryObject);
    }

    public SeleniumQueryObject last(SeleniumQueryObject seleniumQueryObject) {
        return LastFunction.last(seleniumQueryObject);
    }

    public SeleniumQueryObject eqIndex(SeleniumQueryObject seleniumQueryObject, int index) {
        return EqFunction.eq(seleniumQueryObject, index);
    }

    public String text(SeleniumQueryObject seleniumQueryObject) {
        return TextFunction.text(seleniumQueryObject);
    }

    public SeleniumQueryObject click(SeleniumQueryObject seleniumQueryObject) {
        return ClickFunction.click(seleniumQueryObject);
    }

    public SeleniumQueryObject findSelector(SeleniumQueryObject seleniumQueryObject, String selector) {
        return FindFunction.find(seleniumQueryObject, selector);
    }

    public String attributeRead(SeleniumQueryObject seleniumQueryObject, String attributeName) {
        return AttrFunction.attr(seleniumQueryObject, attributeName);
    }

    public SeleniumQueryObject attributeWrite(SeleniumQueryObject seleniumQueryObject, String attributeName, Object value) {
        return AttrFunction.attr(seleniumQueryObject, attributeName, value);
    }

    public WebElement getIndex(SeleniumQueryObject seleniumQueryObject, int index) {
        return GetFunction.get(seleniumQueryObject, index);
    }

    public SeleniumQueryObject removeAttribute(SeleniumQueryObject seleniumQueryObject, String attributeNames) {
        return RemoveAttrFunction.removeAttr(seleniumQueryObject, attributeNames);
    }

    public String html(SeleniumQueryObject seleniumQueryObject) {
        return HtmlFunction.html(seleniumQueryObject);
    }

    public boolean hasClass(SeleniumQueryObject seleniumQueryObject, String className) {
        return HasClassFunction.hasClass(seleniumQueryObject, className);
    }

    public WebElement[] toArray(SeleniumQueryObject seleniumQueryObject) {
        return ToArrayFunction.toArray(seleniumQueryObject);
    }

    public SeleniumQueryObject closestSelector(SeleniumQueryObject seleniumQueryObject, String selector) {
        return ClosestFunction.closest(seleniumQueryObject, selector);
    }

    public SeleniumQueryObject focus(SeleniumQueryObject seleniumQueryObject) {
        return FocusFunction.focus(seleniumQueryObject);
    }

    public SeleniumQueryObject children(SeleniumQueryObject seleniumQueryObject) {
        return ChildrenFunction.children(seleniumQueryObject);
    }

    public SeleniumQueryObject childrenSelector(SeleniumQueryObject seleniumQueryObject, String selector) {
        return ChildrenFunction.children(seleniumQueryObject, selector);
    }

    public SeleniumQueryObject parent(SeleniumQueryObject seleniumQueryObject) {
        return ParentFunction.parent(seleniumQueryObject);
    }

    public SeleniumQueryObject parentSelector(SeleniumQueryObject seleniumQueryObject, String selector) {
        return ParentFunction.parent(seleniumQueryObject, selector);
    }

    public SeleniumQueryObject submit(SeleniumQueryObject seleniumQueryObject) {
        SubmitFunction.submit(seleniumQueryObject);
        return seleniumQueryObject;
    }

}
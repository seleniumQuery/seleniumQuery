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

package testinfrastructure.testdouble.io.github.seleniumquery.functions;

import io.github.seleniumquery.SeleniumQueryObject;
import io.github.seleniumquery.functions.SeleniumQueryFunctions;
import org.openqa.selenium.WebElement;
import testinfrastructure.testdouble.PseudoTestDoubleException;

import java.util.function.Predicate;

public class SeleniumQueryFunctionsMock extends SeleniumQueryFunctions {

    public MethodMockConfiguration<String> propertyReadMethod;
    @SuppressWarnings("unchecked") @Override public <T> T propertyRead(SeleniumQueryObject s, String p) { return (T) propertyReadMethod.executeMethodMock(s, p); }

    public MethodMockConfiguration<SeleniumQueryObject> propertyWriteMethod;
    @Override public SeleniumQueryObject propertyWrite(SeleniumQueryObject s, String p, Object v) { return propertyWriteMethod.executeMethodMock(s, p, v); }

    public MethodMockConfiguration<String> valueReadMethod;
    @Override public String valueRead(SeleniumQueryObject s) { return valueReadMethod.executeMethodMock(s); }

    public MethodMockConfiguration<SeleniumQueryObject> valueWriteStringMethod;
    @Override public SeleniumQueryObject valueWrite(SeleniumQueryObject s, String v) { return valueWriteStringMethod.executeMethodMock(s, v); }

    public MethodMockConfiguration<SeleniumQueryObject> valueWriteNumberMethod;
    @Override public SeleniumQueryObject valueWrite(SeleniumQueryObject s, Number n) { return valueWriteNumberMethod.executeMethodMock(s, n); }

    public MethodMockConfiguration<SeleniumQueryObject> filterPredicateMethod;
    @Override public SeleniumQueryObject filterPredicate(SeleniumQueryObject s, Predicate<WebElement> f) { return filterPredicateMethod.executeMethodMock(s, f); }

    public MethodMockConfiguration<SeleniumQueryObject> filterSelectorMethod;
    @Override public SeleniumQueryObject filterSelector(SeleniumQueryObject s, String e) { return filterSelectorMethod.executeMethodMock(s, e); }

    public MethodMockConfiguration<Boolean> isSelectorMethod;
    @Override public boolean isSelector(SeleniumQueryObject s, String e) { return isSelectorMethod.executeMethodMock(s, e); }

    public MethodMockConfiguration<SeleniumQueryObject> eachMethod;
    @Override public SeleniumQueryObject each(SeleniumQueryObject s, SeleniumQueryObject.EachFunction f) { return eachMethod.executeMethodMock(s, f); }

    public MethodMockConfiguration<SeleniumQueryObject> clickMethod;
    @Override public SeleniumQueryObject click(SeleniumQueryObject s) { return clickMethod.executeMethodMock(s); }

    public MethodMockConfiguration<SeleniumQueryObject> dblclickMethod;
    @Override public SeleniumQueryObject dblclick(SeleniumQueryObject s) { return dblclickMethod.executeMethodMock(s); }

    @Override public SeleniumQueryObject notSelector(SeleniumQueryObject seleniumQueryObject, String selector) { throw new PseudoTestDoubleException(); }
    @Override public SeleniumQueryObject first(SeleniumQueryObject seleniumQueryObject) { throw new PseudoTestDoubleException(); }
    @Override public SeleniumQueryObject last(SeleniumQueryObject seleniumQueryObject) { throw new PseudoTestDoubleException(); }
    @Override public SeleniumQueryObject eqIndex(SeleniumQueryObject seleniumQueryObject, int index) { throw new PseudoTestDoubleException(); }
    @Override public String text(SeleniumQueryObject seleniumQueryObject) { throw new PseudoTestDoubleException(); }
    @Override public SeleniumQueryObject findSelector(SeleniumQueryObject seleniumQueryObject, String selector) { throw new PseudoTestDoubleException(); }
    @Override public String attributeRead(SeleniumQueryObject seleniumQueryObject, String attributeName) { throw new PseudoTestDoubleException(); }
    @Override public SeleniumQueryObject attributeWrite(SeleniumQueryObject seleniumQueryObject, String attributeName, Object value) { throw new PseudoTestDoubleException(); }
    @Override public WebElement getIndex(SeleniumQueryObject seleniumQueryObject, int index) { throw new PseudoTestDoubleException(); }
    @Override public SeleniumQueryObject removeAttribute(SeleniumQueryObject seleniumQueryObject, String attributeNames) { throw new PseudoTestDoubleException(); }
    @Override public String html(SeleniumQueryObject seleniumQueryObject) { throw new PseudoTestDoubleException(); }
    @Override public boolean hasClass(SeleniumQueryObject seleniumQueryObject, String className) { throw new PseudoTestDoubleException(); }
    @Override public WebElement[] toArray(SeleniumQueryObject seleniumQueryObject) { throw new PseudoTestDoubleException(); }
    @Override public SeleniumQueryObject closestSelector(SeleniumQueryObject seleniumQueryObject, String selector) { throw new PseudoTestDoubleException(); }
    @Override public SeleniumQueryObject focus(SeleniumQueryObject seleniumQueryObject) { throw new PseudoTestDoubleException(); }
    @Override public SeleniumQueryObject children(SeleniumQueryObject seleniumQueryObject) { throw new PseudoTestDoubleException(); }
    @Override public SeleniumQueryObject childrenSelector(SeleniumQueryObject seleniumQueryObject, String selector) { throw new PseudoTestDoubleException(); }
    @Override public SeleniumQueryObject parent(SeleniumQueryObject seleniumQueryObject) { throw new PseudoTestDoubleException(); }
    @Override public SeleniumQueryObject parentSelector(SeleniumQueryObject seleniumQueryObject, String selector) { throw new PseudoTestDoubleException(); }
    @Override public SeleniumQueryObject submit(SeleniumQueryObject seleniumQueryObject) { throw new PseudoTestDoubleException(); }

}

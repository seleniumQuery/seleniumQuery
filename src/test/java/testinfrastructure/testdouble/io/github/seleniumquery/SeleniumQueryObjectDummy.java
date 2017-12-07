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

package testinfrastructure.testdouble.io.github.seleniumquery;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.google.common.base.Predicate;
import io.github.seleniumquery.SeleniumQueryFluentFunction;
import io.github.seleniumquery.SeleniumQueryObject;
import io.github.seleniumquery.functions.SeleniumQueryFunctions;
import io.github.seleniumquery.functions.as.SeleniumQueryPlugin;
import io.github.seleniumquery.functions.as.StandardPlugins;
import testinfrastructure.testdouble.PseudoTestDoubleException;

/**
 * Creates a "smart" dummy {@link SeleniumQueryObject}. It's goal is to be a mere placeholder in a test
 * fixture. It is "smart" because any method call to it will throw an exception (which indicates the dummy
 * is actually not a simple placeholder - since it is being used by the SUT when it shouldn't).
 */
public class SeleniumQueryObjectDummy implements SeleniumQueryObject {

    public static SeleniumQueryObject createSeleniumQueryObjectDummy() {
        return new SeleniumQueryObjectDummy();
    }

    private SeleniumQueryObjectDummy() { }

    @Override public SeleniumQueryFluentFunction waitUntil() { throw new PseudoTestDoubleException(); }
    @Override public SeleniumQueryFluentFunction assertThat() { throw new PseudoTestDoubleException(); }
    @Override public SeleniumQueryFluentFunction waitUntil(long waitUntilTimeout) { throw new PseudoTestDoubleException(); }
    @Override public SeleniumQueryFluentFunction waitUntil(long waitUntilTimeout, long waitUntilPollingInterval) { throw new PseudoTestDoubleException(); }
    @Override public StandardPlugins as() { throw new PseudoTestDoubleException(); }
    @Override public <PLUGIN> PLUGIN as(SeleniumQueryPlugin<PLUGIN> pluginFunction) { throw new PseudoTestDoubleException(); }
    @Override public Iterator<WebElement> iterator() { throw new PseudoTestDoubleException(); }
    @Override public WebDriver getWebDriver() { throw new PseudoTestDoubleException(); }
    @Override public By getBy() { throw new PseudoTestDoubleException(); }
    @Override public SeleniumQueryFunctions getSeleniumQueryFunctions() { throw new PseudoTestDoubleException(); }
    @Override public int size() { throw new PseudoTestDoubleException(); }
    @Override public SeleniumQueryObject not(String selector) { throw new PseudoTestDoubleException(); }
    @Override public SeleniumQueryObject first() { throw new PseudoTestDoubleException(); }
    @Override public SeleniumQueryObject last() { throw new PseudoTestDoubleException(); }
    @Override public SeleniumQueryObject eq(int index) { throw new PseudoTestDoubleException(); }
    @Override public String text() { throw new PseudoTestDoubleException(); }
    @Override public SeleniumQueryObject click() { throw new PseudoTestDoubleException(); }
    @Override public SeleniumQueryObject waitViewClick() { throw new PseudoTestDoubleException(); }
    @Override public SeleniumQueryObject dblclick() { throw new PseudoTestDoubleException(); }
    @Override public SeleniumQueryObject val(String value) { throw new PseudoTestDoubleException(); }
    @Override public SeleniumQueryObject val(Number value) { throw new PseudoTestDoubleException(); }
    @Override public String val() { throw new PseudoTestDoubleException(); }
    @Override public SeleniumQueryObject end() { throw new PseudoTestDoubleException(); }
    @Override public SeleniumQueryObject find(String selector) { throw new PseudoTestDoubleException(); }
    @Override public String attr(String attributeName) { throw new PseudoTestDoubleException(); }
    @Override public SeleniumQueryObject attr(String attributeName, Object value) { throw new PseudoTestDoubleException(); }
    @Override public <T> T prop(String propertyName) { throw new PseudoTestDoubleException(); }
    @Override public SeleniumQueryObject prop(String propertyName, Object value) { throw new PseudoTestDoubleException(); }
    @Override public WebElement get(int index) { throw new PseudoTestDoubleException(); }
    @Override public List<WebElement> get() { throw new PseudoTestDoubleException(); }
    @Override public SeleniumQueryObject removeAttr(String attributeNames) { throw new PseudoTestDoubleException(); }
    @Override public String html() { throw new PseudoTestDoubleException(); }
    @Override public boolean is(String selector) { throw new PseudoTestDoubleException(); }
    @Override public boolean hasClass(String className) { throw new PseudoTestDoubleException(); }
    @Override public WebElement[] toArray() { throw new PseudoTestDoubleException(); }
    @Override public SeleniumQueryObject closest(String selector) { throw new PseudoTestDoubleException(); }
    @Override public SeleniumQueryObject focus() { throw new PseudoTestDoubleException(); }
    @Override public SeleniumQueryObject children() { throw new PseudoTestDoubleException(); }
    @Override public SeleniumQueryObject children(String selector) { throw new PseudoTestDoubleException(); }
    @Override public SeleniumQueryObject parent() { throw new PseudoTestDoubleException(); }
    @Override public SeleniumQueryObject parent(String selector) { throw new PseudoTestDoubleException(); }
    @Override public SeleniumQueryObject submit() { throw new PseudoTestDoubleException(); }
    @SuppressWarnings("deprecation") @Override public SeleniumQueryObject selectOptionByVisibleText(String text) { throw new PseudoTestDoubleException(); }
    @SuppressWarnings("deprecation") @Override public SeleniumQueryObject selectOptionByValue(String value) { throw new PseudoTestDoubleException(); }
    @Override public String toString() { throw new PseudoTestDoubleException(); }
    @Override public SeleniumQueryObject filter(Predicate<WebElement> filterFunction) { throw new PseudoTestDoubleException(); }
    @Override public SeleniumQueryObject filter(String selector) { throw new PseudoTestDoubleException(); }
    @Override public SeleniumQueryObject each(EachFunction function) { throw new PseudoTestDoubleException(); }
    @Override public Stream<WebElement> stream() { throw new PseudoTestDoubleException(); }
    @Override public SeleniumQueryObject refresh() { throw new PseudoTestDoubleException(); }

    @Override
    public <T> T eval(String script, Object... args) {
        return null;
    }

}

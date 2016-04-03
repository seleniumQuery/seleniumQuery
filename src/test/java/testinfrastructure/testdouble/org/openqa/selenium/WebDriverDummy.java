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

package testinfrastructure.testdouble.org.openqa.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.internal.FindsByCssSelector;
import testinfrastructure.testdouble.PseudoTestDoubleException;

import java.util.List;
import java.util.Set;

public class WebDriverDummy implements WebDriver, FindsByCssSelector, HasInputDevices {

    public static WebDriver createWebDriverDummy() {
        return new WebDriverDummy();
    }

    @Override public void get(String s) { throw new PseudoTestDoubleException(); }
    @Override public String getCurrentUrl() { throw new PseudoTestDoubleException(); }
    @Override public String getTitle() { throw new PseudoTestDoubleException(); }
    @Override public List<WebElement> findElements(By by) { throw new PseudoTestDoubleException(); }
    @Override public WebElement findElement(By by) { throw new PseudoTestDoubleException(); }
    @Override public String getPageSource() { throw new PseudoTestDoubleException(); }
    @Override public void close() { throw new PseudoTestDoubleException(); }
    @Override public void quit() { throw new PseudoTestDoubleException(); }
    @Override public Set<String> getWindowHandles() { throw new PseudoTestDoubleException(); }
    @Override public String getWindowHandle() { throw new PseudoTestDoubleException(); }
    @Override public WebDriver.TargetLocator switchTo() { throw new PseudoTestDoubleException(); }
    @Override public WebDriver.Navigation navigate() { throw new PseudoTestDoubleException(); }
    @Override public WebDriver.Options manage() { throw new PseudoTestDoubleException(); }
    @Override public WebElement findElementByCssSelector(String s) { throw new PseudoTestDoubleException(); }
    @Override public List<WebElement> findElementsByCssSelector(String s) { throw new PseudoTestDoubleException(); }
    @Override public Keyboard getKeyboard() { throw new PseudoTestDoubleException(); }
    @Override public Mouse getMouse() { throw new PseudoTestDoubleException(); }

}

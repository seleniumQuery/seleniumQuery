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

import org.openqa.selenium.*;

import java.util.List;

class WebElementStub implements WebElement {

    @Override public void click() { }
    @Override public void submit() { }
    @Override public void sendKeys(CharSequence... charSequences) { }
    @Override public void clear() { }
    @Override public String getTagName() { return null; }
    @Override public String getAttribute(String s) { return null; }
    @Override public boolean isSelected() { return false; }
    @Override public boolean isEnabled() { return false; }
    @Override public String getText() { return null; }
    @Override public List<WebElement> findElements(By by) { return null; }
    @Override public WebElement findElement(By by) { return null; }
    @Override public boolean isDisplayed() { return false; }
    @Override public Point getLocation() { return null; }
    @Override public Dimension getSize() { return null; }
    @Override public Rectangle getRect() { return null; }
    @Override public String getCssValue(String s) { return null; }
    @Override public <X> X getScreenshotAs(OutputType<X> outputType) throws WebDriverException { return null; }

}

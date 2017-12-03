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

package endtoend.browser.util;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;
import static testinfrastructure.EndToEndTestUtils.classNameToTestFileUrl;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class JsOnOffTestUtils {

    public static void assertJavaScriptIsOn(WebDriver driver) {
        driver.get(classNameToTestFileUrl(JsOnOffTestUtils.class));
        assertThat(driver.findElements(By.tagName("div")), hasSize(1 + 3));
    }

    public static void assertJavaScriptIsOff(WebDriver driver) {
        driver.get(classNameToTestFileUrl(JsOnOffTestUtils.class));
        assertThat(driver.findElements(By.tagName("div")), hasSize(1));
    }

}

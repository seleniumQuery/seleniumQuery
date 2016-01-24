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

package testinfrastructure.testdouble.io.github.seleniumquery.utils;

import io.github.seleniumquery.utils.DriverVersionUtils;
import org.openqa.selenium.WebDriver;

public class DriverVersionUtilsTestBuilder {

    private WebDriver webDriver;
    private String pseudoWithNativeSupport;
    private boolean isPhantomJSDriver = false;

    public static DriverVersionUtilsTestBuilder createDriverVersionUtils() {
        return new DriverVersionUtilsTestBuilder();
    }

    public DriverVersionUtilsTestBuilder withNativeSupportForPseudo(WebDriver driver, String pseudoWithNativeSupport) {
        this.webDriver = driver;
        this.pseudoWithNativeSupport = pseudoWithNativeSupport;
        return this;
    }

    public DriverVersionUtilsTestBuilder emulatingPhantomJS() {
        this.isPhantomJSDriver = true;
        return this;
    }

    public DriverVersionUtils build() {
        return new DriverVersionUtils() {
            @Override
            public boolean hasNativeSupportForPseudo(WebDriver d, String p) {
                return webDriver != null && webDriver.equals(d) && pseudoWithNativeSupport != null && pseudoWithNativeSupport.equals(p);
            }

            @Override
            public boolean isPhantomJSDriver(WebDriver driver) {
                return isPhantomJSDriver;
            }
            @Override
            public boolean isHtmlUnitDriver(WebDriver driver) {
                return false;
            }
        };
    }

}

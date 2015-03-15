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

package io.github.seleniumquery.browser.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Builds {@link WebDriver} instances for SeleniumQueryDriver.
 *
 * @author acdcjunior
 * @since 0.9.0
 */
public abstract class DriverBuilder<T extends DriverBuilder<T>> {

    protected DesiredCapabilities desiredCapabilities;

    @SuppressWarnings("unchecked")
    public T withCapabilities(DesiredCapabilities desiredCapabilities) {
        this.desiredCapabilities = desiredCapabilities;
        return (T) this;
    }

    protected DesiredCapabilities capabilities(DesiredCapabilities defaultDesiredCapabilities) {
        if (this.desiredCapabilities == null) {
            return defaultDesiredCapabilities;
        }
        return this.desiredCapabilities;
    }

    protected void overwriteCapabilityIfValueNotNull(DesiredCapabilities capabilities, String capabilityName, Object value) {
        if (value != null) {
            capabilities.setCapability(capabilityName, value);
        }
    }

    /**
     * <p>Builds a WebDriver instance based on the default configurations plus the settings
     * changed through other method calls.</p>
     *
     * <b>IMPORTANT: This method should <i>not</i> be public, as the end-user shouldn't be able
     * to call "build()" - that'd be confusing.</b>
     *
     * @return A WebDriver instance based on the previous configurations.
     */
    protected abstract WebDriver build();

}
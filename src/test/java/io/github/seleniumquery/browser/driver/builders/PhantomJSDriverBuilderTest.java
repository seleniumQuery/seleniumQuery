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

package io.github.seleniumquery.browser.driver.builders;

import org.junit.Test;

import java.io.File;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class PhantomJSDriverBuilderTest {

    private static final String EXECUTABLE_THAT_EXISTS_IN_CLASSPATH = "executable-that-exists-in-classpath";
    private static final String EXECUTABLE_THAT_DOESNT_EXIST_IN_CLASSPATH = "executable-that-DOESN'T-exist-in-classpath";

    private static final boolean IS_WINDOWS_OS = true;
    private static final boolean IS_LINUX_OS = false;

    @Test
    public void underWindows__propertyShouldBeSet__ifWindowsExecutableFoundInClasspath() {
        // given
        PhantomJSDriverBuilder driverBuilder = new PhantomJSDriverBuilder(IS_WINDOWS_OS, EXECUTABLE_THAT_EXISTS_IN_CLASSPATH, EXECUTABLE_THAT_DOESNT_EXIST_IN_CLASSPATH);
        // when
        String resolvedPath = driverBuilder.resolvePhantomJsExecutablePath();
        // then
        assertPhantomJSExecutablePropertyPointsToExecutableInClasspath(resolvedPath);
    }

    private void assertPhantomJSExecutablePropertyPointsToExecutableInClasspath(String resolvedPath) {
        assertThat(resolvedPath, endsWithIgnoringCase("/target/test-classes/"+EXECUTABLE_THAT_EXISTS_IN_CLASSPATH));
    }

    @Test
    public void underLinux__propertyShouldBeSet__ifWindowsExecutableFoundInClasspath() {
        // given
        PhantomJSDriverBuilder driverBuilder = new PhantomJSDriverBuilder(IS_LINUX_OS, EXECUTABLE_THAT_DOESNT_EXIST_IN_CLASSPATH, EXECUTABLE_THAT_EXISTS_IN_CLASSPATH);
        // when
        String resolvedPath = driverBuilder.resolvePhantomJsExecutablePath();
        // then
        assertPhantomJSExecutablePropertyPointsToExecutableInClasspath(resolvedPath);
    }

    @Test
    public void underWindows__propertyShouldNotBeSet__whenWindowsExecutableIsNotFound__evenIfItFindsLinuxExecutable() {
        // given
        PhantomJSDriverBuilder driverBuilder = new PhantomJSDriverBuilder(IS_WINDOWS_OS, EXECUTABLE_THAT_DOESNT_EXIST_IN_CLASSPATH, EXECUTABLE_THAT_EXISTS_IN_CLASSPATH);
        // when
        String resolvedPath = driverBuilder.resolvePhantomJsExecutablePath();
        // then
        assertThat(resolvedPath, is(nullValue()));
    }

    @Test
    public void underLinux__propertyShouldNotBeSet__whenLinuxExecutableIsNotFound__evenIfItFindsWindowsExecutable() {
        // given
        PhantomJSDriverBuilder driverBuilder = new PhantomJSDriverBuilder(IS_LINUX_OS, EXECUTABLE_THAT_EXISTS_IN_CLASSPATH, EXECUTABLE_THAT_DOESNT_EXIST_IN_CLASSPATH);
        // when
        String resolvedPath = driverBuilder.resolvePhantomJsExecutablePath();
        // then
        assertThat(resolvedPath, is(nullValue()));
    }

    @Test
    public void underWindows__propertyShouldBeSet__whenCustomPathIsProvided__andExecutableExistsThere() {
        verifyCustomPathIsSetUnderOs(IS_WINDOWS_OS);
    }

    @Test
    public void underLinux__propertyShouldBeSet__whenCustomPathIsProvided__andExecutableExistsThere() {
        verifyCustomPathIsSetUnderOs(IS_LINUX_OS);
    }

    private void verifyCustomPathIsSetUnderOs(boolean isWindowsOs) {
        // given
        PhantomJSDriverBuilder driverBuilder = new PhantomJSDriverBuilder(isWindowsOs, EXECUTABLE_THAT_DOESNT_EXIST_IN_CLASSPATH, EXECUTABLE_THAT_DOESNT_EXIST_IN_CLASSPATH);
        String customPathToPhantomJs = String.format("src%stest%sresources%s%s", File.separator, File.separator, File.separator, EXECUTABLE_THAT_EXISTS_IN_CLASSPATH);
        driverBuilder.withPathToPhantomJS(customPathToPhantomJs);
        // when
        String resolvedPath = driverBuilder.resolvePhantomJsExecutablePath();
        // then
        assertThat(resolvedPath, endsWithIgnoringCase(customPathToPhantomJs));
    }

}

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

package testinfrastructure.testutils;

import java.io.File;

import static org.junit.Assume.assumeTrue;

public class EnvironmentTestUtils {

    public static boolean isNotWindowsOS() {
        return !System.getProperty("os.name").toLowerCase().contains("win");
    }

    public static void onlyRunIfDriverTestExecutableExists(String driverTestExecutable) {
        assumeTrue("This test will only run if '"+driverTestExecutable+"' exists.", new File("src/test/resources/" + driverTestExecutable).exists());
    }

}
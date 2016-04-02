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

package testinfrastructure.testutils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;

import java.io.File;

import static org.junit.Assume.assumeTrue;

public class EnvironmentTestUtils {

    public static boolean isNotWindowsOS() {
        return !SystemUtils.IS_OS_WINDOWS;
    }

    public static void onlyRunIfDriverTestExecutableExists(String driverTestExecutable) {
        assumeTrue("This test will only run if '"+driverTestExecutable+"' exists.", new File("src/test/resources/" + driverTestExecutable).exists());
    }

    public static boolean isRunningAtContinuousIntegrationServer() {
        return "true".equalsIgnoreCase(System.getenv("CI"));
    }

    public static boolean isRunningAtWindowsContinuousIntegrationServer() {
        return "true".equalsIgnoreCase(System.getenv("APPVEYOR"));
    }

    private static String getGitLastCommitMessageIfAvailable() {
        if (isRunningAtContinuousIntegrationServer()) {
            return StringUtils.trimToEmpty(System.getenv("GIT_LAST_COMMIT_MESSAGE"));
        }
        return "";
    }

    public static boolean gitLastCommitMessageContains(String expected) {
        String gitLastCommitMessageIfAvailable = getGitLastCommitMessageIfAvailable();
        System.out.println("# GIT LAST COMMIT MSG WAS: "+gitLastCommitMessageIfAvailable);
        System.out.println("# GIT LAST COMMIT MSG WAS: "+gitLastCommitMessageIfAvailable);
        System.out.println("# GIT LAST COMMIT MSG WAS: "+gitLastCommitMessageIfAvailable);
        return gitLastCommitMessageIfAvailable.contains(expected);
    }

}
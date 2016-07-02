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

import io.github.seleniumquery.SeleniumQuery;
import io.github.seleniumquery.browser.BrowserFunctions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.eclipse.jetty.util.StringUtil.isNotBlank;

public class SauceLabsUtils {

    public static void context(String info) {
        context(info, SeleniumQuery.$);
    }

    public static void context(String info, BrowserFunctions $) {
        WebDriver webDriver = $.driver().get();
        if (isRemoteWebDriver(webDriver)) {
            ((JavascriptExecutor) webDriver).executeScript("sauce:context=" + info);
        }
    }

    public static void reportTestSuccess() {
        context("Reporting Test SUCCESS");
        result(true);
    }

    public static void reportTestFailure() {
        context("Reporting Test FAILURE");
        result(false);
    }

    private static void result(boolean passed) {
        result(passed, SeleniumQuery.$);
    }

    private static void result(boolean passed, BrowserFunctions $) {
        WebDriver webDriver = $.driver().get();
        if (isRemoteWebDriver(webDriver)) {
            ((JavascriptExecutor) webDriver).executeScript("sauce:job-result=" + passed);
        }
    }

    public static void jobName(String urlToOpen, BrowserFunctions $) {
        WebDriver webDriver = $.driver().get();
        if (isRemoteWebDriver(webDriver)) {
            String jobName = urlToOpen.replaceAll("^file:/.*?/src/test/java/(.*)\\.html$", "$1");
            setBuildName((JavascriptExecutor) webDriver);
            ((JavascriptExecutor) webDriver).executeScript("sauce:job-name="+jobName);
        }
    }

    private static void setBuildName(JavascriptExecutor webDriver) {
        // this code does not have tests (faking an env variable was not worth the trouble)
        String lastCommitHash = System.getenv("CI_COMMIT_ID");
        if (isNotBlank(lastCommitHash)) {
            webDriver.executeScript("sauce:job-build=" + lastCommitHash);
        }
    }

    private static boolean isRemoteWebDriver(WebDriver webDriver) {
        // there should probably be a better way of deciding this!
        return webDriver instanceof RemoteWebDriver && webDriver.toString().startsWith("RemoteWebDriver: ");
    }

    public static String fixUrlForRemoteTest(String urlToOpen) {
        if (isRemoteWebDriver($.driver().get())) {
            return urlToOpen.replaceAll("^file:/.*?/src/test/java/", "http://rawgit.com/seleniumQuery/seleniumQuery/master/src/test/java/");
        }
        return urlToOpen;
    }

}

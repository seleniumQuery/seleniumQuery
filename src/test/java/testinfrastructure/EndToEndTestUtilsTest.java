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

package testinfrastructure;

import org.junit.Test;
import org.openqa.selenium.remote.RemoteWebDriver;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static testinfrastructure.EndToEndTestUtils.classNameToTestFileUrl;

public class EndToEndTestUtilsTest {

    @Test
    public void urlIsFixedCorrectlyWhenDriverIsRemote() {
        // given
        RemoteWebDriverUrlSpy remoteWebDriverUrlSpy = new RemoteWebDriverUrlSpy();
        $.driver().use(remoteWebDriverUrlSpy);
        System.out.println(EndToEndTestUtils.isRemoteWebDriver($.driver().get()));
        // when
        EndToEndTestUtils.openUrl(classNameToTestFileUrl(EndToEndTestUtilsTest.class));
        // then
        assertThat(remoteWebDriverUrlSpy.visitedUrl, is("https://rawgit.com/seleniumQuery/seleniumQuery/master/src/test/java/testinfrastructure/EndToEndTestUtilsTest.html"));
        assertThat(remoteWebDriverUrlSpy.executedScript, is("sauce:job-name=testinfrastructure/EndToEndTestUtilsTest"));
    }

}

class RemoteWebDriverUrlSpy extends RemoteWebDriver {
    String executedScript;
    String visitedUrl;
    @Override
    public void get(String s) {
        this.visitedUrl = s;
    }
    @Override
    public Object executeScript(String script, Object... args) {
        this.executedScript = script;
        return null;
    }
    @Override
    public String toString() {
        return "RemoteWebDriver: ";
    }
}
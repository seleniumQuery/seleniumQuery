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

package testinfrastructure.junitrule;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import testinfrastructure.EndToEndTestUtils;
import testinfrastructure.junitrule.config.DriverToRunTestsIn;
import testinfrastructure.junitrule.config.EndToEndTestConfig;
import testinfrastructure.junitrule.statement.TestClassInConfiguredDriversStatement;
import testinfrastructure.junitrule.statement.TestMethodStatement;

import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;

public class SetUpAndTearDownDriver implements TestRule {

	public static final DriverToRunTestsIn driverToRunTestsIn = EndToEndTestConfig.whatDriversShouldTestsRun();

	private final Class<?> classForUrl;

    private final TestClassSession testClassSession = new TestClassSession(driverToRunTestsIn);

    public SetUpAndTearDownDriver() {
		this.classForUrl = null;
	}

	public SetUpAndTearDownDriver(Class<?> htmlTestUrlClass) {
		this.classForUrl = htmlTestUrlClass;
	}

    private String url(Description description) {
        return EndToEndTestUtils.classNameToTestFileUrl(defaultIfNull(this.classForUrl, description.getTestClass()));
	}

	@Override
	public Statement apply(Statement base, Description description) {
        if (description.isSuite()) {
            testClassSession.reportRuleIsAnnotatedWithClassRule(description.getTestClass());
            return new TestClassInConfiguredDriversStatement(testClassSession, base, url(description));
		}
        if (testClassSession.thereWasNoReportThatRuleIsAnnotatedWithClassRule()) {
            throw new RuntimeException("The Test class should be annotated with both @Rule and @ClassRule!");
        }
        return new TestMethodStatement(testClassSession, base, description);
	}

}

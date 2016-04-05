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

import org.junit.runners.model.Statement;
import testinfrastructure.EndToEndTestUtils;
import testinfrastructure.testutils.SauceLabsUtils;

class TestMethodsRunner {

	private String failed = "";
	private Throwable firstFailure;
	private Statement base;
	private String url;

	TestMethodsRunner(Statement base, String url) {
		this.base = base;
		this.url = url;
	}

	void executeMethodForDriver(String driver) {
		System.out.println("   @## >>> Running on "+driver);
		EndToEndTestUtils.openUrl(this.url); // this wont be needed when everyone use this both as @Rule and @ClassRule
		try {
			base.evaluate();
		} catch (Throwable t) {
			if (this.firstFailure == null) {
				this.firstFailure = t;
			}
			failed += driver + " ";
			System.out.println("   @## %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% FAILED on "+driver+"! -> "+t.getMessage());
		}
		System.out.println("   @## <<< Done on "+driver);
		if (this.firstFailure != null) {
            SauceLabsUtils.reportTestFailure();
        } else {
            SauceLabsUtils.reportTestSuccess();
        }
	}

    void reportFailures() throws Throwable {
        // at this point the driver already quit
        if (this.firstFailure != null) {
			throw new AssertionError("There are test failures in some drivers: " + failed, this.firstFailure);
		}
	}

}
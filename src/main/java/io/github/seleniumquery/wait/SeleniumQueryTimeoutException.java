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

package io.github.seleniumquery.wait;

import io.github.seleniumquery.SeleniumQueryConfig;
import io.github.seleniumquery.SeleniumQueryObject;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public class SeleniumQueryTimeoutException extends TimeoutException {

	private static final long serialVersionUID = 3L;
	
	private SeleniumQueryObject seleniumQueryObject;
	
	SeleniumQueryTimeoutException(TimeoutException sourceException, SeleniumQueryObject seleniumQueryObject, String reason) {
		super("Timeout while waiting for selector '"+seleniumQueryObject.getBy()+"' "+reason, sourceException);
		this.seleniumQueryObject = seleniumQueryObject;
		
		saveErrorPage();
		saveErrorScreenshot();
	}

	private void saveErrorScreenshot() {
		if (this.seleniumQueryObject.getWebDriver() instanceof TakesScreenshot) {
			try {
				File srcFile = ((TakesScreenshot) this.seleniumQueryObject.getWebDriver()).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(srcFile, new File(SeleniumQueryConfig.get("ERROR_PAGE_SCREENSHOT_LOCATION")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void saveErrorPage() {
		try {
			PrintWriter out = new PrintWriter(SeleniumQueryConfig.get("ERROR_PAGE_HTML_LOCATION"));
			out.println(this.seleniumQueryObject.getWebDriver().getPageSource());
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}

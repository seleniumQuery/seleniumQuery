package io.github.seleniumquery.wait;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import io.github.seleniumquery.SeleniumQueryConfig;
import io.github.seleniumquery.SeleniumQueryObject;

public class SeleniumQueryWaitException extends RuntimeException {

	private static final long serialVersionUID = 2L;
	
	private SeleniumQueryObject seleniumQueryObject;
	
	public SeleniumQueryWaitException(TimeoutException sourceException, SeleniumQueryObject seleniumQueryObject, String reason) {
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

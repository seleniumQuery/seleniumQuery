package org.openqa.selenium.seleniumquery.exception;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.seleniumquery.SQueryHtmlElement;
import org.openqa.selenium.seleniumquery.SQueryProperties;

public class WaitUntilException extends RuntimeException {

	private static final long serialVersionUID = 4976712340064083676L;
	
	private SQueryHtmlElement htmlElement;
	
	public WaitUntilException(TimeoutException sourceException, SQueryHtmlElement htmlElement, String reason) {
		super("Timeout while waiting for selector '"+htmlElement.getSelector()+"' "+reason, sourceException);
		this.htmlElement = htmlElement;
		
		saveErrorPage();
		saveErrorScreenshot();
	}

	private void saveErrorScreenshot() {
		if (this.htmlElement.getDriver() instanceof TakesScreenshot) {
			try {
				File srcFile = ((TakesScreenshot) this.htmlElement.getDriver()).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(srcFile, new File(SQueryProperties.get("ERROR_PAGE_SCREENSHOT_LOCATION")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void saveErrorPage() {
		try {
			PrintWriter out = new PrintWriter(SQueryProperties.get("ERROR_PAGE_HTML_LOCATION"));
			out.println(this.htmlElement.getDriver().getPageSource());
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}

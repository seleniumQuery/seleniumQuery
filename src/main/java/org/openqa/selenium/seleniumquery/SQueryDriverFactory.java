package org.openqa.selenium.seleniumquery;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebClientOptions;

public class SQueryDriverFactory {
	
	public static WebDriver createFirefoxDriver() {
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
	    return driver;
	}

	public static WebDriver createPhantomJSDriver() {
		DesiredCapabilities dCaps = new DesiredCapabilities();
		dCaps.setJavascriptEnabled(true);
		WebDriver driver = new PhantomJSDriver(dCaps);
		return driver;
	}

	public static WebDriver createHtmlUnitDriver() {
		HtmlUnitDriver driver = new HtmlUnitDriver(true) {
			@Override
			protected WebClient newWebClient(BrowserVersion version) {
				WebClient client = super.newWebClient(version);
				WebClientOptions options = client.getOptions();
				options.setThrowExceptionOnScriptError(false); // ignore JavaScript errors
				return client;
			}
		};
		driver.setJavascriptEnabled(true);
		// disable HTMLUnit's annoying logs 
		java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(java.util.logging.Level.OFF);
	    return driver;
	}

}

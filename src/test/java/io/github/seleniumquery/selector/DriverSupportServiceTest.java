package io.github.seleniumquery.selector;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import com.gargoylesoftware.htmlunit.BrowserVersion;

public class DriverSupportServiceTest {

	@Test
	public void getEmulatedBrowser__chrome() {
		HtmlUnitDriver htmlUnitDriver = new HtmlUnitDriver(BrowserVersion.CHROME);
		String emulatedBrowser = DriverSupportService.getEmulatedBrowser(htmlUnitDriver);
		assertThat(emulatedBrowser, is("Chrome"));
	}
	
	@Test
	public void getEmulatedBrowser__chrome_16() {
		@SuppressWarnings("deprecation")
		HtmlUnitDriver htmlUnitDriver = new HtmlUnitDriver(BrowserVersion.CHROME_16);
		String emulatedBrowser = DriverSupportService.getEmulatedBrowser(htmlUnitDriver);
		assertThat(emulatedBrowser, is("Chrome16"));
	}
	
	@Test
	public void getEmulatedBrowser__firefox_10() {
		@SuppressWarnings("deprecation")
		HtmlUnitDriver htmlUnitDriver = new HtmlUnitDriver(BrowserVersion.FIREFOX_10);
		String emulatedBrowser = DriverSupportService.getEmulatedBrowser(htmlUnitDriver);
		assertThat(emulatedBrowser, is("FF10"));
	}
	
	@Test
	public void getEmulatedBrowser__firefox_17() {
		HtmlUnitDriver htmlUnitDriver = new HtmlUnitDriver(BrowserVersion.FIREFOX_17);
		String emulatedBrowser = DriverSupportService.getEmulatedBrowser(htmlUnitDriver);
		assertThat(emulatedBrowser, is("FF17"));
	}
	
	@Test
	public void getEmulatedBrowser__firefox_3_6() {
		@SuppressWarnings("deprecation")
		HtmlUnitDriver htmlUnitDriver = new HtmlUnitDriver(BrowserVersion.FIREFOX_3_6);
		String emulatedBrowser = DriverSupportService.getEmulatedBrowser(htmlUnitDriver);
		assertThat(emulatedBrowser, is("FF3.6"));
	}
	
	@Test
	public void getEmulatedBrowser__ie6() {
		@SuppressWarnings("deprecation")
		HtmlUnitDriver htmlUnitDriver = new HtmlUnitDriver(BrowserVersion.INTERNET_EXPLORER_6);
		String emulatedBrowser = DriverSupportService.getEmulatedBrowser(htmlUnitDriver);
		assertThat(emulatedBrowser, is("IE6"));
	}
	
	@Test
	public void getEmulatedBrowser__ie7() {
		@SuppressWarnings("deprecation")
		HtmlUnitDriver htmlUnitDriver = new HtmlUnitDriver(BrowserVersion.INTERNET_EXPLORER_7);
		String emulatedBrowser = DriverSupportService.getEmulatedBrowser(htmlUnitDriver);
		assertThat(emulatedBrowser, is("IE7"));
	}
	
	@Test
	public void getEmulatedBrowser__ie8() {
		HtmlUnitDriver htmlUnitDriver = new HtmlUnitDriver(BrowserVersion.INTERNET_EXPLORER_8);
		String emulatedBrowser = DriverSupportService.getEmulatedBrowser(htmlUnitDriver);
		assertThat(emulatedBrowser, is("IE8"));
	}
	
	@Test
	public void getEmulatedBrowser__ie9() {
		HtmlUnitDriver htmlUnitDriver = new HtmlUnitDriver(BrowserVersion.INTERNET_EXPLORER_9);
		String emulatedBrowser = DriverSupportService.getEmulatedBrowser(htmlUnitDriver);
		assertThat(emulatedBrowser, is("IE9"));
	}
	
	@Test
	public void getEmulatedBrowser__ie10() {
		HtmlUnitDriver htmlUnitDriver = new HtmlUnitDriver(BrowserVersion.INTERNET_EXPLORER_10);
		String emulatedBrowser = DriverSupportService.getEmulatedBrowser(htmlUnitDriver);
		assertThat(emulatedBrowser, is("IE10"));
	}
	
	@Test
	public void getEmulatedBrowser__not_HtmlUnit() {
		WebDriver driverStub = new WebDriver() {
			public TargetLocator switchTo() { return null; }
			public void quit() { }
			public Navigation navigate() { return null; }
			public Options manage() { return null; }
			public Set<String> getWindowHandles() { return null; }
			public String getWindowHandle() { return null; }
			public String getTitle() { return null; }
			public String getPageSource() { return null; }
			public String getCurrentUrl() { return null; }
			public void get(String url) { }
			public List<WebElement> findElements(By by) { return null; }
			public WebElement findElement(By by) { return null; }
			public void close() { }
		};
		String emulatedBrowser = DriverSupportService.getEmulatedBrowser(driverStub);
		assertThat(emulatedBrowser, is(""));
	}

}
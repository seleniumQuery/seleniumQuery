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
	public void isHtmlUnitDriverEmulatingIE__chrome() {
		HtmlUnitDriver htmlUnitDriver = new HtmlUnitDriver(BrowserVersion.CHROME);
		boolean isHtmlUnitDriverEmulatingIE = DriverSupportService.isHtmlUnitDriverEmulatingIE(htmlUnitDriver);
		assertThat(isHtmlUnitDriverEmulatingIE, is(false));
	}
	
	@Test
	public void isHtmlUnitDriverEmulatingIE__firefox_24() {
		HtmlUnitDriver htmlUnitDriver = new HtmlUnitDriver(BrowserVersion.FIREFOX_24);
		boolean isHtmlUnitDriverEmulatingIE = DriverSupportService.isHtmlUnitDriverEmulatingIE(htmlUnitDriver);
		assertThat(isHtmlUnitDriverEmulatingIE, is(false));
	}

	@Test
	public void isHtmlUnitDriverEmulatingIE__firefox_17() {
		@SuppressWarnings("deprecation")
		HtmlUnitDriver htmlUnitDriver = new HtmlUnitDriver(BrowserVersion.FIREFOX_17);
		boolean isHtmlUnitDriverEmulatingIE = DriverSupportService.isHtmlUnitDriverEmulatingIE(htmlUnitDriver);
		assertThat(isHtmlUnitDriverEmulatingIE, is(false));
	}

	@Test
	public void isHtmlUnitDriverEmulatingIE__ie8() {
		@SuppressWarnings("deprecation")
		HtmlUnitDriver htmlUnitDriver = new HtmlUnitDriver(BrowserVersion.INTERNET_EXPLORER_8);
		boolean isHtmlUnitDriverEmulatingIE = DriverSupportService.isHtmlUnitDriverEmulatingIE(htmlUnitDriver);
		assertThat(isHtmlUnitDriverEmulatingIE, is(true));
	}

	@Test
	public void isHtmlUnitDriverEmulatingIE__ie9() {
		@SuppressWarnings("deprecation")
		HtmlUnitDriver htmlUnitDriver = new HtmlUnitDriver(BrowserVersion.INTERNET_EXPLORER_9);
		boolean isHtmlUnitDriverEmulatingIE = DriverSupportService.isHtmlUnitDriverEmulatingIE(htmlUnitDriver);
		assertThat(isHtmlUnitDriverEmulatingIE, is(true));
	}
	
	@Test
	public void isHtmlUnitDriverEmulatingIE__ie11() {
		HtmlUnitDriver htmlUnitDriver = new HtmlUnitDriver(BrowserVersion.INTERNET_EXPLORER_11);
		boolean isHtmlUnitDriverEmulatingIE = DriverSupportService.isHtmlUnitDriverEmulatingIE(htmlUnitDriver);
		assertThat(isHtmlUnitDriverEmulatingIE, is(true));
	}
	
	@Test
	public void isHtmlUnitDriverEmulatingIE__not_HtmlUnit() {
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
		boolean isHtmlUnitDriverEmulatingIE = DriverSupportService.isHtmlUnitDriverEmulatingIE(driverStub);
		assertThat(isHtmlUnitDriverEmulatingIE, is(false));
	}

}
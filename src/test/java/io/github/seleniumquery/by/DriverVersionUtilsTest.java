/*
 * Copyright (c) 2015 seleniumQuery authors
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

package io.github.seleniumquery.by;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class DriverVersionUtilsTest {

	@Test
	public void isHtmlUnitDriverEmulatingIE__chrome() {
		HtmlUnitDriver htmlUnitDriver = new HtmlUnitDriver(BrowserVersion.CHROME);
		boolean isHtmlUnitDriverEmulatingIE = DriverVersionUtils.isHtmlUnitDriverEmulatingIE(htmlUnitDriver);
		assertThat(isHtmlUnitDriverEmulatingIE, is(false));
	}
	
	@Test
	public void isHtmlUnitDriverEmulatingIE__firefox_24() {
		HtmlUnitDriver htmlUnitDriver = new HtmlUnitDriver(BrowserVersion.FIREFOX_24);
		boolean isHtmlUnitDriverEmulatingIE = DriverVersionUtils.isHtmlUnitDriverEmulatingIE(htmlUnitDriver);
		assertThat(isHtmlUnitDriverEmulatingIE, is(false));
	}

	@Test
	public void isHtmlUnitDriverEmulatingIE__firefox_17() {
		@SuppressWarnings("deprecation")
		HtmlUnitDriver htmlUnitDriver = new HtmlUnitDriver(BrowserVersion.FIREFOX_17);
		boolean isHtmlUnitDriverEmulatingIE = DriverVersionUtils.isHtmlUnitDriverEmulatingIE(htmlUnitDriver);
		assertThat(isHtmlUnitDriverEmulatingIE, is(false));
	}

	@Test
	public void isHtmlUnitDriverEmulatingIE__ie8() {
		@SuppressWarnings("deprecation")
		HtmlUnitDriver htmlUnitDriver = new HtmlUnitDriver(BrowserVersion.INTERNET_EXPLORER_8);
		boolean isHtmlUnitDriverEmulatingIE = DriverVersionUtils.isHtmlUnitDriverEmulatingIE(htmlUnitDriver);
		assertThat(isHtmlUnitDriverEmulatingIE, is(true));
	}

	@Test
	public void isHtmlUnitDriverEmulatingIE__ie9() {
		@SuppressWarnings("deprecation")
		HtmlUnitDriver htmlUnitDriver = new HtmlUnitDriver(BrowserVersion.INTERNET_EXPLORER_9);
		boolean isHtmlUnitDriverEmulatingIE = DriverVersionUtils.isHtmlUnitDriverEmulatingIE(htmlUnitDriver);
		assertThat(isHtmlUnitDriverEmulatingIE, is(true));
	}
	
	@Test
	public void isHtmlUnitDriverEmulatingIE__ie11() {
		HtmlUnitDriver htmlUnitDriver = new HtmlUnitDriver(BrowserVersion.INTERNET_EXPLORER_11);
		boolean isHtmlUnitDriverEmulatingIE = DriverVersionUtils.isHtmlUnitDriverEmulatingIE(htmlUnitDriver);
		assertThat(isHtmlUnitDriverEmulatingIE, is(true));
	}
	
	@Test
	public void isHtmlUnitDriverEmulatingIE__not_HtmlUnit() {
		WebDriver webDriverMock = mock(WebDriver.class);
		boolean isHtmlUnitDriverEmulatingIE = DriverVersionUtils.isHtmlUnitDriverEmulatingIE(webDriverMock);
		assertThat(isHtmlUnitDriverEmulatingIE, is(false));
	}

}
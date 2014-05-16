package io.github.seleniumquery.functions;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import io.github.seleniumquery.TestInfrastructure;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FocusFunctionTest {
	
	@Before
	public void setUp() {
		$.browser.setDefaultDriver(TestInfrastructure.getDriver());
		$.browser.openUrl(TestInfrastructure.getHtmlTestFileUrl(getClass()));
	}

    @Test
    public void focus() {
//    	final WebElement bbb = $.browser.getDefaultDriver().findElement(By.cssSelector("body"));
//    	bbb.sendKeys(Keys.TAB);
    	for (int i = 0; i < 10; i++) {
			printActive();
			$.browser.getDefaultDriver().switchTo().activeElement().sendKeys(Keys.TAB);
		}
    	System.out.println("NOW BWDs");
    	for (int i = 0; i < 10; i++) {
			printActive();
			$.browser.getDefaultDriver().switchTo().activeElement().sendKeys(Keys.SHIFT, Keys.TAB);
		}

    	
    	debugFocus("STARTING");
    	$.browser.sleep(1000);
    	$("#i1").focus();
    	System.out.println("@# NOW BEGINS!");
    	$("#bodyID").focus();
    	debugFocus("After bodyID");
    	assertThat($("#bodyID").is(":focus"), is(true));
    	$.browser.sleep(5000);
    	$("#i0").focus();
    	debugFocus("After i0");
    	assertThat($("#i0").is(":focus"), is(true));
    	$.browser.sleep(5000);
    	$("#i1").focus();
    	debugFocus("After i1");
    	assertThat($("#i1").is(":focus"), is(true));
    	$.browser.sleep(5000);
    	$("#i2").focus();
    	debugFocus("After i2");
    	assertThat($("#i2").is(":focus"), is(true));
    	$.browser.sleep(15000);
    }

	private void debugFocus(final String x) {
		List<WebElement> els = $(":focus").get();
		System.out.println(x);
    	for (WebElement el : els) {
			System.out.println("@# -> "+$(el).attr("id"));
		}
	}
	
	private static void print(WebElement elementToBeActive) {
		System.out.println(elementToBeActive.getAttribute("id") + "(tag: "+elementToBeActive.getTagName()+")");
	}
	private static void printActive() {
		WebDriver driver = $.browser.getDefaultDriver();
		print(driver.switchTo().activeElement());
	}

}
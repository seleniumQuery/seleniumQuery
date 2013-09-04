package org.openqa.selenium.seleniumquery.example;

import static org.openqa.selenium.seleniumquery.SQuery.$; // this will allow the short syntax

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.seleniumquery.SQuery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class SeleniumQueryExampleTest {

    @Test
    public void mainTest() {
        FirefoxDriver driver = new FirefoxDriver();
        
		SQuery.setDefaultDriver(driver); // the browser/driver used by sQ() calls
        $().openUrl("http://www.google.com");
        $("input[name='q']").val("selenium");
        $("button[name='btnG']").click();
        
        String resultStats = $("#resultStats").text();
		System.out.println(resultStats);
        
		/* Besides the short syntax and the useful assumptions, the best capabilities of
		 * SeleniumQuery are the waitUntil() functions, especially useful for Ajax tests: */
		// sQ("input[name='q']").waitUntil().isNotPresent();
        /* The line above will throw an exception now because that input never goes away
         * in google.com. */
        
		driver.quit();
    }

}
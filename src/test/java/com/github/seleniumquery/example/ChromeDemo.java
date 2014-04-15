package org.openqa.selenium.seleniumquery.example;

import static org.openqa.selenium.seleniumquery.SeleniumQuery.$; // this will allow the short syntax

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@RunWith(JUnit4.class)
public class ChromeDemo {

    @Test
    public void mainTest() {
    	System.setProperty("webdriver.chrome.driver", "F:\\desenv\\chromedriver.exe");
    	WebDriver driver = new ChromeDriver();
    	
        $.browser.setDefaultDriver(driver);
		
        $.location.href("http://www.google.com");

        $("input[name='q']").val("selenium");
        $("button[name='btnG']").click();
        System.out.println($("#resultStats").text());

        // Besides the short syntax and the useful assumptions, the most useful capabilities of
        // SeleniumQuery are the .waitUntil. functions, especially handy for Ajax handling:
        
        /*
        $("input[name='q']").waitUntil.isNotPresent();
        */
        
        // The line above throws an exception as that input never goes away in google.com.

        $.browser.quitDefaultBrowser();
    }

}
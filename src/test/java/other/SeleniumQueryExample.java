package other;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import static io.github.seleniumquery.SeleniumQuery.$; // this will allow the short syntax

public class SeleniumQueryExample {
    public static void main(String[] args) {
        // sets Firefox as the global driver; setting is optional - if omitted, will default
        // to HtmlUnit or whatever you set at the, also optional, config files
        FirefoxProfile firefoxProfile = new FirefoxProfile();
        firefoxProfile.setPreference("javascript.enabled", false);
        $.browser.globalDriver().use(new FirefoxDriver(firefoxProfile));

        $.browser.open("http://www.google.com/?hl=en");

        $(":text[name='q']").val("selenium");
        $(":button:contains('Google Search')").click();

        String resultsText = $("#resultStats").text();
        System.out.println(resultsText);

        // Besides the short syntax and the jQuery behavior you already know,
        // other very useful function in seleniumQuery is .waitUntil(),
        // especially handy for handling/testing Ajax enabled pages:

        $(":input[name='q']").waitUntil().is(":enabled");
        // The line above waits for no time, as that input
        // is always enabled in google.com.

        $.browser.quit(); // quits the global (firefox) driver
    }
}
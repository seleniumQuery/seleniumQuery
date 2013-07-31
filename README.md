SeleniumQuery
======

Allows the use of a jQuery-like interface for Selenium WebDriver, built using Selenium's native capabilities **only** (no `jQuery.js` is embedded at the page).

Other great feature is the leverage of `WebDriver`'s `FluentWait` capabilities **directly** in the element through the use of the `.waitUntil()` method:

    sQ("#ajaxDiv").click().waitUntil().isNotPresent()
    
<br />
Try it out now with the running example below:

`````java
import static org.openqa.selenium.seleniumquery.SQuery.sQ; // this will allow the short syntax

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.seleniumquery.SQuery;

public abstract class SeleniumQueryExample {

    public static void main(String[] args) {
        FirefoxDriver driver = new FirefoxDriver();
        
		SQuery.setDefaultDriver(driver); // the browser/driver used by sQ() calls
        sQ().openUrl("http://www.google.com");
        sQ("input[name='q']").val("selenium");
        sQ("button[name='btnG']").click();
        
        String resultStats = sQ("#resultStats").text();
		System.out.println(resultStats);
        
		/* Besides the short syntax and the useful assumptions, the best capabilities of
		 * SeleniumQuery are the waitUntil() functions, especially useful for Ajax tests: */
		// sQ("input[name='q']").waitUntil().isNotPresent();
        /* The line above will throw an exception now because that input never goes away
         * in google.com. */
        
		driver.quit();
    }

}
`````

To use it, add this to your `pom.xml`:

`````xml
<!-- The project dependency -->
<dependencies>
	<dependency>
		<groupId>com.github.acdcjunior.seleniumquery</groupId>
		<artifactId>SeleniumQuery</artifactId>
		<version>0.0.1</version>
	</dependency>
</dependencies>
<!-- The repository URL, so maven can download it directly -->
<repositories>
	<repository>
		<id>Repo for SeleniumQuery</id>
		<url>https://raw.github.com/acdcjunior/mvn-repo/master</url>
	</repository>
</repositories>
`````

As testing usually involves interactions from the end-user point of view, the development currently focuses on
interface operation commands (such as `.val()`, `.text()` and `.click()`) and waiting commands (such as ***wait until element with ID "#x" is not visible***: `sQ("#x").waitUntil().isNotVisible()`) rather than DOM manipulation (such as `.append()`) - for these, the usual `WebElement` functions are still available and easily accessible at any moment: `sQ("#x").getElement()`.

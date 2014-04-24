#seleniumQuery - http://seleniumquery.github.io
###Cross-Driver (Cross-Browser) jQuery-like native Java interface for Selenium WebDriver

Java library/framework that intends to bring a “cross-driver” (cross-browser) jQuery-like interface in (pure) Java for [Selenium WebDriver](http://docs.seleniumhq.org/projects/webdriver/).

Example snippet, written in **Java**:

`````java
// getting the value
String oldStreet = $("input.street").val();
// setting the value
$("input.street").val("4th St!");
`````
Allows querying elements by **XPath**, **CSS Selectors** and even **jQuery/Sizzle enhancements**, such as `:eq()`, `:contains()` and others!

Built using Selenium WebDriver's native capabilities **only**:
- No `jQuery.js` is embedded at the page, no side-effects are generated;
- Works predictably and exactly the same with any (including legacy, old JSF) system;
- Doesn't matter if the page uses jQuery or not (or even if the JavaScript global variable `$` is other library like `Prototype.js`).
- **Capable of testing JavaScript-disabled pages**
    - Test pages that use [Unobtrusive JavaScript!](http://en.wikipedia.org/wiki/Unobtrusive_JavaScript)
    - Most functions don't even require a browser/driver with JavaScript enabled!
        - Functions like `.trigger()` which require JavaScript on the browser are the exceptions.

##TL; DR: Running Example

Try it out now with the running example below:

`````java
import static org.openqa.selenium.seleniumquery.SeleniumQuery.$; // this will allow the short syntax

import org.openqa.selenium.firefox.FirefoxDriver;

public class SeleniumQueryExample {
    public static void main(String[] args) {
        $.browser.setDefaultDriver(new FirefoxDriver()); // sets the driver used by $()
        
        $.browser.openUrl("http://www.google.com");
        
        $("input[name='q']").val("selenium");
        $("button[name='btnG']").click();
        System.out.println($("#resultStats").text());

        // Besides the short syntax and the useful assumptions, other very useful capabilities of
        // seleniumQuery are the .queryUntil() and .waitUntil() functions, especially handy for
        // handling/testing Ajax enabled pages:
        
        /*
        $("input[name='q']").queryUntil().is(":not(:present)");
        */
        
        // The line above throws an exception as that input never goes away in google.com.

        $.browser.quitDefaultBrowser(); // quits the firefox driver
    }
}
`````
To get seleniumQuery's latest snapshot, add this to your **`pom.xml`**:

`````xml
<!-- The project dependency -->
<dependencies>
    <dependency>
        <groupId>io.github.seleniumquery</groupId>
        <artifactId>seleniumquery</artifactId>
        <version>1.0.0-SNAPSHOT</version>
    </dependency>
</dependencies>
<!-- The repository the snapshots will be downloaded from.
    Can either go in your pom.xml or settings.xml -->
<repositories>
	<repository>
		<id>sonatype-nexus-snapshots</id>
		<name>Sonatype Nexus Snapshots</name>
		<url>https://oss.sonatype.org/content/repositories/snapshots/</url>
		<snapshots>
			<enabled>true</enabled>
			<updatePolicy>always</updatePolicy>
		</snapshots>
	</repository>
</repositories>
`````

See more in http://seleniumquery.github.io

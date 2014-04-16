#seleniumQuery - http://seleniumquery.github.io
###Cross-Driver (Cross-Browser) jQuery-like native Java interface for Selenium WebDriver

Java library/framework that intends to bring a “cross-driver” (cross-browser) jQuery-like interface in (pure) Java for [Selenium WebDriver](http://docs.seleniumhq.org/projects/webdriver/).

Example snippet:

`````java
// getting the value
String oldStreet = $("input.street").val();
// setting the value
$("input.street").val("4th St!");
`````
Allows querying elements by **XPath**, **CSS Selectors** and even some **jQuery/Sizzle enhancements**, such as `:eq()`, `:not()` and others!

Built using Selenium WebDriver's native capabilities **only**:
- No `jQuery.js` is embedded at the page, no side-effects are generated;
- Works predictably and exactly the same with any (including legacy, old JSF) system;
- Doesn't matter if the page uses jQuery or not (or even if the JavaScript global variable `$` is other library like `Prototype.js`).
- **Capable of testing JavaScript-disabled pages**
    - Test pages that use [Unobtrusive JavaScript!](http://en.wikipedia.org/wiki/Unobtrusive_JavaScript)
    - Most functions don't even require a browser/driver with JavaScript enabled!
        - (Exceptions are functions like `.trigger()` which obviously requires JavaScript on the browser.)

##TL; DR: Running Example

Try it out now with the running example below:

`````java
import static org.openqa.selenium.seleniumquery.SeleniumQuery.$; // this will allow the short syntax

import org.openqa.selenium.firefox.FirefoxDriver;

public class SeleniumQueryExample {
    public static void main(String[] args) {
        $.browser.setDefaultDriver(new FirefoxDriver()); // sets the driver used by $()
        
        $.location.href("http://www.google.com"); // opens a page
        
        $("input[name='q']").val("selenium");
        $("button[name='btnG']").click();
        System.out.println($("#resultStats").text());

        // Besides the short syntax and the useful assumptions, the most useful capabilities of
        // seleniumQuery are the .waitUntil. functions, especially handy for Ajax handling:
        
        /*
        $("input[name='q']").waitUntil().is(":not(:present)");
        */
        
        // The line above throws an exception as that input never goes away in google.com.

        $.browser.quitDefaultBrowser(); // quits the firefox driver
    }
}
`````
To get **seleniumQuery**'s latest snapshot, add this to your **`pom.xml`**:

`````xml
<!-- The project dependency -->
<dependencies>
    <dependency>
        <groupId>io.github.seleniumquery</groupId>
        <artifactId>seleniumquery</artifactId>
        <version>1.0.0-SNAPSHOT</version>
    </dependency>
</dependencies>
<!-- The repository URL, so maven can download it directly -->
<repositories>
	<repository>
		<id>Repo for seleniumQuery</id>
		<url>https://raw.githubusercontent.com/seleniumquery/snapshots-repository/master</url>
		<snapshots>
			<enabled>true</enabled>
			<updatePolicy>always</updatePolicy>
		</snapshots>
	</repository>
</repositories>
`````

See more in http://seleniumquery.github.io

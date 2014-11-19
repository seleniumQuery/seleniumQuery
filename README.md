#[seleniumQuery](http://seleniumquery.github.io) - Cross-Driver Selenium Java interface

###Cross-Driver (Cross-Browser) jQuery-like native Java interface for Selenium WebDriver

seleniumQuery is a Java library/framework that intends to bring a "**cross-driver**" **jQuery-like** interface in Java for [Selenium WebDriver](http://docs.seleniumhq.org/projects/webdriver/).

Example snippet:

```java
// getting the value
String oldStreet = $("input.street").val();
// setting the value
$("input.street").val("4th St!");
```

Allows querying elements by:

- **CSS3 Selectors** - `$(".myClass")`, `$("#table tr:nth-child(3n+1)")`
- **jQuery/Sizzle enhancements** - `$(".myClass:eq(3)")`, `$(".myClass:contains('My Text!')")`
- **XPath** - `$("//div/*/label/preceding::*")`
- and even some own **seleniumQuery selectors**: `$("#myOldDiv").is(":not(:present)")`.

Built using Selenium WebDriver's native capabilities **only**:

- No `jQuery.js` is embedded at the page, no side-effects are generated;
    - Doesn't matter if the page uses jQuery or not (or even if the JavaScript global variable `$` is other library like `Prototype.js`).
- Capable of handling/testing **JavaScript-disabled pages**
    - Test pages that use [Unobtrusive JavaScript](http://en.wikipedia.org/wiki/Unobtrusive_JavaScript).
    - Most functions don't even require the browser/driver to have JavaScript enabled!

##Quickstart: A running example

Try it out now with the running example below:

```java
import static io.github.seleniumquery.SeleniumQuery.$; // this will allow the short syntax

public class SeleniumQueryExample {
    public static void main(String[] args) {
        // sets Firefox as the global driver
        $.browser.globalDriver().useFirefox();
        
        $.browser.open("http://www.google.com");
        
        $("input[name='q']").val("selenium");
        $("button[name='btnG']").click();

        String resultsText = $("#resultStats").text();
        System.out.println(resultsText);

        // Besides the short syntax and the jQuery behavior you already know,
        // other very useful function in seleniumQuery is .waitUntil(),
        // especially handy for handling/testing Ajax enabled pages:
        
        $("input[name='q']").waitUntil().is(":enabled");
        // The line above waits for no time, as that input
        // is always enabled in google.com.

        $.browser.quit(); // quits the global (firefox) driver
    }
}
```
To get seleniumQuery's latest snapshot, add this to your **`pom.xml`**:

```xml
<!-- The project dependency -->
<dependencies>
    <dependency>
        <groupId>io.github.seleniumquery</groupId>
        <artifactId>seleniumquery</artifactId>
        <version>0.9.0-SNAPSHOT</version>
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
```

<br>

#Features

###Readable jQuery syntax you already know

Make your code/tests more readable and easier to maintain. Leverage your knowledge of jQuery.

```java
// Instead of regular Selenium code:
WebElement element = driver.findElement(By.id("mySelect"));
new Select(element).selectByValue("ford");

// You can have the same effect writing just:
$("#mySelect").val("ford");
```
###Powerful selector system

Let the tool do the hard work and find elements easily:

- **CSS3 Selectors** - `$(".myClass")`, `$("#table tr:nth-child(3n+1)")`
- **jQuery/Sizzle enhancements** - `$(".myClass:eq(3)")`, `$(".myClass:contains('My Text!')")`
- **XPath** - `$("//div/*/label/preceding::*")`
- and even some own **seleniumQuery selectors**: `$("#myOldDiv").is(":not(:present)")`.

You pick your style. Whatever is more interesting at the moment. Even mix them:

```
$("#table tr:nth-child(3n+1)").find("/img[@alt='calendar']/preceding::input").val("2014-11-12")
```

seleniumQuery allows querying elements by XPath, CSS3 selectors, jQuery/Sizzle extensions and even some exclusive selectors. Find more about them in [seleniumQuery Selectors wiki page.](https://github.com/seleniumQuery/seleniumQuery/wiki/seleniumQuery-Selectors)

###Waiting capabilities for improved Ajax testing

Other important feature is the leverage of `WebDriver`'s `FluentWait` capabilities **directly** in the element (no boilerplate code!) through the use of the `.waitUntil()` function:

```java
// WebDriver cannot natively detect the end of an Ajax call.
// To test your application's behavior, you can and should always work with the
// Ajax's expected effects, visible for the end user.
// Below is an example of a <div> that should be hidden as effect of an Ajax call.
// The code will hold until the modal is gone. If it is never gone, seleniumQuery
// will throw a timeout exception.
$("#modalDiv :button:contains('OK')").click();
$("#modalDiv :button:contains('OK')").waitUntil().is(":not(:visible)");

// Or, fluently:
$("#modalDivOkButton").click().waitUntil().is(":not(:visible)");
```

And, that's right, the `.is()` function above is your old-time friend that takes a selector as argument!

<br>

#API: jQuery, waitUntil and other functions

seleniumQuery aims to implement all relevant jQuery functions, as well as adding some of our own.

Our main goals is emulating user actions and "sensing" the pages, currently our intention is to implement functions that read the state of the page and allow intuitive form manipulation.

Get to know what jQuery functions seleniumQuery supports and what else it brings to the table on our [seleniumQuery API wiki page](https://github.com/seleniumQuery/seleniumQuery/wiki/seleniumQuery-API).

<br>

<br>

###Alternate symbols

If the dollar symbol, `$`, gives you the yikes -- we know, it is used for internal class names --, it is important to notice that the `$` symbol in seleniumQuery is not a class name, but a `static` method (and field) imported statically. Still, if you don't feel like using it, you can resort to `sQ()` or good ol' `jQuery()` and benefit from all the same functions:

```java
import static io.github.seleniumquery.SeleniumQuery.sQ;
import static io.github.seleniumquery.SeleniumQuery.jQuery;
...
String oldStreet = sQ("input.street").val();
sQ("input.street").val("4th St!");

String oldStreetz = jQuery("input.street").val();
jQuery("input.street").val("5th St!");
```


<br>

#More

Find more on our [wiki](https://github.com/seleniumQuery/seleniumQuery/wiki).

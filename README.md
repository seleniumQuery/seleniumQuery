SeleniumQuery v0.2.0 - jQuery in Selenium
======

Java library that allows the use of a **jQuery-like native interface** for [Selenium WebDriver](http://docs.seleniumhq.org/projects/webdriver/).

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

public class SeleniumQueryExample {
    public static void main(String[] args) {
        $.location.href("http://www.google.com");
        
        $("input[name='q']").val("selenium");
        $("button[name='btnG']").click();
        System.out.println($("#resultStats").text());

        // Besides the short syntax and the useful assumptions, the most useful capabilities of
        // SeleniumQuery are the .waitUntil. functions, especially handy for Ajax handling:
        $("input[name='q']").waitUntil.isNotPresent();
        // The line above throws an exception as that input never goes away in google.com.

        $.browser.quit();
    }
}
`````
To use **SeleniumQuery** right away, add this to your **`pom.xml`**:

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


#Features

###More readable jQuery-like syntax code

Make your code/tests more readable and easier to maintain. Leverage your knowledge of jQuery.

`````java
// Instead of regular Selenium code:
WebElement element = driver.findElement(By.id("mySelect"));
new Select(element).selectByVisibleText("Ford");

// You can have the same effect writing just:
$("#mySelect").val("Ford");
`````


###Waiting capabilities for improved Ajax testing

Other important feature is the leverage of `WebDriver`'s `FluentWait` capabilities **directly** in the element through the use of `.waitUntil`:

`````java
/*
 * Selenium WebDriver cannot natively detect the end of an Ajax call.
 * To test your application behaviour, you can/should work with the Ajax's expected effects.
 * 
 * Below is an example of a <div> that should be hidden as effect of an Ajax call.
 * The code will only continue after it is gone. If not, it will throw a timeout exception.
 */
$("#ajaxDiv").click();
$("#ajaxDiv").waitUntil.isNotVisible();

// Or, fluently:
$("#ajaxDiv").click().waitUntil.isNotVisible();
`````




#API

Currently implemented instance functions are:

- `$(".selector").val()`: Gets the value for an input element.
- `$(".selector").val("String")`: Sets the value of an input element.
- `$(".selector").text()`: Gets the text of an element.
- `$(".selector").text("String")`: Sets the text of an element.
- `$(".selector").html()`: Gets the HTML of an element.
- `$(".selector").parent()`: Returns the parent.
- `$(".selector").click()`: Clicks!

Available waiting functions:

- `$(".selector").waitUntil.isEnabled()`
- `$(".selector").waitUntil.isPresent()`
- `$(".selector").waitUntil.isVisible()`
- `$(".selector").waitUntil.isVisibleAndEnabled()`

Static functions:

- `$.location.href("http://www.url.to.go.com");`: Opens a URL
- `$.location.href(new File("path/to/localFile.html"));`: Opens a local file
- `$.browser.setDefaultBrowser(webDriver);`: Sets the browser to be used by `$(".selector")`

###Note on Java Convention

If the dollar symbol `$` gives you the yikes, it is important to notice that the `$` used is not a class name, but a `static` method (and field) imported statically. Still, if you don't feel like using it, you can resort to `sQ()` and benefit from all the same effects:

`````java
import static org.openqa.selenium.seleniumquery.SeleniumQuery.sQ;
...
String oldStreet = sQ("input.street").val();
sQ("input.street").val("4th St!");
`````

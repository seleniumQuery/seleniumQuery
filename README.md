#[seleniumQuery](http://seleniumquery.github.io)
###*Cross-Driver* jQuery-like Java interface for Selenium WebDriver

seleniumQuery is a Java library/framework that brings a ***cross-driver*** **jQuery-like** interface for [Selenium WebDriver](http://docs.seleniumhq.org/projects/webdriver/).

Example snippet:

```java
// getting the value
String oldStreet = $("input.street").val();
// setting the value
$("input.street").val("4th St!");
```

Allows querying elements by:

- **CSS3 Selectors** - `$(".myClass")`, `$("#table tr:nth-child(3n+1)")`;
- **jQuery/Sizzle enhancements** - `$(":text:eq(3)")`, `$(".myClass:contains('My Text!')")`;
- **XPath** - `$("//div/*/label/preceding::*")`;
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
    // sets Firefox as the driver (this is optional, if omitted, will default to HtmlUnit)
    $.driver().useFirefox().withoutJavaScript(); // JavaScript will be disabled!

    $.url("http://www.google.com/?hl=en");

    $(":text[name='q']").val("selenium"); // the keys are actually typed!
    $(":button:contains('Google Search')").click();
    // Another way: $(":text[name='q']").val("selenium").submit();

    // Besides the short syntax and the jQuery behavior you already know,
    // other very useful function in seleniumQuery is .waitUntil(),
    // handy for dealing with user-waiting actions (specially in Ajax enabled pages):
    String resultsText = $("#resultStats").waitUntil().is(":visible").then().text();
    System.out.println(resultsText);

    $.quit(); // quits the currently used driver (firefox)
  }
}
```
The code above can be found at the [seleniumQuery demos project](https://github.com/seleniumQuery/seleniumQuery-demos). To get the latest version, add to your **`pom.xml`**:
```xml
<dependency>
    <groupId>io.github.seleniumquery</groupId>
    <artifactId>seleniumquery</artifactId>
    <version>0.9.0</version>
</dependency>
```

<br>

#Features

seleniumQuery aims to implement all relevant jQuery functions, as well as adding some of our own. Our main goal is to make emulating user actions and reading the state of pages easier than ever, with a consistent behavior across drivers.


###Readable jQuery syntax you already know

Make your code/tests more readable and easier to maintain. Leverage your knowledge of jQuery.

```java
// Instead of regular Selenium code:
WebElement element = driver.findElement(By.id("mySelect"));
new Select(element).selectByValue("ford");

// You can have the same effect writing just:
$("#mySelect").val("ford");
```

Get to know what jQuery functions seleniumQuery supports and what else it brings to the table on our [seleniumQuery API wiki page](https://github.com/seleniumQuery/seleniumQuery/wiki/seleniumQuery-API).

###Powerful selector system

Let the tool do the hard work and find elements easily:

- CSS3 Selectors - `$(".myClass")`, `$("#table tr:nth-child(3n+1)")`
- jQuery/Sizzle enhancements - `$(".claz:eq(3)")`, `$(".claz:contains('My Text!')")`
- XPath - `$("//div/*/label/preceding::*")`
- and even some own seleniumQuery selectors: `$("#myOldDiv").is(":not(:present)")`.

You pick your style. Whatever is more interesting at the moment. Mixing is OK:

```java
$("#tab tr:nth-child(3n+1)").find("/img[@alt='calendar']/preceding::input").val("2014-11-12")
```
Find more about them in [seleniumQuery Selectors wiki page.](https://github.com/seleniumQuery/seleniumQuery/wiki/seleniumQuery-Selectors)

<br>

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

Check out what else `.waitUntil()` can do in the [seleniumQuery API wiki page](https://github.com/seleniumQuery/seleniumQuery/wiki/seleniumQuery-API).

<br>

###Flexible WebDriver builder system

How to setup the `WebDriver`? Simply use our builder. The driver will be instantiated only at the first use.

#####Firefox

```java
$.driver().useFirefox(); // Will set up firefox as driver

$.url("http://seleniumquery.github.io"); // the driver will be instantiated when this executes
```

#####Firefox driver with disabled JavaScript

Want `FirefoxDriver` without JavaScript? Just:
```java
$.driver().useFirefox().withoutJavaScript(); // when started, Firefox will have JS OFF
```

#####Chrome, InternetExplorer, PhantomJS drivers

All you have to do is download [their executables](https://github.com/seleniumQuery/seleniumQuery-demos/tree/master/src/main/resources) before. Setting them up in seleniumQuery is all too easy:

```java
// Using Chrome
$.driver().useChrome(); // will look for chromedriver(.exe) to you, including the classpath!
// Or if you want to set the path yourself
$.driver().useChrome().withPathToChromeDriver("path/to/chromedriver.exe")

// InternetExplorerDriver
$.driver().useInternetExplorer(); // we search IEDriverServer.exe for you
// Or you set the path yourself
$.driver().useInternetExplorer().withPathToIEDriverServerExe("C:\\IEDriverServer.exe");

// PhantomJS
$.driver().usePhantomJS(); // again, we'll find phantomjs[.exe] to you
// Or you may set the path yourself
$.driver().usePhantomJS().withPathToPhantomJS("path/to/phantomjs.exe");
````

#####HtmlUnit

So many ways to set up `HtmlUnitDriver`... If only there was a simple way. Oh, wait:

```java
// HtmlUnit default (Chrome/JavaScript ON)
$.driver().useHtmlUnit();
// Want disabled JavaScript, just call .withoutJavaScript()
$.driver().useHtmlUnit().withoutJavaScript();

// HtmlUnit emulating Chrome
$.driver().useHtmlUnit().emulatingChrome();
$.driver().useHtmlUnit().emulatingChrome().withoutJavaScript();
// HtmlUnit emulating Firefox
$.driver().useHtmlUnit().emulatingFirefox(); // could disable JS here as well
// And IE
$.driver().useHtmlUnit().emulatingInternetExplorer11(); // JS is disableable as well
$.driver().useHtmlUnit().emulatingInternetExplorer8(); // JS is disableable as well
$.driver().useHtmlUnit().emulatingInternetExplorer9(); // JS is disableable as well
$.driver().useHtmlUnit().emulatingInternetExplorer(); // will pick latest IE
````

####But there is more

Explore the auto-complete. There are additional options to every browser, such as `.withCapabilities(DesiredCapabilities)` or some specific, such as `.withProfile(FirefoxProfile)` or `.withOptions(ChromeOptions)`.

Finally, if you want to create the `WebDriver` yourself:

```java
WebDriver myDriver = ...;
$.driver().use(myDriver);
```

<br>

##seleniumQuery still is Selenium - with "just" a jQuery interface

That's why it can work with disabled JavaScript!

But there is a more important aspect to it: As our functions yield the same result as if you were using jQuery, remember we always execute them from the user perspective.
In other words, when you call:
```java
$(":input[name='email']").val("seleniumQuery@example.com");
```

We don't change  the `value` attribute directly like jQuery does. We actually do as a user would: We **clear** the input
and **type, key by key**, the string provided as argument!

But we go the **extra mile**: Our `$().val()` even works on `contenteditable` elements: They don't have `value`, but we type
the text in them, again, key by key, as an user would!

###Always from the user perspective

On the same tone, when selecting/checking `<option>`s or checkboxes or radios, try not to use `$().prop("selected", true)` directly to them.
Do as an user would: call `.click()`!

<br>

###Alternate symbols

If the dollar symbol, `$`, gives you the yikes -- we know, it is used for internal class names --, it is important to notice that the `$` symbol in seleniumQuery is not a class name, but a `static` method (and field). Still, if you don't feel like using it, you can resort to `sQ()` or good ol' `jQuery()` and benefit from all the same goodies:

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

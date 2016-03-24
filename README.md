# [seleniumQuery](http://seleniumquery.github.io)

[![Join the chat at https://gitter.im/seleniumQuery/seleniumQuery](https://badges.gitter.im/seleniumQuery/seleniumQuery.svg)](https://gitter.im/seleniumQuery/seleniumQuery?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)
[![Maven Central](https://img.shields.io/maven-central/v/io.github.seleniumquery/seleniumquery.svg)](http://search.maven.org/#search%7Cgav%7C1%7Cg%3A%22io.github.seleniumquery%22%20AND%20a%3A%22seleniumquery%22)

[![Linux Build Status](https://img.shields.io/travis/seleniumQuery/seleniumQuery/master.svg?label=Linux Build)](https://travis-ci.org/seleniumQuery/seleniumQuery)
[![Windows Build Status](https://img.shields.io/appveyor/ci/acdcjunior/seleniumQuery/master.svg?label=Windows Build)](https://ci.appveyor.com/project/acdcjunior/seleniumQuery/branch/master)
[![codecov.io](https://codecov.io/github/seleniumQuery/seleniumQuery/coverage.svg?branch=master)](https://codecov.io/github/seleniumQuery/seleniumQuery?branch=master)
[![Dependency Status](https://www.versioneye.com/user/projects/56861ab2eb4f47003c000e43/badge.svg?style=flat)](https://www.versioneye.com/user/projects/56861ab2eb4f47003c000e43)
[![GitHub license](https://img.shields.io/badge/license-Apache%202-blue.svg)](https://raw.githubusercontent.com/seleniumQuery/seleniumQuery/master/LICENSE.txt)


### *Cross-Driver* jQuery-like Java interface for Selenium WebDriver

seleniumQuery is a Java library/framework that brings a ***cross-driver*** **jQuery-like** interface for [Selenium WebDriver](http://docs.seleniumhq.org/projects/webdriver/).

### Example snippet:

```java
// getting the value
String oldStreet = $("input.street").val();
// setting the value
$("input.street").val("4th St!");
```

### No special configuration needed - use it in your project right now:

On a regular `WebElement`...

```java
// an existing WebElement...
WebElement existingWebElement = driver.findElement(By.id("myId"));
// call jQuery functions
String elementVal = $(existingWebElement).val();
boolean isButton = $(existingWebElement).is(":button"); // enhanced selector!
for (WebElement child: $(existingWebElement).children()) {
  System.out.println("That element's child: "+child);
}
```

Or an existing `WebDriver`...

```java
// an existing WebDriver...
WebDriver driver = new FirefoxDriver();
// set it up
$.driver().use(driver);
// and use all the goods
for (WebElement e: $(".myClass:contains('My Text!'):not(:button)")) {
  System.out.println("That element: " + e);
}
```

## What can you do with it?

Allows querying elements by:

- **CSS3 Selectors** - `$(".myClass")`, `$("#table tr:nth-child(3n+1)")`;
- **jQuery/Sizzle enhancements** - `$(":text:eq(3)")`, `$(".myClass:contains('My Text!')")`;
- **XPath** - `$("//div/*/label/preceding::*")`;
- and even some own **seleniumQuery selectors**: `$("#myOldDiv").is(":not(:present)")`.

Built using Selenium WebDriver's native capabilities **only**:

- No `jQuery.js` is embedded at the page, no side-effects are generated;
    - Doesn't matter if the page uses jQuery or not (or even if the JavaScript global variable `$` is other library like `Prototype.js`).
- Capable of handling/testing JavaScript-disabled pages
    - Test pages that use [Unobtrusive JavaScript](http://en.wikipedia.org/wiki/Unobtrusive_JavaScript).
    - Most functions don't even require the browser/driver to have JavaScript enabled!

## Quickstart: A running example

Try it out now with the running example below:

```java
import static io.github.seleniumquery.SeleniumQuery.$; // this will allow the short syntax

public class SeleniumQueryExample {
  public static void main(String[] args) {
    // sets Firefox as the driver (this is optional, if omitted, will default to HtmlUnit)
    $.driver().useFirefox(); // The WebDriver will be instantiated only when first used

    // or use ("decorate") any previously existing driver
    $.driver().use(new FirefoxDriver());

    // starts the driver (if not started already) and opens the URL
    $.url("http://www.google.com/?hl=en");

    // interact with the page
    $(":text[name='q']").val("selenium"); // the keys are actually typed!
    $(":button[name=btnG]").click(); // simulates a real user click (not just the JS event)

    // Besides the short syntax and the jQuery behavior you already know,
    // other very useful function in seleniumQuery is .waitUntil(),
    // handy for dealing with user-waiting actions (specially in Ajax enabled pages):
    String resultsText = $("#resultStats").waitUntil().is(":visible").then().text();

    System.out.println(resultsText);
    // should print something like: About 24,900,000 results (0.37 seconds)

    $.quit(); // quits the currently used driver (firefox)
  }
}
```

## Download and execute the [seleniumQuery showcase project](https://github.com/acdcjunior/seleniumQuery-showcase)

...and see it in action [right now](https://github.com/acdcjunior/seleniumQuery-showcase).

To get the latest version of seleniumQuery, add to your **`pom.xml`**:

```xml
<dependency>
    <groupId>io.github.seleniumquery</groupId>
    <artifactId>seleniumquery</artifactId>
    <version>0.14.0</version>
</dependency>
```

<br>

# Features

seleniumQuery aims to implement all relevant jQuery functions, as well as adding some of our own. Our main goal is to make emulating user actions and reading the state of pages easier than ever, with a consistent behavior across drivers.


### Readable jQuery syntax you already know

Make your code/tests more readable and easier to maintain. Leverage your knowledge of jQuery.

```java
// Instead of regular Selenium code:
WebElement element = driver.findElement(By.id("mySelect"));
new Select(element).selectByValue("ford");

// You can have the same effect writing just:
$("#mySelect").val("ford");
```

Get to know what jQuery functions seleniumQuery supports and what else it brings to the table on our [seleniumQuery API wiki page](https://github.com/seleniumQuery/seleniumQuery/wiki/seleniumQuery-API).

### Powerful selector system

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

### Waiting capabilities for improved Ajax testing

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

### Plugin System

seleniumQuery supports plugins through the `.as(PLUGIN)` function, such as:

```java
$("div").as(YOURPLUGIN).someMethodFromYourPlugin();
```

There are some default plugins. To check them out, call `.as()` without arguments. Example:

```java
// the .select() plugin
$("#citiesSelect").as().select().selectByVisibleText("New York");
// picks an <option> in the <select> based in the <option>'s visible text
```

For an example of how to create your own plugin, check the [seleniumQuery Plugin wiki page](https://github.com/seleniumQuery/seleniumQuery/wiki/seleniumQuery-Plugin-Support---.as()-function).


<br>

### Flexible WebDriver builder system

How to setup the `WebDriver`? Simply use our builder. The driver will be instantiated only when first used.

##### Firefox

```java
$.driver().useFirefox(); // Will set up firefox as driver
$.url("http://seleniumquery.github.io"); //the driver will be instantiated when this executes
```

##### Firefox driver with disabled JavaScript

Want `FirefoxDriver` without JavaScript? Just:
```java
$.driver().useFirefox().withoutJavaScript(); // when started, Firefox will have JS OFF
```

##### Chrome, InternetExplorer, PhantomJS drivers

All you have to do is download [their executables](https://github.com/seleniumQuery/seleniumQuery-demos/tree/master/src/main/resources) before. Setting them up in seleniumQuery is all too easy:

```java
// Using Chrome
$.driver().useChrome(); // will look for chromedriver/exe to you, including in the classpath!
// Or if you want to set the path yourself
$.driver().useChrome().withPathToChromeDriver("path/to/chromedriver.exe")

// InternetExplorerDriver
$.driver().useInternetExplorer(); // we search IEDriverServer.exe for you
// Or you set the path yourself
$.driver().useInternetExplorer().withPathToIEDriverServerExe("C:\\IEDriverServer.exe");

// PhantomJS (GhostDriver)
$.driver().usePhantomJS(); // again, we'll find phantomjs[.exe] to you
// Or you may set the path yourself
$.driver().usePhantomJS().withPathToPhantomJS("path/to/phantomjs.exe");
````

##### HtmlUnit

So many possibilities to set up `HtmlUnitDriver`... If only there was a simple way to use them. Oh, wait:

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

#### But there is more

Explore the auto-complete. There are additional options to every driver, such as `.withCapabilities(DesiredCapabilities)` or some specific, such as `.withProfile(FirefoxProfile)` or `.withOptions(ChromeOptions)`.

Finally, if you want to create the `WebDriver` yourself:

```java
WebDriver myDriver = ...;
$.driver().use(myDriver);
```

<br>

## seleniumQuery still is Selenium - with "just" a jQuery interface

That's why it can work with disabled JavaScript!

But there is a more important aspect to it: Although our functions yield the same result as if you were using jQuery, remember we always execute them from the user perspective.
In other words, when you call:
```java
$(":input[name='email']").val("seleniumQuery@example.com");
```

We don't change  the `value` attribute directly like jQuery does. We actually do as a user would: We **clear** the input
and **type, key by key**, the string provided as argument!

And we go the *extra mile* whenever possible:
- Our **`$().val()` even works on `contenteditable` elements**: They don't have `value`, but we type the text in them, again, key by key, as an user would;
- If it is an `<input type="file">` we select the file;
- When the element is a `<select>`, we choose the `<option>` by the value given (same as `$("selector").as().select().selectByValue("123")`).

### Always from the user perspective

On the same tone, when selecting/checking `<option>`s or checkboxes or radios, try not to use `$().prop("selected", true)` directly to them (which to work, of course, would need JS to be enabled on the driver).
Do as an user would: call `.click()`! Or, better yet, use seleniumQuery's `.as().select()` functions: `$().as().select().selectByVisibleText("My Option")` or `$().as().select().selectByValue("123")`.

<br><br>

### Alternate symbols

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

# More

Find more on our [wiki](https://github.com/seleniumQuery/seleniumQuery/wiki).

<br>

# Changelog/Roadmap

See [releases](https://github.com/seleniumQuery/seleniumQuery/releases).

# Contributing

The tool is just beginning, there's a lot of room for improvement. Some of its main functionalities were just made up (and didn't exist in
jQuery), like the `.waitUntil()`, the `.as()` plugins, the driver builder and so on. So if you come up with an idea of something that could
be useful, tell us, or, even better, do it yourself and join the team!

## Goals and non-goals

Goals:
- Have a uniform behavior thoughout targeted WebDriver implementations
    - A given code should behave as similar as possible in all WebDrivers.
        - Selenium itself takes care of that, but it does leave some room for improvement
        - This is important to our functions as well, they should behave the same regardless of WebDriver implementation (browser) used
- Mimic jQuery's interface and behavior, but...
    - Do it all, when possible, from the user's perspective
        - e.g. `$().val("")` types content instead of setting the `value` attribute.
    - Improve it a little (e.g. throw exception when invalid selectors, such as `"div:file"` are used)
- Add functions that tackle common problems when dealing with web (testing) automation, such as waiting (`$().waitUntil()`)
- Add quick commands for common usage patterns (such as driver builder does)
- Simplify overall usage with convention over configuration

Non-goals:
- Add all jQuery's functions
- Replace WebDriver


## History
- What went bad?
    - The selector system is not pure CSS, it allows the extended CSS supported by jQuery (and implemented by Sizzle). To get it done is a challenge by itself.
        - The first version used regexes, didn't work so well and never made it into a release
        - The second version (released as 0.9.0) converts every CSS selector into a XPath expression and executes it.
            - The problem with this approach is that not every CSS can be translated into an equivalent XPath expression (e.g. `:selected`)
        - The third version (currently under development) will parse the selector and...
            - If the selector is plain CSS or XPath, use it directly
            - If the selector is an extended CSS that can be translated fully to an XPath expression, than translate it and use it
            - Otherwise, translate the CSS to the XPath expression that brings the smallest numbers of element possible and then iteratively filter the results before returning

## What else?

Feel free to [request, suggest](https://github.com/seleniumQuery/seleniumQuery/issues/new), create pull requests. As said, any opinions/help are more than welcome!

# [seleniumQuery](http://seleniumquery.github.io)

[![Maven Central](https://img.shields.io/maven-central/v/io.github.seleniumquery/seleniumquery.svg)](http://search.maven.org/#search%7Cgav%7C1%7Cg%3A%22io.github.seleniumquery%22%20AND%20a%3A%22seleniumquery%22)

[![Codacy Badge](https://api.codacy.com/project/badge/grade/6f25f5fe245746a4a7a53f426e0e1288)](https://www.codacy.com/app/acdcjunior/seleniumQuery)
[![codecov.io](https://codecov.io/gh/seleniumQuery/seleniumQuery/branch/master/graph/badge.svg)](https://codecov.io/gh/seleniumQuery/seleniumQuery)
[![GitHub license](https://img.shields.io/badge/license-Apache%202-blue.svg)](https://raw.githubusercontent.com/seleniumQuery/seleniumQuery/master/LICENSE.txt)
[![Join the chat at https://gitter.im/seleniumQuery/seleniumQuery](https://badges.gitter.im/seleniumQuery/seleniumQuery.svg)](https://gitter.im/seleniumQuery/seleniumQuery?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

[![Build Status](https://travis-ci.org/seleniumQuery/seleniumQuery.svg?branch=master)](https://travis-ci.org/seleniumQuery/seleniumQuery)
[![Windows Build Status](https://ci.appveyor.com/api/projects/status/mwvctg5o8ws7l7jg?svg=true)](https://ci.appveyor.com/project/acdcjunior/seleniumQuery/branch/master)
[![Build status](https://codeship.com/projects/7b37d0c0-d5b4-0133-1efe-62329e93051f/status?branch=master)](https://codeship.com/projects/142644)
[![wercker status](https://app.wercker.com/status/b772beb5c952865d659e548bf7d64f48/s "wercker status")](https://app.wercker.com/project/bykey/b772beb5c952865d659e548bf7d64f48)
[![Circle CI](https://circleci.com/gh/seleniumQuery/seleniumQuery.svg?style=svg)](https://circleci.com/gh/seleniumQuery/seleniumQuery)
[![Run Status](https://api.shippable.com/projects/58b5bc1b1304cc0500e0c7b0/badge?branch=master)](https://app.shippable.com/github/seleniumQuery/seleniumQuery)

[![Sauce Test Status](https://saucelabs.com/open_sauce/build_matrix/acdcjunior.svg)](https://saucelabs.com/u/acdcjunior)

### Feature-rich jQuery-like Java interface for Selenium WebDriver

seleniumQuery is a feature-rich *cross-driver* Java library that brings a **jQuery-like** interface for [Selenium WebDriver](http://docs.seleniumhq.org/projects/webdriver/).

It is designed to be a **thin** layer over Selenium. You can use seleniumQuery to manage the WebDriver for you, or you can use seleniumQuery on top of your favorite selenium framework just
to make some cases simpler when needed.

### Example snippet:

```java
// Regular Selenium
WebElement el  = driver.findElement(By.cssSelector(".street"));
String oldStreet = element.getAttribute("value"); // what if ".street" is a <select>? this won't work
element.setAttribute("value", "4th St!")

// seleniumQuery
// getting the value
String oldStreet = $(".street").val(); // works even if it is a <select>, <textarea>, etc.
// setting the value
$("input.street").val("4th St!"); // also would work for a <select>
```

And much more. The example above is of something that has an equivalent in Selenium. Not everything does (many things would require tons of boilerplate in vanilla Selenium).

### No special configuration needed - use seleniumQuery's goodies in your project right now:

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
- **jQuery enhanced selectors** - `$(":text:eq(3)")`, `$(".myClass:contains('My Text!')")`;
- **XPath** - `$("//div/*/label/preceding::*")`;
- and even some own **seleniumQuery selectors**: `$("#myOldDiv").is(":not(:present)")`.

Built using Selenium WebDriver's capabilities, no `jQuery.js` is embedded at the page, no side-effects are generated.

<br><br>

# Quickstart: A running example

Try it out now with the running example below:

```java
import static io.github.seleniumquery.SeleniumQuery.$; // this will allow the short syntax

public class SeleniumQueryExample {
  public static void main(String[] args) {
    // The WebDriver will be instantiated only when first used
    $.driver()
        .useChrome() // sets Chrome as the driver (this is optional, if omitted, will default to HtmlUnit)
        .headless() // configures chrome to be headless
        .autoDriverDownload() // automatically downloads and configures chromedriver.exe
        .autoQuitDriver(); // automatically quits the driver when the JVM shuts down

    // or, instead, use any previously existing driver
    // $.driver().use(myExistingInstanceOfWebDriver);

    // starts the driver (if not started already) and opens the URL
    $.url("http://www.google.com/?hl=en");

    // interact with the page
    $(":text[name='q']").val("seleniumQuery"); // the keys are actually typed!

    // Besides the short syntax and the jQuery behavior you already know,
    // other very useful function in seleniumQuery is .waitUntil(),
    // handy for dealing with user-waiting actions (specially in Ajax enabled pages):

    // the command below waits until the button is visible and then performs a real user click (not just the JS event)
    $(":button[value='Google Search']").waitUntil().isVisible().then().click();

    // this waits for the #resultStats to be visible using a selector and, when it is visible, returns its text content
    String resultsText = $("#resultStats").waitUntil().is(":visible").then().text();

    // .assertThat() functions: fluently asserts that the text contains the string "seconds", ignoring case
    $("#resultStats").assertThat().text().containsIgnoreCase("seconds");

    System.out.println(resultsText);
    // should print something like: About 4,100 results (0.42 seconds)

    // $.quit(); // would quit the driver, but it is not needed as .autoQuitDriver() was used
  }
}
```

To get the latest version of seleniumQuery, add to your **`pom.xml`**:

```xml
<dependency>
    <groupId>io.github.seleniumquery</groupId>
    <artifactId>seleniumquery</artifactId>
    <version>0.20.0</version>
</dependency>
```



<br><br>

## Looking for more examples?

Download and execute the **[seleniumQuery showcase project](https://github.com/acdcjunior/seleniumQuery-showcase)**.
 It contains many demonstrations of what seleniumQuery is capable of.

<br>

# Features

seleniumQuery implements all jQuery functions that are useful to browser manipulation.
On top of it, we add many other useful functions (see `$("selector").waitUntil()` and `$("selector").assertThat()` below).
 
Our main goal is to make emulating user actions and reading the state of pages easier than ever, with a consistent behavior across drivers.


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

<br>

## Waiting (ajax testing) and asserting

`WebDriver`'s `FluentWait` is great, but it requires too much boilerplate code. Enters the `.waitUntil()` function:

```java
// Below is an example of a <div> that should be hidden as effect of an Ajax call.
// The code will hold until the modal is gone. If it is never gone, seleniumQuery will throw a timeout exception
$("#modalDiv :button:contains('OK')").click();
$("#modalDiv :button:contains('OK')").waitUntil().is(":not(:visible)");
// Or the two commands above, fluently:
$("#modalDivOkButton").click().waitUntil().is(":not(:visible)");
```

You can also **assert** directly into the seleniumQuery object using `.assertThat()`:

```java
$("#modalDiv :button:contains('OK')").assertThat().is(":not(:visible)");
$("#myInput").assertThat().val().isBlank();
```

*Any* function that can be used with `$().waitUntil()` can also be used with `$().assertThat()` and vice-versa.
See below, expand (click on the arrow) each item for more details.

<table>
<tr>
    <th>$(). function</th>
    <th>Property/Evaluation Function</th>
    <th>Evaluation Function</th>
</tr>
<tr>
<td rowspan="2">
<details><summary><code>.waitUntil()</code></summary>

In order to handle interactions with Ajax-enabled pages, you can use the `.waitUntil()` function:

- The `.waitUntil()` functions will *requery* the DOM for the elements until the given condition is met, returning a **new** seleniumQuery object when that happens.

```java
// .waitUntil() will requery the DOM every time until the matched set fulfills the requirements

// .is() functions
$(".aDivDiv").waitUntil().is(":present");
$(".myInput").waitUntil().is(":enabled");
$(".aDivDiv").waitUntil().is(":visible");
$(".myInput").waitUntil().is(":visible:enabled");
// functions such as .val(), .text() and others are also available
$(".myInput").waitUntil().val().isEqualTo("expectedValue");
$(".aDivDiv").waitUntil().text().contains("expectedText");
// and more...
$(".myInput").waitUntil().val().matches(".*\d{10}\*");
$(".myInput").waitUntil().size().isGreaterThan(7);
$(".aDivDiv").waitUntil().html().contains("<div>expected</div>");
```

</details>
<details><summary><code>.assertThat()</code></summary>

Asserts, fluently, that the function has a specified value or matches a specified condition.

```java
$("#stuff").assertThat().val().isEqualTo("expectedValue");
$(".m-e").assertThat().attr("attrName").isEqualTo("expectedValue");
$("span").assertThat().size().isGreaterThan(7);
$("#age").assertThat().val().matches(".*\d{10}\*");
$("#ipt").assertThat().val().matches(value -> value.length() > 50)
```

</details>
</td>
<td>
<details><summary><code>.val()</code></summary>

    $(".myInput").assertThat().val().isEqualTo("expectedValue");

</details>
<details><summary><code>.text()</code></summary>

    $(".div").assertThat().text().isEqualTo("expectedValue");

</details>
<details><summary><code>.attr("attrName")</code></summary>

    $(".myInput").assertThat().attr("attrName").isEqualTo("expectedValue");

</details>
<details><summary><code>.prop("propName")</code></summary>

    $(".myInput").assertThat().prop("propName").isEqualTo("expectedValue");

</details>
<details><summary><code>.html()</code></summary>

    $(".div").assertThat().html().isEqualTo("expectedValue");

</details>
<details><summary><code>.size()</code></summary>

    $(".div").assertThat().size().isEqualTo(0);

</details>
</td>
<td>
<details><summary><code>.isEqualTo("string" | &lt;number> | other)</code></summary>

    $(".myInput").assertThat().val().isEqualTo("expectedValue");

</details>
<details><summary><code>.isBlank()</code></summary>

Tests if the result of the preceding function <b>is empty (`""`), `null` or whitespace only</b>:

    (null).isBlank()      = true
    ("").isBlank()        = true
    (" ").isBlank()       = true
    ("bob").isBlank()     = false
    ("  bob  ").isBlank() = false

Example:

    $(".myInput").assertThat().text().isBlank();

</details>
<details><summary><code>.isGreaterThan(&lt;number>)</code></summary>

    $(".myInput").assertThat().size().isGreaterThan(7);

</details>
<details><summary><code>.isLessThan(&lt;number>)</code></summary>

    $(".myInput").assertThat().size().isGreaterThan(7);

</details>
<details><summary><code>.contains("string")</code></summary>

    $(".aDivDiv").waitUntil().html().contains("<div>expected</div>");
    $(".aDivDiv").assertThat().text().contains("expectedText");

</details>
<details><summary><code>.containsIgnoreCase("string")</code></summary>

    $(".aDivDiv").assertThat().text().containsIgnoreCase("eXpeCTedText");

</details>
<details><summary><code>.matches("string regex")</code></summary>

    $(".myInput").assertThat().val().matches(".*\d{10}\*");
    $("...").waitUntil().html().matches("my[0-9]regex.*?");

</details>
<details><summary><code>.matches(java.util.Pattern)</code></summary>

    $("...").assertThat().val().matches(java.util.Pattern);

</details>
<details><summary><code>.matches(&lt;Hamcrest Matcher>)</code></summary>

    $("#myDiv").waitUntil().text().matches(Matchers.containsString("John"));

</details>
<details><summary><code>.matches(&lt;lambda predicate>)</code></summary>

```java
$("#ipt").waitUntil().val().matches(value -> value.length() > 50)
```

</details>
</td>
</tr>
<tr>
<td colspan="2">
<details><summary><code>.is("selector")</code></summary>

    $(".myInput").assertThat().is(":disabled");
    $(".myInput").assertThat().is(":visible:enabled");

</details>
<details><summary><code>.isEmpty()</code></summary>

Evaluates if the size of this seleniumQuery is equal to zero.
     
     $("div").waitUntil().isEmpty();
     $("div").assertThat().isEmpty();

</details>
<details><summary><code>.isNotEmpty()</code></summary>

Evaluates if the size of this seleniumQuery is greated than zero.
     
     $("div").waitUntil().isNotEmpty();
     $("div").assertThat().isNotEmpty();

</details>
<details><summary><code>.isPresent()</code></summary>

Evaluates if this seleniumQuery object has elements (is not empty).

Note: this is an alias to `.isNotEmpty()`.
     
     $("div").waitUntil().isPresent();
     $("div").assertThat().isPresent();

</details>
<details><summary><code>.isVisible()</code></summary>

Evaluates if this seleniumQuery object has <b>only visible</b> elements.

Note: this is different from `.is(":visible")` because `.is()` requires only one
    element to match the selector (to be visible), whereas this `.isVisible()` method
    requires <b>all</b> matched elements to be visible.

    $("span.all-visible").waitUntil().isVisible();
    $("span.all-visible").assertThat().isVisible();

</details>
<details><summary><code>.isDisplayed()</code></summary>

Evaluates if this seleniumQuery object has <b>only visible</b> elements.

Note: this is different from `.is(":visible")` because `.is()` requires only one
    element to match the selector (to be visible), whereas this `.isVisible()` method
    requires <b>all</b> matched elements to be visible.

This is an alias to `.isVisible()`.

    $("span.all-visible").waitUntil().isDisplayed();
    $("span.all-visible").assertThat().isDisplayed();

</details>
<details><summary><code>.isHidden()</code></summary>

Evaluates if this seleniumQuery object is <b>not empty</b> and has <b>only hidden</b> elements.

Note: while `.isNotVisible()` considers an empty set a success, this method doesn't.

    $("span.non-empty-and-all-hidden").waitUntil().isHidden();
    $("span.non-empty-and-all-hidden").assertThat().isHidden();

</details>
<details><summary><code>.isNotVisible()</code></summary>

Evaluates if this seleniumQuery object is <b>empty</b> or has <b>only hidden</b> elements.

Note: while `.isHidden()` considers an empty set a failure, this method doesn't.

    $("span.empty-or-all-hidden").waitUntil().isNotVisible();
    $("span.empty-or-all-hidden").assertThat().isNotVisible();

</details>
</td>
</tr>
</table>

You can also chain calls using `.and()`:

    $("span").assertThat().size().isGreaterThan(5).and().text().isEqualTo("a b c d e");
    
Or use functions after waiting/asserting using `.then()`:

    $("#div").waitUntil().isVisible().then().click();

<br><br>


## Flexible WebDriver builder system

How to setup the `WebDriver`? Simply use our builder. You can download [their executables](https://github.com/SeleniumHQ/selenium/wiki/ChromeDriver) before or you can
let seleniumQuery automatically download and configure them. Setup in seleniumQuery is all too easy:
 
```java
// Using Chrome, general example:
$.driver()
    .useChrome() // configures Chrome as the driver
    .headless() // configures Chrome to run in headless mode
    .autoDriverDownload() // automatically downloads and configures chromedriver.exe
    .autoQuitDriver(); // automatically quits the driver when the JVM shuts down

// Using Firefox
$.driver()
    .useFirefox() // configures Firefox as the driver
    .headless() // configures Firefox to run in headless mode
    .autoDriverDownload() // automatically downloads and configures geckodriver.exe
    .autoQuitDriver(); // automatically quits the driver when the JVM shuts down
```

For more examples, options and all supported drivers, see table below.

<table>
<tr>
<td>

![Existing WebDriver Instance](doc/webdriver.png)</td><td>

### Using seleniumQuery in an ***existing*** `WebDriver` instance

The driver builder functions are a bonus, you don't *have* to use them. For seleniumQuery, it makes no difference.

If you want to create the `WebDriver` yourself or **add seleniumQuery to an existing `WebDriver` instance** just:

```java
WebDriver myExistingDriverInstance = ...; // created elsewhere by whoever

$.driver().use(myExistingDriverInstance); // from now on, $ will work on myExistingDriverInstance

// now you can use all of seleniumQuery's power!
$("#phone").assertThat().val().isEqualTo("99887766");
```

</td>
</tr>
<tr>
<td>

![chrome](doc/chrome.png)</td><td>

### Chrome

Here's how seleniumQuery can simplify a `ChromeDriver` instantiation.

**Headless** mode available. **Automatic driver download** available.

<details><summary><code>.useChrome()</code></summary>

Configures Chrome as the driver to be used. Provides additional configuration options that can be chained.

Calling `$.driver().useChrome()` does not instantiate the driver right away, it merely configures it.
The driver will be instantiated when first used only (e.g. when `$.url("http://somepage")` is called).

</details>
<details><summary><code>.headless()</code></summary>

Runs Chrome in headless mode.

</details>
<details><summary><code>.autoQuitDriver()</code></summary>

Automatically quits the driver upon JVM shutdown.

If you don't use `.autoQuitDriver()`, you can quit the driver calling `$.quit()`.

Having `.autoQuitDriver()` on and still calling `$.quit()` makes no harm, so quit at will. 

</details>
<details><summary><code>.autoDriverDownload()</code></summary>

seleniumQuery automatically downloads and configures the driver. The download is managed by [webdrivermanager](https://github.com/bonigarcia/webdrivermanager).
Additional options are available, pass a lambda to `.autoDriverDownload(<labda>)` to be able to configure:

```java
$.driver().useChrome().autoDriverDownload((BrowserManager configurer) -> {
    // se more options at https://github.com/bonigarcia/webdrivermanager#webdrivermanager-api
    configurer.proxy("http://corpproxy:8182");
    configurer.proxyUser("myUser");
    configurer.proxyPass("myPass")
});
```

</details>
<details><summary><code>.withOptions(ChromeOptions)</code></summary>

Enables additional configuration through a `ChromeOptions` instance.

</details>
<details><summary><code>.withPathToChromeDriver(String)</code></summary>

If you don't want seleniumQuery to automatically download the executable for you (using `.autoDriverDownload()`) you can
specify the location of `chromedriver.exe`/`chromedriver` yourself:

```java
$.driver().useChrome().withPathToChromeDriver("path/to/chromedriver.exe");
```

If you use neither `.autoDriverDownload()` nor `.withPathToChromeDriver()`, seleniumQuery will attempt to find the executable
on your PATH or classpath. If it doesn't find it anywhere, an exception will be thrown.

</details>
<details><summary><code>.withCapabilities(DesiredCapabilities)</code></summary>

Configures the given `DesiredCapabilities` in the driver to be instantiated. Prefer `ChromeOptions` when possible.

</details>

<br>

```java
// Using Chrome
$.driver().useChrome(); // will look for chromedriver/exe to you, including in the classpath!
// if you don't have chromedriver.exe and want seleniumQuery to auto download and configure it
$.driver().useChrome().headless().autoDriverDownload();
// If you want to set the path to chromedriver.exe yourself
$.driver().useChrome().withPathToChromeDriver("path/to/chromedriver.exe")
// General example:
$.driver()
    .useChrome() // configures Chrome as the driver
    .headless() // configures Chrome to run in headless mode
    .autoDriverDownload() // automatically downloads and configures chromedriver.exe
    .autoQuitDriver(); // automatically quits the driver when the JVM shuts down
// using options
$.driver()
    .useChrome()
    .withOptions(<some ChromeOptions instance>)
```

</td>
</tr>
<tr>
<td>

![firefox](doc/firefox.png)</td><td>

### Firefox

Easy `FirefoxDriver` instantiation and configuration with seleniumQuery.

**Headless** mode available. **Automatic driver download** available.

<details><summary><code>.useFirefox()</code></summary>

Configures Firefox as the driver to be used. Provides additional configuration options that can be chained.

Calling `$.driver().useFirefox()` does not instantiate the driver right away, it merely configures it.
The driver will be instantiated when first used only (e.g. when `$.url("http://somepage")` is called).

</details>
<details><summary><code>.headless()</code></summary>

Runs Firefox in headless mode.

</details>
<details><summary><code>.autoQuitDriver()</code></summary>

Automatically quits the driver upon JVM shutdown.

If you don't use `.autoQuitDriver()`, you can quit the driver calling `$.quit()`.

Having `.autoQuitDriver()` on and still calling `$.quit()` makes no harm, so quit at will. 

</details>
<details><summary><code>.autoDriverDownload()</code></summary>

seleniumQuery automatically downloads and configures the driver. The download is managed by [webdrivermanager](https://github.com/bonigarcia/webdrivermanager).
Additional options are available, pass a lambda to `.autoDriverDownload(<labda>)` to be able to configure:

```java
$.driver().useFirefox().autoDriverDownload((BrowserManager configurer) -> {
    // se more options at https://github.com/bonigarcia/webdrivermanager#webdrivermanager-api
    configurer.proxy("http://corpproxy:8182");
    configurer.proxyUser("myUser");
    configurer.proxyPass("myPass")
});
```

</details>
<details><summary><code>.withOptions(FirefoxOptions)</code></summary>

Enables additional configuration through a `FirefoxOptions` instance.

</details>
<details><summary><code>.withBinary(FirefoxBinary)</code></summary>

Enables additional configuration through a `FirefoxBinary` instance.

</details>
<details><summary><code>.withProfile(FirefoxProfile)</code></summary>

Enables additional configuration through a `FirefoxProfile` instance.

</details>
<details><summary><code>.withCapabilities(DesiredCapabilities)</code></summary>

Configures the given `DesiredCapabilities` in the driver to be instantiated. Prefer `FirefoxOptions` when possible.

</details>

<br>

```java
// Using Firefox
$.driver()
    .useFirefox() // configures Firefox as the driver
    .headless() // configures Firefox to run in headless mode
    .autoDriverDownload() // automatically downloads and configures geckodriver.exe
    .autoQuitDriver(); // automatically quits the driver when the JVM shuts down
// simplified setting of profile, options and binary
$.driver()
    .useFirefox()
    .withProfile(<an instance of FirefoxProfile>)
    .withOptions(<an instance of FirefoxOptions>)
    .withBinary(<an instance of FirefoxBinary>);
```

</td>
</tr>
<tr>
<td>

![opera](doc/opera.png)</td><td>

### Opera

 **Automatic driver download** available.

<details><summary><code>.useOpera()</code></summary>

Configures Opera as the driver to be used. Provides additional configuration options that can be chained.

Calling `$.driver().useOpera()` does not instantiate the driver right away, it merely configures it.
The driver will be instantiated when first used only (e.g. when `$.url("http://somepage")` is called).

</details>
<details><summary><code>.autoQuitDriver()</code></summary>

Automatically quits the driver upon JVM shutdown.

If you don't use `.autoQuitDriver()`, you can quit the driver calling `$.quit()`.

Having `.autoQuitDriver()` on and still calling `$.quit()` makes no harm, so quit at will. 

</details>
<details><summary><code>.autoDriverDownload()</code></summary>

seleniumQuery automatically downloads and configures the driver. The download is managed by [webdrivermanager](https://github.com/bonigarcia/webdrivermanager).
Additional options are available, pass a lambda to `.autoDriverDownload(<labda>)` to be able to configure:

```java
$.driver().useOpera().autoDriverDownload((BrowserManager configurer) -> {
    // se more options at https://github.com/bonigarcia/webdrivermanager#webdrivermanager-api
    configurer.proxy("http://corpproxy:8182");
    configurer.proxyUser("myUser");
    configurer.proxyPass("myPass")
});
```

</details>
<details><summary><code>.withOptions(OperaOptions)</code></summary>

Enables additional configuration through a `OperaOptions` instance.

</details>
<details><summary><code>.withBinary(string | File)</code></summary>

Configures the Opera browser binary location. Example:

```java
$.driver().useOpera().autoDriverDownload()
    .withBinary("C:/Program Files/Opera/49.0.2725.47/opera.exe");
```

</details>
<details><summary><code>.withCapabilities(DesiredCapabilities)</code></summary>

Configures the given `DesiredCapabilities` in the driver to be instantiated. Prefer `OperaOptions` when possible.

</details>

<br>

```java
// Opera
// we'll download the driver for you
$.driver().useOpera().autoDriverDownload();
// simplified setting of options and binary
$.driver()
    .useOpera()
    .withOptions(<an instance of OperaOptions>)
    .withBinary("C:/Program Files/Opera/49.0.2725.47/opera.exe") // example path
    .autoDriverDownload();
```

</td>
</tr>
<tr>
<td>

![PhantomJS](doc/phantomjs.png)</td><td>

### PhantomJS

**Always headless**, webkit-based. **Automatic executable download** available.

<details><summary><code>.usePhantomJS()</code></summary>

Configures PhantomJS as the driver to be used. Provides additional configuration options that can be chained.

Calling `$.driver().usePhantomJS()` does not instantiate the driver right away, it merely configures it.
The driver will be instantiated when first used only (e.g. when `$.url("http://somepage")` is called).

</details>
<details><summary><code>.autoQuitDriver()</code></summary>

Automatically quits the driver upon JVM shutdown.

If you don't use `.autoQuitDriver()`, you can quit the driver calling `$.quit()`.

Having `.autoQuitDriver()` on and still calling `$.quit()` makes no harm, so quit at will. 

</details>
<details><summary><code>.autoDriverDownload()</code></summary>

seleniumQuery automatically downloads and configures the driver. The download is managed by [webdrivermanager](https://github.com/bonigarcia/webdrivermanager).
Additional options are available, pass a lambda to `.autoDriverDownload(<labda>)` to be able to configure:

```java
$.driver().usePhantomJS().autoDriverDownload((BrowserManager configurer) -> {
    // se more options at https://github.com/bonigarcia/webdrivermanager#webdrivermanager-api
    configurer.proxy("http://corpproxy:8182");
    configurer.proxyUser("myUser");
    configurer.proxyPass("myPass")
});
```

</details>
<details><summary><code>.withPathToPhantomJS(String)</code></summary>

If you don't want seleniumQuery to automatically download the executable for you (using `.autoDriverDownload()`) you can
specify the location of `phantomjs.exe`/`phantomjs` yourself:

```java
$.driver().usePhantomJS().withPathToPhantomJS("path/to/phantomjs.exe");
```

If you use neither `.autoDriverDownload()` nor `.withPathToPhantomJS()`, seleniumQuery will attempt to find the executable
on your PATH or classpath. If it doesn't find it anywhere, an exception will be thrown.

</details>
<details><summary><code>.withCapabilities(DesiredCapabilities)</code></summary>

Configures the given `DesiredCapabilities` in the driver to be instantiated.

</details>

<br>

```java
// PhantomJS (GhostDriver)
// we'll download phantomjs.exe for you
$.driver().usePhantomJS().autoDriverDownload();
// or, we may find phantomjs[.exe] for you, throwing an error if not present
$.driver().usePhantomJS();  
// Or you may set the path yourself
$.driver().usePhantomJS().withPathToPhantomJS("path/to/phantomjs.exe");
```

</td>
</tr>
<tr>
<td>

![HtmlUnit](doc/htmlunit.png)</td><td>

### HmtlUnit

**Always headless**, java-based. No need to download anything.

<details><summary><code>.useHtmlUnit()</code></summary>

Configures HmtlUnit as the driver to be used. Provides additional configuration options that can be chained.

Calling `$.driver().useHtmlUnit()` does not instantiate the driver right away, it merely configures it.
The driver will be instantiated when first used only (e.g. when `$.url("http://somepage")` is called).

</details>
<details><summary><code>.autoQuitDriver()</code></summary>

Automatically quits the driver upon JVM shutdown.

If you don't use `.autoQuitDriver()`, you can quit the driver calling `$.quit()`.

Having `.autoQuitDriver()` on and still calling `$.quit()` makes no harm, so quit at will.

**Note:** Since HtmlUnit is a java-based driver, it will quit upon JVM shutdown anyway. 

</details>
<details><summary><code>.withoutJavaScript()</code></summary>

Runs HtmlUnit with disabled JavaScript.

</details>
<details><summary><code>.emulatingChrome()</code></summary>

Configures HtmlUnit to emulate Chrome.

</details>
<details><summary><code>.emulatingFirefox()</code></summary>

Configures HtmlUnit to emulate Firefox.

</details>
<details><summary><code>.emulatingInternetExplorer()</code></summary>

Configures HtmlUnit to emulate Internet Explorer.

</details>
<details><summary><code>.withCapabilities(DesiredCapabilities)</code></summary>

Configures the given `DesiredCapabilities` in the driver to be instantiated.

</details>

<br>

```java
// There are many possibilities to set up HtmlUnitDriver
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
$.driver().useHtmlUnit().emulatingInternetExplorer(); // will pick latest IE
```

</td>
</tr>
<tr>
<td>

![safari](doc/safari.png)</td><td>

### Safari

seleniumQuery tests Safari as a **remote driver**.

```java
$.driver().useDriver(new SafariDriver());
```

</td>
</tr>
<tr>
<td>

![edge](doc/edge.png)</td><td>

### Edge

**Automatic driver download** available.

<details><summary><code>.useEdge()</code></summary>

Configures Edge as the driver to be used. Provides additional configuration options that can be chained.

Calling `$.driver().useEdge()` does not instantiate the driver right away, it merely configures it.
The driver will be instantiated when first used only (e.g. when `$.url("http://somepage")` is called).

</details>
<details><summary><code>.autoQuitDriver()</code></summary>

Automatically quits the driver upon JVM shutdown.

If you don't use `.autoQuitDriver()`, you can quit the driver calling `$.quit()`.

Having `.autoQuitDriver()` on and still calling `$.quit()` makes no harm, so quit at will. 

</details>
<details><summary><code>.autoDriverDownload()</code></summary>

seleniumQuery automatically downloads and configures the driver. The download is managed by [webdrivermanager](https://github.com/bonigarcia/webdrivermanager).
Additional options are available, pass a lambda to `.autoDriverDownload(<labda>)` to be able to configure:

```java
$.driver().useEdge().autoDriverDownload((BrowserManager configurer) -> {
    // se more options at https://github.com/bonigarcia/webdrivermanager#webdrivermanager-api
    configurer.proxy("http://corpproxy:8182");
    configurer.proxyUser("myUser");
    configurer.proxyPass("myPass")
});
```

</details>
<details><summary><code>.withOptions(EdgeOptions)</code></summary>

Enables additional configuration through a `EdgeOptions` instance.

</details>
<details><summary><code>.withCapabilities(DesiredCapabilities)</code></summary>

Configures the given `DesiredCapabilities` in the driver to be instantiated. Prefer `EdgeOptions` when possible.

</details>

<br>

```java
// Edge
// we'll download the driver for you
$.driver().useEdge().autoDriverDownload();
// simplified setting of options
$.driver()
    .useEdge()
    .withOptions(<an instance of EdgeOptions>);
```

</td>
</tr>
<tr>
<td>

![internet explorer](doc/ie.png)</td><td>

### Internet Explorer

**Automatic driver download** available. Additional info about configuration [can be found in our IE Driver wiki](https://github.com/seleniumQuery/seleniumQuery/wiki/seleniumQuery-and-IE-Driver).

<details><summary><code>.useInternetExplorer()</code></summary>

Configures Internet Explorer as the driver to be used. Provides additional configuration options that can be chained.

Calling `$.driver().useInternetExplorer()` does not instantiate the driver right away, it merely configures it.
The driver will be instantiated when first used only (e.g. when `$.url("http://somepage")` is called).

</details>
<details><summary><code>.autoQuitDriver()</code></summary>

Automatically quits the driver upon JVM shutdown.

If you don't use `.autoQuitDriver()`, you can quit the driver calling `$.quit()`.

Having `.autoQuitDriver()` on and still calling `$.quit()` makes no harm, so quit at will. 

</details>
<details><summary><code>.autoDriverDownload()</code></summary>

seleniumQuery automatically downloads and configures the driver. The download is managed by [webdrivermanager](https://github.com/bonigarcia/webdrivermanager).
Additional options are available, pass a lambda to `.autoDriverDownload(<labda>)` to be able to configure:

```java
$.driver().useInternetExplorer().autoDriverDownload((BrowserManager configurer) -> {
    // se more options at https://github.com/bonigarcia/webdrivermanager#webdrivermanager-api
    configurer.proxy("http://corpproxy:8182");
    configurer.proxyUser("myUser");
    configurer.proxyPass("myPass")
});
```

</details>
<details><summary><code>.withPathToIEDriverServerExe(String)</code></summary>

If you don't want seleniumQuery to automatically download the executable for you (using `.autoDriverDownload()`) you can
specify the location of `IEDriverServer.exe` yourself:

```java
$.driver().useInternetExplorer().withPathToIEDriverServerExe("C:\\IEDriverServer.exe");
```

If you use neither `.autoDriverDownload()` nor `.withPathToIEDriverServerExe()`, seleniumQuery will attempt to find `IEDriverServer.exe`
on your PATH or classpath. If it doesn't find it anywhere, an exception will be thrown.

</details>
<details><summary><code>.withCapabilities(DesiredCapabilities)</code></summary>

Configures the given `DesiredCapabilities` in the driver to be instantiated.

</details>

<br>

```java
// InternetExplorerDriver
// we'll download the driver for you
$.driver().useInternetExplorer().autoDriverDownload();
// or we search IEDriverServer.exe on your computer (path and classpash) for you
$.driver().useInternetExplorer();
// Or you set the path yourself
$.driver().useInternetExplorer().withPathToIEDriverServerExe("C:\\IEDriverServer.exe");
```

</td>
</tr>
</table>

<br>

### Available `$("selector").functions()`

Check the [javadocs for our `$().functions`](https://static.javadoc.io/io.github.seleniumquery/seleniumquery/0.19.1/index.html?io/github/seleniumquery/SeleniumQueryObject.html).

More info also in our [API wiki page](https://github.com/seleniumQuery/seleniumQuery/wiki/seleniumQuery-API).

<br>

### Available `$.functions()`

Check the [javadocs for our `$.functions`](https://static.javadoc.io/io.github.seleniumquery/seleniumquery/0.19.1/index.html?io/github/seleniumquery/browser/BrowserFunctions.html).

Read about our global functions in the [API wiki page](https://github.com/seleniumQuery/seleniumQuery/wiki/seleniumQuery-API).

<br>

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
<br>

## seleniumQuery still is Selenium - with "just" a jQuery interface

So there is a important aspect of it: Although our functions yield the same result as if you were using jQuery, remember we always execute them from the user perspective.
In other words, when you call:
```java
$(":input[name='email']").val("seleniumQuery@example.com");
```

We don't change  the `value` attribute directly like jQuery does. We actually do as a user would: We **clear** the input
and **type, key by key**, the string provided as argument!

And we go the *extra mile* whenever possible:
- Our **`$().val()` even works on `contenteditable` elements AND `documentMode=on <iframe>`s**: They don't have `value`, but we type the text in them, again, key by key, as an user would;
- If it is an `<input type="file">` we select the file;
- When the element is a `<select>`, we choose the `<option>` by the value given (same as `$("selector").as().select().selectByValue("123")`).

### Always from the user perspective

On the same tone, when selecting/checking `<option>`s or checkboxes or radios, try not to use `$().prop("selected", true)` directly to them (which to work, of course, would need JS to be enabled on the driver).
Do as an user would: call `.click()`! Or, better yet, use seleniumQuery's `.as().select()` functions: `$().as().select().selectByVisibleText("My Option")` or `$().as().select().selectByValue("123")`.

<br>
<br>

# Using multiple browsers/drivers simultaneously

Typically, the `$` is a static variable, thus every command you issue only affects the one same instance of WebDriver.

But... what if you want/need to use two WebDrivers at the same time?

We've got your back, see the [example](https://github.com/seleniumQuery/seleniumQuery-showcase/blob/master/src/main/java/browser/MultipleBrowsersExample.java#L27-L39):

```java
public static void main(String[] args) {
  String demoPage = "https://cdn.rawgit.com/seleniumQuery/seleniumQuery-showcase/master/Agent.html";

  // using two drivers (chrome and firefox) at the same time
  SeleniumQueryBrowser chrome = new SeleniumQueryBrowser();
  chrome.$.driver().useHtmlUnit().emulatingChrome().autoQuitDriver();
  chrome.$.url(demoPage);

  SeleniumQueryBrowser firefox = new SeleniumQueryBrowser();
  firefox.$.driver().useHtmlUnit().emulatingFirefox().autoQuitDriver();
  firefox.$.url(demoPage);

  chrome.$("#agent").assertThat().text().contains("Chrome");
  firefox.$("#agent").assertThat().text().contains("Firefox");
}
```

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

# Contributing or Requesting Features

The tool quite simple, so there's a lot of room for improvement. If you think something would be useful for you, it
 would probably be useful to us all, [tell us what you're thinking](https://github.com/seleniumQuery/seleniumQuery/issues/new)!


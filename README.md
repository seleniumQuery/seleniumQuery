#seleniumQuery - Cross-Driver jQuery in Selenium

http://seleniumquery.github.io

###Cross-Driver (Cross-Browser) jQuery-like native Java interface for Selenium WebDriver

seleniumQuery is a Java library/framework that intends to bring a "cross-driver" (cross-browser) **jQuery-like interface in** (pure) **Java** for [Selenium WebDriver](http://docs.seleniumhq.org/projects/webdriver/).

Example snippet:

```java
// getting the value
String oldStreet = $("input.street").val();
// setting the value
$("input.street").val("4th St!");
```

Allows querying elements by:

- **XPath** - `$("//div/*/label")`
- **CSS Selectors** - `$(".myClass")`,
- **jQuery/Sizzle enhancements** - `:eq()`: `$(".myClass:eq(3)")`, `:contains()`: `$(".myClass:contains('My Text!')")`
- and even some own **seleniumQuery selectors**: `$("#myOldDiv").is(":not(:present)")`.

Built using Selenium WebDriver's native capabilities **only**:

- No `jQuery.js` is embedded at the page, no side-effects are generated;
    - Doesn't matter if the page uses jQuery or not (or even if the JavaScript global variable `$` is other library like `Prototype.js`).
- Capable of handling/testing **JavaScript-disabled pages**
    - Test pages that use [Unobtrusive JavaScript!](http://en.wikipedia.org/wiki/Unobtrusive_JavaScript)
    - Most functions don't even require a browser/driver with JavaScript enabled!
        - Exceptions are functions like `.trigger()` which naturally require JavaScript to execute and some that don't have "human-user" equivalent, like `.html()`, in the sense that ordinary users don't generally care about the HTML of the page.


##Quickstart: A running example

Try it out now with the running example below:

```java
import static io.github.seleniumquery.SeleniumQuery.$; // this will allow the short syntax

import org.openqa.selenium.firefox.FirefoxDriver;

public class SeleniumQueryExample {
    public static void main(String[] args) {
        $.browser.setDefaultDriverAsFirefox(); // sets Firefox as the browser used by $()
        
        $.browser.openUrl("http://www.google.com");
        
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

        $.browser.quitDefaultBrowser(); // quits the firefox driver
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
```

#Features

###Readable jQuery syntax code you already know

Make your code/tests more readable and easier to maintain. Leverage your knowledge of jQuery.

```java
// Instead of regular Selenium code:
WebElement element = driver.findElement(By.id("mySelect"));
new Select(element).selectByValue("ford");

// You can have the same effect writing just:
$("#mySelect").val("ford");
```


###Waiting capabilities for improved Ajax testing

Other important feature is the leverage of `WebDriver`'s `FluentWait` capabilities **directly** in the element (so long, boilerplate code!) through the use of the `.waitUntil()` function:

```java
/*
 * Selenium WebDriver cannot natively detect the end of an Ajax call.
 * To test your application behaviour, you can and should work with the Ajax's expected effects.
 * 
 * Below is an example of a <div> that should be hidden as effect of an Ajax call.
 * The code will only continue after it is gone. If not, it will throw a timeout exception.
 */
$("#ajaxDiv").click();
$("#ajaxDiv").waitUntil().is(":not(:visible)");

// Or, fluently:
$("#ajaxDiv").click().waitUntil().is(":not(:visible)");
```

And, yeah, that's right, the `.is()` function above is your old-time friend that takes a selector as argument!

#API

For the currently implemented jQuery functions check the [supported list below](#supported-jquery-functions).

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

Global object (static) functions:

- `$.browser.openUrl("http://www.url.to.go.com");`: Opens a URL
- `$.browser.openUrl(new File("path/to/localFile.html"));`: Opens a local file
- `$.browser.setDefaultBrowser(webDriver);`: Sets the browser to be used by `$(".selector")`
- `$.browser.sleep(10, TimeUnit.SECONDS);`: Instructs the browser (thread) to wait (sleep) for the given time.

###Alternative symbols

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

#CSS and jQuery Extension Selectors

Below the list of supported and noteworthy CSS and jQuery Extension selectors.

As expected, the note jQuery usually displays in their extensions applies to seleniumQuery as well: Queries using the extended selectors cannot take advantage of the performance boost provided by the native DOM `querySelectorAll()` method. This way, if your code has performance issues, you may achive faster results by using native CSS selectors.

- [`[name!="value"]` - Attribute Not Equal](http://api.jquery.com/attribute-not-equal-selector/) - Select elements that either don't have the specified attribute, or do have the specified attribute but not with a certain value.
- [`:button`](http://api.jquery.com/button-selector/) - Selects all `button` elements and elements of type `button`.
- [`:checkbox`](http://api.jquery.com/checkbox-selector/) - Selects all elements of type `checkbox`.
- [`:checked`](https://api.jquery.com/checked-selector/) - Matches all elements that are checked or selected.
- [`:contains()`](http://api.jquery.com/contains-selector/) - Select all elements that contain the specified text.
- [`:disabled`](http://api.jquery.com/disabled-selector/) - Selects all elements that are disabled.
- [`:enabled`](http://api.jquery.com/enabled-selector/) - Selects all elements that are enabled.
- [`:eq()`](http://api.jquery.com/eq-selector/) - Select the element at index `n` within the matched set.
- ~~[`:even`](http://api.jquery.com/even-selector/) - Selects even elements, zero-indexed. See also `:odd`.~~
- [`:file`](http://api.jquery.com/file-selector/) - Selects all elements of type `file`.
- [`:first`](http://api.jquery.com/first-selector/) -  Selects the first matched element.
- [`:focus`](http://api.jquery.com/focus-selector/) - Selects element if it is currently focused.
- [`:focusable`](http://api.jqueryui.com/focusable-selector/) - **From [jQuery UI](http://api.jqueryui.com/category/selectors/)**. Selects elements which can be focused.
- [`:gt()`](http://api.jquery.com/gt-selector/) -  Select all elements at an index greater than the given `index` within the matched set.
- [`:has()`](http://api.jquery.com/has-selector/) - Selects elements which contain at least one element that matches the specified selector.
- [`:header`](http://api.jquery.com/header-selector/) - Selects all elements that are headers, like `h1`, `h2`, `h3` and so on.
- [`:hidden`](http://api.jquery.com/hidden-selector/) - Selects all elements that are hidden.
- [`:image`](http://api.jquery.com/image-selector/) -  Selects all elements of type image.
- [`:input`](http://api.jquery.com/input-selector/) - Selects all `input`, `textarea`, `select` and `button` elements.
- [`:last`](http://api.jquery.com/last-selector/) - Selects the last matched element.
- [`:lt()`](http://api.jquery.com/lt-selector/) - Select all elements at an index less than the given `index` within the matched set.
- ~~[`:odd`](http://api.jquery.com/odd-selector/) -  Selects odd elements, zero-indexed. See also even.~~
- [`:password`](http://api.jquery.com/password-selector/) - Selects all elements of type `password`.
- ~~[`:parent`](http://api.jquery.com/parent-selector/) - Select all elements that have at least one child node (either an element or text).~~
- [`:radio`](http://api.jquery.com/radio-selector/) - Selects all elements of type `radio`.
- [`:reset`](http://api.jquery.com/reset-selector/) - Selects all elements of type `reset`.
- [`:selected`](http://api.jquery.com/selected-selector/) - Selects all elements that are selected.
- [`:submit`](http://api.jquery.com/submit-selector/) - Selects all elements of type `submit`.
- [`:tabbable`](http://api.jqueryui.com/tabbable-selector/) - **From [jQuery UI](http://api.jqueryui.com/category/selectors/)**. Selects elements which the user can focus via tabbing.
- ~~[`:text`](http://api.jquery.com/text-selector/) - Selects all elements of type `text`.~~
- [`:visible`](http://api.jquery.com/visible-selector/) - Selects all elements that are visible.

###Extra - seleniumQuery only selectors

- `:present` - Matches all elements that are **attached to the DOM**. This is a very important property in Selenium page handling, as detached elements cannot be interacted with - they'd throw the infamous `StaleElementReferenceException`.

#Supported jQuery Functions

As seleniumQuery main goals are emulating user actions and "sensing" the pages, currently our intention is to implement functions that read (that's what we mean by "sense") the state of the page plus those that manipulate forms.

By following the principle above, supporting functions like `.val()` and `.find()` are among our priorities, whereas `.addClass()` and `.attr('attributeName', 'attributeValue')` are not.

Some functions, specially those that require JavaScript enabled in the browser/driver, take the best-case approach, so they may have some small differences in specific versions of some browsers. Adding cross-driver/cross-browser support is among our goals, though keep in mind that user-emulating functions (as stated before) are our priorities and problems with them are likely to be fixed first. Usually, still, we will keep on fixing cross-driver issues as they are reported/found.

Below you will find the list of currently supported jQuery functions, by category.

Looking for a function not listed below? Check the [`ROADMAP.md`](ROADMAP.md) file. The functions we did not add in the list below were either considered not applicable (like `jQuery.noConflict()` or `.data()`) or of no use (as the [Ajax](http://api.jquery.com/category/ajax/) functions: why would anyone want to issue an Ajax function directly/explicitly through selenium? Usually, ajax in selenium is related to waiting for the browser to end Ajax calls. For that, check the `.waitUntil()`  function).

##[Attributes](http://api.jquery.com/category/attributes/)

- [`.attr()`](http://api.jquery.com/attr/) - Get the value of an attribute for the first element in the set of matched elements or set one or more attributes for every matched element.
- [`.hasClass()`](http://api.jquery.com/hasClass/) - Determine whether any of the matched elements are assigned the given class.
- [`.html()`](http://api.jquery.com/html/) - Get the HTML contents of the first element in the set of matched elements or set the HTML contents of every matched element.
- [`.prop()`](http://api.jquery.com/prop/) - Get the value of a property for the first element in the set of matched elements or set one or more properties for every matched element.
- [`.removeAttr()`](http://api.jquery.com/removeAttr/) - Remove an attribute from each element in the set of matched elements.
- [`.val()`](http://api.jquery.com/val/) - Get the current value of the first element in the set of matched elements or set the value of every matched element.

##[CSS](http://api.jquery.com/category/css/)

- [`.hasClass()`](http://api.jquery.com/hasClass/) - Determine whether any of the matched elements are assigned the given class.

##[Events](http://api.jquery.com/category/events/)

- [`.trigger()`](http://api.jquery.com/trigger/) - Execute all handlers and behaviors attached to the matched elements for the given event type.
- [`.click()`](http://api.jquery.com/click/) - Trigger the "click" JavaScript event on the matched elements.
- [`.focus()`](http://api.jquery.com/focus/) - Trigger the "focus" JavaScript event on the matched elements.

###Soon

- [`.keyup()`](http://api.jquery.com/keyup/) - Trigger the "keyup" JavaScript event on the matched elements.
- [`.blur()`](http://api.jquery.com/blur/) - Trigger the "blur" JavaScript event on the matched elements.

##[Forms](http://api.jquery.com/category/forms/)

- [`.val()`](http://api.jquery.com/val/) - Get the current value of the first element in the set of matched elements or set the value of every matched element.

##[Miscellaneous](http://api.jquery.com/category/miscellaneous/)

- [`.get()`](http://api.jquery.com/get/) - Retrieve the DOM elements matched by the jQuery object.
- [`.size()`](http://api.jquery.com/size/) - Return the number of elements in the jQuery object.
- [`.toArray()`](http://api.jquery.com/toArray/) - Retrieve all the elements contained in the jQuery set, as an array.

- [`.each()`](http://api.jquery.com/each/) - Iterate over a jQuery object, executing a function for each matched element.
    - The `.each()` function does not really exist: `$()` object is an `Iterable`, so you can use a Java **foreach** loop.
        - Example: `for (WebElement divElement : $("div")) { ... }`

##[Traversing functions](http://api.jquery.com/category/traversing/)

- [`.closest()`](http://api.jquery.com/closest/) - For each element in the set, get the first element that matches the selector by testing the element itself and traversing up through its ancestors in the DOM tree.
- [`.end()`](http://api.jquery.com/end/) - End the most recent filtering operation in the current chain and return the set of matched elements to its previous state.
- [`.find()`](http://api.jquery.com/find/) - Get the descendants of each element in the current set of matched elements, filtered by a selector, jQuery object, or element.
- [`.first()`](http://api.jquery.com/first/) - Reduce the set of matched elements to the first in the set.
- [`.is()`](http://api.jquery.com/is/) - Check the current matched set of elements against a selector, element, or jQuery object and return true if at least one of these elements matches the given arguments.
- [`.not()`](http://api.jquery.com/not/) - Remove elements from the set of matched elements.
- [`.parent()`](http://api.jquery.com/parent/) - Get the parent of each element in the current set of matched elements, optionally filtered by a selector.

###Soon

- [`.children()`](http://api.jquery.com/children/) - Get the children of each element in the set of matched elements, optionally filtered by a selector.
- [`.eq()`](http://api.jquery.com/eq/) - Reduce the set of matched elements to the one at the specified index.


##[Properties](http://api.jquery.com/category/properties/)

- [`.length`](http://api.jquery.com/length/) - The number of elements in the jQuery object.
    - This functionality is available through the `.size()` function.
- [`.selector`](http://api.jquery.com/selector/) - A selector representing selector passed to jQuery(), if any, when creating the original set.
    - This functionality is available through the `.getBy()` function and, as `.selector` was, depending on the context, is not always available.

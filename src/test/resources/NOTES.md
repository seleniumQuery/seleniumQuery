# General Notes

General notes about the project, including found external issues/bugs, wanted features and TODOs.

# CI Jobs

- Travis (3 jobs)
    - OpenJDK8 @ linux: HtmlUnit + PhantomJS
    - Oracle JDK7 @ linux: HtmlUnit + PhantomJS
    - OpenJDK7 @ linux: HtmlUnit + PhantomJS
- Appveyor
    - Runs at Windows
- Codeship
    - If commit contains `[run sauce]`, it will run e2e tests for all remote (SauceLabs') drivers.
- Wercker
- CircleCI
- [Drone.io](https://drone.io/github.com/seleniumQuery/seleniumQuery)
    - Linux, Java 8, will run e2e tests for JBrowserDriver (not done yet)


# Issues/bugs related to other drives

This files lists **unresolved** bugs or issues related to the drivers of Selenium. Stuff that is outside the scope of seleniumQuery, that we can't have workarounds for.

- HtmlUnitDriver wont trigger the focus event for img or body (even with tabindex) (https://github.com/seleniumQuery/seleniumQuery/issues/7)
- ChromeDriver has a bug on `<object>` and `<embedded>` (https://github.com/seleniumQuery/seleniumQuery/issues/93)


# Wanted Features/Future

- Add `$("#element").contextmenu()`;
    - From [`Actions`](http://selenium.googlecode.com/svn/trunk/docs/api/java/org/openqa/selenium/interactions/Actions.html):

        ```java
        Actions action = new Actions(driver);
        WebElement element = ...;
        action.contextClick(element).perform();
        ```

- Version 1.0.0
    - 1.0.0 should be the first release to have a stable API.
    - Expected functionality:
        - Should have most jQuery extension selectors fully supported
        - .waitUntil() API should be mature
        - All tests should pass for, at least, latest HtmlUnit, Chrome, Firefox, IE and PhantomJS.
        - Stacktraces should be reviewed, in order to provide easy-debugging for the end-user (programmer).


# Technical debt

- add test for .click() without JS
    - Add a form, and test clicking a submit button, just like `.submit()` is being tested.
- Rework .children() function, so it uses .filter()
    - This is sort of a duplicated functionality there.


-----
#### Other wanted

Stories are not necessarily prioritized.

## Add HtmlUnit EDGE to browserBuilder

HtmlUnit now supports it.

## Have empty selectors `$("")` return an empty list right off the bat

That simple.

## Complete seleniumQuery.properties options file

Add options like:

- Should auto quit (when that functionality is implemented)
- if the `$('.stuff')` has no elements, calling something like `.val('other')` should
	- do nothing silently
	- do nothing, but warn if log level is higher

## `$.url(File)` should fail if argument file does not exist

That would be all of it.

## Chrome driver builder should test for OS when trying to locate executable

This applies to Chrome builder.

External-world description of this issue:
> Driver builder will not look for chromedriver.exe executable if OS is not windows.

## Add `$.driver().useSameAs(SeleniumQueryBrowser)`

Not much to say.

## Add SeleniumQueryBrowser#quitAll()

So we can quit'em all.

## Add more .as() functions

SHOULD BE DIVIDED INTO MORE STORIES WHEN IMPLEMENTING.

`$("selector").as().select().selectByText("Ball");`
`$("selector").as().select().selectByValue("Ball");`
`$("selector").as().select().selectByIndex(1);`
`$("selector").as().select().selectByIndex(-1);`
`$("selector").as().select().getSelectedOptionText();`
`$("selector").as().select().getSelectedOptionValue();`
`$("selector").as().select().getSelectedOptionIndex();`
`$("selector").as().select().getSelectedOptionCount();`
`$("selector").as().select().getOptionCount();`
`$("selector").as().select().size();`
`$("selector").as().select().selectFirst();`
`$("selector").as().select().selectLast();`
`$("selector").as().select().selectMatching(Matcher<String>);`
`$("selector").as().select().selectFirstMatching(Matcher<String>);`

also `as().input()` or `as().radio()` to deal with groups.

`.as().table()`.

## Incorporate functions from `WebDriver#.switchTo()` and `Action`s

Functions from `driver.switchTo()` can deal with frames.

`Actions` has some very useful commands:

    new Actions(driver).moveToElement(elements).click().perform();

Maybe we should add functions in the `SeleniumQueryObject` to incorporate those functionalities and make them easily accessible.

## CSS selectors combination (comma selector) rearranges element order

jQuery brings the elements in the order they are at the DOM.
We currently don't make such promise - though we probably should.
See if we can do better at least for the `,` selector.

See issue #121, it has commits referencing it.

## Add `.withProxy()` to browser builder

See closed issue #67: https://github.com/seleniumQuery/seleniumQuery/issues/67

## allow escaping for tag names

This is super-advanced stuff, don't know if we'll ever have time to make it.

## Enable attribute quoting flexibility

This is a very complex story. Should probably be divided into many.

See (closed) issue #40: https://github.com/seleniumQuery/seleniumQuery/issues/40


## Have `^=` and `$=` match sizzle's behavior for empty strings

```javascript
console.log( $('<div id="myDiv" test=""></div>').is("[test^='']") );
console.log( $('<div id="myDiv" test=""></div>').is("[test$='']") );
console.log( $('<div id="myDiv" test=""></div>').is("[test^=]") );
console.log( $('<div id="myDiv" test=""></div>').is("[test$=]") );
```

All of them return **false** for sizzle. We don't right now.

This should have very low priority.


## Add cache for compiled selectors

Make it cache 50 selectors by default and allow changing in via code and properties file or similar.


## Catch and rethrow CSS parsing exceptions

Example selector: `"#name\\+value."` (see the dot at the end).

Catch it and throw a more readable SeleniumQuery exception.
```
org.w3c.css.sac.CSSParseException: Error in class selector. (Invalid token "<EOF>". Was expecting: <IDENT>.)
	at com.steadystate.css.parser.AbstractSACParser.toCSSParseException(AbstractSACParser.java:251)
	at com.steadystate.css.parser.SACParserCSS3._class(SACParserCSS3.java:1347)
	at com.steadystate.css.parser.SACParserCSS3.simpleSelector(SACParserCSS3.java:1254)
	at com.steadystate.css.parser.SACParserCSS3.selector(SACParserCSS3.java:1156)
	at com.steadystate.css.parser.SACParserCSS3.selectorList(SACParserCSS3.java:1111)
	at com.steadystate.css.parser.SACParserCSS3.parseSelectorsInternal(SACParserCSS3.java:1102)
	at com.steadystate.css.parser.AbstractSACParser.parseSelectors(AbstractSACParser.java:345)
	at com.steadystate.css.parser.SACParserCSS3.parseSelectors(SACParserCSS3.java:23)
	at io.github.seleniumquery.selector.parser.SelectorParser.parseSelector(SelectorParser.java:24)
```


# DONE pseudo classes

```
basicfilter:
	- OK :animated (WILL-NOT-ADD: requires access to jQuery's internals)
	- OK :eq()
	- OK :even
	- OK :first
	- OK :gt()
	- OK :header
	- DELAYED :lang()
	- OK :last
	- OK :lt()
	- :not() ------------------------------------------------------ TODO -- test: $("div:not(div em)")
	- OK :nth
	- OK :odd
	- DELAYED :root
	- DELAYED :target
childfilter
	- OK :first-child
	- DELAYED :first-of-type
	- OK :last-child
	- DELAYED :last-of-type
	- OK :nth-child()
	- OK :nth-last-child()
	- DELAYED :nth-last-of-type()
	- DELAYED :nth-of-type()
	- OK :only-child
	- DELAYED :only-of-type
contentfilter
	- OK :contains()
	- OK :empty
	- :has() ------------------------------------------------------ TODO -- test: #yo div[id]:not(:has(div, span)):not(:has(*))
	- DELAYED :parent
form
	- OK :button
	- OK :checkbox
	- OK :checked
	- OK :disabled
	- OK :enabled
	- OK :file
	- OK :focus
	- OK :image
	- OK :input
	- OK :password
	- OK :radio
	- OK :reset
	- OK :selected
	- OK :submit
	- OK :text
jquery-ui
	- DELAYED :focusable
	- DELAYED :tabbable
seleniumquery
	- OK :present
visibility
    - OK :hidden
    - OK :visible


---

OTHER SELECTORS - SUGGESTIONS (NONE OF THEM ARE IMPLEMENTED, NOR IT MEANS THEY WILL):

	- :blank
	- :filled
	- :unchecked
	As seen in:
    http://jqueryvalidation.org/documentation/
    http://ejohn.org/blog/selectors-that-people-actually-use/
    http://ejohn.org/files/selectors.html

Others not supported by Sizzle:
    Some selectors that are not fully supported by Sizzle: https://developer.mozilla.org/en-US/docs/Web/CSS/:required
    Maybe add them.
    :required and :optional are easy to add. The others, maybe in the future.
```

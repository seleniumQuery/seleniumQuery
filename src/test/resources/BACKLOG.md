There is no prioritization here.#

## Incorporate functions from `WebDriver#.switchTo()` and `Action`s

Functions from `driver.switchTo()` can deal with frames.

`Actions` has some very useful commands:

    new Actions(driver).moveToElement(elements).click().perform();

Maybe we should add functions in the `SeleniumQueryObject` to incorporate those functionalities and make them easily accessible.

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
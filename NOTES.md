# General Notes

General notes about the project, including found external issues/bugs, wanted features and TODOs.

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

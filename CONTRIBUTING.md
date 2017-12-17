# Opening issues

Feel free to open issues with suggestions, questions or anything.

If is a bug, try to describe a reproducible scenario and the error stack trace (if exists).

If the issue is about selenium and not seleniumQuery we will let you know.


<br><br>

# Adding features

Some general tips for adding new functions.

## Creating new functions: `$().myNewFunction();`

- Create function in `SeleniumQueryObject` interface
- Create function implementation class (see `StreamFunction` for example)
- Create function in `SqObject` using the implementation class from example above
- Create impl. test class (such as `StreamFunctionTest`)


# CI

| Driver/Browser          | CI        | Coverage | Docker | Grdl/Mvn | Status |
| ----------------------- | --------- | -------- | ------ | -------- | ------ | 
| Saucelabs `[run sauce]` | Codeship  | No       | No     | Maven    | [![Build status](https://codeship.com/projects/7b37d0c0-d5b4-0133-1efe-62329e93051f/status?branch=master)](https://codeship.com/projects/142644)
| Chrome Not Headless     | Shippable | Yes      | Yes    | Gradle   | [![Run Status](https://api.shippable.com/projects/58b5bc1b1304cc0500e0c7b0/badge?branch=master)](https://app.shippable.com/github/seleniumQuery/seleniumQuery)
| Chrome Headless         | -
| Firefox                 | Appveyor  | Yes      | No     | Maven    | [![Windows Build Status](https://img.shields.io/appveyor/ci/acdcjunior/seleniumQuery/master.svg?label=Windows+Build)](https://ci.appveyor.com/project/acdcjunior/seleniumQuery/branch/master)
| Phantom                 | Travis    | Yes      | No     | Maven    | [![Linux Build Status](https://img.shields.io/travis/seleniumQuery/seleniumQuery/master.svg?label=Linux+Build)](https://travis-ci.org/seleniumQuery/seleniumQuery)
| HtmlUnit JS On          | Wercker   | Yes      | No     | Maven    | [![wercker status](https://app.wercker.com/status/b772beb5c952865d659e548bf7d64f48/s "wercker status")](https://app.wercker.com/project/bykey/b772beb5c952865d659e548bf7d64f48)
| HtmlUnit JS Off         | CircleCI  | Yes      | Yes    | Gradle   | [![Circle CI](https://circleci.com/gh/seleniumQuery/seleniumQuery.svg?style=svg)](https://circleci.com/gh/seleniumQuery/seleniumQuery)
| Edge                    | -
| IE                      | -
| Opera                   | -
| jBrowserDriver          | -

[![Sauce Test Status](https://saucelabs.com/open_sauce/build_matrix/acdcjunior.svg)](https://saucelabs.com/u/acdcjunior)

# Test Coverage

| Tool      | Status |
| --------- | --- |
| codecov   | [![codecov.io](https://codecov.io/gh/seleniumQuery/seleniumQuery/branch/master/graph/badge.svg)](https://codecov.io/gh/seleniumQuery/seleniumQuery)
| shippable | [![Coverage Badge](https://api.shippable.com/projects/58b5bc1b1304cc0500e0c7b0/coverageBadge?branch=master)](https://app.shippable.com/github/seleniumQuery/seleniumQuery)


# About The project...

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
    - Since the selector system supports not only pure CSS (it allows the extended CSS supported by jQuery- and implemented by Sizzle), its implementation is a challenge by itself.
        - The first version used regexes, didn't work so well and never made it into a release
        - The second version (released as 0.9.0) converts every CSS selector into a XPath expression and executes it.
            - The advantage is that this makes Selenium bring every element the user wanted already, without the need to iterate over them or anything.
            - The problem with this approach is that not every CSS can be translated into an equivalent XPath expression (e.g. `:selected` or `:visible`)
        - The third version (currently under development, called "secondgen") will parse the selector and...
            - If the selector is plain CSS or XPath, use it directly
            - If the selector is an extended CSS that can be translated fully to an XPath expression, than translate it and use it
            - Otherwise, translate the CSS to the XPath expression that brings the smallest numbers of element possible and then iteratively filter the results before returning

## What else?

Feel free to [request, suggest](https://github.com/seleniumQuery/seleniumQuery/issues/new), create pull requests. As said, any opinions/help are more than welcome!

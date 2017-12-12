# Opening issues

Feel free to open issues with suggestions, questions or anything.

If is a bug, try to describe a reproducible scenario and the error stack trace (if exists).

If the issue is about selenium and not seleniumQuery we will let you know.


<br><br>

# Adding features

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

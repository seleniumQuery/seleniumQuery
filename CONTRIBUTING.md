
# Adding features

## Creating new functions: $().myNewFunction();

- Create function in `SeleniumQueryObject` interface
- Create function implementation class (see `StreamFunction` for example)
- Create function in `SqObject` using the implementation class from example above
- Create impl. test class (such as `StreamFunctionTest`)


# CI


| CI | Role | Time | Status | Notes |
| --- | --- | --- | --- | --- |
| travis | PhantomJS + HtmlUnit-All | 40m | [![Linux Build Status](https://img.shields.io/travis/seleniumQuery/seleniumQuery/master.svg?label=Linux+Build)](https://travis-ci.org/seleniumQuery/seleniumQuery) | |
| shippable | Docker + Chrome NOT Headless | ??m | [![Run Status](https://api.shippable.com/projects/58b5bc1b1304cc0500e0c7b0/badge?branch=master)](https://app.shippable.com/github/seleniumQuery/seleniumQuery)
| appveyor | Firefox NOT Headless + IE | ??m | [![Windows Build Status](https://img.shields.io/appveyor/ci/acdcjunior/seleniumQuery/master.svg?label=Windows+Build)](https://ci.appveyor.com/project/acdcjunior/seleniumQuery/branch/master)
| codeship | Saucelabs if `[run sauce]`, HtmlUnit-Chrome otherwise | 7m/2h30 | [![Build status](https://codeship.com/projects/7b37d0c0-d5b4-0133-1efe-62329e93051f/status?branch=master)](https://codeship.com/projects/142644)
| wercker | HtmlUnit-All JS On only | 6m |  [![wercker status](https://app.wercker.com/status/b772beb5c952865d659e548bf7d64f48/s "wercker status")](https://app.wercker.com/project/bykey/b772beb5c952865d659e548bf7d64f48) | Times out at 25m, so no big things here
| circleci | Docker + HtmlUnit-Chrome | 9m | [![Circle CI](https://circleci.com/gh/seleniumQuery/seleniumQuery.svg?style=svg)](https://circleci.com/gh/seleniumQuery/seleniumQuery)

[![Sauce Test Status](https://saucelabs.com/open_sauce/build_matrix/acdcjunior.svg)](https://saucelabs.com/u/acdcjunior)

# Teste Coverage

| Tool | Status |
| --- | --- |
| codecov | [![codecov.io](https://codecov.io/gh/seleniumQuery/seleniumQuery/branch/master/graph/badge.svg)](https://codecov.io/gh/seleniumQuery/seleniumQuery)
| shippable | [![Coverage Badge](https://api.shippable.com/projects/58b5bc1b1304cc0500e0c7b0/coverageBadge?branch=master)](https://app.shippable.com/github/seleniumQuery/seleniumQuery)

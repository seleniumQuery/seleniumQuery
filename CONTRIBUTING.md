
# Adding features

## Creating new functions: $().myNewFunction();

- Create function in `SeleniumQueryObject` interface
- Create function implementation class (see `StreamFunction` for example)
- Create function in `SqObject` using the implementation class from example above
- Create impl. test class (such as `StreamFunctionTest`)


# CI


| CI | Role | Time | Status | Notes |
| --- | --- | --- | --- | --- |
| travis | PhantomJS  | 40m | [![Linux Build Status](https://img.shields.io/travis/seleniumQuery/seleniumQuery/master.svg?label=Linux+Build)](https://travis-ci.org/seleniumQuery/seleniumQuery) | |
| shippable | Chrome NOT Headless | ??m | [![Run Status](https://api.shippable.com/projects/58b5bc1b1304cc0500e0c7b0/badge?branch=master)](https://app.shippable.com/github/seleniumQuery/seleniumQuery) | Docker being used
| appveyor | Firefox NOT Headless + IE | ??m | [![Windows Build Status](https://img.shields.io/appveyor/ci/acdcjunior/seleniumQuery/master.svg?label=Windows+Build)](https://ci.appveyor.com/project/acdcjunior/seleniumQuery/branch/master) | Only windows environment
| codeship | if Saucelabs:`[run sauce]`, else:HU/Chrome | 7m/2h30 | [![Build status](https://codeship.com/projects/7b37d0c0-d5b4-0133-1efe-62329e93051f/status?branch=master)](https://codeship.com/projects/142644)
| wercker | HtmlUnit-JS/On | 6m | [![wercker status](https://app.wercker.com/status/b772beb5c952865d659e548bf7d64f48/s "wercker status")](https://app.wercker.com/project/bykey/b772beb5c952865d659e548bf7d64f48) | Jobs timeout at 25m
| circleci | Docker + HtmlUnit-JS/Off | 9m | [![Circle CI](https://circleci.com/gh/seleniumQuery/seleniumQuery.svg?style=svg)](https://circleci.com/gh/seleniumQuery/seleniumQuery) | Docker available

[![Sauce Test Status](https://saucelabs.com/open_sauce/build_matrix/acdcjunior.svg)](https://saucelabs.com/u/acdcjunior)


# Per browser

| Driver/Browser          | CI        | Coverage? | Status |
| ----------------------- | --------- | --------- | --- | 
| Saucelabs `[run sauce]` | Codeship  | No        | [![Build status](https://codeship.com/projects/7b37d0c0-d5b4-0133-1efe-62329e93051f/status?branch=master)](https://codeship.com/projects/142644)
| Chrome Not Headless     | Shippable | MAYBE     | [![Run Status](https://api.shippable.com/projects/58b5bc1b1304cc0500e0c7b0/badge?branch=master)](https://app.shippable.com/github/seleniumQuery/seleniumQuery)
| Chrome Headless         | -
| Firefox                 | Appveyor  | Yes       | [![Windows Build Status](https://img.shields.io/appveyor/ci/acdcjunior/seleniumQuery/master.svg?label=Windows+Build)](https://ci.appveyor.com/project/acdcjunior/seleniumQuery/branch/master)
| Edge                    | Appveyor  | Yes       | [![Windows Build Status](https://img.shields.io/appveyor/ci/acdcjunior/seleniumQuery/master.svg?label=Windows+Build)](https://ci.appveyor.com/project/acdcjunior/seleniumQuery/branch/master)
| Phantom                 | Travis    | Yes       | [![Linux Build Status](https://img.shields.io/travis/seleniumQuery/seleniumQuery/master.svg?label=Linux+Build)](https://travis-ci.org/seleniumQuery/seleniumQuery)
| HtmlUnit JS On          | Wercker   | MAYBE     | [![wercker status](https://app.wercker.com/status/b772beb5c952865d659e548bf7d64f48/s "wercker status")](https://app.wercker.com/project/bykey/b772beb5c952865d659e548bf7d64f48)
| HtmlUnit JS Off         | CircleCI  | No        | [![Circle CI](https://circleci.com/gh/seleniumQuery/seleniumQuery.svg?style=svg)](https://circleci.com/gh/seleniumQuery/seleniumQuery)
| IE                      | -
| Opera                   | -
| jBrowserDriver          | -

# Teste Coverage

| Tool | CIs that update it | Status |
| --- | --- | --- |
| codecov | Travis,Appveyor | [![codecov.io](https://codecov.io/gh/seleniumQuery/seleniumQuery/branch/master/graph/badge.svg)](https://codecov.io/gh/seleniumQuery/seleniumQuery)
| shippable | Not configured | [![Coverage Badge](https://api.shippable.com/projects/58b5bc1b1304cc0500e0c7b0/coverageBadge?branch=master)](https://app.shippable.com/github/seleniumQuery/seleniumQuery)

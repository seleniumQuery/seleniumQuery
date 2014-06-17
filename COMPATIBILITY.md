seleniumQuery aims to have predictable and uniform behavior across drivers/browsers. To accomplish that, it must take
into account every drivers and every browsers' issues.

| Driver             | Driver version | Browser | CSS     | XPath | seleniumQuery's behavior |
| ------------------ | -------------- | ------- | ------- | ----- | ------------------------ |
| IEDriverServer.exe | win32_2.41.0   | IE 6    | Sizzle  | 1.0   | Uses driver's Sizzle
| IEDriverServer.exe | win32_2.41.0   | IE 7    | Sizzle? | 1.0   | ?
| IEDriverServer.exe | win32_2.41.0   | IE 8    | Sizzle? | 1.0   | ?
| IEDriverServer.exe | win32_2.41.0   | IE 9    | Native? | 1.0   | 
| IEDriverServer.exe | win32_2.41.0   | IE 10   | Native? | 1.0   | seleniumQuery default*
| chromedriver.exe   | win32_2.10     | Chrome Latest  | Native  | 2.0   |
| -                  | -              | Firefox Latest | Native  | 2.0   |
| -                  | -              | Firefox 3.4    | ?  | ?   |
| -                  | -              | Phantom JS ?  | ?  | ?   |


##* seleniumQuery default behavior

When given a CSS selector, seleniumQuery parses it into both a CSS enhanced selector and a XPath expression.

If the given CSS selector:
- ...can be dealt by the browser's native CSS, then seleniumQuery handles it to the browser.
- ...has enhanced selectors,
  - seleniumQuery checks if the converted XPath expression can fully handle it.
    - If so, seleniumQuery handles the XPath expression to the browser.
    - If not, seleniumQuery checks if it could be handled by an intersection of the CSS and the XPath results;
      - If it can be handled, seleniumQuery queries through the CSS, queries through the XPath and returns their intersection.
      - If it can't be handled, seleniumQuery picks the selector/expr that has the least element filters**, executes it, and then filters.
      
      
** Element filters iterate through every item in the set, removing them if they don't match the selector.

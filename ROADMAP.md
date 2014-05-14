#Current and Future jQuery Functions Support

As seleniumQuery main goals are emulating user actions and "sensing" the pages, currently our intention is to implement functions that read (that's what we mean by "sense") the state of the page plus those that manipulate forms.

By following the principle above, supporting functions like `.val()` and `.find()` are among our priorities, whereas `.addClass()` and `.attr('attributeName', 'attributeValue')` are not.

Some functions, specially those that require JavaScript enabled in the browser/driver, take the best-case approach, so they may have some small differences in specific versions of some browsers. Adding cross-driver/cross-browser support is among our goals, though keep in mind that user-emulating functions (as stated before) are our priorities and problems with them are likely to be fixed first. Usually, still, we will keep on fixing cross-driver issues as they are reported/found.

Below you will find the list of current jQuery functions, by category, divided among supported and not supported by seleniumQuery.

Looking for a function not listed below? The functions we did not add in the list below were either considered not applicable (like `jQuery.noConflict()` or `.data()`) or of no use (as the [Ajax](http://api.jquery.com/category/ajax/) functions: why would anyone want to issue an Ajax function directly/explicitly through selenium? Usually, ajax in selenium is related to waiting for the browser to end Ajax calls. For that, check the `.queryUntil()` and `.waitUntil()`  functions).

##[Attributes](http://api.jquery.com/category/attributes/)

###Suported

- [`.attr()`](http://api.jquery.com/attr/) - Get the value of an attribute for the first element in the set of matched elements or set one or more attributes for every matched element.
- [`.hasClass()`](http://api.jquery.com/hasClass/) - Determine whether any of the matched elements are assigned the given class.
- [`.html()`](http://api.jquery.com/html/) - Get the HTML contents of the first element in the set of matched elements or set the HTML contents of every matched element.
- [`.prop()`](http://api.jquery.com/prop/) - Get the value of a property for the first element in the set of matched elements or set one or more properties for every matched element.
- [`.removeAttr()`](http://api.jquery.com/removeAttr/) - Remove an attribute from each element in the set of matched elements.
- [`.val()`](http://api.jquery.com/val/) - Get the current value of the first element in the set of matched elements or set the value of every matched element.

###Not supported

- [`.addClass()`](http://api.jquery.com/addClass/) - Adds the specified class(es) to each of the set of matched elements.
- [`.removeClass()`](http://api.jquery.com/removeClass/) - Remove a single class, multiple classes, or all classes from each element in the set of matched elements.
- [`.removeProp()`](http://api.jquery.com/removeProp/) - Remove a property for the set of matched elements.
- [`.toggleClass()`](http://api.jquery.com/toggleClass/) - Add or remove one or more classes from each element in the set of matched elements, depending on either the class’s presence or the value of the switch argument.

##[CSS](http://api.jquery.com/category/css/)

###Supported

- [`.hasClass()`](http://api.jquery.com/hasClass/) - Determine whether any of the matched elements are assigned the given class.

###Not Supported

- [`.addClass()`](http://api.jquery.com/addClass/) - Adds the specified class(es) to each of the set of matched elements.
- [`.css()`](http://api.jquery.com/css/) - Get the value of a style property for the first element in the set of matched elements or set one or more CSS properties for every matched element.
- [`.height()`](http://api.jquery.com/height/) - Get the current computed height for the first element in the set of matched elements or set the height of every matched element.
- [`.innerHeight()`](http://api.jquery.com/innerHeight/) - Get the current computed height for the first element in the set of matched elements, including padding but not border.
- [`.innerWidth()`](http://api.jquery.com/innerWidth/) - Get the current computed inner width (including padding but not border) for the first element in the set of matched elements or set the inner width of every matched element.
- [`.offset()`](http://api.jquery.com/offset/) - Get the current coordinates of the first element, or set the coordinates of every element, in the set of matched elements, relative to the document.
- [`.outerHeight()`](http://api.jquery.com/outerHeight/) - Get the current computed height for the first element in the set of matched elements, including padding, border, and optionally margin. Returns a number (without “px”) representation of the value or null if called on an empty set of elements.
- [`.outerWidth()`](http://api.jquery.com/outerWidth/) - Get the current computed width for the first element in the set of matched elements, including padding and border.
- [`.position()`](http://api.jquery.com/position/) - Get the current coordinates of the first element in the set of matched elements, relative to the offset parent.
- [`.removeClass()`](http://api.jquery.com/removeClass/) - Remove a single class, multiple classes, or all classes from each element in the set of matched elements.
- [`.scrollLeft()`](http://api.jquery.com/scrollLeft/) - Get the current horizontal position of the scroll bar for the first element in the set of matched elements or set the horizontal position of the scroll bar for every matched element.
- [`.scrollTop()`](http://api.jquery.com/scrollTop/) - Get the current vertical position of the scroll bar for the first element in the set of matched elements or set the vertical position of the scroll bar for every matched element.
- [`.toggleClass()`](http://api.jquery.com/toggleClass/) - Add or remove one or more classes from each element in the set of matched elements, depending on either the class’s presence or the value of the switch argument.
- [`.width()`](http://api.jquery.com/width/) - Get the current computed width for the first element in the set of matched elements or set the width of every matched element.

##[Events](http://api.jquery.com/category/events/)

###Supported

- [`.trigger()`](http://api.jquery.com/trigger/) - Execute all handlers and behaviors attached to the matched elements for the given event type.
- [`.click()`](http://api.jquery.com/click/) - Trigger the "click" JavaScript event on the matched elements.
- [`.focus()`](http://api.jquery.com/focus/) - Trigger the "focus" JavaScript event on the matched elements.

###Soon

- [`.keyup()`](http://api.jquery.com/keyup/) - Trigger the "keyup" JavaScript event on the matched elements.
- [`.blur()`](http://api.jquery.com/blur/) - Trigger the "blur" JavaScript event on the matched elements.

###Not supported

- [`.change()`](http://api.jquery.com/change/) - Trigger the "change" JavaScript event on the matched elements.
- [`.dblclick()`](http://api.jquery.com/dblclick/) - Trigger the "dblclick" JavaScript event on the matched elements.
- [`.keydown()`](http://api.jquery.com/keydown/) - Trigger the "keydown" JavaScript event on the matched elements.
- [`.keypress()`](http://api.jquery.com/keypress/) - Trigger the "keypress" JavaScript event on the matched elements.
- [`.mousedown()`](http://api.jquery.com/mousedown/) - Trigger the "mousedown" JavaScript event on the matched elements.
- [`.mouseenter()`](http://api.jquery.com/mouseenter/) - Trigger the "mouseenter" JavaScript event on the matched elements.
- [`.mouseleave()`](http://api.jquery.com/mouseleave/) -  Trigger the "mouseleave" JavaScript event on the matched elements.
- [`.mousemove()`](http://api.jquery.com/mousemove/) - Trigger the "mousemove" JavaScript event on the matched elements.
- [`.mouseout()`](http://api.jquery.com/mouseout/) - Trigger the "mouseout" JavaScript event on the matched elements.
- [`.mouseover()`](http://api.jquery.com/mouseover/) - Trigger the "mouseover" JavaScript event on the matched elements.
- [`.mouseup()`](http://api.jquery.com/mouseup/) - Trigger the "mouseup" JavaScript event on the matched elements.
- [`.resize()`](http://api.jquery.com/resize/) - Trigger the "resize" JavaScript event on the matched elements.
- [`.scroll()`](http://api.jquery.com/scroll/) - Trigger the "scroll" JavaScript event on the matched elements.
- [`.select()`](http://api.jquery.com/select/) - Trigger the "select" JavaScript event on the matched elements.
- [`.submit()`](http://api.jquery.com/submit/) - Trigger the "submit" JavaScript event on the matched elements.

##[Forms](http://api.jquery.com/category/forms/)

###Supported

- [`.val()`](http://api.jquery.com/val/) - Get the current value of the first element in the set of matched elements or set the value of every matched element.

###Not Supported

- [`jQuery.param()`](http://api.jquery.com/jQuery.param/) - Create a serialized representation of an array or object, suitable for use in a URL query string or Ajax request.
- [`.serialize()`](http://api.jquery.com/serialize/) - Encode a set of form elements as a string for submission.
- [`.serializeArray()`](http://api.jquery.com/serializeArray/) - Encode a set of form elements as an array of names and values.

##[Miscellaneous](http://api.jquery.com/category/miscellaneous/)

###Supported

- [`.get()`](http://api.jquery.com/get/) - Retrieve the DOM elements matched by the jQuery object.
- [`.size()`](http://api.jquery.com/size/) - Return the number of elements in the jQuery object.
- [`.toArray()`](http://api.jquery.com/toArray/) - Retrieve all the elements contained in the jQuery set, as an array.

###Not Supported

- [`.each()`](http://api.jquery.com/each/) - Iterate over a jQuery object, executing a function for each matched element.
    - The `$()` object is an `Iterable`, though, so you can use a Java **foreach** loop.
        - Example: `for (WebElement divElement : $("div")) { ... }`
- [`.index()`](http://api.jquery.com/index/) - Search for a given element from among the matched elements.

##[Traversing functions](http://api.jquery.com/category/traversing/)

###Supported

- [`.closest()`](http://api.jquery.com/closest/) - For each element in the set, get the first element that matches the selector by testing the element itself and traversing up through its ancestors in the DOM tree.
- [`.find()`](http://api.jquery.com/find/) - Get the descendants of each element in the current set of matched elements, filtered by a selector, jQuery object, or element.
- [`.first()`](http://api.jquery.com/first/) - Reduce the set of matched elements to the first in the set.
- [`.is()`](http://api.jquery.com/is/) - Check the current matched set of elements against a selector, element, or jQuery object and return true if at least one of these elements matches the given arguments.
- [`.not()`](http://api.jquery.com/not/) - Remove elements from the set of matched elements.
- [`.parent()`](http://api.jquery.com/parent/) - Get the parent of each element in the current set of matched elements, optionally filtered by a selector.

###Soon

- [`.children()`](http://api.jquery.com/children/) - Get the children of each element in the set of matched elements, optionally filtered by a selector.
- [`.eq()`](http://api.jquery.com/eq/) - Reduce the set of matched elements to the one at the specified index.

###Not supported

 - [`.add()`](http://api.jquery.com/add/) - Add elements to the set of matched elements.
 - [`.addBack()`](http://api.jquery.com/addBack/) - Add the previous set of elements on the stack to the current set, optionally filtered by a selector.
 - [`.andSelf()`](http://api.jquery.com/andSelf/) - Add the previous set of elements on the stack to the current set.
 - [`.contents()`](http://api.jquery.com/contents/) - Get the children of each element in the set of matched elements, including text and comment nodes.
 - [`.each()`](http://api.jquery.com/each/) - Iterate over a jQuery object, executing a function for each matched element.
 - [`.end()`](http://api.jquery.com/end/) - End the most recent filtering operation in the current chain and return the set of matched elements to its previous state.
 - [`.filter()`](http://api.jquery.com/filter/) - Reduce the set of matched elements to those that match the selector or pass the function’s test.
 - [`.has()`](http://api.jquery.com/has/) - Reduce the set of matched elements to those that have a descendant that matches the selector or DOM element.
 - [`.last()`](http://api.jquery.com/last/) - Reduce the set of matched elements to the final one in the set.
 - [`.map()`](http://api.jquery.com/map/) - Pass each element in the current matched set through a function, producing a new jQuery object containing the return values.
 - [`.next()`](http://api.jquery.com/next/) - Get the immediately following sibling of each element in the set of matched elements. If a selector is provided, it retrieves the next sibling only if it matches that selector.
 - [`.nextAll()`](http://api.jquery.com/nextAll/) - Get all following siblings of each element in the set of matched elements, optionally filtered by a selector.
 - [`.nextUntil()`](http://api.jquery.com/nextUntil/) - Get all following siblings of each element up to but not including the element matched by the selector, DOM node, or jQuery object passed.
 - [`.offsetParent()`](http://api.jquery.com/offsetParent/) - Get the closest ancestor element that is positioned.
 - [`.parents()`](http://api.jquery.com/parents/) - Get the ancestors of each element in the current set of matched elements, optionally filtered by a selector.
 - [`.parentsUntil()`](http://api.jquery.com/parentsUntil/) - Get the ancestors of each element in the current set of matched elements, up to but not including the element matched by the selector, DOM node, or jQuery object.
 - [`.prev()`](http://api.jquery.com/prev/) - Get the immediately preceding sibling of each element in the set of matched elements, optionally filtered by a selector.
 - [`.prevAll()`](http://api.jquery.com/prevAll/) - Get all preceding siblings of each element in the set of matched elements, optionally filtered by a selector.
 - [`.prevUntil()`](http://api.jquery.com/prevUntil/) - Get all preceding siblings of each element up to but not including the element matched by the selector, DOM node, or jQuery object.
 - [`.siblings()`](http://api.jquery.com/siblings/) - Get the siblings of each element in the set of matched elements, optionally filtered by a selector.
 - [`.slice()`](http://api.jquery.com/slice/) - Reduce the set of matched elements to a subset specified by a range of indices.

##[Properties](http://api.jquery.com/category/properties/)

- [`.length`](http://api.jquery.com/length/) - The number of elements in the jQuery object.
    - This functionality is available through the `.size()` function.
- [`.selector`](http://api.jquery.com/selector/) - A selector representing selector passed to jQuery(), if any, when creating the original set.
    - This functionality is available through the `.getBy()` function and, as `.selector` was, depending on the context, is not always available.

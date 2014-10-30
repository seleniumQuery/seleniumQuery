#jQuery API and seleniumQuery Support

Below the current jQuery API and, when applicable, comments about their support (if any) under seleniumQuery.

###Selectors

- [`All Selector (“*”)`](http://api.jquery.com/all-selector/) - Selects all elements.
- ~~[`:animated Selector`](http://api.jquery.com/animated-selector/) - Select all elements that are in the progress of an animation at the time the selector is run.~~
- [`Attribute Contains Prefix Selector [name|="value"]`](http://api.jquery.com/attribute-contains-prefix-selector/) - Selects elements that have the specified attribute with a value either equal to a given string or starting with that string followed by a hyphen (-).
- [`Attribute Contains Selector [name*="value"]`](http://api.jquery.com/attribute-contains-selector/) - Selects elements that have the specified attribute with a value containing the a given substring.
- [`Attribute Contains Word Selector [name~="value"]`](http://api.jquery.com/attribute-contains-word-selector/) - Selects elements that have the specified attribute with a value containing a given word, delimited by spaces.
- [`Attribute Ends With Selector [name$="value"]`](http://api.jquery.com/attribute-ends-with-selector/) - Selects elements that have the specified attribute with a value ending exactly with a given string. The comparison is case sensitive.
- [`Attribute Equals Selector [name="value"]`](http://api.jquery.com/attribute-equals-selector/) - Selects elements that have the specified attribute with a value exactly equal to a certain value.
- [`Attribute Not Equal Selector [name!="value"]`](http://api.jquery.com/attribute-not-equal-selector/) - Select elements that either don’t have the specified attribute, or do have the specified attribute but not with a certain value.
- [`Attribute Starts With Selector [name^="value"]`](http://api.jquery.com/attribute-starts-with-selector/) - Selects elements that have the specified attribute with a value beginning exactly with a given string.
- [`:button Selector`](http://api.jquery.com/button-selector/) - Selects all button elements and elements of type button.
- [`:checkbox Selector`](http://api.jquery.com/checkbox-selector/) - Selects all elements of type checkbox.
- [`:checked Selector`](http://api.jquery.com/checked-selector/) - Matches all elements that are checked or selected.
- [`Child Selector (“parent &gt; child”)`](http://api.jquery.com/child-selector/) - Selects all direct child elements specified by “child” of elements specified by “parent”.
- [`Class Selector (“.class”)`](http://api.jquery.com/class-selector/) - Selects all elements with the given class.
- [`:contains() Selector`](http://api.jquery.com/contains-selector/) - Select all elements that contain the specified text.
- [`Descendant Selector (“ancestor descendant”)`](http://api.jquery.com/descendant-selector/) - Selects all elements that are descendants of a given ancestor.
- [`:disabled Selector`](http://api.jquery.com/disabled-selector/) - Selects all elements that are disabled.
- [`Element Selector (“element”)`](http://api.jquery.com/element-selector/) - Selects all elements with the given tag name.
- [`:empty Selector`](http://api.jquery.com/empty-selector/) - Select all elements that have no children (including text nodes).
- [`:enabled Selector`](http://api.jquery.com/enabled-selector/) - Selects all elements that are enabled.
- [`:eq() Selector`](http://api.jquery.com/eq-selector/) - Select the element at index n within the matched set.
- [`:even Selector`](http://api.jquery.com/even-selector/) - Selects even elements, zero-indexed.  See also odd.
- [`:file Selector`](http://api.jquery.com/file-selector/) - Selects all elements of type file.
- [`:first-child Selector`](http://api.jquery.com/first-child-selector/) - Selects all elements that are the first child of their parent.
- [`:first-of-type Selector`](http://api.jquery.com/first-of-type-selector/) - Selects all elements that are the first among siblings of the same element name.
- [`:first Selector`](http://api.jquery.com/first-selector/) - Selects the first matched element.
- [`:focus Selector`](http://api.jquery.com/focus-selector/) - Selects element if it is currently focused.
- [`:gt() Selector`](http://api.jquery.com/gt-selector/) - Select all elements at an index greater than index within the matched set.
- [`Has Attribute Selector [name]`](http://api.jquery.com/has-attribute-selector/) - Selects elements that have the specified attribute, with any value.
- [`:has() Selector`](http://api.jquery.com/has-selector/) - Selects elements which contain at least one element that matches the specified selector.
- [`:header Selector`](http://api.jquery.com/header-selector/) - Selects all elements that are headers, like h1, h2, h3 and so on.
- [`:hidden Selector`](http://api.jquery.com/hidden-selector/) - Selects all elements that are hidden.
- [`ID Selector (“#id”)`](http://api.jquery.com/id-selector/) - Selects a single element with the given id attribute.
- [`:image Selector`](http://api.jquery.com/image-selector/) - Selects all elements of type image.
- [`:input Selector`](http://api.jquery.com/input-selector/) - Selects all input, textarea, select and button elements.
- [`:lang() Selector`](http://api.jquery.com/lang-selector/) - Selects all elements of the specified language.
- [`:last-child Selector`](http://api.jquery.com/last-child-selector/) - Selects all elements that are the last child of their parent.
- [`:last-of-type Selector`](http://api.jquery.com/last-of-type-selector/) - Selects all elements that are the last among siblings of the same element name.
- [`:last Selector`](http://api.jquery.com/last-selector/) - Selects the last matched element.
- [`:lt() Selector`](http://api.jquery.com/lt-selector/) - Select all elements at an index less than index within the matched set.
- [`Multiple Attribute Selector [name="value"][name2="value2"]`](http://api.jquery.com/multiple-attribute-selector/) - Matches elements that match all of the specified attribute filters.
- [`Multiple Selector (“selector1, selector2, selectorN”)`](http://api.jquery.com/multiple-selector/) - Selects the combined results of all the specified selectors.
- [`Next Adjacent Selector (“prev + next”)`](http://api.jquery.com/next-adjacent-Selector/) - Selects all next elements matching “next” that are immediately preceded by a sibling “prev”.
- [`Next Siblings Selector (“prev ~ siblings”)`](http://api.jquery.com/next-siblings-selector/) - Selects all sibling elements that follow after the “prev” element, have the same parent, and match the filtering “siblings” selector.
- [`:not() Selector`](http://api.jquery.com/not-selector/) - Selects all elements that do not match the given selector.
- [`:nth-child() Selector`](http://api.jquery.com/nth-child-selector/) - Selects all elements that are the nth-child of their parent.
- [`:nth-last-child() Selector`](http://api.jquery.com/nth-last-child-selector/) - Selects all elements that are the nth-child of their parent, counting from the last element to the first.
- [`:nth-last-of-type() Selector`](http://api.jquery.com/nth-last-of-type-selector/) - Selects all elements that are the nth-child of their parent, counting from the last element to the first.
- [`:nth-of-type() Selector`](http://api.jquery.com/nth-of-type-selector/) - Selects all elements that are the nth child of their parent in relation to siblings with the same element name.
- [`:odd Selector`](http://api.jquery.com/odd-selector/) - Selects odd elements, zero-indexed.  See also even.
- [`:only-child Selector`](http://api.jquery.com/only-child-selector/) - Selects all elements that are the only child of their parent.
- [`:only-of-type Selector`](http://api.jquery.com/only-of-type-selector/) - Selects all elements that have no siblings with the same element name.
- [`:parent Selector`](http://api.jquery.com/parent-selector/) - Select all elements that have at least one child node (either an element or text).
- [`:password Selector`](http://api.jquery.com/password-selector/) - Selects all elements of type password.
- [`:radio Selector`](http://api.jquery.com/radio-selector/) - Selects all  elements of type radio.
- [`:reset Selector`](http://api.jquery.com/reset-selector/) - Selects all elements of type reset.
- [`:root Selector`](http://api.jquery.com/root-selector/) - Selects the element that is the root of the document.
- [`:selected Selector`](http://api.jquery.com/selected-selector/) - Selects all elements that are selected.
- [`:submit Selector`](http://api.jquery.com/submit-selector/) - Selects all elements of type submit.
- [`:target Selector`](http://api.jquery.com/target-selector/) - Selects the target element indicated by the fragment identifier of the document’s URI.
- [`:text Selector`](http://api.jquery.com/text-selector/) - Selects all elements of type text.
- [`:visible Selector`](http://api.jquery.com/visible-selector/) - Selects all elements that are visible.
 
- [`[name!="value"]` - Attribute Not Equal](http://api.jquery.com/attribute-not-equal-selector/) - Select elements that either don't have the specified attribute, or do have the specified attribute but not with a certain value.
- [`:button`](http://api.jquery.com/button-selector/) - Selects all `button` elements and elements of type `button`.
- [`:checkbox`](http://api.jquery.com/checkbox-selector/) - Selects all elements of type `checkbox`.
- [`:checked`](https://api.jquery.com/checked-selector/) - Matches all elements that are checked or selected.
- [`:contains()`](http://api.jquery.com/contains-selector/) - Select all elements that contain the specified text.
- [`:disabled`](http://api.jquery.com/disabled-selector/) - Selects all elements that are disabled.
- [`:empty`](http://api.jquery.com/empty-selector/) - Select all elements that have no children (including text nodes). See also `:parent`.
- [`:enabled`](http://api.jquery.com/enabled-selector/) - Selects all elements that are enabled.
- [`:eq()`](http://api.jquery.com/eq-selector/) - Select the element at index `n` within the matched set.
- [`:even`](http://api.jquery.com/even-selector/) - Selects even elements, zero-indexed. See also `:odd`.
- [`:file`](http://api.jquery.com/file-selector/) - Selects all elements of type `file`.
- [`:first`](http://api.jquery.com/first-selector/) -  Selects the first matched element.
- [`:gt()`](http://api.jquery.com/gt-selector/) -  Select all elements at an index greater than the given `index` within the matched set.
- [`:has()`](http://api.jquery.com/has-selector/) - Selects elements which contain at least one element that matches the specified selector.
- [`:header`](http://api.jquery.com/header-selector/) - Selects all elements that are headers, like `h1`, `h2`, `h3` and so on.
- [`:image`](http://api.jquery.com/image-selector/) -  Selects all elements of type `image`.
- [`:input`](http://api.jquery.com/input-selector/) - Selects all `input`, `textarea`, `select` and `button` elements.
- [`:last`](http://api.jquery.com/last-selector/) - Selects the last matched element.
- [`:lt()`](http://api.jquery.com/lt-selector/) - Select all elements at an index less than the given `index` within the matched set.
- [`:odd`](http://api.jquery.com/odd-selector/) -  Selects odd elements, zero-indexed. See also `:even`.
- [`:password`](http://api.jquery.com/password-selector/) - Selects all elements of type `password`.
- [`:parent`](http://api.jquery.com/parent-selector/) - Select all elements that have at least one child node (either an element or text). See `:empty`.
- [`:radio`](http://api.jquery.com/radio-selector/) - Selects all elements of type `radio`.
- [`:reset`](http://api.jquery.com/reset-selector/) - Selects all elements of type `reset`.
- [`:selected`](http://api.jquery.com/selected-selector/) - Selects all elements that are selected.
- [`:submit`](http://api.jquery.com/submit-selector/) - Selects all elements of type `submit`.
- [`:text`](http://api.jquery.com/text-selector/) - Selects all elements of type `text`.

###Extra - seleniumQuery only selectors

- `:present` - Matches all elements that are **attached to the DOM**. This is a very important property in Selenium page handling, as detached elements cannot be interacted with - they'd throw the infamous `StaleElementReferenceException`.

##Unsupported selectors

They are only unsupported in the `$(selector)` constructor function. **They are still available through the `.is()` function.** That is, `$("#x").is(":hidden")` works as expected!

###jQuery Extension

- [~~`:focus`~~](http://api.jquery.com/focus-selector/) - Selects element if it is currently focused.
- [~~`:focusable`~~](http://api.jqueryui.com/focusable-selector/) - **From [jQuery UI](http://api.jqueryui.com/category/selectors/)**. Selects elements which can be focused.
- [~~`:hidden`~~](http://api.jquery.com/hidden-selector/) - Selects all elements that are hidden.
- [~~`:tabbable`~~](http://api.jqueryui.com/tabbable-selector/) - **From [jQuery UI](http://api.jqueryui.com/category/selectors/)**. Selects elements which the user can focus via tabbing.
- [~~`:visible`~~](http://api.jquery.com/visible-selector/) - Selects all elements that are visible.

###CSS3

- [~~`:only-of-type`~~](https://developer.mozilla.org/en-US/docs/Web/CSS/:only-of-type) - Selects all elements that have no siblings of the given type.

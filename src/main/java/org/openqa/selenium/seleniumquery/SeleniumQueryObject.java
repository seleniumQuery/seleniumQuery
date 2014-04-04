package org.openqa.selenium.seleniumquery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.seleniumquery.by.SeleniumQueryBy;
import org.openqa.selenium.seleniumquery.functions.ClickFunction;
import org.openqa.selenium.seleniumquery.functions.FirstFunction;
import org.openqa.selenium.seleniumquery.functions.NotFunction;
import org.openqa.selenium.seleniumquery.functions.TextFunction;
import org.openqa.selenium.seleniumquery.functions.ValFunction;
import org.openqa.selenium.seleniumquery.wait.SeleniumQueryQueryUntil;
import org.openqa.selenium.seleniumquery.wait.SeleniumQueryWaitUntil;

/**
 * Represents the SeleniumQuery Object: a list of WebElements with special methods.
 * <p>
 * A seleniumQuery object (similar to the regular JavaScript jQuery object) contains a collection of
 * Selenium {@link WebElement}s (JavaScript jQuery is a collection of DOM elements) that have been <b>selected
 * from a document</b>. Since seleniumQuery methods often use CSS selectors to match elements from a document,
 * the set of elements in a seleniumQuery object is often called a set of <i>"matched elements"</i> or <i>"selected elements"</i>.
 * </p>
 * <p>
 * The seleniumQuery object itself behaves much like a {@link List}; it has a {@link SeleniumQueryObject#size()} method and the
 * elements in the list can be accessed by their numeric indices through {@link SeleniumQueryObject#get()}.
 * </p>
 * <p>
 * Just like jQuery, many seleniumQuery methods return the seleniumQuery object itself, so that method calls can be chained.
 * </p>
 * <p>
 * In API calls that return a seleniumQuery object, the value returned will be the original seleniumQuery object unless
 * otherwise documented by that API. API methods such as {@link SeleniumQueryObject#first()} or {@link SeleniumQueryObject#not()}
 * modify their incoming set and thus return a <b>new</b> seleniumQuery object.
 * </p>
 * Whenever you use a "destructive" seleniumQuery method that potentially changes the set of elements in the seleniumQuery object,
 * such as {@link SeleniumQueryObject#first()} or {@link SeleniumQueryObject#not()}, that method actually returns a new seleniumQuery
 * object with the resulting elements. To return to the previous seleniumQuery object, you use the {@link SeleniumQueryObject#end()} method.
 * </p>
 * <p>
 * A seleniumQuery object may be empty, containing no elements. It is not an error; any further methods called on that
 * seleniumQuery object simply have no effect since they have no elements to act upon.
 * </p>
 *  
 * @author acdcjunior
 * @since 0.2.0
 */
public class SeleniumQueryObject implements Iterable<WebElement> {
	
	private SeleniumQueryBy by;
	private WebDriver driver;
	public List<WebElement> elements;
	
	/**
	 * The previous (or "father") element, meaning this SeleniumQueryObject was created as result
	 * of calling a "destructive" function (such as <code>.not()</code>) on that element.<br>
	 * This property is retrieved by a call to <code>.end()</code>.
	 * 
	 * @author acdcjunior
	 * @since 0.3.0
	 */
	private SeleniumQueryObject previous;
	
	/**
	 * List of functions that will halt the execution until the specified condition is met.
	 * 
	 * @author acdcjunior
	 * @since 0.4.0
	 */
	public final SeleniumQueryWaitUntil waitUntil() {
		return new SeleniumQueryWaitUntil(this);
	}
	
	/**
	 * List of functions that will halt the execution and requery the selector until the specified condition is met, returning
	 * a new seleniumQuery object at the end.
	 * 
	 * @author acdcjunior
	 * @since 0.4.0
	 */
	public final SeleniumQueryQueryUntil queryUntil() {
		return new SeleniumQueryQueryUntil(this);
	}
	
	
	
	
	/**************************************************************************************************************************************
	 * Java SeleniumQueryObject constrcutors
	 **************************************************************************************************************************************/
	
	public SeleniumQueryObject(WebDriver driver, String selector) {
		this(driver, selector, driver.findElements(SeleniumQueryBy.byEnhancedSelector(selector)));
	}
	
	public SeleniumQueryObject(WebDriver driver, String selector, List<WebElement> webElements) {
		this(driver, selector, webElements, null);
	}

	public SeleniumQueryObject(WebDriver driver, String selector, List<WebElement> webElements, SeleniumQueryObject previous) {
		this.driver = driver;
		this.by = SeleniumQueryBy.byEnhancedSelector(selector);
		this.elements = webElements;
		this.previous = previous;
	}
	
	SeleniumQueryObject(WebDriver driver, List<WebElement> webElements, SeleniumQueryObject previous) {
		this.driver = driver;
		this.by = SeleniumQueryBy.NO_SELECTOR_INVALID_BY;
		this.elements = webElements;
		this.previous = previous;
	}
	
	public SeleniumQueryObject(WebDriver driver, WebElement element) {
		this(driver, new ArrayList<WebElement>(Arrays.asList(element)), null);
	}
	
	public SeleniumQueryObject(WebDriver driver, WebElement element, SeleniumQueryObject previous) {
		this(driver, new ArrayList<WebElement>(Arrays.asList(element)), previous);
	}
	
	/**************************************************************************************************************************************
	 * Java SeleniumQueryObject specific functions
	 **************************************************************************************************************************************/
	
	@Override
	public Iterator<WebElement> iterator() {
		return this.elements.iterator();
	}

	/**
	 * Returns the driver used to create this seleniumQuery object.
	 * @return the driver used to create this seleniumQuery object.
	 */
	public WebDriver getWebDriver() {
		return this.driver;
	}
	
	/**
	 * Returns the By used to search the matched elements of this seleniumQuery object.
	 * @return the By used to search the matched elements of this seleniumQuery object.
	 */
	public SeleniumQueryBy getBy() {
		return this.by;
	}


	
	/**************************************************************************************************************************************
	 * jQuery-emulating functions
	 **************************************************************************************************************************************/

	/**
	 * Returns the number of elements in the seleniumQuery object.
	 * @return the number of elements in the seleniumQuery object.
	 * 
	 * @author acdcjunior
	 * @since 0.2.0
	 */
	public int size() {
		return this.elements.size();
	}
	
	/**
	 * @since 0.2.0
	 */
	public SeleniumQueryObject not(String selector) {
		List<WebElement> filteredElements = NotFunction.not(this, this.elements, selector);
		return new SeleniumQueryObject(this.getWebDriver(), filteredElements, this);
	}

	/**
	 * @since 0.2.0
	 */
	public SeleniumQueryObject first() {
		return FirstFunction.first(this, this.elements);
	}

	/**
	 * @since 0.2.0
	 */
	public String text() {
		return TextFunction.text(this.elements);
	}
	
	/**
	 * @since 0.2.0
	 */
	public SeleniumQueryObject click() {
		return ClickFunction.click(this, this.elements);
	}
	
	/**
	 * @since 0.2.0
	 */
	public SeleniumQueryObject val(String value) {
		return ValFunction.val(this, this.elements, value);
	}
	
	/**
	 * @since 0.2.0
	 */
	public SeleniumQueryObject val(Number value) {
		return ValFunction.val(this, this.elements, value);
	}
	
	/**
	 * @since 0.2.0
	 */
	public String val() {
		return ValFunction.val(this.elements);
	}
	
	/**
	 * Retrieve one of the {@link WebElement} matched by the seleniumQuery object.
	 * 
	 * @param index A zero-based integer indicating which element to retrieve.
	 * @return the element at the specified index.
	 * 
	 * @author acdcjunior
	 * @since 0.3.0
	 */
	public WebElement get(int index) {
		return this.elements.get(index);
	}
	
	/**
	 * End the most recent filtering operation in the current chain and return the set of matched elements to its previous state.
	 * @return the seleniumQuery object that originated the current instance.
	 * 
	 * @author acdcjunior
	 * @since 0.3.0
	 */
	public SeleniumQueryObject end() {
		return this.previous;
	}
   
}
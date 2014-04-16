package io.github.seleniumquery.functions;

import static io.github.seleniumquery.functions.NotPresentParser.NOT_PRESENT_PARSER;
import io.github.seleniumquery.SeleniumQueryObject;
import io.github.seleniumquery.by.SeleniumQueryBy;
import io.github.seleniumquery.by.enhancements.PresentSelector;

import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class IsFunction {
	
	public static boolean is(SeleniumQueryObject seleniumQueryObject, List<WebElement> elements, String selector) {
		return is(seleniumQueryObject.getWebDriver(), elements, selector);
	}
	
	public static boolean is(WebDriver driver, List<WebElement> elements, String selector) {
		if (selector.trim().equals(":present")) {
			return areAllElementsPresent(elements);
		}
		if (selector.trim().equals(":not(:present)")) {
			return areAllElementsPresent(elements);
		}
		if (selector.trim().equals(":visible")) {
			return areAllElementsVisible(elements);
		}
		if (selector.trim().equals(":not(:visible)")) {
			return areAllElementsNotVisible(elements);
		}
		if (selector.trim().equals(":enabled")) {
			return areAllElementsEnabled(elements);
		}
		if (selector.trim().equals(":not(:enabled)")) {
			return areAllElementsNotEnabled(elements);
		}
		if (selector.trim().equals(":visible:enabled")) {
			return areAllElementsVisible(elements) && areAllElementsEnabled(elements);
		}
		
		
		boolean hasNegatedPresent = NOT_PRESENT_PARSER.hasNegatedPresentSelector(selector);
		return privateIs(driver, elements, selector, hasNegatedPresent);
	}


	private static boolean privateIs(WebDriver driver, List<WebElement> elements, String selector, boolean hasNegatedPresent) {
		// if there is a ":not(:present)" the .findElements() will always return no elements
		if (hasNegatedPresent) {
			if (elements.isEmpty()) {
				return true;
			}
			return areAllElementsNotPresent(elements);
		}
		if (elements.isEmpty()) {
			// because "elements" is empty, the .containsAll() will always return true.
			// but as we reached this code, we must find an element (because hasNegatedPresent is false),
			// then we should return false (because there are no elements)!
			return false;
		}
		List<WebElement> selectorElements = driver.findElements(SeleniumQueryBy.byEnhancedSelector(selector));
		return selectorElements.containsAll(elements);
	}

	private static boolean areAllElementsNotPresent(List<WebElement> elements) {
		for (WebElement webElement : elements) {
			if (PresentSelector.isPresent(webElement)) {
				return false;
			}
		}
		return true;
	}
	
	private static boolean areAllElementsPresent(List<WebElement> elements) {
		for (WebElement webElement : elements) {
			if (!PresentSelector.isPresent(webElement)) {
				return false;
			}
		}
		return true;
	}
	
	private static boolean areAllElementsVisible(List<WebElement> elements) {
		for (WebElement webElement : elements) {
			if (!webElement.isDisplayed()) {
				return false;
			}
		}
		return true;
	}
	
	private static boolean areAllElementsNotVisible(List<WebElement> elements) {
		for (WebElement webElement : elements) {
			if (webElement.isDisplayed()) {
				return false;
			}
		}
		return true;
	}
	
	private static boolean areAllElementsEnabled(List<WebElement> elements) {
		for (WebElement webElement : elements) {
			if (!webElement.isEnabled()) {
				return false;
			}
		}
		return true;
	}
	
	private static boolean areAllElementsNotEnabled(List<WebElement> elements) {
		for (WebElement webElement : elements) {
			if (webElement.isEnabled()) {
				return false;
			}
		}
		return true;
	}

}

class NotPresentParser {
	
	public static final NotPresentParser NOT_PRESENT_PARSER = new NotPresentParser();
	
	private NotPresentParser() { }
	
	private static final Character END_OF_STRING = null;
	
	boolean insideNot = false;
	int bracesOpen = 0;
	int currIndex;
	String str;
	LinkedList<Boolean> wasBracerOpeningANot = new LinkedList<Boolean>();
	boolean negatedPresentFound;
	
	public boolean hasNegatedPresentSelector(String str) {
		this.str = str;
		this.insideNot = false;
		this.bracesOpen = 0;
		this.currIndex = 0;
		this.negatedPresentFound = false;

		eatNext(getChar());
		
		return this.negatedPresentFound;
	}
	
	private Character getChar() {
		// already found a negated :present, can end the processing
		if (this.negatedPresentFound) {
			return END_OF_STRING;
		}
		// reached end of string, nothing else to process
		if (currIndex == str.length()) {
			return END_OF_STRING;
		}
		return str.charAt(currIndex++);
	}
	
	private void eatNext(Character c) {
		if (c == END_OF_STRING) {
			return;
		}
		switch (c) {
		case ':':
			char next = getChar();
			if (next == 'n') eatNot();
			if (next == 'p') eatPresent();
			eatNext(getChar());
			return;
		case '\\':
			getChar(); // eat the escaped
			eatNext(getChar());
			return;
		case '(':
			wasBracerOpeningANot.add(false);
			eatNext(getChar());
			return;
		case ')':
			if (wasBracerOpeningANot.size() == 0) {
				System.err.println("Invalid selector: \""+str+"\"! Attempts to close a never opened bracket pair at "+currIndex+"!");
				return;
			}
			boolean wasANot = wasBracerOpeningANot.pop();
			if (wasANot) {
				insideNot = !insideNot;
			}
			eatNext(getChar());
			return;
		default:
			eatNext(getChar());
			return;
		}
	}

	private void eatNot() {
		// n was consumed before reaching here
		Character o = getChar(); if (o != 'o') { eatNext(o); return; }
		Character t = getChar(); if (t != 't') { eatNext(t); return; }
		Character b = getChar(); if (b != '(') { eatNext(b); return; }
		insideNot = !insideNot;
		wasBracerOpeningANot.add(true);
		eatNext(getChar());
	}
	
	private void eatPresent() {
		// p was consumed before reaching here
		Character r = getChar(); if (r != 'r') { eatNext(r); return; }
		Character e = getChar(); if (e != 'e') { eatNext(e); return; }
		Character s = getChar(); if (s != 's') { eatNext(s); return; }
		Character f = getChar(); if (f != 'e') { eatNext(f); return; }
		Character n = getChar(); if (n != 'n') { eatNext(n); return; }
		Character t = getChar(); if (t != 't') { eatNext(t); return; }
		Character other = getChar();
		if (other == END_OF_STRING || (!Character.isLetterOrDigit(other) && other != '-'  && other != '_')) {
			if (insideNot) {
				this.negatedPresentFound = true;
				return;
			}
		}
		eatNext(other);
	}

}
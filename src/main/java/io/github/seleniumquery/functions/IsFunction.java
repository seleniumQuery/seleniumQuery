package io.github.seleniumquery.functions;

import static io.github.seleniumquery.functions.NotPresentParser.NOT_PRESENT_PARSER;
import io.github.seleniumquery.SeleniumQueryObject;
import io.github.seleniumquery.by.evaluator.SelectorEvaluator;

import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class IsFunction {
	
	public static boolean is(SeleniumQueryObject seleniumQueryObject, List<WebElement> elements, String selector) {
		return is(seleniumQueryObject.getWebDriver(), elements, selector);
	}
	
	public static boolean is(WebDriver driver, List<WebElement> elements, String selector) {
		boolean hasNegatedPresent = NOT_PRESENT_PARSER.hasNegatedPresentSelector(selector);
		return privateIs(driver, elements, selector, hasNegatedPresent);
	}


	private static boolean privateIs(WebDriver driver, List<WebElement> elements, String selector, boolean hasNegatedPresent) {
		if (elements.isEmpty() && !hasNegatedPresent) {
			// because "elements" is empty, the foreach will never enter and the method will always return true.
			// but as we must find at least one element (as there is no ":not(:present)" -- hasNegatedPresent is false),
			// then we should return false (because there are no elements)!
			return false;
		}
		if (elements.isEmpty() && hasNegatedPresent) {
			// there are no elements (because "elements" is empty), and we want to
			// find no elements (as there is a ":not(:present)" -- hasNegatedPresent is true),
			// then we should return true!
			return true;
		}
		// comment for the future refactorers: We realize there could be only one if with "return hasNegatedPresent",
		// we left this way, though, because we believe it is more descriptive of the code's intention

		for (WebElement element : elements) {
			if (SelectorEvaluator.elementMatchesStringSelector(driver, element, selector)) {
				return true;
			}
		}
		return false;
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
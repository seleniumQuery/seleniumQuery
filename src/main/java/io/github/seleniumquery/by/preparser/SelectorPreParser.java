package io.github.seleniumquery.by.preparser;

import static java.lang.Character.isLetter;

import java.util.HashMap;
import java.util.Map;

public class SelectorPreParser {

	private static final Character END_OF_STRING = null;
	
	private String selector;
	private int selectorCurrentParsingIndex;
	
	private StringBuilder transformedSelector = new StringBuilder();
	private Map<String, String> stringMap = new HashMap<String, String>();
	
	public TransformedSelector transformSelector(String selector) {
		this.selector = selector;
		this.selectorCurrentParsingIndex = 0;

		eatChar(getNextChar());
		
		return new TransformedSelector(transformedSelector.toString(), stringMap);
	}
	
	private Character getNextChar() {
		if (selectorCurrentParsingIndex == selector.length()) {
			// reached end of string, nothing else to process
			return END_OF_STRING;
		}
		return selector.charAt(selectorCurrentParsingIndex++);
	}
	
	private void eatChar(Character c) {
		if (c == END_OF_STRING) {
			return;
		}
		appendToFinalSelector(c);
		switch (c) {
		case ':':
			eatPseudoClass();
			return;
		case '\\':
			Character escapedChar = getNextChar(); // eat the char that was escaped
			appendToFinalSelector(escapedChar);
			
			eatChar(getNextChar());
			return;
		case  '\'':
			// begins a string not inside a pseudoclass (that is, not in :pseudo('HERE!!')), probably in an attribute value, such as [attr='value']
			String contentSingleQuote = eatEverythingUntilStringEnd('\'');
			appendToFinalSelector(contentSingleQuote.substring(1)).append('\'');
			eatChar(getNextChar());
			return;
		case  '"':
			// begins a string not inside a pseudoclass (that is, not in :pseudo("HERE!!")), probably in an attribute value, such as [attr="value"]
			String contentDoubleQuotes = eatEverythingUntilStringEnd('"');
			appendToFinalSelector(contentDoubleQuotes.substring(1)).append('"');
			eatChar(getNextChar());
			return;
		default:
			eatChar(getNextChar());
			return;
		}
	}

	private void eatPseudoClass() {
		Character firstCharOfPseudoClass = getNextChar();
		if (!Character.isLetter(firstCharOfPseudoClass)) { throw new RuntimeException("Parsing error. First char of pseudoclass is not a letter! -> "+firstCharOfPseudoClass); }
		
		StringBuilder pseudoClass = new StringBuilder();
		pseudoClass.append(firstCharOfPseudoClass);
		
		Character nextChar = getNextChar();
		while (nextChar != null && (isLetter(nextChar) || nextChar == '-')) {
			pseudoClass.append(nextChar);
			nextChar = getNextChar();
		}
		if (nextChar != null && nextChar == '(') {
			String bracerContent = eatEverythingUntilBracerEnd();
			String index = String.valueOf(stringMap.size());
			stringMap.put(index, bracerContent);
			
			String pseudoClassName = pseudoClass.toString();
			if ("not".equals(pseudoClassName)) {
				pseudoClassName = "not-sq";
			}
			if ("lang".equals(pseudoClassName)) {
				pseudoClassName = "lang-sq";
			}
			appendToFinalSelector(pseudoClassName + '(' + index + ')');
			eatChar(getNextChar());
		} else {
			appendToFinalSelector(pseudoClass.toString());
			eatChar(nextChar);
		}
	}

	private String eatEverythingUntilBracerEnd() {
		StringBuilder bracerContent = new StringBuilder();
		Character nextChar = getNextChar();
		while (nextChar != null) {
			if (nextChar == '\'') {
				bracerContent.append(eatEverythingUntilStringEnd('\''));
			}
			if (nextChar == '"') {
				bracerContent.append(eatEverythingUntilStringEnd('"'));
			}
			if (nextChar == ')') {
				return bracerContent.toString();
			}
			if (nextChar == '(') {
				String nestedBracerContent = eatEverythingUntilBracerEnd();
				bracerContent.append('(').append(nestedBracerContent).append(')');
			} else if (nextChar == '\\') {
				nextChar = getNextChar(); // get the char that was escaped
				if (nextChar == ')') {
					// if the ')' was escaped, we ommit the \
					bracerContent.append(nextChar);
				} else {
					bracerContent.append('\\');
					bracerContent.append(nextChar);
				}
			} else {
				bracerContent.append(nextChar);
			}
			nextChar = getNextChar();
		}
		// if we reached here, nextChar is null!
		throw new RuntimeException("End of string reached while expecting a ).");
	}

	private String eatEverythingUntilStringEnd(char quote) {
		StringBuilder stringContent = new StringBuilder();
		stringContent.append(quote);
		
		Character nextChar = getNextChar();
		while (nextChar != null) {
			// closing quote
			if (nextChar == quote) {
				return stringContent.toString();
			}
			// the escaping char, we eat the next char, without parsing it, because if it is a ' or ", we dont
			// want to consider the string closed
			if (nextChar == '\\') {
				stringContent.append('\\');
				nextChar = getNextChar(); // eat the char that was escaped
				stringContent.append(nextChar);
			} else {
				stringContent.append(nextChar);
			}
			nextChar = getNextChar();
		}
		// if we reached here, nextChar is null!
		throw new RuntimeException("End of string reached while closing quote: "+quote);
	}

	private StringBuilder appendToFinalSelector(Character c) {
		return transformedSelector.append(c);
	}
	
	private StringBuilder appendToFinalSelector(String str) {
		return transformedSelector.append(str);
	}

}
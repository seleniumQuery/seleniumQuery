/*
 * Copyright (c) 2017 seleniumQuery authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.seleniumquery.by.common.preparser;

import static java.lang.Character.isLetter;

import java.util.HashMap;
import java.util.Map;

import io.github.seleniumquery.SeleniumQueryException;

/**
 * Parses a selector string (such as "span.warn:not(.something)") into a {@link PreParsedSelector}.
 *
 * A "Pre-Parsed Selector" is a:
 * - Transformed Selector (String), repesenting the original selector string, but with some information taken out;
 * - and a {@code Map<String, String>}, which will contain the information taken out from the original selector (and not
 * present in the Transformed Selector).
 *
 * This is done to simplify some selectors and to enable others that the SAC Parser can't parse (we replace
 * those with something else here and deal with them later).
 *
 * @author acdcjunior
 * @since 0.10.0
 */
class CssSelectorPreParser {

    private static final NotEqualsAttributeSelectorFix NOT_EQUALS_ATTRIBUTE_SELECTOR_FIX = new NotEqualsAttributeSelectorFix();

    static PreParsedSelector preParseSelector(String selector) {
        String fixedSelector = NOT_EQUALS_ATTRIBUTE_SELECTOR_FIX.turnAttributeNotEqualsIntoNotAttributeEquals(selector);
        return transformSelector(fixedSelector);
    }

    static class PreParsedSelector {
		private String transformedSelector;
		private ArgumentMap argumentMap;
		private PreParsedSelector(String transformedSelector, ArgumentMap argumentMap) {
			this.transformedSelector = transformedSelector;
			this.argumentMap = argumentMap;
		}
		String getTransformedSelector() { return this.transformedSelector; }
		public ArgumentMap getArgumentMap() { return this.argumentMap; }
	}

    private static final Character END_OF_STRING = null;

	private String selector;
	private int selectorCurrentParsingIndex;
	private StringBuilder transformedSelector;
	private Map<Integer, String> argumentMap;

    static PreParsedSelector transformSelector(String selector) {
        return new CssSelectorPreParser(selector).transformSelector();
    }

    private CssSelectorPreParser(String selector) {
        this.selector = selector;
        this.selectorCurrentParsingIndex = 0;
        this.transformedSelector = new StringBuilder();
        this.argumentMap = new HashMap<>();
    }

    private PreParsedSelector transformSelector() {
		eatChar(getNextChar());
		return new PreParsedSelector(transformedSelector.toString(), new ArgumentMap(argumentMap));
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
		}
	}

	private void eatPseudoClass() {
		Character firstCharOfPseudoClass = getNextChar();
		if (!isLetter(firstCharOfPseudoClass)) { throw new SeleniumQueryException("Parsing error. First char of pseudoclass is not a letter! -> "+firstCharOfPseudoClass); }

		StringBuilder pseudoClass = new StringBuilder();
		pseudoClass.append(firstCharOfPseudoClass);

		Character nextChar = getNextChar();
		while (nextChar != null && (isLetter(nextChar) || nextChar == '-')) {
			pseudoClass.append(nextChar);
			nextChar = getNextChar();
		}
		if (nextChar != null && nextChar == '(') {
			String bracerContent = eatEverythingUntilBracerEnd();
			int index = argumentMap.size();
			argumentMap.put(index, bracerContent);

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
		throw new SeleniumQueryException("End of string reached while expecting a ).");
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
		throw new SeleniumQueryException("End of string reached while closing quote: "+quote);
	}

	private void appendToFinalSelector(Character c) {
        transformedSelector.append(c);
    }

	private StringBuilder appendToFinalSelector(String str) {
		return transformedSelector.append(str);
	}

}

/*
 * Copyright (c) 2016 seleniumQuery authors
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

import com.steadystate.css.parser.SACParserCSS3;
import io.github.seleniumquery.SeleniumQueryException;
import io.github.seleniumquery.by.common.preparser.CssSelectorPreParser.PreParsedSelector;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.css.sac.*;

import java.io.StringReader;

public class CssSelectorParser {

	private static final Log LOGGER = LogFactory.getLog(CssSelectorParser.class);

	private static final NotEqualsAttributeSelectorFix NOT_EQUALS_ATTRIBUTE_SELECTOR_FIX = new NotEqualsAttributeSelectorFix();

	public static CssParsedSelectorList parseSelector(String selector) {
        PreParsedSelector preParsedSelector = preParseSelector(selector);
		SelectorList selectorList = parseSelectorIntoParseTree(preParsedSelector.getTransformedSelector());
		return new CssParsedSelectorList(selectorList, preParsedSelector.getArgumentMap());
	}

    private static PreParsedSelector preParseSelector(String selector) {
        String fixedSelector = NOT_EQUALS_ATTRIBUTE_SELECTOR_FIX.turnAttributeNotEqualsIntoNotAttributeEquals(selector);
        return CssSelectorPreParser.transformSelector(fixedSelector);
    }

    /**
	 * Parses a selector into a parse tree using SAC CSS3 Parser.
	 */
	private static SelectorList parseSelectorIntoParseTree(String selector) {
		try {
            return SAC_CSS3_PARSER.parseSelectors(new InputSource(new StringReader(selector)));
        } catch (Exception e) {
            throw new SeleniumQueryException("Problem parsing selector: "+selector, e);
        }
	}

	private static final SACParserCSS3 SAC_CSS3_PARSER = new SACParserCSS3();

    static {
	    SAC_CSS3_PARSER.setErrorHandler(new ErrorHandler() {
			public void warning(final CSSParseException cssParseException) throws CSSException {
				LOGGER.warn(cssParseException.toString(), cssParseException);
			}

			public void error(final CSSParseException cssParseException) throws CSSException {
				throw cssParseException;
			}

			public void fatalError(final CSSParseException cssParseException) throws CSSException {
				throw cssParseException;
			}
		});
    }

}
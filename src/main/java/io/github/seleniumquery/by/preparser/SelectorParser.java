package io.github.seleniumquery.by.preparser;

import java.io.StringReader;

import io.github.seleniumquery.by.preparser.SelectorPreParser.PreParsedSelector;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.css.sac.CSSException;
import org.w3c.css.sac.CSSParseException;
import org.w3c.css.sac.ErrorHandler;
import org.w3c.css.sac.InputSource;
import org.w3c.css.sac.SelectorList;

import com.steadystate.css.parser.SACParserCSS3;

public class SelectorParser {

	private static final Log LOGGER = LogFactory.getLog(SelectorParser.class);

	private static final NotEqualsAttributeSelectorFix NOT_EQUALS_ATTRIBUTE_SELECTOR_FIX = new NotEqualsAttributeSelectorFix();

	public static ParsedSelectorList parseSelector(String selector) {
		String fixedSelector = NOT_EQUALS_ATTRIBUTE_SELECTOR_FIX.turnAttributeNotEqualsIntoNotAttributeEquals(selector);
		PreParsedSelector preParsedSelector = new SelectorPreParser().transformSelector(fixedSelector);
		SelectorList selectorList = parseSelectorIntoParseTree(preParsedSelector.getTransformedSelector());
		return new ParsedSelectorList(selectorList, preParsedSelector.getStringMap());
	}

	/**
	 * Parses a selector into a parse tree using SAC CSS3 Parser.
	 */
	private static SelectorList parseSelectorIntoParseTree(String selector) {
		try {
            return SAC_CSS3_PARSER.parseSelectors(new InputSource(new StringReader(selector)));
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
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
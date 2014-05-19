package io.github.seleniumquery.by.parser;

import io.github.seleniumquery.by.evaluator.conditionals.attributes.NotEqualsAttributeSelectorFix;

import java.io.StringReader;

import org.w3c.css.sac.CSSException;
import org.w3c.css.sac.CSSParseException;
import org.w3c.css.sac.ErrorHandler;
import org.w3c.css.sac.InputSource;
import org.w3c.css.sac.SelectorList;

import com.steadystate.css.parser.SACParserCSS3;

public class SelectorParser {
	
	private static final NotEqualsAttributeSelectorFix NOT_EQUALS_ATTRIBUTE_SELECTOR_FIX = new NotEqualsAttributeSelectorFix();

	public static ParsedSelector<SelectorList> parseSelector(String selector) {
		String fixedSelector = NOT_EQUALS_ATTRIBUTE_SELECTOR_FIX.fixAttributeNotEquals(selector);
		TransformedSelector transformedSelector = new SelectorPreParser().transformSelector(fixedSelector);
	    SelectorList selectorList = null;
	    try {
	    	selectorList = css3Parser.parseSelectors(new InputSource(new StringReader(transformedSelector.getTransformedSelector())));
	    } catch (RuntimeException e) {
	    	throw e;
	    } catch (Exception e) {
	    	throw new RuntimeException(e);
	    }
	    return new ParsedSelector<SelectorList>(selectorList, transformedSelector.getStringMap());
	}
	
	private static SACParserCSS3 css3Parser = new SACParserCSS3();
    static {
	    css3Parser.setErrorHandler(new ErrorHandler() {
	        public void warning(final CSSParseException cssParseException) throws CSSException {
	        	System.err.println("WARNING: "+cssParseException);
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
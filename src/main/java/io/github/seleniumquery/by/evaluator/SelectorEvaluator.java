package io.github.seleniumquery.by.evaluator;

import io.github.seleniumquery.by.evaluator.conditionals.attributes.NotEqualsAttributeSelectorFix;

import java.io.StringReader;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.CSSException;
import org.w3c.css.sac.CSSParseException;
import org.w3c.css.sac.ErrorHandler;
import org.w3c.css.sac.InputSource;
import org.w3c.css.sac.Selector;
import org.w3c.css.sac.SelectorList;

import com.steadystate.css.parser.SACParserCSS3;

public class SelectorEvaluator {
	
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
	
    private static final NotEqualsAttributeSelectorFix NOT_EQUALS_ATTRIBUTE_SELECTOR_FIX = new NotEqualsAttributeSelectorFix();
    
	public static boolean is(WebDriver driver, WebElement element, String selector) {
		String fixedSelector = NOT_EQUALS_ATTRIBUTE_SELECTOR_FIX.fixAttributeNotEquals(selector);
        SelectorList selectorList = null;
        try {
        	selectorList = css3Parser.parseSelectors(new InputSource(new StringReader(fixedSelector)));
        } catch (RuntimeException e) {
        	throw e;
        } catch (Exception e) {
        	throw new RuntimeException(e);
        }
        for (int i = 0; i < selectorList.getLength(); i++) {
			if (!SelectorEvaluator.is(driver, element, selectorList.item(i))) {
				return false;
			}
		}
        return true;
	}

	@SuppressWarnings("unchecked")
	public static boolean is(WebDriver driver, WebElement element, Selector selector) {
		CSSSelector<Selector> cssSelector =  (CSSSelector<Selector>) SelectorEvaluatorFactory.getInstance().getSelector(selector);
		return cssSelector.is(driver, element, selector);	
	}

}
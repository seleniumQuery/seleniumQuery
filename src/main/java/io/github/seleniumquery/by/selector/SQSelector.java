package io.github.seleniumquery.by.selector;

import io.github.seleniumquery.by.evaluator.CSSSelector;
import io.github.seleniumquery.by.evaluator.SelectorEvaluatorFactory;
import io.github.seleniumquery.by.evaluator.conditionals.attributes.NotEqualsAttributeSelectorFix;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.CSSException;
import org.w3c.css.sac.CSSParseException;
import org.w3c.css.sac.ErrorHandler;
import org.w3c.css.sac.InputSource;
import org.w3c.css.sac.Selector;
import org.w3c.css.sac.SelectorList;

import com.steadystate.css.parser.SACParserCSS3;

public class SQSelector {
	
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
    
	public static List<CompiledSelector> compile(WebDriver driver, SelectorList selectorList) {
		List<CompiledSelector> css = new ArrayList<CompiledSelector>(selectorList.getLength()); 
        for (int i = 0; i < selectorList.getLength(); i++) {
			Selector selector = selectorList.item(i);
			System.out.println("SELECTOR #"+i+" IS: "+selector.getClass());
			CompiledSelector cs = compile(driver, selector);
			css.add(cs);
		}
		return css;
	}

	@SuppressWarnings("unchecked")
	public static CompiledSelector compile(WebDriver driver, Selector selector) {
		CSSSelector<Selector> cssSelector =  (CSSSelector<Selector>) SelectorEvaluatorFactory.getInstance().getSelector(selector);
		return cssSelector.compile(driver, selector);
	}

	public static CompiledSelectorList compile(WebDriver driver, String selector) {
		String fixedSelector = NOT_EQUALS_ATTRIBUTE_SELECTOR_FIX.fixAttributeNotEquals(selector);
        SelectorList selectorList = null;
        try {
        	selectorList = css3Parser.parseSelectors(new InputSource(new StringReader(fixedSelector)));
        } catch (RuntimeException e) {
        	throw e;
        } catch (Exception e) {
        	throw new RuntimeException(e);
        }
        System.out.println(selectorList.getClass());
        for (int i = 0; i < selectorList.getLength(); i++) {
			System.out.println("@"+i+": "+selectorList.item(i));
		}
        return new CompiledSelectorList(compile(driver, selectorList));
	}
	
	public static void main(String[] args) {
		CompiledSelectorList csl = compile(null, "div#myId.class,.w00t:lang(fr)");
		for (CompiledSelector cs : csl.css) {
			System.out.println(cs);
		}
	}

}
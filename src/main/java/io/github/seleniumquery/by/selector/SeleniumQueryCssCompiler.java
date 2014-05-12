package io.github.seleniumquery.by.selector;

import io.github.seleniumquery.by.evaluator.CSSSelector;
import io.github.seleniumquery.by.evaluator.SelectorEvaluatorFactory;
import io.github.seleniumquery.by.evaluator.conditionals.attributes.NotEqualsAttributeSelectorFix;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.w3c.css.sac.CSSException;
import org.w3c.css.sac.CSSParseException;
import org.w3c.css.sac.ErrorHandler;
import org.w3c.css.sac.InputSource;
import org.w3c.css.sac.Selector;
import org.w3c.css.sac.SelectorList;

import com.steadystate.css.parser.SACParserCSS3;

public class SeleniumQueryCssCompiler {
	
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

	public static CompiledSelectorList compileSelectorList(WebDriver driver, String selector) {
		String fixedSelector = NOT_EQUALS_ATTRIBUTE_SELECTOR_FIX.fixAttributeNotEquals(selector);
        SelectorList selectorList = null;
        try {
        	selectorList = css3Parser.parseSelectors(new InputSource(new StringReader(fixedSelector)));
        } catch (Exception e) {
        	throw new RuntimeException(e);
        }
		List<CompiledSelector> css = new ArrayList<CompiledSelector>(selectorList.getLength()); 
		for (int i = 0; i < selectorList.getLength(); i++) {
			Selector selector1 = selectorList.item(i);
			System.out.println("SELECTOR #"+i+" IS: "+selector1.getClass());
			CompiledSelector cs = compileSelector(driver, selector1);
			css.add(cs);
		}
        
		return new CompiledSelectorList(css);
	}
    
	@SuppressWarnings("unchecked")
	public static CompiledSelector compileSelector(WebDriver driver, Selector selector) {
		CSSSelector<Selector> cssSelector =  (CSSSelector<Selector>) SelectorEvaluatorFactory.getInstance().getSelector(selector);
		return cssSelector.compile(driver, selector);
	}
	
	public static void main(String[] args) {
		CompiledSelectorList csl = compileSelectorList(null, "h1:not(ul),span:hidden,div#myId.class,.w00t:lang(fr)");
		for (CompiledSelector cs : csl.css) {
			System.out.println(cs);
		}
		//csl.execute(driver);
	}

}
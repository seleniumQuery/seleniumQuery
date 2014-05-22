package io.github.seleniumquery.selectors.conditionals;

import io.github.seleniumquery.selector.CssConditionalSelector;
import io.github.seleniumquery.selectors.attributes.ClassAttributeCssSelector;
import io.github.seleniumquery.selectors.attributes.ContainsPrefixAttributeCssSelector;
import io.github.seleniumquery.selectors.attributes.ContainsSubstringAttributeCssSelector;
import io.github.seleniumquery.selectors.attributes.ContainsWordAttributeCssSelector;
import io.github.seleniumquery.selectors.attributes.EndsWithAttributeCssSelector;
import io.github.seleniumquery.selectors.attributes.EqualsOrHasAttributeCssSelector;
import io.github.seleniumquery.selectors.attributes.IdAttributeCssSelector;
import io.github.seleniumquery.selectors.attributes.StartsWithAttributeCssSelector;
import io.github.seleniumquery.selectors.pseudoclasses.LangPseudoClassEvaluator;
import io.github.seleniumquery.selectors.pseudoclasses.PseudoClassCssSelector;

import org.w3c.css.sac.Condition;

public class ConditionalCssSelectorFactory {

	private static final ConditionalCssSelectorFactory instance = new ConditionalCssSelectorFactory();
	
	public static ConditionalCssSelectorFactory getInstance() {
		return instance;
	}
	
	private ConditionalCssSelectorFactory() { }

	public CssConditionalSelector<? extends Condition> getSelector(Condition condition) {
	    switch (condition.getConditionType()) {
		    case Condition.SAC_AND_CONDITION:
		    	return AndConditionalCssSelector.getInstance();
		    case Condition.SAC_OR_CONDITION:
		    	// if the exception below gets thrown, this means the CSS Parser has changed and
		    	// we must update our code as well.
		    	throw new RuntimeException("The Condition.SAC_OR_CONDITION is not used by the CSS Parser. " +
		    			"This version of seleniumQuery is not compatible with the CSS Parser present in the" +
		    			"classpath.");
		    	
		    case Condition.SAC_ATTRIBUTE_CONDITION:
		    	if (condition instanceof com.steadystate.css.parser.selectors.PrefixAttributeConditionImpl) {
		    		return StartsWithAttributeCssSelector.getInstance();
		    	}
		    	if (condition instanceof com.steadystate.css.parser.selectors.SuffixAttributeConditionImpl) {
		    		return EndsWithAttributeCssSelector.getInstance();
		    	}
		    	if (condition instanceof com.steadystate.css.parser.selectors.SubstringAttributeConditionImpl) {
		    		return ContainsSubstringAttributeCssSelector.getInstance();
		    	}
		    	// else: condition is most probably a instance of com.steadystate.css.parser.selectors.AttributeConditionImpl
		    	return EqualsOrHasAttributeCssSelector.getInstance();
	        case Condition.SAC_ID_CONDITION:
				return IdAttributeCssSelector.getInstance();
	        case Condition.SAC_ONE_OF_ATTRIBUTE_CONDITION:
	        	return ContainsWordAttributeCssSelector.getInstance();
	        case Condition.SAC_BEGIN_HYPHEN_ATTRIBUTE_CONDITION:
	        	return ContainsPrefixAttributeCssSelector.getInstance();
	        case Condition.SAC_CLASS_CONDITION:
	        	return ClassAttributeCssSelector.getInstance();
				
	        case Condition.SAC_PSEUDO_CLASS_CONDITION:
	        	return PseudoClassCssSelector.getInstance();
	        case Condition.SAC_LANG_CONDITION:
	        	return LangPseudoClassEvaluator.getInstance();
	            
	        default:
				return new UnknownConditionalCssSelector<Condition>(condition.getConditionType());
		}
	}
	
}
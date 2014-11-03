package io.github.seleniumquery.selectors.conditionals;

import io.github.seleniumquery.selectorcss.CssConditionalSelector;
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

/**
 * @author acdcjunior
 * @since 1.0.0
 */
public class ConditionalCssSelectorFactory {

    private final AndConditionalCssSelector andConditionalCssSelector;
    private final StartsWithAttributeCssSelector startsWithAttributeCssSelector = new StartsWithAttributeCssSelector();
    private final EndsWithAttributeCssSelector endsWithAttributeCssSelector = new EndsWithAttributeCssSelector();
    private final ContainsSubstringAttributeCssSelector containsSubstringAttributeCssSelector = new ContainsSubstringAttributeCssSelector();
    private final EqualsOrHasAttributeCssSelector equalsOrHasAttributeCssSelector = new EqualsOrHasAttributeCssSelector();
    private final IdAttributeCssSelector idAttributeCssSelector = new IdAttributeCssSelector();
    private final ContainsWordAttributeCssSelector containsWordAttributeCssSelector = new ContainsWordAttributeCssSelector();
    private final ContainsPrefixAttributeCssSelector containsPrefixAttributeCssSelector = new ContainsPrefixAttributeCssSelector();
    private final ClassAttributeCssSelector classAttributeCssSelector = new ClassAttributeCssSelector();
    private final PseudoClassCssSelector pseudoClassCssSelector = new PseudoClassCssSelector();
    private final LangPseudoClassEvaluator langPseudoClassEvaluator = new LangPseudoClassEvaluator();

	public ConditionalCssSelectorFactory(ConditionalCssSelector conditionalCssSelector) {
		this.andConditionalCssSelector = new AndConditionalCssSelector(conditionalCssSelector);
	}

	public CssConditionalSelector<? extends Condition> getSelector(Condition condition) {
	    switch (condition.getConditionType()) {
		    case Condition.SAC_AND_CONDITION:
		    	return andConditionalCssSelector;
		    case Condition.SAC_OR_CONDITION:
		    	// if the exception below gets thrown, this means the CSS Parser has changed and
		    	// we must update our code as well.
		    	throw new RuntimeException("The Condition.SAC_OR_CONDITION is not used by the CSS Parser. " +
		    			"This version of seleniumQuery is not compatible with the CSS Parser present in the" +
		    			"classpath.");
		    	
		    case Condition.SAC_ATTRIBUTE_CONDITION:
		    	if (condition instanceof com.steadystate.css.parser.selectors.PrefixAttributeConditionImpl) {
		    		return startsWithAttributeCssSelector;
		    	}
		    	if (condition instanceof com.steadystate.css.parser.selectors.SuffixAttributeConditionImpl) {
		    		return endsWithAttributeCssSelector;
		    	}
		    	if (condition instanceof com.steadystate.css.parser.selectors.SubstringAttributeConditionImpl) {
		    		return containsSubstringAttributeCssSelector;
		    	}
		    	// else: condition is most probably a instance of com.steadystate.css.parser.selectors.AttributeConditionImpl
		    	return equalsOrHasAttributeCssSelector;
	        case Condition.SAC_ID_CONDITION:
				return idAttributeCssSelector;
	        case Condition.SAC_ONE_OF_ATTRIBUTE_CONDITION:
	        	return containsWordAttributeCssSelector;
	        case Condition.SAC_BEGIN_HYPHEN_ATTRIBUTE_CONDITION:
	        	return containsPrefixAttributeCssSelector;
	        case Condition.SAC_CLASS_CONDITION:
	        	return classAttributeCssSelector;
				
	        case Condition.SAC_PSEUDO_CLASS_CONDITION:
	        	return pseudoClassCssSelector;
	        case Condition.SAC_LANG_CONDITION:
	        	return langPseudoClassEvaluator;
	            
	        default:
				return new UnknownConditionalCssSelector<Condition>(condition.getConditionType());
		}
	}
	
}
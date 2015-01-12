package io.github.seleniumquery.by.parser.translator.condition;

import io.github.seleniumquery.by.css.attributes.*;
import io.github.seleniumquery.by.css.conditionals.UnknownConditionalCssSelector;
import io.github.seleniumquery.by.css.pseudoclasses.LangPseudoClassEvaluator;
import io.github.seleniumquery.by.css.pseudoclasses.PseudoClassCssSelector;
import io.github.seleniumquery.by.parser.translator.selector.SQCssConditionalSelectorTranslator;
import io.github.seleniumquery.by.parser.parsetree.condition.SQCssCondition;
import org.w3c.css.sac.CombinatorCondition;
import org.w3c.css.sac.Condition;
import org.w3c.css.sac.SimpleSelector;

import java.util.Map;

/**
 * @author acdcjunior
 * @since 0.9.0
 */
public class SQCssConditionTranslator {

    private final SQCssAndConditionTranslator andConditionalCssSelector;
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

	public SQCssConditionTranslator(SQCssConditionalSelectorTranslator sqCssConditionalSelectorTranslator) {
//		this.andConditionalCssSelector = new AndConditionalCssSelector(sqCssConditionalSelectorTranslator);
		this.andConditionalCssSelector = new SQCssAndConditionTranslator(this);
	}

	public SQCssCondition translate(SimpleSelector simpleSelector, Map<String, String> stringMap, Condition condition) {
	    switch (condition.getConditionType()) {
		    case Condition.SAC_AND_CONDITION:
		    	return andConditionalCssSelector.translate(simpleSelector, stringMap, (CombinatorCondition) condition);
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
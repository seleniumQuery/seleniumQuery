package io.github.seleniumquery.by.evaluator.conditionals;

import io.github.seleniumquery.by.evaluator.CSSCondition;
import io.github.seleniumquery.by.evaluator.conditionals.attributes.ClassAttributeEvaluator;
import io.github.seleniumquery.by.evaluator.conditionals.attributes.ContainsPrefixAttributeEvaluator;
import io.github.seleniumquery.by.evaluator.conditionals.attributes.ContainsSubstringAttributeEvaluator;
import io.github.seleniumquery.by.evaluator.conditionals.attributes.ContainsWordAttributeEvaluator;
import io.github.seleniumquery.by.evaluator.conditionals.attributes.EndsWithAttributeEvaluator;
import io.github.seleniumquery.by.evaluator.conditionals.attributes.EqualsOrHasAttributeEvaluator;
import io.github.seleniumquery.by.evaluator.conditionals.attributes.IdAttributeEvaluator;
import io.github.seleniumquery.by.evaluator.conditionals.attributes.StartsWithAttributeEvaluator;
import io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses.LangPseudoClassEvaluator;
import io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses.PseudoClassEvaluator;

import org.w3c.css.sac.Condition;

public class ConditionalEvaluatorFactory {

	private static final ConditionalEvaluatorFactory instance = new ConditionalEvaluatorFactory();
	
	public static ConditionalEvaluatorFactory getInstance() {
		return instance;
	}
	
	private ConditionalEvaluatorFactory() { }

	public CSSCondition<? extends Condition> getSelector(Condition condition) {
	    switch (condition.getConditionType()) {
		    case Condition.SAC_AND_CONDITION:
		    	return AndCombinationConditionEvaluator.getInstance();
		    case Condition.SAC_OR_CONDITION:
		    	return OrCombinationConditionEvaluator.getInstance();
		    	
		    case Condition.SAC_ATTRIBUTE_CONDITION:
		    	if (condition instanceof com.steadystate.css.parser.selectors.PrefixAttributeConditionImpl) {
		    		return StartsWithAttributeEvaluator.getInstance();
		    	}
		    	if (condition instanceof com.steadystate.css.parser.selectors.SuffixAttributeConditionImpl) {
		    		return EndsWithAttributeEvaluator.getInstance();
		    	}
		    	if (condition instanceof com.steadystate.css.parser.selectors.SubstringAttributeConditionImpl) {
		    		return ContainsSubstringAttributeEvaluator.getInstance();
		    	}
		    	// else: condition is most probably a instance of com.steadystate.css.parser.selectors.AttributeConditionImpl
		    	return EqualsOrHasAttributeEvaluator.getInstance();
	        case Condition.SAC_ID_CONDITION:
				return IdAttributeEvaluator.getInstance();
	        case Condition.SAC_ONE_OF_ATTRIBUTE_CONDITION:
	        	return ContainsWordAttributeEvaluator.getInstance();
	        case Condition.SAC_BEGIN_HYPHEN_ATTRIBUTE_CONDITION:
	        	return ContainsPrefixAttributeEvaluator.getInstance();
	        case Condition.SAC_CLASS_CONDITION:
	        	return ClassAttributeEvaluator.getInstance();
				
	        case Condition.SAC_PSEUDO_CLASS_CONDITION:
	        	return PseudoClassEvaluator.getInstance();
	        case Condition.SAC_LANG_CONDITION:
	        	return LangPseudoClassEvaluator.getInstance();
	            
	        default:
				return new UnknownConditionType<Condition>(condition.getConditionType());
		}
	}
	
}
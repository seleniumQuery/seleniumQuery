/*
 * Copyright (c) 2015 seleniumQuery authors
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

package io.github.seleniumquery.by.firstgen.css.conditionals;

import io.github.seleniumquery.by.firstgen.css.CssConditionalSelector;
import io.github.seleniumquery.by.firstgen.css.attributes.*;
import io.github.seleniumquery.by.firstgen.css.pseudoclasses.LangPseudoClassEvaluator;
import io.github.seleniumquery.by.firstgen.css.pseudoclasses.PseudoClassCssSelector;
import io.github.seleniumquery.by.firstgen.xpath.component.ConditionComponent;
import org.w3c.css.sac.Condition;

/**
 * @author acdcjunior
 * @since 0.9.0
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

	public CssConditionalSelector<? extends Condition, ? extends ConditionComponent> getSelector(Condition condition) {
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
				return new UnknownConditionalCssSelector<>(condition.getConditionType());
		}
	}
	
}
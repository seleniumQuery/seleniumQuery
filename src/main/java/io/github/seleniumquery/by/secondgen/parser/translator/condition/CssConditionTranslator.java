/*
 * Copyright (c) 2017 seleniumQuery authors
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

package io.github.seleniumquery.by.secondgen.parser.translator.condition;

import org.w3c.css.sac.AttributeCondition;
import org.w3c.css.sac.CombinatorCondition;
import org.w3c.css.sac.Condition;
import org.w3c.css.sac.SimpleSelector;

import io.github.seleniumquery.SeleniumQueryException;
import io.github.seleniumquery.by.common.preparser.ArgumentMap;
import io.github.seleniumquery.by.secondgen.csstree.condition.CssCondition;
import io.github.seleniumquery.by.secondgen.csstree.condition.CssUnknownConditionException;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.CssPseudoClassCondition;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.CssPseudoClassConditionVisitor;
import io.github.seleniumquery.by.secondgen.csstree.selector.CssConditionVisitor;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.AstCssConditionVisitor;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.AstCssPseudoClassConditionVisitor;

/**
 * Translates a SAC {@link Condition} selector into a {@link CssCondition}.
 *
 * @author acdcjunior
 * @since 0.10.0
 */
public class CssConditionTranslator {

    private final CssAndConditionTranslator andConditionTranslator = new CssAndConditionTranslator(this);
    private final CssAttributeConditionTranslator cssAttributeConditionTranslator = new CssAttributeConditionTranslator();
    private final CssPseudoClassConditionTranslator pseudoClassConditionTranslator = new CssPseudoClassConditionTranslator();

    private AstCssConditionVisitor<CssCondition> astCssConditionVisitor = new CssConditionVisitor();
    private AstCssPseudoClassConditionVisitor<CssPseudoClassCondition> astCssPseudoClassConditionVisitor = new CssPseudoClassConditionVisitor();

	public CssCondition translate(SimpleSelector simpleSelector, ArgumentMap argumentMap, Condition condition) {
	    switch (condition.getConditionType()) {
		    case Condition.SAC_AND_CONDITION:
		    	return andConditionTranslator.translate(simpleSelector, argumentMap, (CombinatorCondition) condition).accept(astCssConditionVisitor);
		    case Condition.SAC_OR_CONDITION:
				return incompatible("Condition.SAC_OR_CONDITION");

			case Condition.SAC_ATTRIBUTE_CONDITION:
				if (condition instanceof com.steadystate.css.parser.selectors.PrefixAttributeConditionImpl) {
		    		return cssAttributeConditionTranslator.translateStartsWithAttr((AttributeCondition) condition).accept(astCssConditionVisitor);
		    	}
		    	if (condition instanceof com.steadystate.css.parser.selectors.SuffixAttributeConditionImpl) {
		    		return cssAttributeConditionTranslator.translateEndsWithAtt((AttributeCondition) condition).accept(astCssConditionVisitor);
		    	}
		    	if (condition instanceof com.steadystate.css.parser.selectors.SubstringAttributeConditionImpl) {
		    		return cssAttributeConditionTranslator.translateContainsSubstring((AttributeCondition) condition).accept(astCssConditionVisitor);
		    	}
		    	// else: condition is most probably a instance of com.steadystate.css.parser.selectors.AttributeConditionImpl
		    	return cssAttributeConditionTranslator.translateEqualsOrHasAttr((AttributeCondition) condition).accept(astCssConditionVisitor);
	        case Condition.SAC_ID_CONDITION:
				return cssAttributeConditionTranslator.translateIdAttr((AttributeCondition) condition).accept(astCssConditionVisitor);
	        case Condition.SAC_ONE_OF_ATTRIBUTE_CONDITION:
	        	return cssAttributeConditionTranslator.translateContainsWord((AttributeCondition) condition).accept(astCssConditionVisitor);
	        case Condition.SAC_BEGIN_HYPHEN_ATTRIBUTE_CONDITION:
	        	return cssAttributeConditionTranslator.translateAttributeContains((AttributeCondition) condition).accept(astCssConditionVisitor);
	        case Condition.SAC_CLASS_CONDITION:
	        	return cssAttributeConditionTranslator.translateClasAttribute((AttributeCondition) condition).accept(astCssConditionVisitor);

	        case Condition.SAC_PSEUDO_CLASS_CONDITION:
                return pseudoClassConditionTranslator.translate(simpleSelector, argumentMap, (AttributeCondition) condition).accept(astCssPseudoClassConditionVisitor);
	        case Condition.SAC_LANG_CONDITION:
				return incompatible("Condition.SAC_LANG_CONDITION");

	        default:
				throw new CssUnknownConditionException(condition);
		}
	}

	private CssCondition incompatible(String s) {
		// if the exception below gets thrown, this means the CSS Parser has changed and
		// we must update our code as well.
		throw new SeleniumQueryException("The condition "+s+" is not used by the CSS Parser. " +
                "This version of seleniumQuery is not compatible with the CSS Parser present in the" +
                "classpath.");
	}

}

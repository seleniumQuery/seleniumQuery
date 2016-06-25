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

package io.github.seleniumquery.by.firstgen.css;

import io.github.seleniumquery.by.firstgen.css.combinators.DescendantCssSelector;
import io.github.seleniumquery.by.firstgen.css.combinators.DirectAdjacentCssSelector;
import io.github.seleniumquery.by.firstgen.css.combinators.DirectDescendantCssSelector;
import io.github.seleniumquery.by.firstgen.css.combinators.GeneralAdjacentCssSelector;
import io.github.seleniumquery.by.firstgen.css.conditionals.ConditionalCssSelector;
import io.github.seleniumquery.by.firstgen.css.tagname.TagNameSelector;
import io.github.seleniumquery.by.firstgen.xpath.component.TagComponent;
import io.github.seleniumquery.by.firstgen.xpath.component.XPathComponent;
import org.w3c.css.sac.Selector;

/**
 * Picks a high level CssSelector based on the Selector type.
 *
 * @author acdcjunior
 * @since 0.9.0
 */
public class CssSelectorFactory {

    private static final ConditionalCssSelector conditionalCssSelector = new ConditionalCssSelector();
    private static final TagNameSelector tagNameSelector = new TagNameSelector();
    private static final DescendantCssSelector descendantCssSelector = new DescendantCssSelector();
    private static final DirectDescendantCssSelector directDescendantCssSelector = new DirectDescendantCssSelector();
    private static final DirectAdjacentCssSelector directAdjacentCssSelector = new DirectAdjacentCssSelector();
    private static final GeneralAdjacentCssSelector generalAdjacentCssSelector = new GeneralAdjacentCssSelector();
    
    private CssSelectorFactory() {}

    @SuppressWarnings("unchecked")
	public static CssSelector<Selector, TagComponent> parsedSelectorToCssSelector(Selector parsedSimpleSelector) {
    	return (CssSelector<Selector, TagComponent>) CssSelectorFactory.getSelector(parsedSimpleSelector);
    }
    
	private static CssSelector<? extends Selector, ? extends XPathComponent> getSelector(Selector selector) {
		switch (selector.getSelectorType()) {
			case Selector.SAC_CONDITIONAL_SELECTOR:
				return conditionalCssSelector;
				
			case Selector.SAC_ELEMENT_NODE_SELECTOR:
				return tagNameSelector;
				
			// COMBINATORS
			case Selector.SAC_DESCENDANT_SELECTOR:
				return descendantCssSelector;
			case Selector.SAC_CHILD_SELECTOR:
				return directDescendantCssSelector;
			case Selector.SAC_DIRECT_ADJACENT_SELECTOR:
				return directAdjacentCssSelector;
			// the parser returns this code for the "E ~ F" selector. Go figure...
			case Selector.SAC_ANY_NODE_SELECTOR:
				return generalAdjacentCssSelector;
				
			case Selector.SAC_ROOT_NODE_SELECTOR:
			case Selector.SAC_NEGATIVE_SELECTOR:
			case Selector.SAC_TEXT_NODE_SELECTOR:
			case Selector.SAC_CDATA_SECTION_NODE_SELECTOR:
			case Selector.SAC_PROCESSING_INSTRUCTION_NODE_SELECTOR:
			case Selector.SAC_COMMENT_NODE_SELECTOR:
			case Selector.SAC_PSEUDO_ELEMENT_SELECTOR:
			default:
				return new UnknownCssSelector<>(selector.getSelectorType());
		}
	}

}
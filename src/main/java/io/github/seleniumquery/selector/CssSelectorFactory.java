package io.github.seleniumquery.selector;

import io.github.seleniumquery.selectors.combinators.DescendantCssSelector;
import io.github.seleniumquery.selectors.combinators.DirectAdjacentCssSelector;
import io.github.seleniumquery.selectors.combinators.DirectDescendantCssSelector;
import io.github.seleniumquery.selectors.combinators.GeneralAdjacentCssSelector;
import io.github.seleniumquery.selectors.conditionals.ConditionalCssSelector;
import io.github.seleniumquery.selectors.tagname.TagNameSelector;

import org.w3c.css.sac.Selector;

public class CssSelectorFactory {

	private static final CssSelectorFactory instance = new CssSelectorFactory();
	
	public static CssSelectorFactory getInstance() {
		return instance;
	}
	
	private CssSelectorFactory() { }

	public CssSelector<? extends Selector> getSelector(Selector selector) {
		switch (selector.getSelectorType()) {
			case Selector.SAC_CONDITIONAL_SELECTOR:
				return ConditionalCssSelector.getInstance();
				
			case Selector.SAC_ELEMENT_NODE_SELECTOR:
				return TagNameSelector.getInstance();
				
			// COMBINATORS
			case Selector.SAC_DESCENDANT_SELECTOR:
				return DescendantCssSelector.getInstance();
			case Selector.SAC_CHILD_SELECTOR:
				return DirectDescendantCssSelector.getInstance();
			case Selector.SAC_DIRECT_ADJACENT_SELECTOR:
				return DirectAdjacentCssSelector.getInstance();
			// the parser returns this code for the "E ~ F" selector. Go figure...
			case Selector.SAC_ANY_NODE_SELECTOR:
				return GeneralAdjacentCssSelector.getInstance();
				
			case Selector.SAC_ROOT_NODE_SELECTOR:
			case Selector.SAC_NEGATIVE_SELECTOR:
			case Selector.SAC_TEXT_NODE_SELECTOR:
			case Selector.SAC_CDATA_SECTION_NODE_SELECTOR:
			case Selector.SAC_PROCESSING_INSTRUCTION_NODE_SELECTOR:
			case Selector.SAC_COMMENT_NODE_SELECTOR:
			case Selector.SAC_PSEUDO_ELEMENT_SELECTOR:
			default:
				return new UnknownCssSelector<Selector>(selector.getSelectorType());
		}
	}

}
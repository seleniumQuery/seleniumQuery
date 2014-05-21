package io.github.seleniumquery.by.evaluator;

import io.github.seleniumquery.by.evaluator.combinators.ChildSelectorEvaluator;
import io.github.seleniumquery.by.evaluator.combinators.DescendantEvaluator;
import io.github.seleniumquery.by.evaluator.combinators.DirectAdjacentEvaluator;
import io.github.seleniumquery.by.evaluator.combinators.GeneralAdjacentEvaluator;
import io.github.seleniumquery.by.evaluator.conditionals.ConditionalSelectorEvaluator;
import io.github.seleniumquery.by.evaluator.tagname.TagNameEvaluator;

import org.w3c.css.sac.Selector;

public class SelectorEvaluatorFactory {

	private static final SelectorEvaluatorFactory instance = new SelectorEvaluatorFactory();
	
	public static SelectorEvaluatorFactory getInstance() {
		return instance;
	}
	
	private SelectorEvaluatorFactory() { }

	public CSSSelector<? extends Selector> getSelector(Selector selector) {
		switch (selector.getSelectorType()) {
			case Selector.SAC_CONDITIONAL_SELECTOR:
				return ConditionalSelectorEvaluator.getInstance();
				
			case Selector.SAC_ELEMENT_NODE_SELECTOR:
				return TagNameEvaluator.getInstance();
				
			// COMBINATORS
			case Selector.SAC_DESCENDANT_SELECTOR:
				return DescendantEvaluator.getInstance();
			case Selector.SAC_CHILD_SELECTOR:
				return ChildSelectorEvaluator.getInstance();
			case Selector.SAC_DIRECT_ADJACENT_SELECTOR:
				return DirectAdjacentEvaluator.getInstance();
			// the parser returns this code for the "E ~ F" selector. Go figure...
			case Selector.SAC_ANY_NODE_SELECTOR:
				return GeneralAdjacentEvaluator.getInstance();
				
			case Selector.SAC_ROOT_NODE_SELECTOR:
			case Selector.SAC_NEGATIVE_SELECTOR:
			case Selector.SAC_TEXT_NODE_SELECTOR:
			case Selector.SAC_CDATA_SECTION_NODE_SELECTOR:
			case Selector.SAC_PROCESSING_INSTRUCTION_NODE_SELECTOR:
			case Selector.SAC_COMMENT_NODE_SELECTOR:
			case Selector.SAC_PSEUDO_ELEMENT_SELECTOR:
			default:
				return new UnknownSelectorType<Selector>(selector.getSelectorType());
		}
	}

}
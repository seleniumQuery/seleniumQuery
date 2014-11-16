package io.github.seleniumquery.by.xpath;

import io.github.seleniumquery.by.selector.UnsupportedConditionalSelector;

import static io.github.seleniumquery.by.xpath.XPathExpression.EMPTY_LOCAL_NAME_CONDITIONAL;

public enum CssSelectorType {
	
	/*
	 * if CONDITIONAL_SIMPLE, then the expr can be just appended to other, such as:
	 * //*[@other][@thisSelector]
	 * 
	 * if CONDITIONAL_TO_ALL, then this must be applied to the whole result of the other, such as:
	 * (//*[@other])[@thisSelector]
	 */
	CONDITIONAL_SIMPLE {
		@Override
		public String merge(String sourceXPathExpression, CssSelectorType sourceKind, String otherXPathExpression) {
			if (sourceXPathExpression.endsWith("]")) {
				// because the previous was merged as a conditional, and we are a conditional as well, so we just 'AND it
				return sourceXPathExpression.substring(0, sourceXPathExpression.length()-1) + " and " + otherXPathExpression.substring(1);
			}
			return sourceXPathExpression + otherXPathExpression;
		}
		@Override
		public String mergeAsCondition(String sourceXPathExpression, CssSelectorType sourceKind, String otherXPathExpression) {
			return sourceXPathExpression + " and " + removeBraces(otherXPathExpression);
		}
	},
	CONDITIONAL_TO_ALL {
		@Override
		public String merge(String sourceXPathExpression, CssSelectorType sourceKind, String otherXPathExpression) {
			return "(" + sourceXPathExpression + ")" + otherXPathExpression;
		}
		@Override
		public String mergeAsCondition(String sourceXPathExpression, CssSelectorType sourceKind, String otherXPathExpression) {
			if (sourceXPathExpression.equals(EMPTY_LOCAL_NAME_CONDITIONAL)) {
				return removeBraces(otherXPathExpression);
			}
			return sourceXPathExpression + " and " + removeBraces(otherXPathExpression);
		}
	},

	// "//"
	DESCENDANT_GENERAL {
		@Override
		public String merge(String sourceXPathExpression, CssSelectorType sourceKind, String otherXPathExpression) {
			return sourceXPathExpression + "//" + otherXPathExpression;
		}
		@Override
		public String mergeAsCondition(String sourceXPathExpression, CssSelectorType sourceKind, String otherXPathExpression) {
			return unsupported("general descendant");
		}
	},
	
	// "/"
	DESCENDANT_DIRECT {
		@Override
		public String merge(String sourceXPathExpression, CssSelectorType sourceKind, String otherXPathExpression) {
			return sourceXPathExpression + "/" + otherXPathExpression;
		}
		@Override
		public String mergeAsCondition(String sourceXPathExpression, CssSelectorType sourceKind, String otherXPathExpression) {
			return unsupported("direct descendant");
		}
	},
	
	// "/following-sibling::"  (the direct will add a position()=1 by itself)
	// acdcjunior: read the above later but didnt understand: what direct??
	ADJACENT {
		@Override
		public String merge(String sourceXPathExpression, CssSelectorType sourceKind, String otherXPathExpression) {
			return sourceXPathExpression + "/following-sibling::" + otherXPathExpression;
		}
	},
	
	TAG {
		@Override
		public String merge(String sourceXPathExpression, CssSelectorType sourceKind, String otherXPathExpression) {
			if ("*".equals(otherXPathExpression)) {
				return CONDITIONAL_SIMPLE.merge(sourceXPathExpression, null, "[local-name()]");
			}
			return CONDITIONAL_SIMPLE.merge(sourceXPathExpression, null, "[local-name() = '"+otherXPathExpression+"']");
		}
	};

	/**
	 * @return The merged expression.
	 */
	public abstract String merge(String sourceXPathExpression, CssSelectorType sourceKind, String otherXPathExpression);

	public String mergeAsCondition(String sourceXPathExpression, CssSelectorType sourceKind, String otherXPathExpression) {
		return this.merge(sourceXPathExpression, sourceKind, otherXPathExpression);
	}

	private static String unsupported(String selectorKind) {
		throw new UnsupportedConditionalSelector("We do not support "+selectorKind+" as conditions inside selectors.");
	}

	private static String removeBraces(String src) {
		return src.substring(1, src.length()-1);
	}

}
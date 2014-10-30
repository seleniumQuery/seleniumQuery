package io.github.seleniumquery.selectorxpath;


public enum SqSelectorKind {
	
	/*
	 * if CONDITIONAL_SIMPLE, then the expr can be just appended to other, such as:
	 * //*[@other][@thisSelector]
	 * 
	 * if CONDITIONAL_TO_ALL, then this must be applied to the whole result of the other, such as:
	 * (//*[@other])[@thisSelector]
	 */
	CONDITIONAL_SIMPLE {
		@Override
		public String merge(String sourceXPathExpression, String otherXPathExpression) {
			if (sourceXPathExpression.endsWith("]")) {
				return sourceXPathExpression.substring(0, sourceXPathExpression.length()-1) + " and " + otherXPathExpression.substring(1);
			}
			return sourceXPathExpression + otherXPathExpression;
		}
	},
	CONDITIONAL_TO_ALL {
		@Override
		public String merge(String sourceXPathExpression, String otherXPathExpression) {
			return "(" + sourceXPathExpression + ")" + otherXPathExpression;
		}
	},
	
	// "//"
	DESCENDANT_GENERAL {
		@Override
		public String merge(String sourceXPathExpression, String otherXPathExpression) {
			return sourceXPathExpression + "//" + otherXPathExpression;
		}
	},
	
	// "/"
	DESCENDANT_DIRECT {
		@Override
		public String merge(String sourceXPathExpression, String otherXPathExpression) {
			return sourceXPathExpression + "/" + otherXPathExpression;
		}
	},
	
	// "/following-sibling::"  (the direct will add a position()=1 by itself)
	ADJACENT {
		@Override
		public String merge(String sourceXPathExpression, String otherXPathExpression) {
			return sourceXPathExpression + "/following-sibling::" + otherXPathExpression;
		}
	},
	
	TAG {
		@Override
		public String merge(String sourceXPathExpression, String otherXPathExpression) {
			if ("*".equals(otherXPathExpression)) {
				return CONDITIONAL_SIMPLE.merge(sourceXPathExpression, "[local-name()]");
			}
			return CONDITIONAL_SIMPLE.merge(sourceXPathExpression, "[local-name() = '"+otherXPathExpression+"']");
		}
	};
	
	/**
	 * @return The merged expression.
	 */
	public abstract String merge(String sourceXPathExpression, String otherXPathExpression);

}
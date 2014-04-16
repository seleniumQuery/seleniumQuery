package io.github.seleniumquery.functions.isparser;

public class IsPseudo extends BaseCondition {
	
	@Override
	protected String getName() {
		return "pseudo";
	}

	@Override
	public boolean acceptsChildren() {
		return true;
	}

}
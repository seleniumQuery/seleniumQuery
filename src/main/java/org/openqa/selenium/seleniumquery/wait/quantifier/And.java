package org.openqa.selenium.seleniumquery.wait.quantifier;

public class And implements Quantifier {
	
	private Quantifier[] quantifiers;
	
	public And(Quantifier... quantifiers) {
		this.quantifiers = quantifiers;

	}

	@Override
	public boolean isQuantityGoodEnough(int totalAcquired, int totalSatisfyingRestrictions) {
		for (Quantifier quantifier : quantifiers) {
			if (!quantifier.isQuantityGoodEnough(totalAcquired, totalSatisfyingRestrictions)) {
				return false;
			}
		}
		return true;
	}

}
package org.openqa.selenium.seleniumquery.wait.quantifier;

public class Or implements Quantifier {
	
	private Quantifier[] quantifiers;
	
	public Or(Quantifier... quantifiers) {
		this.quantifiers = quantifiers;

	}

	@Override
	public boolean isQuantityGoodEnough(int totalAcquired, int totalSatisfyingRestrictions) {
		for (Quantifier quantifier : quantifiers) {
			if (quantifier.isQuantityGoodEnough(totalAcquired, totalSatisfyingRestrictions)) {
				return true;
			}
		}
		return false;
	}

}
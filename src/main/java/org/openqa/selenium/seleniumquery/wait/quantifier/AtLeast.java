package org.openqa.selenium.seleniumquery.wait.quantifier;

public class AtLeast implements Quantifier {
	
	public static final AtLeast ONE = new AtLeast(1);
	
	private int desiredQuantity;
	
	public AtLeast(int desiredQuantity) {
		this.desiredQuantity = desiredQuantity;
	}

	@Override
	public boolean isQuantityGoodEnough(int totalAcquired, int totalSatisfyingRestrictions) {
		return totalSatisfyingRestrictions >= this.desiredQuantity;
	}
	
}
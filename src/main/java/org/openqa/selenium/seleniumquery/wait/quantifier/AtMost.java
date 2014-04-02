package org.openqa.selenium.seleniumquery.wait.quantifier;

public class AtMost implements Quantifier {
	
	public static final AtMost ONE = new AtMost(1);
	
	private int desiredQuantity;
	
	public AtMost(int desiredQuantity) {
		this.desiredQuantity = desiredQuantity;
	}

	@Override
	public boolean isQuantityGoodEnough(int totalAcquired, int totalSatisfyingRestrictions) {
		return totalSatisfyingRestrictions <= this.desiredQuantity;
	}
	
}
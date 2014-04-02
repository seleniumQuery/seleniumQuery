package org.openqa.selenium.seleniumquery.wait.quantifier;

public class Exactly implements Quantifier {
	
	public static final Exactly ONE = new Exactly(1);
	
	private int desiredQuantity;
	
	public Exactly(int desiredQuantity) {
		this.desiredQuantity = desiredQuantity;
	}

	@Override
	public boolean isQuantityGoodEnough(int totalAcquired, int totalSatisfyingRestrictions) {
		return totalSatisfyingRestrictions == this.desiredQuantity;
	}
	
}
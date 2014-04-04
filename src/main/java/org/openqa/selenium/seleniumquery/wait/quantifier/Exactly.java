package org.openqa.selenium.seleniumquery.wait.quantifier;

/**
 * Tests if <strong>exactly<strong> a given number of elements satisfy the imposed restriction.
 * 
 * @author acdcjunior
 * @since 0.4.0
 */
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
	
	@Override
	public String toString() {
		return "exactly "+this.desiredQuantity;
	}
	
}
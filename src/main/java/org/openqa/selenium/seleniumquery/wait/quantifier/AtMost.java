package org.openqa.selenium.seleniumquery.wait.quantifier;

/**
 * Tests if <strong>at most<strong> a given number of elements satisfy the imposed restriction.
 * 
 * @author acdcjunior
 * @since 0.4.0
 */
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
	
	@Override
	public String toString() {
		return "at most "+this.desiredQuantity;
	}
	
}
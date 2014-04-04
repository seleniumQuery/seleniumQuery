package org.openqa.selenium.seleniumquery.wait.quantifier;

/**
 * Tests if <strong>at least<strong> a given number of elements satisfy the imposed restriction.
 * 
 * @author acdcjunior
 * @since 0.4.0
 */
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
	
	@Override
	public String toString() {
		return "at least "+this.desiredQuantity;
	}
	
}
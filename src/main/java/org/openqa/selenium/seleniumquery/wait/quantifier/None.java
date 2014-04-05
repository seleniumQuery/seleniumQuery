package org.openqa.selenium.seleniumquery.wait.quantifier;

/**
 * Tests if <strong>no<strong> acquired element satisfies the imposed restriction.
 * 
 * @author acdcjunior
 * @since 0.4.0
 */
public class None implements Quantifier {

	public static final None NONE = new None();
	
	private static final int NO_ONE_SATISFIES = 0;

	@Override
	public boolean isQuantityGoodEnough(int totalAcquired, int totalSatisfyingRestrictions) {
		return totalSatisfyingRestrictions == NO_ONE_SATISFIES;
	}
	
	@Override
	public String toString() {
		return "no";
	}

}
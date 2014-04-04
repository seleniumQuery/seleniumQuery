package org.openqa.selenium.seleniumquery.wait.quantifier;

/**
 * Tests if <strong>no<strong> acquired element satisfies the imposed restriction.
 * 
 * @author acdcjunior
 * @since 0.4.0
 */
public class None implements Quantifier {

	public static final None NONE = new None();
	
	private static final int LEAST_QUANTITY_TO_BE_CONSIDERED_NON_EMPTY = 1;
	private static final int NO_ONE_IS_SATISFYING = 0;

	@Override
	public boolean isQuantityGoodEnough(int totalAcquired, int totalSatisfyingRestrictions) {
		return totalAcquired > LEAST_QUANTITY_TO_BE_CONSIDERED_NON_EMPTY && totalSatisfyingRestrictions == NO_ONE_IS_SATISFYING;
	}
	
	@Override
	public String toString() {
		return "no";
	}

}
package org.openqa.selenium.seleniumquery.wait.quantifier;

public class Every implements Quantifier {
	
	public static final Every EVERY = new Every();
	
	private static final int LEAST_QUANTITY_TO_BE_CONSIDERED_NON_EMPTY = 1;

	@Override
	public boolean isQuantityGoodEnough(int totalAcquired, int totalSatisfyingRestrictions) {
		return totalAcquired > LEAST_QUANTITY_TO_BE_CONSIDERED_NON_EMPTY && totalSatisfyingRestrictions == totalAcquired;
	}
	
}
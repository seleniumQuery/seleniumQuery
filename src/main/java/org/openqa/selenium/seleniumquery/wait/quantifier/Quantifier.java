package org.openqa.selenium.seleniumquery.wait.quantifier;

public interface Quantifier {
	
	public boolean isQuantityGoodEnough(int totalAcquired, int totalSatisfyingRestrictions);

}
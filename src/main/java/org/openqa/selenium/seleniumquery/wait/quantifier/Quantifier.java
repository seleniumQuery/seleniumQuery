package org.openqa.selenium.seleniumquery.wait.quantifier;

/**
 * Interface of objects that test if the given total number of elements and given number of
 * those satisfying the restriction is good enough.
 * 
 * @author acdcjunior
 * @since 0.4.0
 */
public interface Quantifier {
	
	boolean isQuantityGoodEnough(int totalAcquired, int totalSatisfyingRestrictions);

}
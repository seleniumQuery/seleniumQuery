package org.openqa.selenium.seleniumquery.wait.quantifier;

/**
 * @author acdcjunior
 * @since 0.4.0
 */
public class And implements Quantifier {
	
	/**
	 * @author acdcjunior
	 * @since 0.4.0
	 */
	public static And and(Quantifier... quantifiers) {
		return new And(quantifiers);
	}
	
	private Quantifier[] quantifiers;
	
	/**
	 * @author acdcjunior
	 * @since 0.4.0
	 */
	private And(Quantifier... quantifiers) {
		this.quantifiers = quantifiers;
	}

	/**
	 * @author acdcjunior
	 * @since 0.4.0
	 */
	@Override
	public boolean isQuantityGoodEnough(int totalAcquired, int totalSatisfyingRestrictions) {
		for (Quantifier quantifier : quantifiers) {
			if (!quantifier.isQuantityGoodEnough(totalAcquired, totalSatisfyingRestrictions)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * @author acdcjunior
	 * @since 0.4.0
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(3 + quantifiers.length * 10);
		for (Quantifier quantifier : quantifiers) {
			sb.append(quantifier.toString()).append(" and ");
		}
		sb.setLength(sb.length() - 5);
		return sb.toString();
	}

}
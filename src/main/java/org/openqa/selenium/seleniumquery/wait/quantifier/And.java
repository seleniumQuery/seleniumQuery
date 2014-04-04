package org.openqa.selenium.seleniumquery.wait.quantifier;


public class And implements Quantifier {
	
	public static And and(Quantifier... quantifiers) {
		return new And(quantifiers);
	}
	
	private Quantifier[] quantifiers;
	
	private And(Quantifier... quantifiers) {
		this.quantifiers = quantifiers;
	}

	@Override
	public boolean isQuantityGoodEnough(int totalAcquired, int totalSatisfyingRestrictions) {
		for (Quantifier quantifier : quantifiers) {
			if (!quantifier.isQuantityGoodEnough(totalAcquired, totalSatisfyingRestrictions)) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(3 + quantifiers.length * 10);
		sb.append(" ");
		for (Quantifier quantifier : quantifiers) {
			sb.append(quantifier.toString()).append(" and ");
		}
		sb.setLength(sb.length() - 5);
		return sb.toString();
	}

}
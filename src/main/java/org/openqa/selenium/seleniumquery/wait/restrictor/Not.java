package org.openqa.selenium.seleniumquery.wait.restrictor;

import org.openqa.selenium.WebElement;

public final class Not implements Restrictor {
	private Restrictor[] restrictors;

	public Not(Restrictor...restrictors) {
		this.restrictors = restrictors;
	}

	@Override
	public boolean fulfillsRestriction(WebElement webElement) {
		for (Restrictor restrictor : restrictors) {
			if (!restrictor.fulfillsRestriction(webElement)) {
				return true;
			}
		}
		return false;
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(3 + restrictors.length * 10);
		sb.append("not ");
		for (Restrictor restrictor : restrictors) {
			sb.append(restrictor.toString()).append(" ");
		}
		sb.setLength(sb.length() - 1);
		return sb.toString();
	}

	public static final Restrictor not(Restrictor...restrictors) {
		return new Not(restrictors);
	}
}
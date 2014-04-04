package org.openqa.selenium.seleniumquery.wait.restrictor;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.seleniumquery.functions.ValFunction;

public class Is {
	
	public static final Restrictor PRESENT = new Restrictor() {
		@Override
		public boolean fulfillsRestriction(WebElement webElement) {
			try {
				// Calling any method forces a staleness check
				webElement.isEnabled();
				// passed staleness check, thus present
				return true;
			} catch (StaleElementReferenceException expected) {
				// failed staleness check, so not present
				return false;
			}
		}
		@Override
		public String toString() {
			return "present";
		}
	};
	
	public static final Restrictor VISIBLE = new Restrictor() {
		@Override
		public boolean fulfillsRestriction(WebElement webElement) {
			return webElement.isDisplayed();
		}
		@Override
		public String toString() {
			return "visible";
		}
	};
	
	public static final Restrictor ENABLED = new Restrictor() {
		@Override
		public boolean fulfillsRestriction(WebElement webElement) {
			return webElement.isEnabled();
		}
		@Override
		public String toString() {
			return "enabled";
		}
	};
	
	public static final Restrictor VISIBLE_AND_ENABLED = new Restrictor() {
		@Override
		public boolean fulfillsRestriction(WebElement webElement) {
			return webElement.isDisplayed() && webElement.isEnabled();
		}
		@Override
		public String toString() {
			return "visible and enabled";
		}
	};
	
	public static final Restrictor not(Restrictor...restrictors) {
		return new Not(restrictors);
	}
	
	public static final class Not implements Restrictor {
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
	};
	
	
	public static final <PARAMETER_TYPE> Restrictor withValue(PARAMETER_TYPE parameterValue) {
		return new WithValue<PARAMETER_TYPE>(parameterValue);
	}
	
	public static final class WithValue<PARAMETER_TYPE> implements Restrictor {
		private PARAMETER_TYPE parameterValue;
		
		public WithValue(PARAMETER_TYPE parameterValue) {
			this.parameterValue = parameterValue;
		}
		
		@Override
		public boolean fulfillsRestriction(WebElement webElement) {
			return ValFunction.val(webElement).equals(this.parameterValue);
		}
		@Override
		public String toString() {
			return "with value "+this.parameterValue;
		}
	};
	
	public static final Restrictor textContaining(String parameterValue) {
		return new ContainingValue(parameterValue);
	}
	
	public static final class ContainingValue implements Restrictor {
		private String parameterValue;
		
		public ContainingValue(String parameterValue) {
			this.parameterValue = parameterValue;
		}
		
		@Override
		public boolean fulfillsRestriction(WebElement webElement) {
			return webElement.getText().contains(parameterValue);
		}
		@Override
		public String toString() {
			return "with value "+this.parameterValue;
		}
	};
	
}
package org.openqa.selenium.seleniumquery.wait.restrictor;


public interface RestrictorDecorator {
	
	RestrictorDecorator NO_DECORATION = new RestrictorDecorator() {
		@Override
		public Restrictor decorate(Restrictor restrictor) {
			return restrictor;
		}
	};
	
	RestrictorDecorator NEGATION = new RestrictorDecorator() {
		@Override
		public Restrictor decorate(Restrictor restrictor) {
			return Is.not(restrictor);
		}
	};
	
	Restrictor decorate(Restrictor restrictor);
	
}
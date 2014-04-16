package io.github.seleniumquery.wait;

import io.github.seleniumquery.SeleniumQueryObject;
import io.github.seleniumquery.wait.SeleniumQueryFluentWait.WaitMethod;
import io.github.seleniumquery.wait.evaluators.ContainsEvaluator;
import io.github.seleniumquery.wait.evaluators.EqualsEvaluator;
import io.github.seleniumquery.wait.evaluators.Evaluator;
import io.github.seleniumquery.wait.evaluators.GreaterThanEvaluator;
import io.github.seleniumquery.wait.evaluators.LessThanEvaluator;
import io.github.seleniumquery.wait.evaluators.MatchesEvaluator;
import io.github.seleniumquery.wait.getters.Getter;

/**
 * @author acdcjunior
 *
 * @param <T> The type returned by the getter and TYPE OF THE ARGUMENT used in the end function.
 */
public class SeleniumQueryEvaluateUntil<T> {
	
	protected WaitMethod waitMethod;
	protected Getter<T> getter;
	protected SeleniumQueryObject seleniumQueryObject;
	
	public SeleniumQueryEvaluateUntil(WaitMethod waitMethod, Getter<T> getter, SeleniumQueryObject seleniumQueryObject) {
		this.waitMethod = waitMethod;
		this.getter = getter;
		this.seleniumQueryObject = seleniumQueryObject;
	}

	public SeleniumQueryAndOrThen isEqualTo(T valueToEqual) {
		Evaluator<T> equalsEvaluator = new EqualsEvaluator<T>(getter);
		SeleniumQueryObject sq = waitMethod.evaluateUntil(equalsEvaluator, valueToEqual, seleniumQueryObject);
		return new SeleniumQueryAndOrThen(sq);
	}
	
	public SeleniumQueryAndOrThen contains(String string) {
		Evaluator<String> containsEvaluator = new ContainsEvaluator(getter);
		return new SeleniumQueryAndOrThen(waitMethod.evaluateUntil(containsEvaluator, string, seleniumQueryObject));
	}
	
	public SeleniumQueryAndOrThen matches(String regex) {
		Evaluator<String> matchesEvaluator = new MatchesEvaluator(getter);
		return new SeleniumQueryAndOrThen(waitMethod.evaluateUntil(matchesEvaluator, regex, seleniumQueryObject));
	}
	
	public SeleniumQueryAndOrThen isGreaterThan(Number valueToCompare) {
		Evaluator<Number> greaterThanEvaluator = new GreaterThanEvaluator(getter);
		SeleniumQueryObject sq = waitMethod.evaluateUntil(greaterThanEvaluator, valueToCompare, seleniumQueryObject);
		return new SeleniumQueryAndOrThen(sq);
	}
	
	public SeleniumQueryAndOrThen isLessThan(Number valueToCompare) {
		Evaluator<Number> lessThanEvaluator = new LessThanEvaluator(getter);
		SeleniumQueryObject sq = waitMethod.evaluateUntil(lessThanEvaluator, valueToCompare, seleniumQueryObject);
		return new SeleniumQueryAndOrThen(sq);
	}

}
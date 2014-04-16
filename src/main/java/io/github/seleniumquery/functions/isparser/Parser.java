package io.github.seleniumquery.functions.isparser;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Parser {
	
	private Parser() { }
	
	private static final Character END_OF_STRING = null;
	
	boolean insideNot = false;
	int bracesOpen = 0;
	int currIndex;
	String str;
	LinkedList<Boolean> wasBracerOpeningANot = new LinkedList<Boolean>();
	boolean negatedPresentFound;
	
	private List<Condition> conditions = new ArrayList<Condition>();
	
	public List<Condition> parse(String str, int startingIndex) {
		this.str = str;
		this.insideNot = false;
		this.bracesOpen = 0;
		this.currIndex = 0;
		this.negatedPresentFound = false;

		eatNext(advanceChar());
		
		return conditions;
	}
	
	private Character getChar() {
		// already found a negated :present, can end the processing
		if (this.negatedPresentFound) {
			return END_OF_STRING;
		}
		// reached end of string, nothing else to process
		if (currIndex == str.length()) {
			return END_OF_STRING;
		}
		return str.charAt(currIndex);
	}
	
	private Character advanceChar() {
		currIndex++;
		return getChar();
	}
	
	private void eatNext(Character c) {
		System.out.println("eatNext, c is "+c);
		if (c == END_OF_STRING || c == ' ') {
			return;
		}
		if (Character.isLetter(c)) {
			eatTagName(c);
		}
		switch (c) {
		case '.':
			eatClassName(advanceChar());
			return;
		case ':':
			eatPseudo(advanceChar());
			return;
		case '\\':
			advanceChar(); // eat the escaped
			eatNext(advanceChar());
			return;
		case '(':
			wasBracerOpeningANot.add(false);
			eatNext(advanceChar());
			return;
		case ')':
			if (wasBracerOpeningANot.size() == 0) {
				System.err.println("Invalid selector: \""+str+"\"! Attempts to close a never opened bracket pair at "+currIndex+"!");
				return;
			}
			boolean wasANot = wasBracerOpeningANot.pop();
			if (wasANot) {
				insideNot = !insideNot;
			}
			eatNext(advanceChar());
			return;
		default:
			eatNext(advanceChar());
			return;
		}
	}

	private void eatIdentifier(Character c, Class<? extends Condition> conditionType, boolean insideOpenBraces) {
		Condition condition = null;
		try {
			condition = conditionType.newInstance();
		} catch (Exception e) { throw new RuntimeException(e); }
		
		// : was consumed before reaching here
		StringBuilder sb = new StringBuilder();
		while (c != END_OF_STRING) {
			if (isValidClassName(c)) {
				sb.append(c);
				c = advanceChar();
			} else if (c == '(' && condition.acceptsChildren()) {
				List<Condition> children = new Parser().parse(this.str, this.currIndex);
				condition.addChildren(children);
				sb.append(c);
				c = advanceChar();
			} else {
				break;
			}
		}
		condition.setCondition(sb.toString());
		conditions.add(condition);
		eatNext(c);
	}
	
	private void eatTagName(Character c) {
		eatIdentifier(c, IsTag.class, false);
	}
	
	private void eatClassName(Character c) {
		eatIdentifier(c, HasClass.class, false);
	}
	
	private void eatPseudo(Character c) {
		eatIdentifier(c, IsPseudo.class, false);
	}

	private boolean isValidClassName(Character c) {
		return Character.isLetterOrDigit(c) || c == '-' || c == '_';
	}
	
	public static void main(String[] args) {
		List<Condition> go = new Parser().parse("bozo.myBall:enabled:visible.go:contains()", 0);
		System.out.println("@# Conditions parsed:");
		for (Condition condition : go) {
			System.out.println(condition);
		}
	}

}
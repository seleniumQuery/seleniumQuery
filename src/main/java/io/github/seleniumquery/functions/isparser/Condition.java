package io.github.seleniumquery.functions.isparser;

import java.util.List;

public interface Condition {

	void setCondition(String string);
	
	boolean acceptsChildren();
	
	void addChild(Condition c);
	
	List<Condition> getChildren();

	void addChildren(List<Condition> children);

}
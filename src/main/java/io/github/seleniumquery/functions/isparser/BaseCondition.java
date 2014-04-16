package io.github.seleniumquery.functions.isparser;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseCondition implements Condition {
	
	protected String condition;
	protected List<Condition> children = new ArrayList<Condition>();
	
	@Override
	public void setCondition(String condition) {
		this.condition = condition;
	}
	
	@Override
	public String toString() {
		return "Is "+getName()+" with name '"+this.condition+"'";
	}

	@Override
	public boolean acceptsChildren() {
		return false;
	}
	
	protected abstract String getName();
	
	@Override
	public void addChild(Condition c) {
		this.children.add(c);
	}
	
	@Override
	public void addChildren(List<Condition> children) {
		this.children.addAll(children);
	}
	
	public List<Condition> getChildren() {
		return children;
	}

}
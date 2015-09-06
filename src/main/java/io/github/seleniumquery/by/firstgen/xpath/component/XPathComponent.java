/*
 * Copyright (c) 2015 seleniumQuery authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.seleniumquery.by.firstgen.xpath.component;

import io.github.seleniumquery.by.firstgen.filter.ElementFilterList;

import java.util.Collections;
import java.util.List;

public abstract class XPathComponent {

	public static final String MATCH_EVERYTHING_XPATH_CONDITIONAL = "true()";
	protected String xPathExpression;
	protected final List<XPathComponent> combinatedComponents;
	protected ElementFilterList elementFilterList;
	
	XPathComponent(String xPathExpression, ElementFilterList elementFilterList) {
		this(xPathExpression, Collections.<XPathComponent>emptyList(), elementFilterList);
	}

	XPathComponent(String xPathExpression, List<XPathComponent> combinatedComponents, ElementFilterList elementFilterList) {
		this.xPathExpression = xPathExpression;
		this.combinatedComponents = Collections.unmodifiableList(combinatedComponents);
		this.elementFilterList = elementFilterList;
	}
	
	@Override
	public String toString() {
		return "[XPath: \""+xPathExpression+"\", combinatedComponents: "+ combinatedComponents +", filter: "+elementFilterList+"]";
	}
	
	public ElementFilterList mergeIntoFilter(ElementFilterList sourceElementFilterList) {
		return sourceElementFilterList;
	}

	public abstract String mergeIntoExpression(String sourceXPathExpression);

	public ElementFilterList mergeFilterAsCondition(ElementFilterList sourceElementFilterList) {
		return sourceElementFilterList;
	}

	public abstract String mergeExpressionAsCondition(String sourceXPathExpression);

	public String toSingleXPathExpression() {
		return this.xPathExpression;
	}

}
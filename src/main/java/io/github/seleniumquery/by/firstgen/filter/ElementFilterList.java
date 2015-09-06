package io.github.seleniumquery.by.filter;

import io.github.seleniumquery.by.SelectorUtils;
import io.github.seleniumquery.by.css.pseudoclasses.UnsupportedPseudoClassException;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * This class should be IMMUTABLE.
 *
 * @author acdcjunior
 * @author ricardo-sc
 *
 * @since 0.10.0
 */
public class ElementFilterList {

    public static ElementFilterList asFilterList(ElementFilter... filters) {
        return new ElementFilterList(asList(filters));
    }

    public static final ElementFilterList FILTER_NOTHING_LIST = new ElementFilterList(Collections.<ElementFilter>emptyList()) {
        @Override
        public List<WebElement> filter(WebDriver driver, List<WebElement> elements) {
            return elements;
        }
        @Override
        public ElementFilterList merge(ElementFilterList elementFilterList) {
            return elementFilterList;
        }
    };

	private List<ElementFilter> elementFilters;
	
	public ElementFilterList(List<ElementFilter> elementFilters) {
		this.elementFilters = Collections.unmodifiableList(elementFilters);
	}

	public List<WebElement> filter(SearchContext context, List<WebElement> elements) {
		WebDriver driver = SelectorUtils.getWebDriver(context);
		return filter(driver, elements);
	}

	public List<WebElement> filter(WebDriver driver, List<WebElement> elements) {
		if (this.elementFilters.size() > 0) {
			// we are currently disabling the filter support
			// we will only take it back when the system is stable
			throw new UnsupportedPseudoClassException("The current selector is not yet supported. Please try a simpler one.");
		}
		for (ElementFilter elementFilter : elementFilters) {
			elements = elementFilter.filterElements(driver, elements);
		}
		return elements;
	}

    public boolean isEmpty() {
        return elementFilters.isEmpty();
    }

	public List<ElementFilter> getElementFilters() {
		return elementFilters;
	}

    public ElementFilterList merge(ElementFilterList elementFilterList) {
        if (elementFilterList == FILTER_NOTHING_LIST) {
            return this;
        }
        List<ElementFilter> myFilters = this.getElementFilters();
        List<ElementFilter> theirFilters = elementFilterList.getElementFilters();
        List<ElementFilter> ourFilters = new ArrayList<ElementFilter>(myFilters.size()+theirFilters.size());
        ourFilters.addAll(myFilters);
        ourFilters.addAll(theirFilters);
        return new ElementFilterList(ourFilters);
    }

}
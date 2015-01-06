package io.github.seleniumquery.by.css.pseudoclasses;

import io.github.seleniumquery.by.xpath.XPathComponent;
import io.github.seleniumquery.by.css.CssConditionalSelector;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.AttributeCondition;
import org.w3c.css.sac.Selector;

/**
 * :pseudo-classes
 * :pseudo-classes([args])
 *
 * @author acdcjunior
 *
 * @since 0.9.0
 */
public class PseudoClassCssSelector implements CssConditionalSelector<AttributeCondition> {

	private final List<PseudoClass> pseudoClasses = Arrays.asList(new CheckedPseudoClass(),
			new SelectedPseudoClass(), new EqPseudoClass(), new OnlyChildPseudoClass(),
			new ContainsPseudoClass(), new FirstChildPseudoClass(), new NotPseudoClass(),
			new OnlyOfTypePseudoClass(), new RootPseudoClass(), new PresentPseudoClass(),
			new EnabledPseudoClass(), new DisabledPseudoClass(), new VisiblePseudoClass(),
			new HiddenPseudoClass(), new FirstPseudoClass(), new LastPseudoClass(),
			new CheckboxPseudoClass(), new RadioPseudoClass(), new ImagePseudoClass(),
			new PasswordPseudoClass(), new FilePseudoClass(), new SubmitPseudoClass(),
			new ResetPseudoClass(), new ButtonPseudoClass(), new InputPseudoClass(),
			new HeaderPseudoClass(), new LtPseudoClass(), new GtPseudoClass(),
			new FocusPseudoClass(), new FocusablePseudoClass(), new TabbablePseudoClass(),
			new HasPseudoClass(), new LangPseudoClass(), new ParentPseudoClass(),
			new EmptyPseudoClass(), new TextPseudoClass(), new EvenPseudoClass(),
			new OddPseudoClass(), new NthChildPseudoClass());

	@Override
	public boolean isCondition(WebDriver driver, WebElement element, Map<String, String> stringMap, Selector selectorUpToThisPoint, AttributeCondition attributeCondition) {
		String pseudoClassValue = attributeCondition.getValue();
		for (PseudoClass pseudoClass : pseudoClasses) {
			if (pseudoClass.isApplicable(pseudoClassValue)) {
				return pseudoClass.isPseudoClass(driver, element, new PseudoClassSelector(stringMap, selectorUpToThisPoint, pseudoClassValue));
			}
		}
		System.err.println("Warning: Unsupported pseudo-class: " + pseudoClassValue);
		return false;
	}
	
	@Override
	public XPathComponent conditionToXPath(Map<String, String> stringMap, Selector selectorUpToThisPoint, AttributeCondition attributeCondition) {
		String pseudoClassValue = attributeCondition.getValue();
		for (PseudoClass pseudoClass : pseudoClasses) {
			if (pseudoClass.isApplicable(pseudoClassValue)) {
				return pseudoClass.pseudoClassToXPath(new PseudoClassSelector(stringMap, selectorUpToThisPoint, pseudoClassValue));
			}
		}
		PseudoClassSelector pseudoClassSelector = new PseudoClassSelector(stringMap, selectorUpToThisPoint, pseudoClassValue);
		// right now we'll just exit, hoping to cause less problems
		throw new UnsupportedPseudoClassException(pseudoClassSelector.getOriginalPseudoClassSelector());
	}

}
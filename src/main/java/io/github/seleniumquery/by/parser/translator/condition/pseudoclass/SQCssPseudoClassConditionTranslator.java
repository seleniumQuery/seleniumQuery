package io.github.seleniumquery.by.parser.translator.condition.pseudoclass;

import io.github.seleniumquery.by.css.pseudoclasses.PseudoClassSelector;
import io.github.seleniumquery.by.css.pseudoclasses.UnsupportedPseudoClassException;
import io.github.seleniumquery.by.parser.parsetree.condition.SQCssCondition;
import io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.SQCssPseudoClassCondition;
import io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.basicfilter.*;
import io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.childfilter.SQCssFirstChildPseudoClass;
import io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.form.*;
import org.w3c.css.sac.AttributeCondition;
import org.w3c.css.sac.SimpleSelector;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * :pseudo-classes
 * :pseudo-classes([args])
 *
 * @author acdcjunior
 * @since 0.10.0
 */
public class SQCssPseudoClassConditionTranslator {

	private Map<String, Class<? extends SQCssPseudoClassCondition>> pseudoClasses = new HashMap<String, Class<? extends SQCssPseudoClassCondition>>();

	public SQCssPseudoClassConditionTranslator() {
		// form
		pseudoClasses.put(SQCssButtonPseudoClass.PSEUDO, SQCssButtonPseudoClass.class);
		pseudoClasses.put(SQCssCheckboxPseudoClass.PSEUDO, SQCssCheckboxPseudoClass.class);
		pseudoClasses.put(SQCssCheckedPseudoClass.PSEUDO, SQCssCheckedPseudoClass.class);
		pseudoClasses.put(SQCssEnabledPseudoClass.PSEUDO, SQCssEnabledPseudoClass.class);
		pseudoClasses.put(SQCssDisabledPseudoClass.PSEUDO, SQCssDisabledPseudoClass.class);
		pseudoClasses.put(SQCssFilePseudoClass.PSEUDO, SQCssFilePseudoClass.class);
		pseudoClasses.put(SQCssFocusPseudoClass.PSEUDO, SQCssFocusPseudoClass.class);
		pseudoClasses.put(SQCssImagePseudoClass.PSEUDO, SQCssImagePseudoClass.class);
		pseudoClasses.put(SQCssInputPseudoClass.PSEUDO, SQCssInputPseudoClass.class);
		pseudoClasses.put(SQCssPasswordPseudoClass.PSEUDO, SQCssPasswordPseudoClass.class);
		pseudoClasses.put(SQCssRadioPseudoClass.PSEUDO, SQCssRadioPseudoClass.class);
		pseudoClasses.put(SQCssResetPseudoClass.PSEUDO, SQCssResetPseudoClass.class);
		pseudoClasses.put(SQCssSelectedPseudoClass.PSEUDO, SQCssSelectedPseudoClass.class);
		pseudoClasses.put(SQCssSubmitPseudoClass.PSEUDO, SQCssSubmitPseudoClass.class);
		pseudoClasses.put(SQCssTextPseudoClass.PSEUDO, SQCssTextPseudoClass.class);

		// basic filter
		pseudoClasses.put(SQCssAnimatedPseudoClass.PSEUDO, SQCssAnimatedPseudoClass.class);
		pseudoClasses.put(SQCssEqPseudoClass.PSEUDO, SQCssEqPseudoClass.class);
		pseudoClasses.put(SQCssEvenPseudoClass.PSEUDO, SQCssEvenPseudoClass.class);
		pseudoClasses.put(SQCssFirstPseudoClass.PSEUDO, SQCssFirstPseudoClass.class);
		pseudoClasses.put(SQCssGtPseudoClass.PSEUDO, SQCssGtPseudoClass.class);
		pseudoClasses.put(SQCssHeaderPseudoClass.PSEUDO, SQCssHeaderPseudoClass.class);
		pseudoClasses.put(SQCssLastPseudoClass.PSEUDO, SQCssLastPseudoClass.class);
		pseudoClasses.put(SQCssLtPseudoClass.PSEUDO, SQCssLtPseudoClass.class);
		pseudoClasses.put(SQCssNotPseudoClass.PSEUDO, SQCssNotPseudoClass.class);
		pseudoClasses.put(SQCssNotPseudoClass.PSEUDO_PURE_NOT, SQCssNotPseudoClass.class);
		pseudoClasses.put(SQCssOddPseudoClass.PSEUDO, SQCssOddPseudoClass.class);
		pseudoClasses.put(SQCssRootPseudoClass.PSEUDO, SQCssRootPseudoClass.class);
		pseudoClasses.put(SQCssTargetPseudoClass.PSEUDO, SQCssTargetPseudoClass.class);

		// child filter
		pseudoClasses.put(SQCssFirstChildPseudoClass.PSEUDO, SQCssFirstChildPseudoClass.class);
		// :first-of-type
		// :last-child
		// :last-of-type
		//new NthChildPseudoClass()
		// :nth-last-child()
		// :nth-last-of-type()
		// :nth-of-type()
		//new OnlyChildPseudoClass(),
		//new OnlyOfTypePseudoClass()

		// content filter
		//new ContainsPseudoClass()
		//new EmptyPseudoClass()
		//new HasPseudoClass()
		//new ParentPseudoClass(),

		//new LangPseudoClass()

		// jquery-ui
		//new FocusablePseudoClass()
		//new TabbablePseudoClass(),

		// visibility
		//new VisiblePseudoClass(),
		//new HiddenPseudoClass()

		// seleniumQuery additions
		//new PresentPseudoClass(),
		// :blank
		// :filled
		// :unchecked
	}

	public SQCssCondition translate(SimpleSelector selectorUpToThisPoint, Map<String, String> stringMap, AttributeCondition attributeCondition) {
		String pseudoClassName = getPseudoClassName(attributeCondition);
		Class<? extends SQCssPseudoClassCondition> pseudoClass = pseudoClasses.get(pseudoClassName);
		if (pseudoClass != null) {
			return instantiate(pseudoClass, new PseudoClassSelector(stringMap, selectorUpToThisPoint, attributeCondition.getValue()));
		}
		throw new UnsupportedPseudoClassException(stringMap, selectorUpToThisPoint, pseudoClassName);
	}

	private String getPseudoClassName(AttributeCondition attributeCondition) {
		return attributeCondition.getValue().replaceAll("\\(.*", "");
	}

	@SuppressWarnings("unchecked")
	private <T extends SQCssPseudoClassCondition> T instantiate(Class<T> pseudoClass, PseudoClassSelector pseudoClassSelector) {
		try {
			return (T) pseudoClass.getConstructor(new Class[]{PseudoClassSelector.class}).newInstance(pseudoClassSelector);
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		}
	}

}
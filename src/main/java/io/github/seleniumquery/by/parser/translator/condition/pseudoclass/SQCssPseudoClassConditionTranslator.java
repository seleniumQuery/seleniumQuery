package io.github.seleniumquery.by.parser.translator.condition.pseudoclass;

import io.github.seleniumquery.by.css.pseudoclasses.PseudoClassSelector;
import io.github.seleniumquery.by.css.pseudoclasses.UnsupportedPseudoClassException;
import io.github.seleniumquery.by.parser.parsetree.condition.SQCssCondition;
import io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.SQCssPseudoClassCondition;
import io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.form.SQCssCheckedPseudoClass;
import io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.form.SQCssDisabledPseudoClass;
import io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.form.SQCssEnabledPseudoClass;
import io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.form.SQCssSelectedPseudoClass;
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
		//new ButtonPseudoClass()
		//new CheckboxPseudoClass()
		pseudoClasses.put(SQCssCheckedPseudoClass.PSEUDO, SQCssCheckedPseudoClass.class);
		pseudoClasses.put(SQCssEnabledPseudoClass.PSEUDO, SQCssEnabledPseudoClass.class);
		pseudoClasses.put(SQCssDisabledPseudoClass.PSEUDO, SQCssDisabledPseudoClass.class);
		//new FilePseudoClass()
		//new FocusPseudoClass()
		//new ImagePseudoClass(),
		//new InputPseudoClass(),
		//new PasswordPseudoClass()
		//new RadioPseudoClass()
		//new ResetPseudoClass()
		pseudoClasses.put(SQCssSelectedPseudoClass.PSEUDO, SQCssSelectedPseudoClass.class);
		//new SubmitPseudoClass(),
		//new TextPseudoClass()

		// basic filter
		// :animated
		//new EqPseudoClass()
		//new EvenPseudoClass(),
		//new FirstPseudoClass()
		//new GtPseudoClass(),
		//new HeaderPseudoClass()
		//new LastPseudoClass(),
		//new LtPseudoClass()
		//new NotPseudoClass(),
		//new OddPseudoClass()
		//new RootPseudoClass()
		// :target

		// child filter
		//new FirstChildPseudoClass()
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
	}

	public SQCssCondition translate(SimpleSelector selectorUpToThisPoint, Map<String, String> stringMap, AttributeCondition attributeCondition) {
		String pseudoClassName = getPseudoClassName(attributeCondition);
		Class<? extends SQCssPseudoClassCondition> pseudoClass = pseudoClasses.get(pseudoClassName);
		if (pseudoClass != null) {
			return instantiate(pseudoClass, new PseudoClassSelector(stringMap, selectorUpToThisPoint, pseudoClassName));
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
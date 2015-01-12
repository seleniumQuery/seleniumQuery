package io.github.seleniumquery.by.parser.translator.condition.pseudoclass;

import io.github.seleniumquery.by.css.pseudoclasses.PseudoClassSelector;
import io.github.seleniumquery.by.css.pseudoclasses.UnsupportedPseudoClassException;
import io.github.seleniumquery.by.parser.parsetree.condition.SQCssCondition;
import io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.SQCssPseudoClassCondition;
import io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.SQCssSelectedPseudoClass;
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
		//new CheckedPseudoClass(),
		pseudoClasses.put(SQCssSelectedPseudoClass.PSEUDO, SQCssSelectedPseudoClass.class);
		//new EqPseudoClass()
		//new OnlyChildPseudoClass(),
		//new ContainsPseudoClass()
		//new FirstChildPseudoClass()
		//new NotPseudoClass(),
		//new OnlyOfTypePseudoClass()
		//new RootPseudoClass()
		//new PresentPseudoClass(),
		//new EnabledPseudoClass()
		//new DisabledPseudoClass()
		//new VisiblePseudoClass(),
		//new HiddenPseudoClass()
		//new FirstPseudoClass()
		//new LastPseudoClass(),
		//new CheckboxPseudoClass()
		//new RadioPseudoClass()
		//new ImagePseudoClass(),
		//new PasswordPseudoClass()
		//new FilePseudoClass()
		//new SubmitPseudoClass(),
		//new ResetPseudoClass()
		//new ButtonPseudoClass()
		//new InputPseudoClass(),
		//new HeaderPseudoClass()
		//new LtPseudoClass()
		//new GtPseudoClass(),
		//new FocusPseudoClass()
		//new FocusablePseudoClass()
		//new TabbablePseudoClass(),
		//new HasPseudoClass()
		//new LangPseudoClass()
		//new ParentPseudoClass(),
		//new EmptyPseudoClass()
		//new TextPseudoClass()
		//new EvenPseudoClass(),
		//new OddPseudoClass()
		//new NthChildPseudoClass()
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
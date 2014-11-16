package io.github.seleniumquery.selectors.pseudoclasses;

import com.gargoylesoftware.htmlunit.html.DomAttr;
import com.gargoylesoftware.htmlunit.html.DomElement;
import io.github.seleniumquery.selector.xpath.XPathExpression;
import io.github.seleniumquery.selector.xpath.XPathExpressionFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitWebElement;

import java.lang.reflect.Method;

import static io.github.seleniumquery.selector.DriverVersionUtils.isHtmlUnitDriverEmulatingIEBelow11;

/**
 * <p>
 * http://api.jquery.com/submit-selector/
 * </p>
 * <b>
 *     Notice that <code>:submit</code> is not consistent accross browsers when the type attribute is not defined.
 *     IE, HtmlUnit acting as IE and even FirefoxDriver (not Firefox itself) "implicitly create" a type attribute
 *     when it doesn't exist! (The jQuery docs also talks about this issue!)
 * </b>
 * 
 * @author acdcjunior
 *
 * @since 1.0.0
 */
public class SubmitPseudoClass implements PseudoClass {

	private static final Log LOGGER = LogFactory.getLog(SubmitPseudoClass.class);
	
	private static final String SUBMIT = "submit";
	private static final String INPUT = "input";
	private static final String BUTTON = "button";
	
	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return SUBMIT.equals(pseudoClassValue);
	}
	
	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, PseudoClassSelector pseudoClassSelector) {
		return inputWithTypeSubmit(element) || buttonWithTypeSubmitOrWithoutType(driver, element);
	}

	private boolean inputWithTypeSubmit(WebElement element) {
		return INPUT.equals(element.getTagName()) && SUBMIT.equalsIgnoreCase(element.getAttribute("type"));
	}

	private boolean buttonWithTypeSubmitOrWithoutType(WebDriver driver, WebElement element) {
		boolean isButtonTag = BUTTON.equals(element.getTagName());
		if (!isButtonTag) {
			return false;
		}
		boolean isTypeSubmit = SUBMIT.equalsIgnoreCase(element.getAttribute("type"));
		if (isTypeSubmit) {
			return true;
		}
		if (isHtmlUnitDriverEmulatingIEBelow11(driver)) {
			return getDeclaredTypeAttributeFromHtmlUnitButton(element) == null;
		} else {
			return element.getAttribute("type") == null;
		}
	}

	/**
	 * Latest HtmlUnit returns @type=button when type is not set and browser is <=IE9. We don't want that,
	 * we want null if it is not set, so we can decide if it is submit or not. Because if it is null,
	 * then it is :submit. If type is "button", though, it is not.
	 *
	 * #Cross-Driver
	 * #HtmlUnit #reflection #hack
	 */
	private String getDeclaredTypeAttributeFromHtmlUnitButton(WebElement element) {
		try {
			if (element instanceof HtmlUnitWebElement) {
				Method getElementMethod = HtmlUnitWebElement.class.getDeclaredMethod("getElement");
				getElementMethod.setAccessible(true);
				Object htmlUnitElement = getElementMethod.invoke(element);
				if (htmlUnitElement instanceof DomElement) {
					DomAttr domAttr = ((DomElement) htmlUnitElement).getAttributesMap().get("type");
					return domAttr == null ? null : domAttr.getNodeValue();
				}
			}
		} catch (Exception e) {
			LOGGER.debug("Unable to retrieve declared \"type\" attribute from HtmlUnitWebElement "+element+".", e);
		}
		return element.getAttribute("type");
	}

	@Override
	public XPathExpression pseudoClassToXPath(PseudoClassSelector pseudoClassSelector) {
		return XPathExpressionFactory.createNoFilterSelector("[("
				+ "( local-name() = 'input' and @type = 'submit' ) or "
				+ "( local-name() = 'button' and (@type = 'submit' or not(@type)) )"
				+ ")]");
	}
	
}
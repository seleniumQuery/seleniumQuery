package infrastructure.junitrule;

import infrastructure.IntegrationTestUtils;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.CommandExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.lang.reflect.Field;
import java.util.Map;

import static io.github.seleniumquery.SeleniumQuery.$;

@SuppressWarnings("unused")
public class SetUpAndTearDownDriver implements TestRule {

	private static final DriverToRunTestsIn driverToRunTestsIn = DriverToRunTestsIn.HTMLUNIT_CHROME_JS_ON_ONLY;
	private static final String NOT_SPECIFIED = null;

	private final String testUrl;

	public SetUpAndTearDownDriver() {
		this.testUrl = NOT_SPECIFIED;
	}

	public SetUpAndTearDownDriver(Class<?> htmlTestUrlClass) {
		this.testUrl = IntegrationTestUtils.classNameToTestFileUrl(htmlTestUrlClass);
	}

	private String url(Description description) {
		//noinspection StringEquality
		if (this.testUrl == NOT_SPECIFIED) {
			return IntegrationTestUtils.classNameToTestFileUrl(description.getTestClass());
		}
		return this.testUrl;
	}

	private boolean browserWasStarted = false;

	@Override
	public Statement apply(final Statement base, final Description description) {
		if (description.isSuite()) {
			browserWasStarted = true;
			// TODO check if all methods of the class are annotated with JSOnly and skip driver creation when it wont have JS ON
			return new RunTestMethodsInChosenDrivers(driverToRunTestsIn, base, url(description));
			/*return new Statement() {
				@Override
				public void evaluate() throws Throwable {
					beforeClass();
					base.evaluate();
					afterClass();
				}
			};*/
		}
		Statement runTestMethodStatement = new Statement() {
			@Override
			public void evaluate() throws Throwable {
				boolean isJavaScriptOnlyTest = description.getAnnotation(JavaScriptOnly.class) != null;
				if (isJavaScriptOnlyTest && !isJavaScriptOn()) {
					System.out.println("\t\t-> Skipping JavaScript-only test: " + description);
					return;
				}
				beforeMethod(description);
				base.evaluate();
				afterMethod(description);
			}
		};
		if (!browserWasStarted) { // the test class has this as @Rule only and not as @ClassRule/@Rule, so we restart the browser every method
			return new RunTestMethodsInChosenDrivers(driverToRunTestsIn, runTestMethodStatement, url(description));
		}
		return runTestMethodStatement;
	}

	private void beforeClass() { }
	private void afterClass() { }
	private void beforeMethod(Description description) {
		$.url(url(description));
	}
	private void afterMethod(Description description) { }

	private boolean isJavaScriptOn() {
		WebDriver webDriver = $.driver().get();
		if (webDriver instanceof HtmlUnitDriver) {
			return ((HtmlUnitDriver) webDriver).isJavascriptEnabled();
		}
		if (webDriver instanceof FirefoxDriver) {
			try {
				CommandExecutor commandExecutor = ((RemoteWebDriver) webDriver).getCommandExecutor();
				Class<?> innerClass = FirefoxDriver.class.getDeclaredClasses()[0];
				Field profileField = innerClass.getDeclaredField("profile");
				profileField.setAccessible(true);
				FirefoxProfile firefoxProfile = (FirefoxProfile) profileField.get(commandExecutor);

				Field additionalPrefsField = FirefoxProfile.class.getDeclaredField("additionalPrefs");
				additionalPrefsField.setAccessible(true);
				Object additionalPrefs = additionalPrefsField.get(firefoxProfile);

				Field allPrefsField = additionalPrefs.getClass().getDeclaredField("allPrefs");
				allPrefsField.setAccessible(true);
				@SuppressWarnings("unchecked")
				Map<String, Object> allPrefs = (Map<String, Object>) allPrefsField.get(additionalPrefs);
				Object javaScriptEnabledPref = allPrefs.get("javascript.enabled");
				return javaScriptEnabledPref == null || !javaScriptEnabledPref.toString().equals("false");
			} catch (Exception e) {
				System.out.println("Unable to determine if JS is on or OFF in Firefox. Returning IS ON.");
				return true;
			}
		}
		return true;
	}

	private void dump(Description description) {
		final String methodName = description.getMethodName();
		System.out.println("***************************************************");
		System.out.println(" - isSuite: "+description.isSuite());
		System.out.println(" - isTest: "+description.isTest());
		System.out.println(" - annotations: "+description.getAnnotations());
		System.out.println(" - TestClass: "+description.getTestClass());
		System.out.println(" - class name: "+description.getClassName());
		System.out.println(" - method name: "+description.getMethodName());
		System.out.println("###################################################");
	}

}
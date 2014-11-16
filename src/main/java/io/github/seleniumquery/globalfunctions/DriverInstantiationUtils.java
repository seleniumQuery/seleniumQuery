package io.github.seleniumquery.globalfunctions;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.SessionNotFoundException;

import java.io.File;
import java.lang.reflect.Constructor;
import java.net.URL;

public class DriverInstantiationUtils {
	
	private static final String HTMLUNIT_DRIVER_CLASSNAME = "org.openqa.selenium.htmlunit.HtmlUnitDriver";

	private static final Log LOGGER = LogFactory.getLog(DriverInstantiationUtils.class);

	private static final String PHANTOM_JS_DRIVER_CLASS_NAME = "org.openqa.selenium.phantomjs.PhantomJSDriver";

	WebDriver instantiateChromeDriverWithPath(String pathToChromeDriverExe) {
		return instantiateDriverWithPath(pathToChromeDriverExe,
									"Chrome Driver Server",
										"http://chromedriver.storage.googleapis.com/index.html",
											"$.browser.setDefaultDriverAsChrome(\"other/path/to/chromedriver.exe\")",
												"webdriver.chrome.driver",
													ChromeDriver.class);
	}

	WebDriver instantiateChromeDriverWithoutPath() {
		return instantiateChromeDriverWithPath(getFullPathForFileInClassPath("chromedriver.exe"));
	}

	WebDriver instantiateIeDriverWithPath(String pathToIEDriverServerExe) {
		try {
			WebDriver iEWebDriver = instantiateDriverWithPath(pathToIEDriverServerExe,
                    "IE Driver Server",
                    "http://selenium-release.storage.googleapis.com/index.html",
                    "$.browser.setDefaultDriverAsIE(\"other/path/to/IEDriverServer.exe\")",
                    "webdriver.ie.driver",
                    InternetExplorerDriver.class);

			guaranteeActiveXIsNotBlocked(iEWebDriver);

			return iEWebDriver;
		} catch (SessionNotFoundException snfe) {
			String message = snfe.getLocalizedMessage();
			System.out.println("MSG: "+message);
			System.out.println("MSG: "+snfe.getMessage());
			if (message != null && message.contains("Protected Mode")) {
				throw new SeleniumQueryException("IE Driver requires Protected Mode settings to be the same for all zones.\n" +
						"Go to Tools -> Internet Options -> Security Tab, and set all zones to the same protected mode," +
						" be it enabled or disabled, does not matter.\n" +
						"If this does not solve the problem, or for more info, check the " +
						" https://github.com/seleniumQuery/seleniumQuery/wiki/seleniumQuery-and-IE-Driver wiki page.", snfe);
			}
			throw snfe;
		}
	}

	private void guaranteeActiveXIsNotBlocked(WebDriver iEWebDriver) {
		try {
			iEWebDriver.get(new File(getFullPathForFileInClassPath("ie.html")).toURI().toString());
			iEWebDriver.findElements(By.xpath("/nobody"));
		} catch (InvalidSelectorException ise) {
			LOGGER.debug("Failed while testing if ActiveX is enabled in IE Driver.", ise);
			try {
				System.out.println("Your IE Driver is probably blocking ActiveX. Enable it.");
				iEWebDriver.get(new File(getFullPathForFileInClassPath("ie-activex.html")).toURI().toString());
				for (int i = 0; i < 45; i++) {
					try {
						iEWebDriver.findElements(By.xpath("/nobody"));
						break;
					} catch (Exception e) {
						LOGGER.debug("Forcing ActiveX error again["+i+"].", e);
					}
					Thread.sleep(1000);
				}
			} catch (Exception e) {
				e.printStackTrace();
				LOGGER.debug("Failed after giving the user some time to enable ActiveX.", e);
			}
		}
	}

	WebDriver instantiateIeDriverWithoutPath() {
		return instantiateIeDriverWithPath(getFullPathForFileInClassPath("IEDriverServer.exe"));
	}
	
	WebDriver instantiatePhantomJsDriverWithPath(String pathToPhantomJs) {
		checkIfPhantomJsDriverIsInTheClassPath();
		Class<? extends WebDriver> phantomJsClass;
		try {
			phantomJsClass = getDriverClassByName(PHANTOM_JS_DRIVER_CLASS_NAME);
		} catch (Exception e) {
			// this should never happen, as the first line of this method checks if the PhantomJSDriver
			// is in the classpath
			throw new RuntimeException(e);
		}
		return instantiateDriverWithPath(pathToPhantomJs,
				"PhantomJS Executable",
					"http://phantomjs.org/download.html",
						"$.browser.setDefaultDriverAsPhantomJS(\"other/path/to/PhantomJS.exe\")",
							"phantomjs.binary.path",
								phantomJsClass);
	}
	
	WebDriver instantiatePhantomJsDriverWithoutPath() {
		checkIfPhantomJsDriverIsInTheClassPath();
		return instantiatePhantomJsDriverWithPath(getFullPathForFileInClassPath("phantomjs.exe"));
	}

	private void checkIfPhantomJsDriverIsInTheClassPath() {
		if (!checkIfClassIsInTheClassPath(PHANTOM_JS_DRIVER_CLASS_NAME)) {
			throw new RuntimeException("PhantomJSDriver class is NOT in the classpath! " +
					" Download its JAR from https://github.com/detro/ghostdriver and add it" +
					" to the classpath or, via maven, include in your pom:\n" +
					"\t<dependency>\n" +
					"\t    <groupId>com.github.detro.ghostdriver</groupId>\n" +
					"\t    <artifactId>phantomjsdriver</artifactId>\n" +
					"\t    <version>1.1.0</version> <!-- make sure you change 1.1.0 with the latest version -->\n" +
					"\t</dependency>");
		}
	}
	
	public WebDriver instantiateFirefoxDriverWithoutPath() {
		return new FirefoxDriver();
	}

	/**
	 * Attempts to initialize the HtmlUnitDriver, whatever
	 * version is available at the classpath.
	 */
	public WebDriver instantiateHtmlUnitDriverWithoutPath() {
		try {
			LOGGER.debug("Attempting to initialize HtmlUnitDriver.");
			WebDriver htmlUnitDriver = initializeHtmlUnitDriverByClassName(HTMLUNIT_DRIVER_CLASSNAME);
			LOGGER.debug("Initialized HtmlUnitDriver.");
			return htmlUnitDriver;
		} catch (Exception e) {
			if (!checkIfClassIsInTheClassPath(HTMLUNIT_DRIVER_CLASSNAME)) {
				LOGGER.error("HtmlUnitDriver is NOT present in the classpath.");	
			}
			throw new RuntimeException("SeleniumQuery was unable to initialize HtmlUnitDriver. Please set " +
					"the global default driver for seleniumQuery through " +
					"$.browser.setDefaultDriver(YourDriverClassInstance) before using it.", e);
		}
	}
	
	private WebDriver initializeHtmlUnitDriverByClassName(final String className) throws Exception {
		Class<?> clazz = Class.forName(className);
		Constructor<?> constructor = clazz.getConstructor(Boolean.TYPE);
		return (WebDriver) constructor.newInstance(true);
	}
	
	private boolean checkIfClassIsInTheClassPath(String fullClassName) {
		try {
			Class.forName(fullClassName, false, this.getClass().getClassLoader());
			return true;
		} catch (ClassNotFoundException e) {
			return false;
		}
	}

	private Class<? extends WebDriver> getDriverClassByName(final String driverClassName) throws Exception {
		@SuppressWarnings("unchecked")
		Class<? extends WebDriver> driverClass = (Class<? extends WebDriver>) Class.forName(driverClassName);
		return driverClass;
	}
	
	private WebDriver instantiateDriverWithPath(String pathToDriverServerExecutable,
			String driverServerFileDescription, String driverServerDownloadPage,
				String alternativeMethod, String driverServerSystemPropertyPath,
					Class<? extends WebDriver> driverClass) {
		try {
			File driverServerExecutableFile = new File(pathToDriverServerExecutable);
			String driverSErverExecutableFilePath = driverServerExecutableFile.getCanonicalPath();
			if (!driverServerExecutableFile.exists() || driverServerExecutableFile.isDirectory()) {
				throw new RuntimeException("No " + driverServerFileDescription + " file was found at '" +
						driverSErverExecutableFilePath + "'. Download the latest release at " + driverServerDownloadPage
						+ " and place it there or specify a different path using " + alternativeMethod + ".");
			}
			System.setProperty(driverServerSystemPropertyPath, driverSErverExecutableFilePath);
			return driverClass.newInstance();
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private String getFullPathForFileInClassPath(String executableFileName) {
		String slashExecutableFileName = "/" + executableFileName;
		URL executableFileInTheClassPathUrl = getClass().getResource(slashExecutableFileName);
		if (executableFileInTheClassPathUrl == null) {
			return getClass().getResource("/").getPath() + slashExecutableFileName;
		}
		return executableFileInTheClassPathUrl.getPath();
	}

}
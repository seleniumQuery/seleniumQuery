package io.github.seleniumquery;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SeleniumQueryConfig {
	
	private static final Log LOGGER = LogFactory.getLog(SeleniumQueryConfig.class);
	
	static {
		loadProperties();
	}
	
	private static void loadProperties() {
		try {
			loadPropertiesFileFromClasspath();

			GLOBAL_TIMEOUT_IN_MILLISSECONDS = getLongProperty("GLOBAL_TIMEOUT_IN_MILLISSECONDS", GLOBAL_TIMEOUT_IN_MILLISSECONDS);
			TIMEOUT_IN_SECONDS = getLongProperty("TIMEOUT_IN_SECONDS", TIMEOUT_IN_SECONDS);
			POLLING_IN_MILLISSECONDS = getLongProperty("POLLING_IN_MILLISSECONDS", POLLING_IN_MILLISSECONDS);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void loadPropertiesFileFromClasspath() throws IOException {
		properties = new Properties();
		LOGGER.debug("Attempting to load properties from seleniumQuery.properties in classpath.");
		InputStream in = SeleniumQueryConfig.class.getClassLoader().getResourceAsStream("seleniumQuery.properties");
		if (in == null) {
			LOGGER.info("No seleniumQuery.properties found in classpath, falling back to defaults.");
		}
		properties.load(in);
		in.close();
	}
	
	private static long getLongProperty(String propertyName, long defaultValue) {
		String propertyAsString = SeleniumQueryConfig.properties.getProperty(propertyName);
		if (propertyAsString == null || propertyAsString.isEmpty()) {
			return defaultValue;
		}
		return Long.valueOf(propertyAsString);
	}
	
	private static Properties properties;
	
	public static String get(String property) {
		return properties.getProperty(property);
	}	
	
	private static long GLOBAL_TIMEOUT_IN_MILLISSECONDS = 1500;
	private static long TIMEOUT_IN_SECONDS = 10;
	private static long POLLING_IN_MILLISSECONDS = 900;
	
	public static long getGlobalTimeoutInMillisseconds() {
		return GLOBAL_TIMEOUT_IN_MILLISSECONDS;
	}
	public static long getWaitTimeoutInSeconds() {
		return TIMEOUT_IN_SECONDS;
	}
	public static long getWaitPollingInMillisseconds() {
		return POLLING_IN_MILLISSECONDS;
	}

}
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

			GLOBAL_TIMEOUT = getLongProperty(PROP_GLOBAL_TIMEOUT, GLOBAL_TIMEOUT);
			WAITUNTIL_TIMEOUT = getLongProperty(PROP_WAITUNTIL_TIMEOUT, WAITUNTIL_TIMEOUT);
			WAITUNTIL_POLLING_INTERVAL = getLongProperty(PROP_WAITUNTIL_POLLING_INTERVAL, WAITUNTIL_POLLING_INTERVAL);

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
		if (propertyAsString == null || propertyAsString.trim().isEmpty()) {
			return defaultValue;
		}
		return Long.valueOf(propertyAsString);
	}
	
	private static Properties properties;
	
	public static String get(String property) {
		return properties.getProperty(property);
	}	
	
	/**
	 * All times are in milliseconds.
	 */
	private static long GLOBAL_TIMEOUT = 1500;
	private static long WAITUNTIL_TIMEOUT = 10000;
	private static long WAITUNTIL_POLLING_INTERVAL = 900;
	
	private static final String PROP_GLOBAL_TIMEOUT = "GLOBAL_TIMEOUT";
	private static final String PROP_WAITUNTIL_TIMEOUT = "WAITUNTIL_TIMEOUT";
	private static final String PROP_WAITUNTIL_POLLING_INTERVAL = "WAITUNTIL_POLLING_INTERVAL";

	public static long getGlobalTimeout() {
		return GLOBAL_TIMEOUT;
	}
	public static long getWaitUntilTimeout() {
		return WAITUNTIL_TIMEOUT;
	}
	public static long getWaitUntilPollingInterval() {
		return WAITUNTIL_POLLING_INTERVAL;
	}

}
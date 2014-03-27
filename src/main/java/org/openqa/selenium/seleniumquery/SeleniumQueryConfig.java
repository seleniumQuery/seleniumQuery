package org.openqa.selenium.seleniumquery;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SeleniumQueryConfig {
	
	static {
		loadProperties();
	}
	
	private static void loadProperties() {
		try {
			SeleniumQueryConfig.properties = new Properties();
			InputStream in = SeleniumQueryConfig.class.getClassLoader().getResourceAsStream("seleniumquery.properties");
			SeleniumQueryConfig.properties.load(in);
			in.close();
			{
				String gtim = SeleniumQueryConfig.properties.getProperty("GLOBAL_TIMEOUT_IN_MILLISSECONDS");
				if (gtim != null && !gtim.isEmpty()) {
					GLOBAL_TIMEOUT_IN_MILLISSECONDS = Long.valueOf(gtim);
				}
			}
			{
				String tis = SeleniumQueryConfig.properties.getProperty("TIMEOUT_IN_SECONDS");
				if (tis != null && !tis.isEmpty()) {
					TIMEOUT_IN_SECONDS = Long.valueOf(tis);
				}
			}
			{
				String tim = SeleniumQueryConfig.properties.getProperty("POLLING_IN_MILLISSECONDS");
				if (tim != null && !tim.isEmpty()) {
					POLLING_IN_MILLISSECONDS = Long.valueOf(tim);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
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

package io.github.seleniumquery.browser.driver.builders;

import io.github.seleniumquery.SeleniumQueryException;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Utilities for instantiating drivers.
 *
 * @author acdcjunior
 *
 * @since 0.9.0
 */
class DriverInstantiationUtils {

	static WebDriver instantiateDriverWithPath(String pathToDriverServerExecutable,
			String driverServerFileDescription, String driverServerDownloadPage,
				String alternativeMethod, String driverServerSystemPropertyPath,
					Class<? extends WebDriver> driverClass) {
		try {
			File driverServerExecutableFile = new File(pathToDriverServerExecutable);
			String driverServerExecutableFilePath = driverServerExecutableFile.getCanonicalPath();

			if (!driverServerExecutableFile.exists() || driverServerExecutableFile.isDirectory()) {
				throw new RuntimeException("No " + driverServerFileDescription + " file was found at '" +
						driverServerExecutableFilePath + "'. Download the latest release at " + driverServerDownloadPage
						+ " and place it there or specify a different path using " + alternativeMethod + ".");
			}
			System.setProperty(driverServerSystemPropertyPath, driverServerExecutableFilePath);
			return driverClass.newInstance();
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	static String getFullPathForFileInClasspath(String executableFileName) {
		String slashExecutableFileName = "/" + executableFileName;
		URL executableFileInTheClassPathUrl = DriverInstantiationUtils.class.getResource(slashExecutableFileName);
		if (executableFileInTheClassPathUrl == null) {
			return DriverInstantiationUtils.class.getResource("/").getPath() + slashExecutableFileName;
		}
		return executableFileInTheClassPathUrl.getPath();
	}

	static String getFullPath(String file) {
		try {
			File driverServerExecutableFile = new File(file);
			return driverServerExecutableFile.getCanonicalPath();
		} catch (IOException e) {
			throw new SeleniumQueryException("Unable to get canonical path for "+file, e);
		}
	}

}
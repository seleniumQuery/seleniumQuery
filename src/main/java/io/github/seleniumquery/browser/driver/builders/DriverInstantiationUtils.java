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

	public static boolean isValidFile(File driverServerExecutableFile) {
        return driverServerExecutableFile.exists() && !driverServerExecutableFile.isDirectory();
    }

	public static boolean executableExistsInClasspath(String file) {
        String strPath = getFullPathForFileInClasspath(file);
        File driverServerExecutableFile = new File(strPath);
        return isValidFile(driverServerExecutableFile);
    }
}
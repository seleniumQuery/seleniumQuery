/*
 * Copyright (c) 2015 seleniumQuery authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.seleniumquery.browser.driver.builders;

import io.github.seleniumquery.SeleniumQueryException;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import static java.lang.String.format;

/**
 * Utilities for instantiating drivers.
 *
 * @author acdcjunior
 *
 * @since 0.9.0
 */
public class DriverInstantiationUtils {

	public static String getFullPathForFileInClasspath(String executableFileName) {
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

	static boolean isValidFile(File driverServerExecutableFile) {
        return driverServerExecutableFile.exists() && !driverServerExecutableFile.isDirectory();
    }

	static boolean executableExistsInClasspath(String file) {
        String strPath = getFullPathForFileInClasspath(file);
        File driverServerExecutableFile = new File(strPath);
        return isValidFile(driverServerExecutableFile);
    }

    static boolean customPathWasProvidedAndExecutableExistsThere(String pathToExecutable, String exceptionMessage) {
        boolean customPathWasProvided = pathToExecutable != null;
        if (!customPathWasProvided) {
            return false;
        }
        File driverServerExecutableFile = new File(pathToExecutable);
        if (!isValidFile(driverServerExecutableFile)) {
            throw new SeleniumQueryException(format(exceptionMessage, getFullPath(pathToExecutable)));
        }
        return true;
    }

}
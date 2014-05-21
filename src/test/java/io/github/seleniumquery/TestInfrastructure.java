package io.github.seleniumquery;

import java.io.File;

public class TestInfrastructure {
	
	private static final String TEST_SRC_FOLDER = "src/test/java/";

	public static String getHtmlTestFileUrl(Class<?> clazz) {
		String classFullName = clazz.getName();
		String classPath = classFullName.replace('.', '/');
		String htmlPath = TEST_SRC_FOLDER + classPath + ".html"; 
		return new File(htmlPath).toURI().toString();
	}
	
}
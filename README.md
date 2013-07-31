SeleniumQuery
======

Allows the use of a jQuery-like interface for Selenium WebDriver, built using Selenium's native capabilities **only** (no `jQuery.js` is embedded at the page).

`````java
import static org.openqa.selenium.seleniumquery.SQuery.sQ; // this will allow the short syntax

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.seleniumquery.SQuery;

public abstract class SeleniumQueryExample {
  
	public static void main(String[] args) {
		SQuery.setDefaultDriver(new FirefoxDriver()); // the browser/driver used by sQ() calls
		sQ().openUrl("http://www.google.com");
		sQ("input[name='q']").val("selenium");
		sQ("input[name='btnK']").click();
		// optional (useful for Ajax clicks): sQ("input[name='q']").waitUntil().isNotPresent();
		System.out.println(sQ("#resultStats").text());
	}
  
}
`````

To use it, add this to your `pom.xml`:

`````xml
	<dependencies>
        <dependency>
			<groupId>com.github.acdcjunior.seleniumquery</groupId>
			<artifactId>SeleniumQuery</artifactId>
			<version>0.0.1</version>
		</dependency>
	</dependencies>

	<repositories>
		<repository>
			<id>Repo for SeleniumQuery</id>
			<url>https://raw.github.com/acdcjunior/mvn-repo/master</url>
		</repository>
	</repositories>
`````

As testing usually involves interactions from the end-user point of view, the development currently focuses on
interface operation commands (such as `.val()`, `.text()` and `.click()`) and waiting commands (such as *wait until element #x is not visible*: `sQ("#x").waitUntil().isNotVisible()`) rather than DOM manipulation (such as `.append()`) - for these, the usual `WebElement` functions are still available and easily accessible at any moment.

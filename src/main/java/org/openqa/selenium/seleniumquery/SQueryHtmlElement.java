package org.openqa.selenium.seleniumquery;

import java.io.File;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;


public class SQueryHtmlElement {
	
	private By by;
	private WebDriver driver;
	private WebElement element;
	private String selector;
	
	public SQueryHtmlElement(WebDriver driver, String selector) {
		this.driver = driver;
		
		if (selector.matches("^\\(*/.*")) { /* Example: "//..." ou "(//..." ou "(((((//...." */
			// Assuming the selector given is a XPath expression
			this.selector = selector;
			this.by = By.xpath(selector);
		} else {
			this.selector = selector;
			this.by = By.cssSelector(selector);
		}
		
		this.element = this.fluentWait(new Function<By, WebElement>() {
			@Override
			public WebElement apply(By selector) {
				if (selector.toString().contains("labDescricaoAnexo")) {
					System.out.println("CLLAED: "+by);
				}
				return ExpectedConditions.presenceOfElementLocated(by).apply(SQueryHtmlElement.this.driver);
			}
		});
	}
	
	public SQueryHtmlElement val(String value) {
		if ("select".equals(element.getTagName())) {
			new Select(element).selectByVisibleText(value);
		} else if ("input".equals(element.getTagName()) && "file".equals(element.getAttribute("type"))) {
			element.sendKeys(value);
		} else {
			element.clear();
			element.sendKeys(value);
		}
		return this;
	}
	
	public SQueryHtmlElement val(Number valor) {
		return this.val(valor.toString());
	}
	
	public String val() {
		if ("input".equals(element.getTagName())) {
			return element.getAttribute("value");
		} else if ("select".equals(element.getTagName())) {
			return new Select(element).getFirstSelectedOption().getText();
		} else {
			return element.getText();
		}
	}
	
	public SQueryHtmlElement click() {
		this.element = fluentWait(new Function<By, WebElement>() {
			@Override
			public WebElement apply(By selector) {
				WebElement element = ExpectedConditions.visibilityOfElementLocated(by).apply(driver);
				if (element != null && element.isEnabled()) {
					return element;
				}
				return null;
			}
		});
		this.element.click();
		return this;
	}

	private WebElement fluentWait(Function<By, WebElement> function) {
		try {
			return new FluentWait<By>(by)
					.withTimeout(SQueryProperties.getTimeoutInSeconds(), TimeUnit.SECONDS)
					.pollingEvery(SQueryProperties.getPollingInMillisseconds(), TimeUnit.MILLISECONDS)
					.ignoring(org.openqa.selenium.StaleElementReferenceException.class)
					.ignoring(org.openqa.selenium.NoSuchElementException.class)
					.until(function);
		} catch (TimeoutException te) {
			try {
				PrintWriter out = new PrintWriter(SQueryProperties.get("ERROR_PAGE_HTML_LOCATION"));
				out.println(driver.getPageSource());
				out.close();
				if (this.driver instanceof TakesScreenshot) {
					File srcFile = ((TakesScreenshot) this.driver).getScreenshotAs(OutputType.FILE);
					FileUtils.copyFile(srcFile, new File(SQueryProperties.get("ERROR_PAGE_SCREENSHOT_LOCATION")));
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			throw new RuntimeException("TimeoutException while waiting for selector: "+selector, te);
		}
	}
	
	public SQueryHtmlElement waitForSeconds(final int timeToWait) {
		final Long finalTime = System.currentTimeMillis() + timeToWait * 1000;
		new FluentWait<By>(by).withTimeout(timeToWait+2, TimeUnit.SECONDS)
							  .pollingEvery(timeToWait*200, TimeUnit.MILLISECONDS)
							  .until(new Function<By, Boolean>() {
			@Override public Boolean apply(By selector) { return System.currentTimeMillis() > finalTime; }
		});
		return this;
	}
	
	public SQueryHtmlElement waitUntilVisible() {
		this.element = fluentWait(new Function<By, WebElement>() {
			@Override
			public WebElement apply(By selector) {
				return ExpectedConditions.visibilityOfElementLocated(by).apply(driver); // can be null (will wait again), or the element
			}
		});
		return this;
	}
	
	public SQueryHtmlElement waitUntilNotVisible() {
		new WebDriverWait(driver, SQueryProperties.getTimeoutInSeconds()).until(ExpectedConditions.invisibilityOfElementLocated(by));
		return this;
	}
	
	public SQueryHtmlElement waitUntilContainsText(final String text) {
		this.element = this.fluentWait(new Function<By, WebElement>() {
			@Override
			public WebElement apply(By selector) {
				WebElement element = ExpectedConditions.presenceOfElementLocated(by).apply(driver);
				if (element != null && element.getText().contains(text)) {
					return element;
				}
				return null;
			}
		});
		return this;
	}
	
	public SQueryHtmlElement waitUntilValueIsNot(final String value) {
		return waitUntilValueIsOrIsNot(value, false);
	}
	
	public SQueryHtmlElement waitUntilValueIs(final String value) {
		return waitUntilValueIsOrIsNot(value, true);
	}

	private SQueryHtmlElement waitUntilValueIsOrIsNot(final String value, final boolean trueSeIgualFalseSeDiferente) {
		this.element = this.fluentWait(new Function<By, WebElement>() {
			@Override
			public WebElement apply(By selector) {
				SQueryHtmlElement.this.element = ExpectedConditions.presenceOfElementLocated(by).apply(driver);
				if (SQueryHtmlElement.this.element != null) {
					boolean textoEhIgual = SQueryHtmlElement.this.val().equals(value);
					if ((trueSeIgualFalseSeDiferente && textoEhIgual) || (!trueSeIgualFalseSeDiferente && !textoEhIgual)) {
						return element;
					}
				}
				return null;
			}
		});
		return this;
	}
	
	public String prop(String attributeName) {
		return element.getAttribute(attributeName);
	}
	
	public SQueryHtmlElement trigger(String event) {
		if (by instanceof ByXPath) {
			String id = this.prop("id");
			if (id == null) {
				throw new UnsupportedOperationException("Unable to determine current element's ID!");
			}
			((JavascriptExecutor) driver).executeScript("document.getElementById('" + id + "')." + event + "()");
		} else {
			// CSS selector used
			((JavascriptExecutor) driver).executeScript("document.querySelector('"+selector.replace("\\", "\\\\")+"')."+event+"()");
		}
		return this;
	}
	
	public SQueryHtmlElement focus() {
		return this.trigger("focus");
	}
	
	public String html() {
		return this.element.getAttribute("innerHTML");
	}
	
	public String text() {
		return this.element.getText();
	}
	
}

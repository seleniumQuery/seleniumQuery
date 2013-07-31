package org.openqa.selenium.seleniumquery;

import java.io.File;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

public class SQueryWait {
	
	private SQueryHtmlElement htmlElement;
	
	public SQueryWait(SQueryHtmlElement htmlElement) {
		this.htmlElement = htmlElement;
	}

	public <T> T fluentWait(Function<By, T> function) {
		try {
			return new FluentWait<By>(this.htmlElement.getBy())
					.withTimeout(SQueryProperties.getTimeoutInSeconds(), TimeUnit.SECONDS)
					.pollingEvery(SQueryProperties.getPollingInMillisseconds(), TimeUnit.MILLISECONDS)
					.ignoring(StaleElementReferenceException.class)
					.ignoring(NoSuchElementException.class)
					.until(function);
		} catch (TimeoutException te) {
			try {
				PrintWriter out = new PrintWriter(SQueryProperties.get("ERROR_PAGE_HTML_LOCATION"));
				out.println(this.htmlElement.getDriver().getPageSource());
				out.close();
				if (this.htmlElement.getDriver() instanceof TakesScreenshot) {
					File srcFile = ((TakesScreenshot) this.htmlElement.getDriver()).getScreenshotAs(OutputType.FILE);
					FileUtils.copyFile(srcFile, new File(SQueryProperties.get("ERROR_PAGE_SCREENSHOT_LOCATION")));
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			throw new RuntimeException("TimeoutException while waiting for selector: "+this.htmlElement.getSelector(), te);
		}
	}
	
	public SQueryHtmlElement isPresent() {
		WebElement element = this.fluentWait(new Function<By, WebElement>() {
			@Override
			public WebElement apply(By selector) {
				return ExpectedConditions.presenceOfElementLocated(htmlElement.getBy()).apply(htmlElement.getDriver());
			}
		});
		htmlElement.setElement(element);
		return htmlElement;
	}
	
	public SQueryHtmlElement isNotPresent() {
		this.fluentWait(new Function<By, Boolean>() {
			@Override
			public Boolean apply(By selector) {
				return ExpectedConditions.not(ExpectedConditions.presenceOfElementLocated(htmlElement.getBy())).apply(htmlElement.getDriver());
			}
		});
		return htmlElement;
	}
	
	public SQueryHtmlElement isVisibleAndEnabled() {
		WebElement element = this.fluentWait(new Function<By, WebElement>() {
			@Override
			public WebElement apply(By selector) {
				WebElement element = ExpectedConditions.visibilityOfElementLocated(htmlElement.getBy()).apply(htmlElement.getDriver());
				if (element != null && element.isEnabled()) {
					return element;
				}
				return null;
			}
		});
		htmlElement.setElement(element);
		return htmlElement;
	}
	
	public SQueryHtmlElement isVisible() {
		WebElement element = this.fluentWait(new Function<By, WebElement>() {
			@Override
			public WebElement apply(By selector) {
				return ExpectedConditions.visibilityOfElementLocated(htmlElement.getBy()).apply(htmlElement.getDriver()); // can be null (will wait again), or the element
			}
		});
		htmlElement.setElement(element);
		return htmlElement;
	}
	
	public SQueryHtmlElement isNotVisible() {
		new WebDriverWait(htmlElement.getDriver(), SQueryProperties.getTimeoutInSeconds()).until(ExpectedConditions.invisibilityOfElementLocated(htmlElement.getBy()));
		return htmlElement;
	}
	
	public SQueryHtmlElement containsText(final String text) {
		WebElement element = this.fluentWait(new Function<By, WebElement>() {
			@Override
			public WebElement apply(By selector) {
				WebElement element = ExpectedConditions.presenceOfElementLocated(htmlElement.getBy()).apply(htmlElement.getDriver());
				if (element != null && element.getText().contains(text)) {
					return element;
				}
				return null;
			}
		});
		htmlElement.setElement(element);
		return htmlElement;
	}
	
	public SQueryHtmlElement seconds(final int timeToWait) {
		final Long finalTime = System.currentTimeMillis() + timeToWait * 1000;
		new FluentWait<By>(htmlElement.getBy()).withTimeout(timeToWait+2, TimeUnit.SECONDS)
							  .pollingEvery(timeToWait*200, TimeUnit.MILLISECONDS)
							  .until(new Function<By, Boolean>() {
			@Override public Boolean apply(By selector) { return System.currentTimeMillis() > finalTime; }
		});
		return htmlElement;
	}
	
	public SQueryHtmlElement valueIs(final String value) {
		return this.valueIsOrIsNot(value, true);
	}
	
	public SQueryHtmlElement valueIsNot(final String value) {
		return this.valueIsOrIsNot(value, false);
	}
	
	private SQueryHtmlElement valueIsOrIsNot(final String value, final boolean trueSeIgualFalseSeDiferente) {
		this.fluentWait(new Function<By, WebElement>() {
			@Override
			public WebElement apply(By selector) {
				WebElement e = ExpectedConditions.presenceOfElementLocated(htmlElement.getBy()).apply(htmlElement.getDriver());
				if (e != null) {
					htmlElement.setElement(e); // notice the element is set here, thus not necessary at the end of fluentWait
					boolean textoEhIgual = htmlElement.val().equals(value);
					if ((trueSeIgualFalseSeDiferente && textoEhIgual) || (!trueSeIgualFalseSeDiferente && !textoEhIgual)) {
						return e;
					}
				}
				return null;
			}
		});
		return htmlElement;
	}

}

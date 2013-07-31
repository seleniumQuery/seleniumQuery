package org.openqa.selenium.seleniumquery;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.seleniumquery.exception.WaitUntilException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

public class SQueryWait {
	
	private SQueryHtmlElement htmlElement;
	
	public SQueryWait(SQueryHtmlElement htmlElement) {
		this.htmlElement = htmlElement;
	}

	private <T> T fluentWait(Function<By, T> function, String reason) {
		try {
			return new FluentWait<By>(this.htmlElement.getBy())
					.withTimeout(SQueryProperties.getTimeoutInSeconds(), TimeUnit.SECONDS)
					.pollingEvery(SQueryProperties.getPollingInMillisseconds(), TimeUnit.MILLISECONDS)
					.ignoring(StaleElementReferenceException.class)
					.ignoring(NoSuchElementException.class)
					.until(function);
		} catch (TimeoutException sourceException) {
			throw new WaitUntilException(sourceException, this.htmlElement, reason);
		}
	}
	
	public SQueryHtmlElement isPresent() {
		WebElement element = this.fluentWait(new Function<By, WebElement>() {
			@Override
			public WebElement apply(By selector) {
				return ExpectedConditions.presenceOfElementLocated(htmlElement.getBy()).apply(htmlElement.getDriver());
			}
		}, "to be present.");
		htmlElement.setElement(element);
		return htmlElement;
	}
	
	public SQueryHtmlElement isNotPresent() {
		this.fluentWait(new Function<By, Boolean>() {
			@Override
			public Boolean apply(By selector) {
				return htmlElement.getDriver().findElements(htmlElement.getBy()).size() == 0;
			}
		}, "to be not present.");
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
		}, "to be visible and enabled.");
		htmlElement.setElement(element);
		return htmlElement;
	}
	
	public SQueryHtmlElement isVisible() {
		WebElement element = this.fluentWait(new Function<By, WebElement>() {
			@Override
			public WebElement apply(By selector) {
				return ExpectedConditions.visibilityOfElementLocated(htmlElement.getBy()).apply(htmlElement.getDriver()); // can be null (will wait again), or the element
			}
		}, "to be visible.");
		htmlElement.setElement(element);
		return htmlElement;
	}
	
	@Deprecated
	public SQueryHtmlElement isInvisible() {
		new WebDriverWait(htmlElement.getDriver(), SQueryProperties.getTimeoutInSeconds()).until(ExpectedConditions.invisibilityOfElementLocated(htmlElement.getBy()));
		return htmlElement;
	}
	
	public SQueryHtmlElement isNotVisible() {
		this.fluentWait(new Function<By, Boolean>() {
			@Override
			public Boolean apply(By selector) {
				return ExpectedConditions.invisibilityOfElementLocated(htmlElement.getBy()).apply(htmlElement.getDriver());
			}
		}, "to be not visible.");
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
		}, "to contain text \""+text+"\".");
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
	
	private SQueryHtmlElement valueIsOrIsNot(final String value, final boolean shouldValueBeEqual) {
		this.fluentWait(new Function<By, WebElement>() {
			@Override
			public WebElement apply(By selector) {
				WebElement e = ExpectedConditions.presenceOfElementLocated(htmlElement.getBy()).apply(htmlElement.getDriver());
				if (e != null) {
					htmlElement.setElement(e); // notice the element is set here, thus not necessary at the end of fluentWait
					boolean valueEquals = htmlElement.val().equals(value);
					if ((shouldValueBeEqual && valueEquals) || (!shouldValueBeEqual && !valueEquals)) {
						return e;
					}
				}
				return null;
			}
		}, "to"+ (shouldValueBeEqual ? "" : " not") + " have value \""+value+"\".");
		return htmlElement;
	}

}

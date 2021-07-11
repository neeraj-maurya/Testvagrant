package com.test.weather;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseTestPage {

	WebDriver driver;

	public void waitForPageLoad() {
		new WebDriverWait(driver, 10)
				.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState"))
				.equals("complete");
	}
}

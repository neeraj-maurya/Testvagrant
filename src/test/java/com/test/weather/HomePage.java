package com.test.weather;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BaseTestPage {

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "input.search-input")
	private WebElement textBoxSearchLocation;

	public void searchCity(String cityName) {
		textBoxSearchLocation.sendKeys(cityName + Keys.ENTER);
		waitForPageLoad();
	}
}

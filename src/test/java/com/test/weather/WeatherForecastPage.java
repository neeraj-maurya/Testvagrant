package com.test.weather;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WeatherForecastPage extends BaseTestPage {

	public WeatherForecastPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[contains(@class,'weather-card')]//*[normalize-space(text())='More Details']")
	private WebElement linkMoreDetailsCurrentWeather;

	@FindBy(css = "div.display-temp")
	private WebElement labelTemperature;

	@FindBy(xpath = "//div[normalize-space(text())='Humidity']/following-sibling::div")
	private WebElement labelHumidity;

	public void expandWeatherCard() {
		linkMoreDetailsCurrentWeather.click();
		waitForPageLoad();
	}

	public double getTemperature() {

		String tempValue = labelTemperature.getText().trim();
		return Double.valueOf(tempValue.substring(0, tempValue.indexOf('°')));
	}

	public int getHumidity() {
		String humidityValue = labelHumidity.getText().trim();
		return Integer.valueOf(humidityValue.substring(0, humidityValue.indexOf('%')));
	}

}

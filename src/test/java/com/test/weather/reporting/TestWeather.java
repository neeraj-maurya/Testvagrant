package com.test.weather.reporting;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.test.weather.HomePage;
import com.test.weather.WeatherForecastPage;
import com.test.weather.utils.WeatherUtils;

import io.restassured.RestAssured;
import io.restassured.config.JsonConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.path.json.config.JsonPathConfig.NumberReturnType;

public class TestWeather extends BaseTestCase {

	@Test
	public void verifyTemperatureAndHumidity() {
		//Configurable variance example 
		double tempVariance = 2;
		int humidityVariance =10;
		
		//Naviagte to homepage
		driver.get("https://www.accuweather.com/");
		HomePage homePage = new HomePage(driver);
		homePage.waitForPageLoad();
		
		//search for bangalore city
		homePage.searchCity("Bangalore");

		WeatherForecastPage weatherForecastPage = new WeatherForecastPage(driver);
		weatherForecastPage.expandWeatherCard();
		weatherForecastPage.waitForPageLoad();
		
		//Get weather values from UI
		double temperature = weatherForecastPage.getTemperature();
		int humidity = weatherForecastPage.getHumidity();
		
		//Configure and connect to Web service
		RestAssuredConfig config = RestAssured.config().jsonConfig(new JsonConfig(NumberReturnType.DOUBLE));
		Map<String, String> queryParams = new HashMap<>();
		queryParams.put("q", "bangalore");
		queryParams.put("appid", "7fe67bf08c80ded756e598d6f8fedaea");
		String endpoint = "https://api.openweathermap.org/data/2.5/weather";
		
		given().
			config(config).
			queryParams(queryParams).
		when().
			get(endpoint).
		then().
			assertThat().
				statusCode(200).
				//validate temperature converted to kelvin with variance applied
				body("main.temp", closeTo(WeatherUtils.degreeCelciusToKelvin(temperature), tempVariance)).
			assertThat().
			//validate humidity with variance applied
				body("main.humidity", both(is(greaterThanOrEqualTo(humidity - humidityVariance)))
						.and(is(lessThanOrEqualTo(humidity + humidityVariance))));
	}

}

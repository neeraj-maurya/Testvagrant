package com.test.weather.utils;

public class WeatherUtils {

	public WeatherUtils() {
		throw new IllegalStateException("Utility Class");
	}

	public static double kelvinToDegreeCelcius(double temperatureInKelvin) {
		return temperatureInKelvin - 273.15;
	}
	
	public static double degreeCelciusToKelvin(double temperatureInDegreeCelcius) {
		return temperatureInDegreeCelcius + 273.15;
	}
}

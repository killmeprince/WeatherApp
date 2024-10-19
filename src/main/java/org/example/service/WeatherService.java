package org.example.service;

import org.example.model.WeatherData;

public interface WeatherService {

    WeatherData getWeatherData(double lat, double lon);

    double calculateAverageTemperature(double lat, double lon, int limit);
}

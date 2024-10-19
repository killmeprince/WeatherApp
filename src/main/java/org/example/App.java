package org.example;

import org.example.api.ApiClient;
import org.example.api.ApiClientYandex;
import org.example.service.YandexWeatherService;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        //60.03927 с.ш. 30.3982 в.д
        ApiClient clientYandex = new ApiClientYandex();
        YandexWeatherService yandexWeatherService = new YandexWeatherService(clientYandex);


        System.out.println(yandexWeatherService.getWeatherData(60.03927,30.3982));
    }
}

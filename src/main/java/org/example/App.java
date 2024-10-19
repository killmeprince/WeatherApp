package org.example;

import org.example.api.ApiClient;
import org.example.api.ApiClientYandex;
import org.example.service.YandexWeatherService;

import java.util.Scanner;


public class App {
    public static void main(String[] args) {
        //60.03927 с.ш. 30.3982 в.д
        ApiClient clientYandex = new ApiClientYandex();
        YandexWeatherService yandexWeatherService = new YandexWeatherService(clientYandex);
        System.out.println("If u want to see an average temperature in that area type the limit value | or '-' to see the data by coordinates ");
        System.out.println("Enter len ");
        Scanner sc = new Scanner(System.in);

        double x = sc.nextDouble();
        System.out.println("Enter lon ");
        double y = sc.nextDouble();
        System.out.println("limit or '-' ");
        var limit = sc.next();
        if (limit.equals("-")) {
            try {
                System.out.println(yandexWeatherService.getWeatherData(x, y));
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(" getWeatherData not working");
            }
        } else {
            try {
                System.out.println(yandexWeatherService.calculateAverageTemperature(x, y, Integer.parseInt(limit)));
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(" calculateAverageTemperature not working");
            }
        }

        try {

            System.out.println(yandexWeatherService.calculateAverageTemperature(x, y, 5));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("App died:(");
        }
    }
}

package org.example.api;

import java.io.InputStream;
import java.util.Properties;

public class ApiKeyLoader {
    public static String keyLoad() {
        // interesting way to hide the api key
        try (InputStream inputStream = ApiKeyLoader.class.getClassLoader().getResourceAsStream("application.properties")) {
            Properties props = new Properties();
            if (inputStream == null) {
                System.out.println("no props");

                return null;
            }

            props.load(inputStream);
        //    System.out.println("Loaded API Key: " + props.getProperty("api.key"));
            return props.getProperty("api.key");
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }
}

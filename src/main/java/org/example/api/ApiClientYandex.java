package org.example.api;

import org.w3c.dom.Node;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiClientYandex implements ApiClient {
    private final HttpClient httpClient = HttpClient.newHttpClient();
    @Override
    public String get(String url) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .header("X-Yandex-Weather-Key", ApiKeyLoader.keyLoad())
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }


    }
}

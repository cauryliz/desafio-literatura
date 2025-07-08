package com.example.desafioliteratura.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumoAPI {
    public String obtenerDatos(String url) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.ALWAYS)
                .build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("User-Agent", "Java HttpClient")
                .GET()
                .build();
        HttpResponse<String>  response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 200){
            System.out.println("Error: Codigo de estado" + response.statusCode());
            return "";
        }
        String json = response.body();
        if (json == null || json.isBlank()) {
            System.out.println("La API devolvió un cuerpo vacío.");
        }
        return json;
    }

    }

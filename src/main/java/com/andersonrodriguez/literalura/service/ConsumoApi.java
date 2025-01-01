package com.andersonrodriguez.literalura.service;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumoApi {
    private String titulo;

    public ConsumoApi(String titulo) {
        this.titulo = URLEncoder.encode(titulo);
    }
    public String ObtenerJsonBusqueda(){
        String URL_BASE = "https://gutendex.com/books/?search=%20";
        HttpRequest request = HttpRequest.newBuilder().
                uri(URI.create(URL_BASE + this.titulo)).
                GET().
                build();

        HttpClient client = HttpClient.newHttpClient();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

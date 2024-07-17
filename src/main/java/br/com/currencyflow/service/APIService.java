package br.com.currencyflow.service;

import br.com.currencyflow.utils.APIException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class APIService {
    private static final String API_KEY = "34870654ca9d4c1240cca55f";
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";
    private static final String ENDPOINT = "/latest/";

    public static JsonObject fetchExchangeRates(String baseCurrency) {
        String url = BASE_URL + API_KEY + ENDPOINT + baseCurrency;

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return JsonParser.parseString(response.body()).getAsJsonObject();
        } catch (IOException | InterruptedException e) {
            throw new APIException("Erro ao buscar taxas de câmbio: " + e.getMessage());
        }
    }
}

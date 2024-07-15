package br.com.currencyflow.service;

import java.net.http.HttpClient;

public class ExchangeRateService {
    public final HttpClient client;

    public ExchangeRateService() {
        this.client = HttpClient.newHttpClient();
    }
}

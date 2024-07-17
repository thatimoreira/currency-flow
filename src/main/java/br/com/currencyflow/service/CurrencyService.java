package br.com.currencyflow.service;

import br.com.currencyflow.model.Currency;
import br.com.currencyflow.utils.APIException;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Map;

public class CurrencyService {
    private final Map<String, Currency> currencyMap = new HashMap<>();

    public CurrencyService(String fromCurrency) {
        try {
            JsonObject exchangeRates = APIService.fetchExchangeRates(fromCurrency);
            JsonObject rates = exchangeRates.getAsJsonObject("conversion_rates");

            for (String key : rates.keySet()) {
                currencyMap.put(key, new Currency(key, rates.get(key).getAsDouble()));
            }
        } catch (APIException e) {
            System.err.println(e.getMessage());
        }
    }

    public double convert(String toCurrency, double amount) {
        if (currencyMap.containsKey(toCurrency)) {
            double toRate = currencyMap.get(toCurrency).getRate();
            return amount * toRate;
        } else {
            throw new IllegalArgumentException("Código de moeda inválido");
        }
    }
}

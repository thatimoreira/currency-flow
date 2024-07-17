package br.com.currencyflow.service;

import br.com.currencyflow.model.Currency;
import br.com.currencyflow.utils.APIException;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Map;

public class CurrencyService {
    private final Map<String, Currency> currencyMap = new HashMap<>();

    public CurrencyService() {
        try {
            JsonObject exchangeRates = APIService.fetchExchangeRates();
            JsonObject rates = exchangeRates.getAsJsonObject("rates");

            for (String key : rates.keySet()) {
                currencyMap.put(key, new Currency(key, rates.get(key).getAsDouble()));
            }
        } catch (APIException e) {
            System.err.println(e.getMessage());
        }
    }

    public double convert(String fromCurrency, String toCurrency, double amount) {
        if (currencyMap.containsKey(fromCurrency) && currencyMap.containsKey(toCurrency)) {
            double fromRate = currencyMap.get(fromCurrency).getRate();
            double toRate = currencyMap.get(toCurrency).getRate();
            return amount * (toRate / fromRate);
        } else {
            throw new IllegalArgumentException("Invalid currency code");
        }
    }
}

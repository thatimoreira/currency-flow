package br.com.currencyflow;

import br.com.currencyflow.service.CurrencyService;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        CurrencyService currencyService = new CurrencyService();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Currency-Flow Converter!");

        while (true) {
            System.out.println("Select the currency you want to convert from (e.g., USD, EUR): ");
            String fromCurrency = scanner.nextLine().toUpperCase();

            System.out.println("Select the currency you want to convert to (e.g., USD, EUR): ");
            String toCurrency = scanner.nextLine().toUpperCase();

            System.out.println("Enter the amount to convert: ");
            double amount = scanner.nextDouble();
            scanner.nextLine();  // Consume newline

            try {
                double convertedAmount = currencyService.convert(fromCurrency, toCurrency, amount);
                System.out.printf("Converted amount: %.2f %s\n", convertedAmount, toCurrency);
            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage());
            }

            System.out.println("Do you want to perform another conversion? (yes/no): ");
            String response = scanner.nextLine().toLowerCase();
            if (!response.equals("yes")) {
                break;
            }
        }

        System.out.println("Thank you for using Currency-Flow Converter!");
    }
}

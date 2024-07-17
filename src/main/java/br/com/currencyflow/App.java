package br.com.currencyflow;

import br.com.currencyflow.service.CurrencyService;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n============== CURRENCY FLOW ==============");

        while (true) {
            System.out.print("\nDigite a moeda de origem (ex: BRL, USD, EUR): ");
            String fromCurrency = scanner.nextLine().toUpperCase();

            CurrencyService currencyService = new CurrencyService(fromCurrency);

            System.out.print("Digite a moeda de destino (ex: BRL, USD, EUR): ");
            String toCurrency = scanner.nextLine().toUpperCase();

            System.out.print("Digite o valor a ser convertido: ");
            double amount = scanner.nextDouble();
            scanner.nextLine();

            try {
                double convertedAmount = currencyService.convert(toCurrency, amount);
                System.out.printf("\nValor convertido: %.2f %s\n\n", convertedAmount, toCurrency);
            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage());
            }

            System.out.println("Escolha uma opção: ");
            System.out.println("0 - Sair");
            System.out.println("1  - Continuar");
            int option = scanner.nextInt();
            scanner.nextLine();

            if (option == 0) {
                break;
            } else if (option != 1) {
                System.out.println("Opção inválida! Consulta encerrada.");
                break;
            }
        }

        System.out.println("\nConsulta encerrada com sucesso!");
    }
}

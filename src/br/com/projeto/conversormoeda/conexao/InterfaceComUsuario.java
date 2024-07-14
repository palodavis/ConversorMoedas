package br.com.projeto.conversormoeda.conexao;

import java.util.Scanner;

public class InterfaceComUsuario {
    boolean ativador = true;
    Scanner scanner = new Scanner(System.in);
    public void menu() {
        while (ativador) {
            System.out.println("""
                ****************************************************
                Seja bem vindo/a ao Conversor de Moedas =|
                                
                Escolha uma opção de para conversão:

                1) Dólar (USD) => Peso Argentino (ARS)
                2) Peso Argentino (ARS) => Dólar (USD)
                3) Dólar (USD) => Real Brasileiro (BRL)
                4) Real Brasileiro (BRL) => Dólar (USD)
                5) Dólar (USD) => Peso Colombiano (COP)
                6) Peso Colombiano (COP) => Dólar (USD)
                7) Sair
                                
                ****************************************************
                """);
            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    this.coletaValor("USD", "ARS");
                    break;

                case "2":
                    this.coletaValor("ARS", "USD");
                    break;

                case "3":
                    this.coletaValor("USD", "BRL");
                    break;

                case "4":
                    this.coletaValor("BRL", "USD");
                    break;

                case "5":
                    this.coletaValor("USD", "COP");
                    break;

                case "6":
                    this.coletaValor("COP", "USD");
                    break;

                default:
                    if (opcao.equals("7")) {
                        System.out.println("Saindo...");
                        ativador = false;
                    } else {
                        System.out.println("Opção Inválida! " +
                                "Selecione uma opção valida. ");
                    }
                    break;
            }
        }
    }
    public void coletaValor(String moeda1, String moeda2){
        System.out.println("Digite o valor a ser convertido: ");
        try {
            double valor = scanner.nextDouble();
            scanner.nextLine();
            ConversorMoeda conversor = new ConversorMoeda(moeda1, moeda2, valor);

            ConexaoComAPI conexaoApi = new ConexaoComAPI(conversor);
            System.out.println(conexaoApi.ConsumoApi());

        } catch (Exception e){
            System.out.println("Valor digitado inválido. Tente novamente!");
            scanner.nextLine(); // Limpa o buffer do scanner
        }
    }
}

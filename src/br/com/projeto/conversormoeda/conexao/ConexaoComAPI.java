package br.com.projeto.conversormoeda.conexao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConexaoComAPI {
    private final String base_code;
    private final String target_code;
    private final double amount;

    public ConexaoComAPI(ConversorMoeda valores) {
        this.base_code = valores.moedaEscolhida();
        this.target_code = valores.moedaAConverter();
        this.amount = valores.valorCotacao();
    }

    public String ConsumoApi() {
        String apiKEY = "63cba06267e9de035de6dc01";
        String url = String.format("https://v6.exchangerate-api.com/v6/%s/pair/%s/%s", apiKEY, base_code, target_code);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException("Falha ao enviar requisição para a API", e);
        }

        String json = response.body();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        ConversorMoeda conversor = gson.fromJson(json, ConversorMoeda.class);
        double valorConvertido = conversor.valorCotacao() * amount;
        String mensagemUsuario = String.format("Resposta: %.2f %s equivalem a %.2f %s", amount, conversor.moedaEscolhida(), valorConvertido, conversor.moedaAConverter());
        return mensagemUsuario;
    }
}

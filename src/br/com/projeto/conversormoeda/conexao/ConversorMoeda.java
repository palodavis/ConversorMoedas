package br.com.projeto.conversormoeda.conexao;

import com.google.gson.annotations.SerializedName;

public record ConversorMoeda(@SerializedName("base_code") String moedaEscolhida,
                             @SerializedName("target_code") String moedaAConverter,
                             @SerializedName("conversion_rate") double valorCotacao) {
}

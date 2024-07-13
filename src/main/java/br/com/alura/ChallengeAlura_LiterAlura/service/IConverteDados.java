package br.com.alura.ChallengeAlura_LiterAlura.service;

import java.util.List;

public interface IConverteDados {

    <T> T obterDados(String json, Class<T> classe);
}

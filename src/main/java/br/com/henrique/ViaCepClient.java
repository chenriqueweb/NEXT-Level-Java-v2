package br.com.henrique;

import static java.time.temporal.ChronoUnit.MINUTES;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import com.google.gson.Gson;

import br.com.henrique.model.Cep;
import br.com.henrique.utils.CEPUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ViaCepClient {

    private static final String viaCepUrl = "https://viacep.com.br/ws/";
    private static final Gson gson = new Gson();

    public static Cep findCep(String cepString) {
        CEPUtils.validaCep(cepString);
        try {
            HttpClient httpClient = HttpClient.newBuilder()
                    .connectTimeout(Duration.of(1, MINUTES))
                    .build();

            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(viaCepUrl+cepString+"/json"))
                    .build();

            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            // log.info("[VIA CEP API] - [RESULTADO DA BUSCA: {}]", httpResponse.body());

            return gson.fromJson(httpResponse.body(), Cep.class);

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }
}

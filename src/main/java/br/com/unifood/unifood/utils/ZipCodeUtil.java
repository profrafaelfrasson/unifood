package br.com.unifood.unifood.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ZipCodeUtil {
    public static boolean isValidCep(String cep) {
        try {
            String url = "https://viacep.com.br/ws/" + cep + "/json/";

            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                StringBuilder response = new StringBuilder();
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                in.close();
                JsonObject jsonResponse = JsonParser.parseString(response.toString()).getAsJsonObject();

                // Check for the "erro" field which indicates an invalid CEP
                if (jsonResponse.has("erro") && jsonResponse.get("erro").getAsBoolean()) {
                    return false;
                }

                if (jsonResponse.has("cep") && !jsonResponse.get("cep").isJsonNull()) {
                    return true;
                }
            }

            return false;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String cleanCep(String cep) {
        if (cep == null) {
            return null;
        }
        // Remove os caracteres ., -, /
        return cep.replaceAll("[.\\-/]", "");
    }
}

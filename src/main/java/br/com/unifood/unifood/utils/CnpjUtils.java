package br.com.unifood.unifood.utils;

public class CnpjUtils {

    public static String cleanCnpj(String cnpj) {
        if (cnpj == null) {
            return null;
        }
        // Remove os caracteres ., -, /
        return cnpj.replaceAll("[.\\-/]", "");
    }

}

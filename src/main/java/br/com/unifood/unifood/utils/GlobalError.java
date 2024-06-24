package br.com.unifood.unifood.utils;

import java.util.HashMap;
import java.util.Map;

public class GlobalError {
    public Map<String, String> createErrorResponse(String error, String message) {
        Map<String, String> errorDetails = new HashMap<>();
        errorDetails.put("error", error);
        errorDetails.put("message", message);
        return errorDetails;
    }
}

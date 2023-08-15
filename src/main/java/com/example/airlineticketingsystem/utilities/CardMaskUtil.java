package com.example.airlineticketingsystem.utilities;



public class CardMaskUtil {

    public static String maskCardNumber(String cardNumber) {
        String cleanCardNumber = cardNumber.replaceAll("[^\\d]", ""); // Sadece rakamları al

        int visibleDigits = 6;
        int maskedLength = cleanCardNumber.length() - visibleDigits;

        String maskedDigits = "*".repeat(maskedLength);
        String visiblePart = cleanCardNumber.substring(0, visibleDigits);

        return visiblePart + maskedDigits + cleanCardNumber.substring(visibleDigits + maskedLength);
    }
}



package com.neueda.tinyurl.service;

import org.springframework.stereotype.Service;

/**
 *  This is service class for Tinyurl which contains logic for base 64 conversion.
 *
 */
@Service
public class BaseConversion {

    private static final String allowedString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private char[] allowedCharacters = allowedString.toCharArray();
    private int base = allowedCharacters.length; 
    
    /**
     *  This method is used for base 64 encoding.
     *
     */
    public String encode(long input){
        StringBuilder encodedString = new StringBuilder();

        if(input == 0) {
            return String.valueOf(allowedCharacters[0]);
        }

        while (input > 0) {
            encodedString.append(allowedCharacters[(int) (input % base)]);
            input = input / base;
        }

        return encodedString.reverse().toString();
    }
    
    /**
     *  This method is used for base 64 decoding.
     *
     */
    public long decode(String input) {
        char[] characters = input.toCharArray();
         int length = characters.length;

         int decoded = 0;

        //counter is used to avoid reversing input string
        int counter = 1;
        for (int i = 0; i < length; i++) {
            decoded += allowedString.indexOf(characters[i]) * Math.pow(base, length - counter);
            counter++;
        }
        return decoded;
    }
}

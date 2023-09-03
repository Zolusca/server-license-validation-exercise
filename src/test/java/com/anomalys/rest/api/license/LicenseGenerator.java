package com.anomalys.rest.api.license;

import org.junit.jupiter.api.Test;

import java.util.Random;

public class LicenseGenerator {
    @Test
    public void randomString() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 12;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        System.out.println(generatedString);
        System.out.println(dashAppend(generatedString));
    }

    private String dashAppend(String value){

        StringBuilder result = new StringBuilder();
        byte charIndex = 0;

        for(byte index = 0; index < value.length()+2 ; index++){
            if(index == 3 || index == 7){
                result.append('-');
            }else {
                result.append(value.charAt(charIndex));
                charIndex++;
            }

        }

        return String.valueOf(result);
    }
}

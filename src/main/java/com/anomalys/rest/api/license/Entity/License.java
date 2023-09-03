package com.anomalys.rest.api.license.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Random;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Data
public class License {
    private String id;
    private String licenseKey;
    private TypeStatus status;

    /**
     * method pembuatan object license key, string license berjumlah 15 karakter
     * terdiri dari 13 huruf dan 2 dash (-) example : sask-sdk-qweasd
     * @Return = object license dengan id dibuat oleh UUID, status aktif, dan string licensekey
     * */
    public License generateLicenseKey()
    {
        UUID uuid = UUID.randomUUID();

        return new License(uuid.toString(),randomString(),TypeStatus.AKTIF);
    }
    private String randomString() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 13;

        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

       return dashAppend(generatedString);
    }

    /**
     * @Param = value string dari random string method
     * @Return = string value akan ditambah dash ('-') di index ke 4 dan 8
     *
     * @Description = charIndex berfungsi sebagai indexing characther pada parameter
     * for loop pertama yang akan bertugas inject pada index ke 4 dan 8 dengan,
     * index < value.length()+2 agar loop ini tidak berhenti sebelum charIndex menyentuh
     * characther terakhir pada parameter
     */
    private String dashAppend(String value){

        StringBuilder result = new StringBuilder();
        byte charIndex = 0;

        for(byte index = 0; index < value.length()+2 ; index++)
        {
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

package com.example.RestApi;


public class Parser {
    public static String parserMain(String data) {

        // parse data -> {"message":"Piano-1"}
        int dataLen = data.length();
        int dataEnd = dataLen - 2;
        String strings = data.substring(12, dataEnd);
        String[] stringArr = strings.split("-");

        return stringArr[1] + "_" + stringArr[0] + ".wav";
    }
}

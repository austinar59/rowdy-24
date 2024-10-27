package com.example.RestApi;


public class Parser {
    public static void parserMain(String data) {


        // parse data -> {"message":"Piano-1"}
        int dataLen = data.length();
        int dataEnd = dataLen - 2;
        String strings = data.substring(12, dataEnd);
        String[] stringArr = strings.split("-");

        for (int i = 0; i < strings.length(); i += 2) {
            String specFile = stringArr[1] + "_" + stringArr[0] + ".wav";
            String filename = ("..\\..\\..\\..\\..\\..\\resources\\rowdyKeys" + specFile);
            System.out.println("Playing: " + filename);

            PlayClip playClip = new PlayClip(filename);

            Thread thread = new Thread(playClip);
            thread.start();
        }
    }

}

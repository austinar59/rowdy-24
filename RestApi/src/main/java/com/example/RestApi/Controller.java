package com.example.RestApi;


import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {

    @PostMapping("/sendData")
    public @ResponseBody String recieveData(@RequestBody String data) {

        String filename = Parser.parserMain(data);
        System.out.println("Playing: " + filename);

        callPLayClip(filename);
        return "Data recieved in Java" + data;
    }
    public void callPLayClip(String filename){
        PlayClip playClip = new PlayClip(filename);
        Thread thread = new Thread(playClip);
        thread.start();
    }

}

package com.example.RestApi;


import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {

    @PostMapping("/sendData")
    public @ResponseBody String recieveData(@RequestBody String data) {

        // essentially our main
        Parser.parserMain(data);
        System.out.println("Recieved message: "+ data);


        return "Data recieved in Java" + data;
    }

}

package com.example.RestApi;

import org.springframework.web.bind.annotation.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
public class Controller {
    ArrayList<Map<String, String>> notesList = new ArrayList<>();

    @PostMapping("/sendData")
    public @ResponseBody String receiveData(@RequestBody String data) {
        String filename = Parser.parserMain(data);
        System.out.println("Playing: " + filename);

        // Store note with timestamp
        Map<String, String> noteData = new HashMap<>();
        noteData.put("note", Parser.moreParse(filename));
        noteData.put("timestamp", Instant.now().toString());

        notesList.add(noteData);

        callPlayClip(filename);
        return "Data received in Java: " + data;
    }

    @GetMapping("/getArray")
    public ArrayList<Map<String, String>> getItems() {
        System.out.println("Gui called this method yippe");
        System.out.println(notesList.toString());
        return notesList;
    }

    public void callPlayClip(String filename) {
        PlayClip playClip = new PlayClip(filename);
        Thread thread = new Thread(playClip);
        thread.start();
    }
}

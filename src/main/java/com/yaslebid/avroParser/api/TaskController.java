package com.yaslebid.avroParser.api;

import org.springframework.web.bind.annotation.*;

@RestController
public class TaskController {

    public TaskController() {
    }

    @GetMapping(path = "/")
    public String simpleResponse() {
        return "OK";
    }
}
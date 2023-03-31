package com.dailycodebuffer.SpringBootDemo.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/homeController")
public class HomeController {

    @GetMapping("/")
    public String demo() {
        return "Hello World!";
    }

    @GetMapping("/{id1}/{id2}")
    public String pathVariable(@PathVariable("id1") String id, @PathVariable("id2") String name) {
        return "The path variables are: " + id + " & " + name;
    }

    @GetMapping("/requestParam")
    public String requestParams(@RequestParam String name,
                                @RequestParam(name = "email", required = false, defaultValue = "") String emailId) {
        return "Name:" + name + " , Emaild:" + emailId;
    }
}

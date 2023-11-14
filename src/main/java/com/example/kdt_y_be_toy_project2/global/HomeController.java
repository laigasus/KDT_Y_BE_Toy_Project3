package com.example.kdt_y_be_toy_project2.global;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/")
    public String welcome() {
        return "home. welcome!";
    }
}

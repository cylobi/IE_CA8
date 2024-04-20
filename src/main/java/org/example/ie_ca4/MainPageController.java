package org.example.ie_ca4;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainPageController {

    @GetMapping("/")
    String hello() {
        return "Hello, World!";
    }
}
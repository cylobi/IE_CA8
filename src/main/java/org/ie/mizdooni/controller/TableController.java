package org.ie.mizdooni.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TableController {

    @GetMapping("/table")
    String hello() {
        return "table ";
    }
}

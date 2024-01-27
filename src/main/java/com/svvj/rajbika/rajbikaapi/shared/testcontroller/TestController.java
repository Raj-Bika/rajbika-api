package com.svvj.rajbika.rajbikaapi.shared.testcontroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class TestController {

    @GetMapping("/test")
    public String helloWorld() {
        return "Hello World";
    }
}

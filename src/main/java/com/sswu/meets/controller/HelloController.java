package com.sswu.meets.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class HelloController {
    @Value("${spring.jpa.database}")
    private String databaseName;

    @GetMapping("/hello")
    public String helloTest() {
        return "hello";
    }

    @GetMapping("/hello/test")
    public String applicationPropertyTest() {
        return databaseName;
    }
}

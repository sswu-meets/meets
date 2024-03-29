package com.sswu.meets.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@Slf4j
@RequiredArgsConstructor
@RestController
public class HelloController {
    @Value("${web.origin}")
    private String host;

    @GetMapping("/hello")
    public String helloTest() {
        return "hello";
    }

    @GetMapping("/hello/test")
    public String applicationPropertyTest() {
        return host;
    }
}

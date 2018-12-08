package com.gobatis.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class WebApplication implements CommandLineRunner{


    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @ResponseBody
    @GetMapping(value = "/string", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String string(){
        return "test String.";
    }

    @ResponseBody
    @GetMapping(value = "/exception", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void exception() throws Exception {
        throw new Exception("test exception");
    }
}

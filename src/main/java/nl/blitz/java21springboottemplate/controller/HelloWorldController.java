package nl.blitz.java21springboottemplate.controller;

import nl.blitz.java21springboottemplate.dto.HelloWorldDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/api")
public class HelloWorldController {


    @PostMapping("/hello")
    public ResponseEntity<String> hello(@RequestBody HelloWorldDto HelloWorldDto) {
        final String message = "Welcome, " + HelloWorldDto.getName() + "!";
        return ResponseEntity.ok(message);
    }


}
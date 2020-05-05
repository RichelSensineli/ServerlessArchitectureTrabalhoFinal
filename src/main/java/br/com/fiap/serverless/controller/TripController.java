package br.com.fiap.serverless.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trips")
public class TripController {

    @GetMapping("/healthcheck")
    ResponseEntity<String> hello() {
        return new ResponseEntity<>("UP", HttpStatus.OK);
    }
}
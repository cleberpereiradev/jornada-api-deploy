package com.jornada.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceController {
    @GetMapping("/home")
    public String homeEndpoint() {
        return "EXEMPLO DE DEU CERTO !";
    }
}
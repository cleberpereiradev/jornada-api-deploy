package com.jornada.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceController {
    @GetMapping("/")
    public String homeEndpoint() {
        return "BEM VINDO À JORNADA API! VOCÊ TEM ACESSOS APENAS ÀS ROTAS GET SE NÃO TIVER UMA API-KEY VÁLIDA!";
    }
}
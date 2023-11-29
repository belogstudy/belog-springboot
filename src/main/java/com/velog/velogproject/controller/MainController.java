package com.velog.velogproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    /**
     * 루트 경로(/) 접근을 Swagger Page로 리다이렉션합니다.
     */
    @GetMapping("/")
    public String redirectSwagger() {
        return "redirect:/swagger-ui/index.html";
    }

}

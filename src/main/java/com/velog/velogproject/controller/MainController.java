package com.velog.velogproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * API 명세(swagger)를 위한 View 연결 구현
 */
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

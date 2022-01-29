package com.magnus.demo;

import com.magnus.service.infrastruct.I18nUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class ApiController {

    @Resource
    I18nUtils i18nUtils;

    @GetMapping("/api")
    public String index() {
        return "guten tag";
    }

    @GetMapping("/api/i18n")
    public String test() {
        String result = i18nUtils.get("user.welcome");
        return result;
    }
}
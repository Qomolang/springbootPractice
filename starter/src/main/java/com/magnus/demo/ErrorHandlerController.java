package com.magnus.demo;

import com.magnus.service.domain.BaseDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author 84028
 */
@Slf4j
@RestController
public class ErrorHandlerController {

    @GetMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @GetMapping("/demo")
    public String demoGet(String info) {
        return "Greetings from Spring Boot:" + "info";
    }

    @PostMapping("/demo")
    public String demo(@Valid BaseDO baseDO, Errors errors) {

        String returnmsg = "Greetings from Spring Boot!";

        if (errors.hasErrors()) {
            errors.getAllErrors().stream()
                    .forEach(foo -> log.info(foo.getDefaultMessage()));
        }

        return returnmsg;
    }


}
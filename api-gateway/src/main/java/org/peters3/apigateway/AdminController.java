package org.peters3.apigateway;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/hello")
    public String hello() {
        return "Hola ADMIN!";
    }
}

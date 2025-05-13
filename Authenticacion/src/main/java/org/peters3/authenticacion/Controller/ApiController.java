package org.peters3.authenticacion.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping("/protected")
    public String protectedEndpoint() {
        return "Este es un endpoint protegido";
    }
}

package org.peters3.usersecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private BCryptPasswordEncoder encoder;

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        userRepo.save(user);
        return "Usuario registrado";
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        var dbUser = userRepo.findByUsername(user.getUsername()).orElseThrow();
        if (encoder.matches(user.getPassword(), dbUser.getPassword())) {
            return jwtUtil.generateToken(user.getUsername());
        }
        throw new RuntimeException("Credenciales inválidas");
    }

    @GetMapping("/admin")
    public String adminTest() {
        return "Ruta protegida solo accesible con JWT válido";
    }
}

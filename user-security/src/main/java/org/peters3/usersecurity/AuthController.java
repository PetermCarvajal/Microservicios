package org.peters3.usersecurity;

import org.peters3.apigateway.AuthRequest;
import org.slf4j.LoggerFactory; // This is the correct import
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;
import org.slf4j.Logger;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);  // Use LoggerFactory

 // En AuthController.java

@PostMapping("/register")
public ResponseEntity<?> register(@RequestBody User user) {
    // Verificación si el usuario ya existe (esto está bien)
    if (userRepo.findByUsername(user.getUsername()).isPresent()) { //
        return ResponseEntity.status(HttpStatus.CONFLICT).body("El nombre de usuario ya existe"); //
    }

    // 1. Guarda la contraseña original en una variable antes de encriptarla
    String rawPassword = user.getPassword();

    // 2. Encripta la contraseña para guardarla en la base de datos
    user.setPassword(encoder.encode(rawPassword)); //
    userRepo.save(user); //

    try {
        // 3. Usa la contraseña original (rawPassword) para la autenticación inmediata
        Authentication auth = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(user.getUsername(), rawPassword) // Corregido
        );
        if (auth.isAuthenticated()) { //
            String token = jwtUtil.generateToken(user.getUsername()); //
            logger.info("Token generado para el usuario: {}", user.getUsername()); //
            return ResponseEntity.ok(Map.of( //
                "message", "Usuario registrado y autenticado exitosamente", //
                "token", token //
            ));
        } else {
            logger.warn("La autenticación falló después del registro para el usuario {} (auth.isAuthenticated() es false)", user.getUsername()); //
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Usuario registrado, pero la autenticación falló."); //
        }
    } catch (BadCredentialsException e) { // Captura específicamente BadCredentialsException
        logger.error("Error de 'Bad credentials' durante la autenticación post-registro para el usuario {}: {}", user.getUsername(), e.getMessage()); //

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Usuario registrado exitosamente, pero la autenticación automática falló. Por favor, intente iniciar sesión manualmente.");
    }
    catch (Exception e) { // Otras excepciones
        logger.error("Error inesperado durante el proceso de registro/autenticación para el usuario {}: {}", user.getUsername(), e.getMessage(), e); //
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al registrar el usuario: " + e.getMessage()); //
    }
}

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody AuthRequest request) {
        try {
            Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
            String token = jwtUtil.generateToken(auth.getName());
            return ResponseEntity.ok(Map.of("token", token));
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Usuario no encontrado"));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Credenciales inválidas"));
        }
    }
}
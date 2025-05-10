package org.peters3.usersecurity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Table(name = "users")
@AllArgsConstructor
@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private String telefono;
    private String direccion;
    private String password;

    public User() {

    }
}

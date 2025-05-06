package org.peters3.productoservice;

import java.sql.*;

public class TestConnection {
    public static void main(String[] args) throws Exception {
        String url = "jdbc:mysql://localhost:3306/productos_microservicio?useSSL=false&serverTimezone=UTC";
        String username = "root";
        String password = "qwertyuiop";

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            System.out.println("✅ ¡Conexión exitosa a MySQL!");
        }
    }
}

package com.proyecto.config;

import com.proyecto.domain.Usuario;
import com.proyecto.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initAdminUser(UsuarioRepository usuarioRepository, BCryptPasswordEncoder passwordEncoder) {
        return args -> {
            if (usuarioRepository.count() == 0) {
                Usuario admin = new Usuario();
                admin.setNombre("Administrador");
                admin.setCorreo("admin@hospital.com");
                admin.setCedula("000000000");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setRol("ADMIN");
                admin.setActivo(true);
                usuarioRepository.save(admin);
                System.out.println("Usuario admin creado: admin@hospital.com / admin123");
            }
        };
    }
}


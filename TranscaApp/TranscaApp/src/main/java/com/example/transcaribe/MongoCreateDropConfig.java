package com.example.transcaribe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoCreateDropConfig {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Bean
    public ApplicationRunner createDropMongoCollections() {
        return args -> {
            // Eliminar y volver a crear la colección 'recargas'

            mongoTemplate.createCollection("recargas");
            mongoTemplate.createCollection("admin_trayecto");
            mongoTemplate.createCollection("administradores");
            mongoTemplate.createCollection("conductores");
            mongoTemplate.createCollection("estaciones");
            mongoTemplate.createCollection("horarios");
            mongoTemplate.createCollection("tarjetas");
            mongoTemplate.createCollection("trayectos");
            mongoTemplate.createCollection("trayectos_estación");
            mongoTemplate.createCollection("usuarios");

            // Repite esto para otras colecciones si lo deseas
        };
    }
}

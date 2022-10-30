package com.gmarquezp.web.app;

import com.gmarquezp.web.app.clients.DragonBallCharacterFeign;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.ResponseEntity;

@SpringBootApplication
@EnableFeignClients // Habilitando el cliente Feign
public class SpringBootMicroservicesClientStandaloneDragonApplication implements ApplicationRunner {

    @Autowired
    private EurekaClient eurekaClient;

    @Autowired
    private DragonBallCharacterFeign dragonBallCharacterFeign;

    private static final Logger log = LoggerFactory.getLogger(SpringBootMicroservicesClientStandaloneDragonApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMicroservicesClientStandaloneDragonApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Inicializo la app correctamente =)");

        // dragon-ball // aplication name de un microservicio registrado en Erukea
        Application appClienteDragonBall = this.eurekaClient.getApplication("dragon-ball");
        log.info("Obteniendo informacion de la a app dragon-ball");
        log.info("Aplication Name {}", appClienteDragonBall.getName());
        log.info("Aplication Instances {}", appClienteDragonBall.getInstances());
        appClienteDragonBall.getInstances().forEach((appInstance) -> {
            log.info("Ip: {}", appInstance.getIPAddr());
            log.info("Port: {}", appInstance.getPort());
        });


        for (int i = 0; i < 10; i++) {
            log.info("Consumiendo un servicio con Feign Iteracion: {}", i);
            ResponseEntity<String> applicationNameFeignService = this.dragonBallCharacterFeign.getApplicationName();
            log.info("Respuesta body: {} - Iteracion: {}", applicationNameFeignService.getBody(), i);
            log.info("Respuesta code: {} - Iteracion: {}", applicationNameFeignService.getStatusCode(), i);
        }


    }
}

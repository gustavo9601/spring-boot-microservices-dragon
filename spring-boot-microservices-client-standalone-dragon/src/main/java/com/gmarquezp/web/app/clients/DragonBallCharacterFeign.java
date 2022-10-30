package com.gmarquezp.web.app.clients;

import com.gmarquezp.web.app.configs.LoadBalancerConfiguration;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "dragon-ball") // Indica el nombre del microservicio registrado en eureka, y que se va a consumir

// LoadBalancerConfiguration // Especificara el comportamiento del balanceo
@LoadBalancerClient(name = "dragon-ball", configuration = LoadBalancerConfiguration.class) // Mismo nombre del cliente feign
public interface DragonBallCharacterFeign {

    /*
    * Se especifican todas las rutas que se van a consumir del microservicio
    * */
    @GetMapping("/application-name")
    public ResponseEntity<String> getApplicationName();
}

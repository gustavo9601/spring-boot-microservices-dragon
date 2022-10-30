package com.gmarquezp.web.app.configs;

import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadBalancerConfiguration {

    /*
    * Bean encargado de especificar como redirijira el trafico a los diferentes microservicios de haber mas de una instancia
    * */
    @Bean
    public ServiceInstanceListSupplier discoveryClientServiceInstanceListSupplier(ConfigurableApplicationContext context) {
        return ServiceInstanceListSupplier.builder()
                // .withSameInstancePreference() // Especifica que todas las peticiones se redirijan a un solo nodo sin importar que existan mas
                .withBlockingDiscoveryClient()
                .build(context);


    }
}

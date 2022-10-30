package springbootmicroserviceseurekadragon.springbootmicroserviceseurekadragon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/*
* En la raiz del proyecto, se desplegara el dashboard base de Eureka Server
*
* path para ver XML con la info que se pinta en el dashboard
* http://localhost:8761/eureka/apps
*
* */
@SpringBootApplication
@EnableEurekaServer // Habilintado el servidor de Eureka
public class SpringBootMicroservicesEurekaDragonApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMicroservicesEurekaDragonApplication.class, args);
	}

}

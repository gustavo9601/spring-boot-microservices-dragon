package springbootmicroservicesclientdragon.springbootmicroservicesclientdragon.configs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@Configuration
public class KafkaStreamConfig {


    // logger
    private static final Logger logger = LoggerFactory.getLogger(KafkaStreamConfig.class);

    /*
    * Funcion (lambda) que recibe un string y lo devuelve en mayusculas
    *
    * - Solo con esta configuracion se crearan 2 topic uno in y otro out
    * - Funcionara como un transformer (procesor) que recibe en el topic in, ejecuta la funcion y carga a el out
    *
    *
    * Flujo normal
    * producer (cargar data, consumo de servicios) -> proceesor (trasnformacion de data) -> consumer (consumo de data)
    * */
    @Bean
    public Function<String, String> uppercase() {
        // return value -> value.toUpperCase();
        return value -> {
            logger.info("Ejecutando uppercase al valor {}", value);
            return value.toUpperCase();
        };
    }

    /*
    * Ejemplo completo con producer, procesor y consumer
    * */

    // topic : producernumbers-out-0
    @Bean
    public Supplier<Flux<Long>> producernumbers() {
        // Creara un producer reactivo, que cada segundo emitira un valor
        return () -> Flux.interval(Duration.ofMinutes(10)).log();
    }

    // topic : processornumbers-in-0 && processorNumbers-out-0
    @Bean
    public Function<Flux<Long>, Flux<Long>> procesornumbers(){
        return flux -> flux.map(value -> {
            logger.info("Ejecutando procesorNumbers al valor {}", value);
            return value * 2;
        });
    }

    // topic: consumernumbers-in-0
    @Bean
    public Consumer<Long> consumernumbers(){
        return value -> logger.info("Ejecutando consumerNumbers al valor {}", value);
    }

}

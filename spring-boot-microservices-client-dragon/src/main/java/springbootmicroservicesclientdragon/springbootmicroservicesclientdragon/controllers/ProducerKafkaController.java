package springbootmicroservicesclientdragon.springbootmicroservicesclientdragon.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/procuder-mensajes")
public class ProducerKafkaController {

    @Autowired
    private StreamBridge streamBridge;

    @GetMapping("/{word}")
    public ResponseEntity<Void> sendWord(@PathVariable String word){
        // .send("nombre-del-topic", mensaje)
        this.streamBridge.send("uppercase-in-0", word);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }


}

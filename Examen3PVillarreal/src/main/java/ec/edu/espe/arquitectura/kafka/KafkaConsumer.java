/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.kafka;

import ec.edu.espe.arquitectura.kafka.controller.PrestamoController;
import ec.edu.espe.arquitectura.kafka.model.Prestamo;
//import ec.edu.espe.arquitectura.kafka.service.MensajeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Windows Boo
 */
@Slf4j
@Component
public class KafkaConsumer {

    private final PrestamoController mensajeController;
    private RestTemplate restTemplate = new RestTemplate();

    public KafkaConsumer(PrestamoController mensajeController) {
        this.mensajeController = mensajeController;
    }

    @KafkaListener(
            topics = "prestamos",
            groupId = "groupId"
    )
    public void Listener(Prestamo p) {
        log.info("Data recibida sin procesar nada: {}", p);
        this.restTemplate.postForObject("http://localhost:8080/api/v1/prestamo", p, Prestamo.class);

    }
}

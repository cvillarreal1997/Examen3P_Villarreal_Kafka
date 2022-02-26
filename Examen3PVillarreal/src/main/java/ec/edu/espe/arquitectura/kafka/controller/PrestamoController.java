/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.kafka.controller;

import ec.edu.espe.arquitectura.kafka.model.Prestamo;
import ec.edu.espe.arquitectura.kafka.service.PrestamoService;
//import ec.edu.espe.arquitectura.kafka.service.MensajeService;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author terry
 */
@RestController
@RequestMapping("api/v1/")
public class PrestamoController {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final PrestamoService prestamoService;

    public PrestamoController(KafkaTemplate<String, Object> kafkaTemplate, PrestamoService prestamoService) {
        this.kafkaTemplate = kafkaTemplate;
        this.prestamoService = prestamoService;
    }


    @PostMapping("prestamo")
    public void enviarMensaje(@RequestBody Prestamo p) {
        this.prestamoService.guardarPrestamo(p);
    }
}

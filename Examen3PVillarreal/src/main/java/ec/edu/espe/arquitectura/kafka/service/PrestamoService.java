/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.kafka.service;

import ec.edu.espe.arquitectura.kafka.model.Prestamo;
import org.springframework.stereotype.Service;
import ec.edu.espe.arquitectura.kafka.dao.PrestamoRepository;

/**
 *
 * @author Windows Boo
 */
@Service
public class PrestamoService {

    private final PrestamoRepository prestamoRepository;

    public PrestamoService(PrestamoRepository prestamoRepository) {
        this.prestamoRepository = prestamoRepository;
    }

    public void guardarPrestamo(Prestamo p) {
        this.prestamoRepository.save(p);
    }
}

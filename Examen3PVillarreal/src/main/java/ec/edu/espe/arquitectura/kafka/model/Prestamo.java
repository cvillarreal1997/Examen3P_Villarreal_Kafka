/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.kafka.model;

import com.github.javafaker.DateAndTime;
import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;

/**
 *
 * @author Windows Boo
 */
@Data
@Entity
@Table(name = "prestamo")
public class Prestamo implements Serializable {

    private static final long serialVersionUID = 123456L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_prestamo", nullable = false)
    private Integer cod_prestamo;

    @Column(name = "valor_pago", nullable = false, length = 50)
    private Number valor_pago;

    @Column(name = "fecha_pago", nullable = false, length = 50)
    private Date fecha_pago;

    @Column(name = "hora_pago", nullable = false, length = 10)
    private DateAndTime hora_pago;

    @Column(name = "nro_cuota", nullable = false, length = 150)
    private Integer nro_cuota;



    

}

package ec.edu.espe.arquitectura.kafka;

import com.github.javafaker.Faker;
import ec.edu.espe.arquitectura.kafka.model.Prestamo;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaProducerException;
import org.springframework.kafka.core.KafkaSendCallback;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;

@SpringBootApplication
@Slf4j
public class KafkaProducer {
    
    public static void main(String[] args) {
        SpringApplication.run(KafkaProducer.class, args);
    }
    
    @Bean
    CommandLineRunner commandLineRunner(KafkaTemplate<String, Object> kafkaTemplate) {
        return args -> {
            Integer code=0;
            Faker faker = new Faker();
            Prestamo prestamo = new Prestamo();
            for (int i = 0; i < 100; i++) {
                code=(int)(10000000 * Math.random());
                prestamo.setCod_prestamo(code);
                prestamo.setFecha_pago(getRandomDate());
                prestamo.setHora_pago(faker.date());
                prestamo.setValor_pago(Math.random()*i);
                prestamo.setNro_cuota(i);
                
               
                log.info("Prestamo: {}", prestamo);
                ListenableFuture<SendResult<String, Object>> send = kafkaTemplate.send("prestamos", prestamo);
                send.addCallback(new KafkaSendCallback<String, Object>() {
                    @Override
                    public void onSuccess(SendResult<String, Object> result) {
                        log.info("Mensaje enviado: {}",result.getRecordMetadata());
                    }
                    
                    @Override
                    public void onFailure(Throwable ex) {
                        log.error("Error al enviar el mensaje {}",ex);
                    }
                    
                    @Override
                    public void onFailure(KafkaProducerException ex) {
                         log.error("Error al enviar el mensaje {}",ex); //To change body of generated methods, choose Tools | Templates.
                    }
                    
                });
            }
        };
    }
    
    public static Date getRandomDate() {
		Random rand = new Random();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd ");
		Calendar cal = Calendar.getInstance();
		cal.set(2000, 0, 1);
		long start = cal.getTimeInMillis();
		cal.set(2012, 10, 1);
		long end = cal.getTimeInMillis();
		Date d = new Date(start + (long) (rand.nextDouble() * (end - start)));
		System.out.println(format.format(d));
		return d;
	}
}

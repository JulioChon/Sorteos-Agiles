package com.agiles.sorteos.capanegocios.capasnegocios.servicios;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.ScheduledFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import com.agiles.sorteos.capadatos.capadatos.dominio.Boleto;
import com.agiles.sorteos.capadatos.capadatos.dominio.ConfiguracionEnvio;
import com.agiles.sorteos.capadatos.capadatos.fachadas.IFachadaSorteos;

import jakarta.annotation.PostConstruct;

@Service
public class EnvioCorreoService implements IEnvioCorreoService {

    @Autowired
    private IFachadaSorteos fachadaSorteos;
    @Autowired
    private JavaMailSender emailSender;

    private final ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
    private ScheduledFuture<?> verificationTask;
    private ScheduledFuture<?> scheduledTask;

    public EnvioCorreoService() {
        taskScheduler.initialize();
      
    }

    @PostConstruct
    public void iniciarVerificacionPeriodic() {
        // La tarea se ejecuta cada 10 segundos (puedes ajustar el intervalo)
        verificationTask = taskScheduler.scheduleAtFixedRate(this::verificarDatosYProgramarEnvio, Duration.ofSeconds(10));
    }

    public void verificarDatosYProgramarEnvio() {
        ConfiguracionEnvio config = fachadaSorteos.obtenerConfiguracionEnvio();
        List<Boleto> boletosApartados = fachadaSorteos.obtenerBoletosApartados();

        if (config != null && boletosApartados != null && !boletosApartados.isEmpty()) {
            System.out.println("Datos encontrados. Programando envíos de correos...");
            programarEnvioDinamico(config.getIntervaloEnMilisegundos());
            verificationTask.cancel(true); // Detener la tarea de verificación periódica
        } else {
            System.out.println("Esperando datos en la base de datos para iniciar el envío de correos...");
        }
    }

    public void programarEnvioDinamico(long intervalo) {
        if (scheduledTask != null) {
            scheduledTask.cancel(true); // Cancela la tarea anterior si existe
        }
        scheduledTask = taskScheduler.scheduleAtFixedRate(this::enviarRecordatorios, Duration.ofMillis(intervalo));
    }

    public void enviarRecordatorios() {
        List<Boleto> boletosApartados = fachadaSorteos.obtenerBoletosApartados();
        if (boletosApartados == null || boletosApartados.isEmpty()) {
            System.out.println("No hay boletos apartados para enviar recordatorios.");
            return; // No hace nada si no hay boletos
        }
        for (Boleto boleto : boletosApartados) {
            enviarCorreo(boleto.getIdCliente().getCorreo(), boleto);
        }
    }

    public void enviarCorreo(String correo, Boleto boleto) {
        SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setTo(correo);
        mensaje.setSubject("Recordatorio de Pago de Boleto");
        mensaje.setText("Detalles del sorteo: " + boleto.getIdSorteo().getNombre() + "\n"
                + "Fecha de sorteo: " + boleto.getIdSorteo().getFechaSorteo() + "\n" + "Número de boleto: "
                + boleto.getNumeroBoleto()
                + "\n" + "Precio: " + boleto.getPrecio() + "\n" + "Fecha límite de pago: " + boleto.getFechaLimApart());
        emailSender.send(mensaje);
    }

    @Override
    public void guardarConfiguracionEnvio(ConfiguracionEnvio configuracionEnvio) {
        fachadaSorteos.guardarConfiguracionEnvio(configuracionEnvio);
    }
}
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class ProcesadorReconocimiento {
    // Colas para las diferentes prioridades
    private BlockingQueue<Reconocimiento> colaAltaPrioridad;  // Cola de alta prioridad (FCFS)
    private PriorityBlockingQueue<Reconocimiento> colaMediaPrioridad;  // Cola de media prioridad (SJF)
    private BlockingQueue<Reconocimiento> colaBajaPrioridad;  // Cola de baja prioridad (FCFS)

    public ProcesadorReconocimiento() {
        // Inicialización de las colas
        colaAltaPrioridad = new LinkedBlockingQueue<>();
        colaMediaPrioridad = new PriorityBlockingQueue<>();
        colaBajaPrioridad = new LinkedBlockingQueue<>();
    }

    // Método para clasificar y encolar reconocimientos
    public void procesarReconocimiento(Reconocimiento reconocimiento) {
        switch (reconocimiento.getPrioridad()) {
            case 1:
                colaAltaPrioridad.offer(reconocimiento);
                break;
            case 2:
                colaMediaPrioridad.offer(reconocimiento);
                break;
            case 3:
                colaBajaPrioridad.offer(reconocimiento);
                break;
        }
    }

    // Inicia los hilos para procesar cada cola
    public void procesarColas() {
        new Thread(() -> procesarColaAltaPrioridad()).start();
        new Thread(() -> procesarColaMediaPrioridad()).start();
        new Thread(() -> procesarColaBajaPrioridad()).start();
    }

    // Procesa la cola de alta prioridad (FCFS)
    private void procesarColaAltaPrioridad() {
        while (true) {
            try {
                Reconocimiento reconocimiento = colaAltaPrioridad.take();
                procesar(reconocimiento);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    // Procesa la cola de media prioridad (SJF)
    private void procesarColaMediaPrioridad() {
        while (true) {
            try {
                Reconocimiento reconocimiento = colaMediaPrioridad.take();
                procesar(reconocimiento);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    // Procesa la cola de baja prioridad (FCFS)
    private void procesarColaBajaPrioridad() {
        while (true) {
            try {
                Reconocimiento reconocimiento = colaBajaPrioridad.take();
                procesar(reconocimiento);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    // Método para procesar cada reconocimiento
    private void procesar(Reconocimiento reconocimiento) {
        System.out.println("Procesando reconocimiento: " + reconocimiento.getPrioridad());
        try {
            Thread.sleep(reconocimiento.getTiempoProcesamiento());  // Simula el tiempo de procesamiento
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        if (reconocimiento.tieneEntradaPermitida() && reconocimiento.esReconocido()) {
            System.out.println("Acceso permitido.");
        } else {
            System.out.println("Acceso denegado.");
        }
    }
}
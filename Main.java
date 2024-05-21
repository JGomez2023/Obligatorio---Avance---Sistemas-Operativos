import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String archivo = "reconocimientos.txt";  // Ejemplo de archivo de entrada
        ProcesadorReconocimiento procesador = new ProcesadorReconocimiento();  // Procesador de reconocimientos

        // Lectura del archivo y creación de hilos para cada línea (cada reconocimiento facial)
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                int prioridad = Integer.parseInt(partes[0]);
                boolean tieneEntradaPermitida = Boolean.parseBoolean(partes[1]);
                boolean esReconocido = Boolean.parseBoolean(partes[2]);
                int tiempoProcesamiento = Integer.parseInt(partes[3]);

                // Crear un objeto Reconocimiento y un hilo para procesarlo
                Reconocimiento reconocimiento = new Reconocimiento(prioridad, tieneEntradaPermitida, esReconocido, tiempoProcesamiento);
                new Thread(() -> procesador.procesarReconocimiento(reconocimiento)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Inicia el procesamiento de las colas
        procesador.procesarColas();
    }
}
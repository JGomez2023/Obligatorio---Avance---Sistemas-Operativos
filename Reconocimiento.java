public class Reconocimiento implements Comparable<Reconocimiento> {
    private int prioridad;  // Prioridad del reconocimiento (1, 2, 3)
    private boolean tieneEntradaPermitida;  // Indica si la entrada está permitida
    private boolean esReconocido;  // Indica si la persona es reconocida
    private int tiempoProcesamiento;  // Tiempo de procesamiento simulado

    public Reconocimiento(int prioridad, boolean tieneEntradaPermitida, boolean esReconocido, int tiempoProcesamiento) {
        this.prioridad = prioridad;
        this.tieneEntradaPermitida = tieneEntradaPermitida;
        this.esReconocido = esReconocido;
        this.tiempoProcesamiento = tiempoProcesamiento;
    }

    // Getters para las propiedades
    public int getPrioridad() {
        return prioridad;
    }

    public boolean tieneEntradaPermitida() {
        return tieneEntradaPermitida;
    }

    public boolean esReconocido() {
        return esReconocido;
    }

    public int getTiempoProcesamiento() {
        return tiempoProcesamiento;
    }

    // Implementación del método compareTo para ordenar por tiempo de procesamiento (usado en SJF)
    @Override
    public int compareTo(Reconocimiento otro) {
        return Integer.compare(this.tiempoProcesamiento, otro.tiempoProcesamiento);
    }
}
package entidades;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Fecha {

    private LocalDate fecha;

    
    private static final DateTimeFormatter FORMATO_MOSTRAR = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    
    private static final DateTimeFormatter FORMATO_ISO = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    //Constructores 
    public Fecha(int dia, int mes, int anio) {
        this.fecha = LocalDate.of(anio, mes, dia);
    }

    public Fecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    
    public Fecha(String texto) {
        try {
            if (texto.contains("-")) { 
                this.fecha = LocalDate.parse(texto, FORMATO_ISO);
            } else if (texto.contains("/")) { 
                this.fecha = LocalDate.parse(texto, FORMATO_MOSTRAR);
            } else {
                throw new IllegalArgumentException("Formato de fecha no reconocido: " + texto);
            }
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Error al parsear fecha: " + texto);
        }
    }

    //Métodos estáticos
    public static Fecha hoy() {
        return new Fecha(LocalDate.now());
    }

    //Operaciones 
    // Suma o resta días (positivo o negativo)
    public void modificarDias(int dias) {
        this.fecha = this.fecha.plusDays(dias);
    }

    //Comparaciones 
    public boolean esMenor(Fecha otra) {
        return this.fecha.isBefore(otra.getLocalDate());
    }

    public boolean esMayor(Fecha otra) {
        return this.fecha.isAfter(otra.getLocalDate());
    }

    public boolean esIgual(Fecha otra) {
        return this.fecha.isEqual(otra.getLocalDate());
    }

    //Getters
    public LocalDate getLocalDate() {
        return fecha;
    }

    //Representación 
    @Override
    public String toString() {
        return fecha.format(FORMATO_MOSTRAR);
    }

    
    public String toISO() {
        return fecha.format(FORMATO_ISO);
    }
}


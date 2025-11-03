package entidades;

public class Tarea {

    // Atributos
    private String titulo;
    private String descripcion;
    private double duracionDias;
    private double retraso;
    private Empleado empleado;
    private boolean finalizada;

    // Constructor
    public Tarea(String titulo, String descripcion, double duracionDias) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.duracionDias = duracionDias;
        this.retraso = 0;
        this.empleado = null;
        this.finalizada = false;
    }

    
    public void cambiarResponsable(Empleado e) {
        
        if (this.empleado != null) {
            this.empleado.cambiarOcupado(false);
        }

        this.empleado = e;
        if (this.empleado != null) {
            this.empleado.cambiarOcupado(true);
        }
    }

    
    public void agregarRetrasoDias(double diasRetraso) {
        this.retraso += diasRetraso;

        
        if (empleado != null) {
            empleado.sumarRetraso();
        }
    }

    
    public void marcarFinalizada() {
        this.finalizada = true;

        
        if (empleado != null) {
            empleado.cambiarOcupado(false);
        }
    }

    
    public boolean estaFinalizada() {
        return finalizada;
    }

    
    public double devolverHorasTrabajadas() {
        double dias = duracionDias;

        
        if (empleado instanceof EmpleadoPermanente && duracionDias < 1.0) {
            dias = 1.0;
        }

        return dias * 8; 
    }
    
 //GETTERS Y SETTERS
    
    public String obtenerTitulo() {
        return titulo;
    }

    public String obtenerDescripcion() {
        return descripcion;
    }

    public double obtenerDuracionDias() {
        return duracionDias;
    }

    public double obtenerRetraso() {
        return retraso;
    }

    public Empleado obtenerEmpleado() {
        return empleado;
    }

    public void cambiarTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void cambiarDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void cambiarDuracionDias(double duracionDias) {
        this.duracionDias = duracionDias;
    }

    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Tarea [")
          .append("Título: ").append(titulo);
        sb.append("]");
        return sb.toString();
    }
}


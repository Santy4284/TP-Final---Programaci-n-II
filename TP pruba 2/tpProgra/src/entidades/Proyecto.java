package entidades;

import java.time.LocalDate;
import java.util.*;

public class Proyecto {

    //Atributos 
    private int nroProyecto;
    private String cliente;
    private String direccion;
    private String telefono;
    private String email;
    private Fecha fechaInicio;
    private Fecha fechaEstimada;
    private Fecha fechaReal;
    private Map<String, Tarea> tareas;
    private boolean finalizado;
    private boolean tieneRetraso;	//agregado
    private double costoFinal;
    private List<Empleado> historialEmpleados;

    //Constructor
    public Proyecto(int nro, String cliente, String dir, String telefono, String email,
                    Fecha ini, Fecha finEstimada) {

        if (nro <= 0) {
        	throw new IllegalArgumentException("Número de proyecto inválido");
        }
        this.nroProyecto = nro;
        this.cliente = cliente;
        this.direccion = dir;
        this.telefono = telefono;
        this.email = email;
        this.fechaInicio = ini;
        this.fechaEstimada = finEstimada;
        this.fechaReal = new Fecha(finEstimada.getLocalDate());
        this.tareas = new HashMap<>();
        this.finalizado = false;
        this.tieneRetraso = false;
        this.costoFinal = 0;
        this.historialEmpleados = new ArrayList<>();
    }

    //Operaciones 

    public void agregarTarea(String titulo, String descripcion, double diasEstimados) {
        if (finalizado) {
        	throw new IllegalStateException("Proyecto finalizado");
        }
        if (tareas.containsKey(titulo)) {
            throw new IllegalArgumentException("Tarea duplicada");
        }

        Tarea nueva = new Tarea(titulo, descripcion, diasEstimados);
        tareas.put(titulo, nueva);
    }

    public void reasignarEmpleado(String tituloTarea, Empleado nuevo) {
        Tarea t = tareas.get(tituloTarea);
        if (t == null) {
        	throw new IllegalArgumentException("Tarea inexistente");
        }
        Empleado anterior = t.obtenerEmpleado();
        if (anterior != null) {
        	historialEmpleados.add(anterior);
        }
        t.cambiarResponsable(nuevo);
        historialEmpleados.add(nuevo);
    }

    public void registrarRetraso(String tituloTarea, double diasRetraso) {
        Tarea t = tareas.get(tituloTarea);
        if (t == null) {
        	throw new IllegalArgumentException("Tarea inexistente");
        }
        if (diasRetraso <= 0) {
        	throw new IllegalArgumentException("Retraso inválido");
        }
        t.agregarRetrasoDias(diasRetraso);
        actualizarFechaRealFin();
        cambiartieneRetrasos(true);
    }

    public void actualizarFechaRealFin() {
        double totalRetrasos = 0;
        for (Tarea t : tareas.values()) {
            totalRetrasos += t.obtenerRetraso();
        }
        fechaReal.modificarDias((int) totalRetrasos);
    }

    public void finalizarTarea(String tituloTarea) {
        Tarea t = tareas.get(tituloTarea);
        if (t == null) {
        	throw new IllegalArgumentException("Tarea inexistente");
        }
        t.marcarFinalizada();
        Empleado e = t.obtenerEmpleado();
        if (e != null) {
        	historialEmpleados.add(e);
        }
    }

    public void cambiarFinalizado() {
        this.finalizado = true;
        this.fechaReal = Fecha.hoy();
        calcularCostoTotal();
    }

    public boolean estaFinalizado() {
        return finalizado;
    }

    public void calcularCostoTotal() {
        double total = 0;
        boolean huboRetrasos = tieneRetraso;

        for (Tarea t : tareas.values()) {
            Empleado emp = t.obtenerEmpleado();
            if (emp != null) {
                double costo = emp.calcularPago(
                        t.obtenerDuracionDias(),
                        t.devolverHorasTrabajadas(),
                        t.obtenerRetraso() == 0
                );
                total += costo;
            }
        }
        if(huboRetrasos) total *= 1.25;
        else total *= 1.35;

        costoFinal = total;
    }

    public List<Empleado> devolverHistorial() {
        Set<Empleado> unicos = new HashSet<>(historialEmpleados);
        return new ArrayList<>(unicos);
    }

    // Getters
    public int obtenerNroProyecto() { 
    	return nroProyecto; }
    
    public double obtenerCostoFinal() {
    	return costoFinal;
    }
    
    public String obtenerCliente() { 
    	return cliente; }
    
    public String obtenerDireccion() { 
    	return direccion; }
    
    public String obtenerTelefono() { 
    	return telefono; }
    
    public String obtenerEmail() { 
    	return email; }
    
    public Fecha obtenerFechaInicio() { 
    	return fechaInicio; }
    
    public Fecha obtenerFechaEstimada() { 
    	return fechaEstimada; }
    
    public Fecha obtenerFechaReal() { 
    	return fechaReal; }
    
    public Map<String, Tarea> obtenerTareas() { 
    	return tareas; }
    
    public boolean obtenertieneRetrasos() {
    	return tieneRetraso;
    }
    
    public void cambiartieneRetrasos(boolean retraso) {
    	tieneRetraso = retraso;
    }

    @Override
    public String toString() {
        return "Proyecto #" + nroProyecto + " - " + direccion + " (" +
                (finalizado ? "Finalizado" : "Activo") + ")" ;
    }
}

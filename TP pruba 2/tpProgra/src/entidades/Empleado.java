package entidades;

public abstract class Empleado {
private String nombre;
private int legajo;
private boolean ocupado;
private int cantidadRetrasos = 0;


public Empleado(String nombre, int legajo){
    this.nombre = nombre;
    this.legajo= legajo;
    this.ocupado = false;
    this.cantidadRetrasos = 0;
}

public boolean estaOcupado(){
    return  ocupado;
}

public void cambiarOcupado(boolean estado){
    this.ocupado = estado;
}

public int obtenerCantRetrasos(){
    return  cantidadRetrasos;
}

public void sumarRetraso(){
    this.cantidadRetrasos++;
}

public boolean tuvoRetraso(){
    return  this.cantidadRetrasos > 0;
}

public abstract double calcularPago(double diasTrabajados, double horasTrabajadas, boolean sinRetrasos);

//GETTERS Y SETTERS

public String obtenerNombre(){
    return  nombre;
}

public int obtenerLegajo(){
    return  legajo;
}
public void cambiarNombre(String nombre){
    this.nombre = nombre;
}
public void cambiarLegajo(int legajo){
    this.legajo = legajo;
}

@Override
public String toString(){
    StringBuilder sb = new StringBuilder();
    sb.append("Empleado [")
       .append("Nombre: ").append(nombre)
       .append(", Legajo: ").append(legajo)
       .append(", Ocupado: ").append(ocupado)
       .append(", Cant. Retrasos: ").append(cantidadRetrasos)
       .append("]");
       return sb.toString();
}


}

package entidades;

public class EmpleadoContratado extends Empleado {

    private double valorHora;

    //Const.
    public EmpleadoContratado (String nombre, int legajo,double valorHora){
        super(nombre, legajo);
        this.valorHora=valorHora;
    }


    @Override
    public double calcularPago(double diasTrabajados, double horasTrabajadas, boolean sinRetrasos){
       return  horasTrabajadas*valorHora;
        
    }

    //GETTERS Y SETTERS

    public double  obtenervalorHora(){
        return  valorHora;
    }
    public void cambiarValorHora(double  valorHora){
        this.valorHora = valorHora;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Empleado Contratado [")
          .append("Nombre: ").append(obtenerNombre())
          .append(", Legajo: ").append(obtenerLegajo())
          .append(", Valor Hora: ").append(valorHora)
          .append("]");
          return sb.toString();
    }
}

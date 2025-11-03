package entidades;


public class EmpleadoPermanente extends Empleado {
    private double valorDia;
    private String categoria;


      //Const.
    public EmpleadoPermanente(String nombre, int legajo , double valorDia,String categoria){
        super(nombre,legajo);
        this.valorDia=valorDia;
        this.categoria= categoria;
    }

    @Override
    public double calcularPago(double diasTrabajados, double horasTrabajadas, boolean  sinRetrasos){
        double pago= diasTrabajados*valorDia;
        if(sinRetrasos){
            pago+=pago*0.02;
        }
        return pago;
    }

    //GETTERS Y SETTERS

    public double obtenerValorDia(){
        return  valorDia;
    }
    public void cambiarValorDia(double valorDia){
        this.valorDia = valorDia;
    }
    public String obtenerCategoria(){
        return  categoria;
    }
    public void cambiarCategoria(String categoria){
        this.categoria = categoria;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Empleado Permanente [")
          .append("Nombre: ").append(obtenerNombre())
          .append(", Legajo: ").append(obtenerLegajo())
          .append(", Valor Día: ").append(obtenerValorDia())
          .append(", Categoría: ").append(obtenerCategoria())
          .append("]");
          return sb.toString();
    }

}

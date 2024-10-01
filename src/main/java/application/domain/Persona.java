package application.domain;

public class Persona {
    String nombre;
    String apellidos;
    String DNI;

    public Persona(String DNI, String apellidos, String nombre) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.DNI = DNI;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", DNI='" + DNI + '\'' +
                '}';
    }
}

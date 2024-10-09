package application.domain;

// implemento imprimible
public class Persona implements Imprimible {
    String nombre;
    String apellidos;
    String DNI;

    public Persona(String nombre, String apellidos, String DNI) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.DNI = DNI;
    }

    @Override
    public String imprimir() {
        return "nombre=" + nombre + ", apellidos=" + apellidos + ", DNI=" + DNI;
    }
}

package models;
// lo añadimos en model y no en view ya que en este se guarda la información de casa invitado, maneja la lógica de esta cosa y tendrá los datiños
//en cambio view solo muestra recoge info, pero no los guarda directamente
// para el mcv esta bien guardarlo aquip
//sooo el guest en modelo presentará los datos del invitaduki
public class Guest {
    private String nombre;
    private String celular;
    private String genero; // solo femenino o masculino , no más
    private String fechaNacimiento;
    private String direccion;
    private boolean aceptaTerminos;

    public Guest(String nombre, String celular, String genero, String fechaNacimiento, String direccion, boolean aceptaTerminos) {
        this.nombre = nombre;
        this.celular = celular;
        this.genero = genero;
        this.fechaNacimiento = fechaNacimiento;
        this.direccion = direccion;
        this.aceptaTerminos = aceptaTerminos;
    }

    @Override
    public String toString() {
        return nombre + ";" + celular + ";" + genero + ";" + fechaNacimiento + ";" + direccion + ";" + (aceptaTerminos ? "1" : "0");
    }
}

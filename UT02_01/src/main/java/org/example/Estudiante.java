package org.example;

public class Estudiante{
    private String nombre;
    private Dni dni;
    private int edad;
    private double notaMedia;
    private boolean matriculado;

    public Estudiante(String nombre, int edad, double nota, boolean matriculado) {
        setNombre(nombre);
        setEdad(edad);
        setNotaMedia(nota);
        setMatriculado(matriculado);
    }


    //=====SETTERS 'n GETTERS=====
    //----Nombre----
    public String getNombre(){return this.nombre;}

    public void setNombre(String nombre){

        if(nombre == null || nombre.isEmpty()){
            throw new RuntimeException("Nombre no válido");

        } else {
            this.nombre = nombre.trim();
        }
    }

    //----DNI----
    public String getDni(){ return dni.toString();}

    public void setDni(Character l, long n){

        if(l == null || l == ' '){
            throw new RuntimeException("DNI no válido");

        } else {
            this.dni = new Dni(l, n);
        }
    }

    //----EDAD----
    public int getEdad(){return this.edad;}

    public void setEdad(int edad){

        if(edad < 0 || edad > 150){
            throw new RuntimeException("Edad no válida");
        } else {
            this.edad = edad;
        }
    }

    //----NOTAMEDIA----
    public double getNotaMedia(){return this.notaMedia;}

    public void setNotaMedia(double nota){
        if (nota < 0.0 || nota > 10.0) {
            throw new RuntimeException("Nota no válida");
        } else {
            this.notaMedia = nota;
        }
    }


    //----MATRICULADO----
    public boolean getMatriculado(){return this.matriculado;}

    public void setMatriculado(boolean matricualdo){
        this.matriculado = matricualdo;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre +
                "\nDNI: " + dni +
                "\nEdad: " + edad +
                "\nNota Media: " + notaMedia +
                "\nMatriculado: " + matriculado + "\n";
    }
}
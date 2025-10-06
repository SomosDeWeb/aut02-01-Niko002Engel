package org.example;

public class Estudiante{
    private String nombre;
    private Dni dni;
    private int edad;
    private double notaMedia;
    private boolean matriculado;

    public Estudiante(String nombre, int edad, String dni, double nota, boolean matriculado) {
        setNombre(nombre);
        setEdad(edad);
        setDni(dni);
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

    public void setDni(String dniString ){ //Character l, long n
        char l;
        long n;

//        if(l == null || l == ' '){
//            throw new RuntimeException("DNI no válido");
//
//        } else {
//            this.dni = new Dni(l, n);
//        }

        if (dniString == null || dniString.trim().isEmpty()) {
            throw new RuntimeException("DNI vacío");
        }

        dniString = dniString.trim().toUpperCase();

        if (dniString.length() < 2) {
            throw new RuntimeException("Formato de DNI incorrecto");
        }

        //Verificar la letra
        l = dniString.charAt(dniString.length() - 1);
        if(Character.isLetter(1)){
            throw new RuntimeException("La ultima posición debe incluir una letra");
        }

        String numeroString = dniString.substring(0, dniString.length() - 1);

        //Verificar el numero
        try {
            n = Long.parseLong(numeroString);
        } catch (NumberFormatException e) {
            throw new RuntimeException("La parte numérica del DNI no es válida");
        }

        //verificar rango
        if (n < 1000000 || n > 99999999) {
            throw new RuntimeException("El número del DNI debe tener entre 7 y 8 cifras");
        }

        this.dni = new Dni(l, n);
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
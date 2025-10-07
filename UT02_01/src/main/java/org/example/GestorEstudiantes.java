package org.example;
import java.util.ArrayList;
import java.util.Scanner;

//tener en cuanta: nombres vacios, nombres con solo letras, edades negativas, opciones de menu fuera de rango, nombres repetidos?, notas negativas,

public class GestorEstudiantes {
    private static ArrayList<Estudiante> listaEstudiantes = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;

        try {
            do {
                System.out.println("===== GESTOR DE ESTUDIANTES =====");
                System.out.println("1. Añadir estudiante");
                System.out.println("2. Listar estudiantes");
                System.out.println("3. Buscar estudiante por DNI");
                System.out.println("4. Calcular nota media general");
                System.out.println("5. Mostrar mejor estudiante");
                System.out.println("6. Salir");
                System.out.print("Seleccione una opción: ");
                opcion = leerNumEntero();

                //if(opcion >= 1 && opcion <= 6)

                switch (opcion) {
                    case 1:
                        anadirEstudiante();
                        break;
                    case 2:
                      listarEstudiantes();
                        break;
                    case 3:
                        buscarEstudiante();
                        break;
                    case 4:
                        calcularMedia();
                        break;
                    case 5:
                        mostrarMejorEstudiante();
                        break;
                    case 6:
                        System.out.println("Saliendo del programa...");
                        break;
                    default:
                        System.out.println("Opción no válida. Intente nuevamente.");
                }
                System.out.println();

            } while (opcion != 6);

        }catch(Exception e){ System.out.println(e.getMessage());}
    }

    private static int leerNumEntero() {
        int num;

        while (true) {
            try{
                num = Integer.parseInt(sc.nextLine());
                return num;
            }catch (NumberFormatException e){
                System.out.println("Introduce un número entero: ");
            }
        }
    }

    private static double leerNumDouble(){
        double num;

        while (true){
            try{
                num = Double.parseDouble(sc.nextLine());
                return num;

            }catch (NumberFormatException e) {
                System.out.println("Introduce un número decimal: ");
            }
        }
    }

    private static void anadirEstudiante(){
        String nombre = pedirNombre();
        String dni = pedirDni();
        int edad = pedirEdad();
        double nota = pedirNota();
        boolean matriculado = pedirMatriculado();

        for (Estudiante e : listaEstudiantes) {
            if (e.getDni().toUpperCase().equals(dni)) {
                System.out.println("Ya existe un estudiante con este DNI");
                dni = pedirDni();
            }
        }

        Estudiante estudiante = new Estudiante(nombre, edad, dni, nota, matriculado);
        listaEstudiantes.add(estudiante);
        System.out.println("Estudiante añadido correctamente.");

    }

    private static String pedirNombre(){
        String nombre = null;
        boolean isValid = false;

        do {
            try {
                System.out.print("Introduce nombre:");
                nombre = sc.nextLine().trim();

                if (nombre.isEmpty()) {
                    //System.out.println("El nombre no puede estar vacío. Intente de nuevo.");
                    throw new RuntimeException("El nombre no puede estar vacío. Intente de nuevo.");

                } else if (!nombre.matches("[a-zA-Z]+")) {
                    //System.out.println("El nombre solo puede contener letras. Intente de nuevo.");
                    throw new RuntimeException("El nombre solo puede contener letras. Intente de nuevo.");

                } else{ isValid = true;}


            }catch (RuntimeException e){ System.out.println(e.getMessage()); }

        } while (!isValid);

        return nombre;
    }

    private static int pedirEdad(){
        int edad = -1;

        do {
            try{
                System.out.print("Introduce Edad:");
                edad = leerNumEntero();

                if(edad < 0 || edad > 150){
                    throw new RuntimeException("Edad no válida");
                }

            }catch (RuntimeException e){ System.out.println(e.getMessage());}

        } while(edad < 0 || edad > 150);

        return edad;
    }

    private static double pedirNota(){
        double nota = -1.0;

        do {
            try{
                System.out.print("Introduce Nota:");
                nota = leerNumDouble();

                if(nota < 0.0 || nota > 10.0){
                    throw new RuntimeException("Nota no válida");
                }

            }catch (RuntimeException e){ System.out.println(e.getMessage());}

        } while(nota < 0.0 || nota > 10.0);

        return nota;
    }

    private static String pedirDni(){
        String dniString = null;
        boolean isValid = false;
        char letraDni;
        long numeroDni;

        do{
            try{
                System.out.print("Introduce Dni:");
                dniString = sc.nextLine().trim();

                if (dniString == null || dniString.trim().isEmpty()) {
                    throw new RuntimeException("DNI vacío");
                }

                dniString = dniString.trim().toUpperCase();

                if (dniString.length() < 2) {
                    throw new RuntimeException("Formato de DNI incorrecto");
                }

                //Verificar la letra
                letraDni = dniString.charAt(dniString.length() - 1);
                if(Character.isLetter(1)){
                    throw new RuntimeException("La ultima posición debe incluir una letra");
                }

                String numeroString = dniString.substring(0, dniString.length() - 1);

                //Verificar el numero
                try {
                    numeroDni = Long.parseLong(numeroString);
                } catch (NumberFormatException e) {
                    throw new RuntimeException("La parte numérica del DNI no es válida");
                }

                //verificar rango
                if (numeroDni < 1000000 || numeroDni > 99999999) {
                    throw new RuntimeException("El número del DNI debe tener entre 7 y 8 cifras");
                }

                Character letraCalculada = Dni.letraValida(numeroDni);

                if ((letraDni = Character.toUpperCase(letraDni)) != letraCalculada){
                    throw new RuntimeException("La letra del DNI es incorrecta. Debería ser " + letraCalculada);
                }

//                for (Estudiante e : listaEstudiantes) {
//                    if (e.getDni().toUpperCase().equals(dniString)) {
//                        throw new RuntimeException("Ya existe un estudiante con este DNI");
//                    }
//                }

                isValid = true;

            }catch (RuntimeException e){ System.out.println(e.getMessage());}

        }while(!isValid);

        return dniString;
    }

    private static boolean pedirMatriculado() {
        boolean matriculado = false;
        String input;

        while (true) {
            System.out.print("¿Está matriculado? (SI/NO): ");
            input = sc.nextLine().trim().toUpperCase();

            if (input.isEmpty()) {
                System.out.println("Debe ingresar SI o NO. Intente de nuevo.");
            } else if (input.equals("SI")) {
                matriculado = true;
                break;
            } else if (input.equals("NO")) {
                matriculado = false;
                break;
            } else {
                System.out.println("Entrada no válida. Solo puede ser SI o NO. Intente de nuevo.");
            }
        }

        return matriculado;
    }

    private static void listarEstudiantes() {
        if (listaEstudiantes.isEmpty()) {
            System.out.println("No hay estudiantes registrados.");
        } else {
            System.out.println("\n=== Lista De Estudiantes ===\n");
            for (Estudiante e : listaEstudiantes) {
                System.out.println(e);
            }
        }
    }

    private static void calcularMedia() {
        double suma = 0;
        double media = 0;

        if (listaEstudiantes.isEmpty()) {
            System.out.println("No hay estudiantes registrados.");
            return;
        }


        for (Estudiante e : listaEstudiantes) {
            suma += e.getNotaMedia();
        }

        media = suma / listaEstudiantes.size();
        System.out.println("\n==== Nota Media General ====");
        System.out.println("\n La media de las notas es: " + media + "\n");
    }

    private static void mostrarMejorEstudiante(){
        ArrayList<Estudiante> mejoresEstudiantes = new ArrayList<>();
        Estudiante mejorEstudiante;
        double mejorNota = listaEstudiantes.getFirst().getNotaMedia();

        if (listaEstudiantes.isEmpty()) {
            System.out.println("No hay estudiantes registrados.");
            return;
        }


        mejoresEstudiantes.add(listaEstudiantes.getFirst());

        for (int i = 1; i < listaEstudiantes.size(); i++) {
            Estudiante e = listaEstudiantes.get(i);
            double nota = e.getNotaMedia();

            if (nota > mejorNota) {

                mejorNota = nota;
                mejoresEstudiantes.clear();
                mejoresEstudiantes.add(e);

            } else if (nota == mejorNota) {
                mejoresEstudiantes.add(e);
            }
        }

        System.out.println("\n==== Mejor Nota ====");
        System.out.println("El estudiante con mejor nota es: ");
        for (Estudiante e : mejoresEstudiantes) {
            System.out.println(e);
        }
    }

    private static void buscarEstudiante(){
        System.out.println("\n==== Buscar Estudiante ====");
        System.out.print("Ingrese el DNI del estudiante a buscar: ");
        String dniString = pedirDni();
        boolean encontrado = false;

        for(Estudiante e : listaEstudiantes){
            if(e.getDni().toUpperCase().equals(dniString)){
                System.out.println("Estudiante encontrado: ");
                System.out.println(e);
                encontrado = true;
                break;
            }
        }

        if (!encontrado){
            System.out.println("No se encontró un estudiante con ese DNI");
        }
    }
}



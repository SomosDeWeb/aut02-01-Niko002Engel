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
        }catch(Exception e){}
    }

    private static int leerNumEntero() {
        int num;

        while (true) {
            try{
                num = Integer.parseInt(sc.nextLine());
                return num;
            }catch (NumberFormatException e){
                System.out.println("Error. Introduce un número entero: ");
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
                System.out.println("Error. Introduce un número decimal: ");
            }
        }
    }

    private static void anadirEstudiante(){
            System.out.print("Introduce nombre:");
    }
}



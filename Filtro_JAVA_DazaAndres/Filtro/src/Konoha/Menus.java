package Konoha;

import java.util.InputMismatchException;
import java.util.Scanner;
/**
 *
 * @author camper
 */
public class Menus {
    Scanner scanner = new Scanner(System.in);
    public int leerOpcion(){
        int option = 0;
        while (true) {
            try {
                option = scanner.nextInt(); 
                scanner.nextLine(); // Limpiar el buffer del scanner
                break; // Salir del bucle si la entrada es válida
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Por favor, ingrese un número entero.");
                scanner.nextLine(); // Limpiar el buffer del scanner
            }
        }
        return option;
    }
    public void MenuMision(){
        MisionDAO mision = new MisionDAO();
        int option = 0;
        do {
            System.out.println("-----------------------------------------");
            System.out.println("-      Menu de Misiones                 -");
            System.out.println("-                                       -");
            System.out.println("- 1.Misiones en proceso por ninja       -");
            System.out.println("- 2.Misiones completadas por ninja      -");
            System.out.println("- 3.Asignar mision a ninja              -");
            System.out.println("- 4.Marcar mision como completada       -");
            System.out.println("- 5.ver todas las misiones completadas  -");
            System.out.println("- 6.Insertar mision                     -");
            System.out.println("- 7.ver misiones                        -");
            System.out.println("- 8.Actualizar misiones                 -");            
            System.out.println("- 9.Salir                               -");
            System.out.println("-                                       -");
            System.out.println("-----------------------------------------");
            
            System.out.print("--> ");
            option = leerOpcion(); 
            
            switch (option) {
                case 1 -> mision.VerMisionesPNinja();
                case 2 -> mision.VerMisionesFNinja();
                case 3 -> mision.AsignarMisionNinja();
                case 4 -> mision.CompletarMision();
                case 5 -> mision.ObtenerMisionesCompletadas();
                case 6 -> mision.insertarMision();
                case 7 -> mision.ObtenerMisionPorId();
                case 8 -> mision.actualizarMision();
                case 9 -> System.out.println("Saliendo del programa...");
                default -> System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
            }            
        } while (option != 6);    
    }
    
    public void MenuHabilidades(){
        HabilidadDAO habil = new HabilidadDAO();
        int option = 0;
        do {
            System.out.println("-----------------------------------------");
            System.out.println("-      Menu de Habilidades              -");
            System.out.println("-                                       -");
            System.out.println("- 1.Insertar actividad                  -");
            System.out.println("- 2.Ver habilidades                     -");
            System.out.println("- 3.Actualizar habilidades              -");
            System.out.println("- 4.ver habilidades por ninja           -");            
            System.out.println("- 5.Salir                               -");
            System.out.println("-                                       -");
            System.out.println("-----------------------------------------");
            
            System.out.print("--> ");
            option = leerOpcion(); 
            
            switch (option) {
                case 1 -> habil.insertarHabilidad();
                case 2 -> habil.ObtenerHabilidadPorId();
                case 3 -> habil.actualizarHabilidad();
                case 4 -> habil.ninjasHabilidades();
                case 5 -> System.out.println("Saliendo del programa...");
                default -> System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
            }            
        } while (option != 5);    
    }

    
    public void MenuNinja(){
        int option = 0;
        NinjaDAO ninja = new NinjaDAO();
        do {
            System.out.println("-----------------------------------------");
            System.out.println("-      Menu de Ninjas                   -");
            System.out.println("-                                       -");
            System.out.println("- 1.insertar Ninja                      -");
            System.out.println("- 2.ver Ninjas                          -");
            System.out.println("- 3.Actualizar Ninjas                   -");            
            System.out.println("- 4.Salir                               -");
            System.out.println("-                                       -");
            System.out.println("-----------------------------------------");
            
            System.out.print("--> ");
            option = leerOpcion(); 
            
            switch (option) {
                case 1 -> ninja.insertarNinja();
                case 2 -> ninja.obtenerNinjaporId();
                case 3 -> ninja.actualizarNinja();
                case 4 -> System.out.println("Saliendo del programa...");
                default -> System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
            }            
        } while (option != 4);    
    }

}
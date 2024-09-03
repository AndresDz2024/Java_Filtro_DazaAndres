package Konoha;

import java.util.Scanner;
import java.util.InputMismatchException;
/**
 *
 * @author camper
 */
public class Main {
    Scanner scanner = new Scanner(System.in);
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Menus menu = new Menus();
        Conexion con = new Conexion();
        int option = 0;
        do {
            System.out.println("------------------------------");
            System.out.println("-       Menu principal       -");
            System.out.println("-                            -");
            System.out.println("- 1.Menu Ninja               -");
            System.out.println("- 2.Menu Misiones            -");
            System.out.println("- 3.menu Habilidades         -");           
            System.out.println("- 4.Salir                    -");
            System.out.println("-                            -");
            System.out.println("------------------------------");

            System.out.print("--> ");
            option = menu.leerOpcion();
            switch(option) {
                case 1 -> menu.MenuNinja();
                case 2 -> menu.MenuMision();
                case 3 -> menu.MenuHabilidades();
                case 4 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
            }            
        } while(option != 4);
    }            
}

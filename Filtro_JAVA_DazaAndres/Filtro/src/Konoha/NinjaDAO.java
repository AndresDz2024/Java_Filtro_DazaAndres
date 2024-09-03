/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Konoha;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.sql.ResultSet;
import java.util.Scanner;

/**
 *
 * @author camper
 */
public class NinjaDAO {
    private Conexion con = new Conexion();
    Scanner scanner = new Scanner(System.in);

    public void insertarNinja(){
        System.out.println("Ingrese el nombre del Ninja que desea registrar: ");
        String nombre = scanner.nextLine();
        if(nombre.isEmpty()){
            System.out.println("El nombre no puede estar vacìo");
            return;
        }
        
        System.out.println("ingrese el rango del ninja");
        String rango = scanner.nextLine();
        if(rango.isEmpty()){
            System.out.println("El rango no puede estar vacìo");
            return;
        }
        
        System.out.println("Ingrese la aldea del ninja");
        String aldea = scanner.nextLine();
        if(aldea.isEmpty()){
            System.out.println("La aldea no puede estar vacìa");
            return;
        }
        
        String sql = "insert into Ninja (nombre, rango, aldea) values (?, ?, ?)";
        try (Connection conn = con.establecerConexion(); PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, nombre);
            stmt.setString(2, rango);
            stmt.setString(3, aldea);
            stmt.executeUpdate();
            System.out.println("Ninja insertado con exito :)");
        }catch(SQLException e){
            System.out.println("Error en esa vuelta: " + e.getMessage());
        }   
    }
    
    public void actualizarNinja() {
        Scanner scanner = new Scanner(System.in);

        int id = 0;
        while (true) {
            try {
                System.out.println("Ingrese el ID del ninja a actualizar:");
                id = scanner.nextInt();
                scanner.nextLine(); // Consumir la nueva línea
                if (id <= 0) {
                    throw new IllegalArgumentException("El ID del ninja debe ser un número positivo.");
                }
                break;
            } catch (InputMismatchException e) {
                System.err.println("Error: Debe ingresar un número entero para el ID del ninja.");
                scanner.next(); // Limpiar la entrada incorrecta
            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage());
            }
        }

        System.out.println("Ingrese el nuevo nombre del Ninja:");
        String nombre = scanner.nextLine();
        if (nombre.isEmpty()) {
            System.err.println("Error: El nombre no puede estar vacío.");
            return;
        }

        System.out.println("Ingrese el nuevo rango del Ninja:");
        String rango = scanner.nextLine();
        if (rango.isEmpty()) {
            System.err.println("Error: el rango no puede estar vacìo.");
            return;
        }

        System.out.println("Ingrese la nueva aldea del Ninja:");
        String aldea = scanner.nextLine();
        if (aldea.isEmpty()) {
            System.err.println("Error: la aldea no puede estar vacía.");
            return;
        }

        String sql = "UPDATE Ninja SET nombre = ?, rango = ?, aldea = ? WHERE id = ?";
        try (Connection conn = con.establecerConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            stmt.setString(2, rango);
            stmt.setString(3, aldea);
            stmt.setInt(4, id);
            stmt.executeUpdate();
            System.out.println("Ninja actualizado con éxito.");
        } catch (SQLException e) {
            System.err.println("Error al actualizar Ninja: " + e.getMessage());
        }
    }
    
    public void obtenerNinjaporId() {
        System.out.print("Ingrese el ID del Ninja: ");
        int id = scanner.nextInt();  // Leer el ID ingresado por el usuario
        scanner.nextLine(); // Consumir la nueva línea

        String sql = "SELECT * FROM Ninja WHERE id = ?";
        try (Connection conn = con.establecerConexion(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println("+-------------------------+--------------------------------+");
                    System.out.println("| Campo                   | Valor                          |");
                    System.out.println("+-------------------------+--------------------------------+");
                    System.out.printf("| %-23s | %-30s |\n", "ID", rs.getInt("id"));
                    System.out.printf("| %-23s | %-30s |\n", "Nombre", rs.getString("nombre"));
                    System.out.printf("| %-23s | %-30s |\n", "Identificación", rs.getString("rango"));
                    System.out.printf("| %-23s | %-30s |\n", "Teléfono", rs.getString("aldea"));
                    System.out.println("+-------------------------+--------------------------------+");
                } else {
                    System.out.println("No se encontró el ninja con ID: " + id);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener ninja por ID: " + e.getMessage());
        }
    }
}

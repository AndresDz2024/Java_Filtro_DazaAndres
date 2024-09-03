/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Konoha;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author camper
 */
public class HabilidadDAO {
    private Conexion con = new Conexion();
    Scanner scanner = new Scanner(System.in);
    public void insertarHabilidad(){
        System.out.println("Ingrese el id del ninja que usa la habilidad: ");
        String id_ninja = scanner.nextLine();
        if(id_ninja.isEmpty()){
            System.out.println("el id_ninja del ninja no puede estar vacìa");
            return;
        }
        
        System.out.println("ingrese el nombre de la habilidad");
        String nombre = scanner.nextLine();
        if(nombre.isEmpty()){
            System.out.println("El nombre no puede estar vacìo");
            return;
        }
        
        System.out.println("Ingrese la descripcion de la habilidad");
        String descripcion = scanner.nextLine();
        if(descripcion.isEmpty()){
            System.out.println("La descripcion no puede estar vacìa");
            return;
        }
        
        String sql = "insert into Habilidad (id_ninja, nombre, descripcion) values (?, ?, ?)";
        try (Connection conn = con.establecerConexion(); PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, id_ninja);
            stmt.setString(2, nombre);
            stmt.setString(3, descripcion);
            stmt.executeUpdate();
            System.out.println("habilidad insertado con exito :)");
        }catch(SQLException e){
            System.out.println("Error en esa vuelta: " + e.getMessage());
        }        
    }
    
    public void actualizarHabilidad() {
        int id = 0;
        while (true) {
            try {
                System.out.println("Ingrese el ID de la habilidad a actualizar:");
                id = scanner.nextInt();
                scanner.nextLine(); // Consumir la nueva línea
                if (id <= 0) {
                    throw new IllegalArgumentException("El ID de la habilidad debe ser un número positivo.");
                }
                break;
            } catch (InputMismatchException e) {
                System.err.println("Error: Debe ingresar un número entero para el ID de la habilidad.");
                scanner.next(); // Limpiar la entrada incorrecta
            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage());
            }
        }
        
        
        System.out.println("Ingrese el nuevo id del ninja que tiene esta habilidad:");
        String id_ninja = scanner.nextLine();
        if (id_ninja.isEmpty()) {
            System.err.println("Error: el id_ninja no puede estar vacío.");
            return;
        }

        System.out.println("Ingrese el nombre de la habilidad:");
        String nombre = scanner.nextLine();
        if (nombre.isEmpty()) {
            System.err.println("Error: el nombre no puede estar vacía.");
            return;
        }

        System.out.println("Ingrese la nueva descripciòn de la mision:");
        String descripcion = scanner.nextLine();
        if (descripcion.isEmpty()) {
            System.err.println("Error: la descripcion no puede estar vacìa.");
            return;
        }

        String sql = "UPDATE Habilidad SET id_ninja = ?, nombre = ?, descripcion = ? WHERE id = ?";
        try (Connection conn = con.establecerConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id_ninja);
            stmt.setString(2, nombre);
            stmt.setString(3, descripcion);
            stmt.setInt(4, id);
            stmt.executeUpdate();
            System.out.println("Habilidad actualizada con éxito.");
        } catch (SQLException e) {
            System.err.println("Error al actualizar Habilidad: " + e.getMessage());
        }
    }
    
    public void ObtenerHabilidadPorId () {
        System.out.print("Ingrese el ID de la Habilidad: ");
        int id = scanner.nextInt();  // Leer el ID ingresado por el usuario
        scanner.nextLine(); // Consumir la nueva línea

        String sql = "SELECT * FROM Habilidad WHERE id = ?";
        try (Connection conn = con.establecerConexion(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println("+-------------------------+--------------------------------+");
                    System.out.println("| Campo                   | Valor                          |");
                    System.out.println("+-------------------------+--------------------------------+");
                    System.out.printf("| %-23s | %-30s |\n", "ID", rs.getInt("id"));
                    System.out.printf("| %-23s | %-30s |\n", "ID del ninja", rs.getString("id_ninja"));
                    System.out.printf("| %-23s | %-30s |\n", "nombre", rs.getString("nombre"));
                    System.out.printf("| %-23s | %-30s |\n", "descripcion", rs.getString("descripcion"));
                    System.out.println("+-------------------------+--------------------------------+");
                } else {
                    System.out.println("No se encontró la habilidad con ID: " + id);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener Habilidad por ID: " + e.getMessage());
        }
    }    

    public void ninjasHabilidades () {
        String sql = "SELECT * FROM Ninja n inner join Habilidad h on n.id = h.id_ninja";
        try (Connection conn = con.establecerConexion(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println("+-------------------------+--------------------------------+");
                    System.out.println("| Campo                   | Valor                          |");
                    System.out.println("+-------------------------+--------------------------------+");
                    System.out.printf("| %-23s | %-30s |\n", "ID", rs.getInt("n.id"));
                    System.out.printf("| %-23s | %-30s |\n", "ID", rs.getString("n.nombre"));
                    System.out.printf("| %-23s | %-30s |\n", "ID", rs.getInt("h.id"));
                    System.out.printf("| %-23s | %-30s |\n", "ID del ninja", rs.getString("h.id_ninja"));
                    System.out.printf("| %-23s | %-30s |\n", "nombre", rs.getString("h.nombre"));
                    System.out.printf("| %-23s | %-30s |\n", "descripcion", rs.getString("h.descripcion"));
                    System.out.println("+-------------------------+--------------------------------+");
                } else {
                    System.out.println("No se encontraron ninjas");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener Habilidad por ID: " + e.getMessage());
        }
    }    
    
}

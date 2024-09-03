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
import java.sql.Date;

/**
 *
 * @author camper
 */
public class MisionDAO {
    private Conexion con = new Conexion();
    Scanner scanner = new Scanner(System.in);
    public void insertarMision(){
        System.out.println("Ingrese la descripcion de la mision que desea registrar: ");
        String descripcion = scanner.nextLine();
        if(descripcion.isEmpty()){
            System.out.println("la descripcion no puede estar vacìa");
            return;
        }
        
        System.out.println("ingrese el rango de la mision");
        String rango = scanner.nextLine();
        if(rango.isEmpty()){
            System.out.println("El rango no puede estar vacìo");
            return;
        }
        
        System.out.println("Ingrese la recompensa de la Mision");
        String recompensa = scanner.nextLine();
        if(recompensa.isEmpty()){
            System.out.println("La recompensa no puede estar vacìa");
            return;
        }
        
        String sql = "insert into Mision (descripcion, rango, recompensa) values (?, ?, ?)";
        try (Connection conn = con.establecerConexion(); PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, descripcion);
            stmt.setString(2, rango);
            stmt.setString(3, recompensa);
            stmt.executeUpdate();
            System.out.println("Ninja insertado con exito :)");
        }catch(SQLException e){
            System.out.println("Error en esa vuelta: " + e.getMessage());
        }        
    }
    
    public void actualizarMision() {
        int id = 0;
        while (true) {
            try {
                System.out.println("Ingrese el ID de la mision a actualizar:");
                id = scanner.nextInt();
                scanner.nextLine(); // Consumir la nueva línea
                if (id <= 0) {
                    throw new IllegalArgumentException("El ID de la mision debe ser un número positivo.");
                }
                break;
            } catch (InputMismatchException e) {
                System.err.println("Error: Debe ingresar un número entero para el ID de la mision.");
                scanner.next(); // Limpiar la entrada incorrecta
            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage());
            }
        }

        System.out.println("Ingrese la descripcion de la mision:");
        String descripcion = scanner.nextLine();
        if (descripcion.isEmpty()) {
            System.err.println("Error: la descripciòn no puede estar vacía.");
            return;
        }

        System.out.println("Ingrese el nuevo rango de la mision:");
        String rango = scanner.nextLine();
        if (rango.isEmpty()) {
            System.err.println("Error: el rango no puede estar vacìo.");
            return;
        }

        System.out.println("Ingrese la nueva recompensa de la mision:");
        String recompensa = scanner.nextLine();
        if (recompensa.isEmpty()) {
            System.err.println("Error: la recompensa no puede estar vacía.");
            return;
        }

        String sql = "UPDATE Mision SET descripcion = ?, rango = ?, recompensa = ? WHERE id = ?";
        try (Connection conn = con.establecerConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, descripcion);
            stmt.setString(2, rango);
            stmt.setString(3, recompensa);
            stmt.setInt(4, id);
            stmt.executeUpdate();
            System.out.println("Mision actualizada con éxito.");
        } catch (SQLException e) {
            System.err.println("Error al actualizar Mision: " + e.getMessage());
        }
    }
    
    public void ObtenerMisionPorId() {
        System.out.print("Ingrese el ID de la mision: ");
        int id = scanner.nextInt();  // Leer el ID ingresado por el usuario
        scanner.nextLine(); // Consumir la nueva línea

        String sql = "SELECT * FROM Mision WHERE id = ?";
        try (Connection conn = con.establecerConexion(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println("+-------------------------+--------------------------------+");
                    System.out.println("| Campo                   | Valor                          |");
                    System.out.println("+-------------------------+--------------------------------+");
                    System.out.printf("| %-23s | %-30s |\n", "ID", rs.getInt("id"));
                    System.out.printf("| %-23s | %-30s |\n", "Nombre", rs.getString("descripcion"));
                    System.out.printf("| %-23s | %-30s |\n", "Identificación", rs.getString("rango"));
                    System.out.printf("| %-23s | %-30s |\n", "Teléfono", rs.getString("recompensa"));
                    System.out.println("+-------------------------+--------------------------------+");
                } else {
                    System.out.println("No se encontró la mision con ID: " + id);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener mision por ID: " + e.getMessage());
        }
    }    

    public void ObtenerMisionesCompletadas() {
        System.out.print("Ingrese el ID de la mision: ");
        int id = scanner.nextInt();  // Leer el ID ingresado por el usuario
        scanner.nextLine(); // Consumir la nueva línea

        String sql = "select m.id, m.descripcion, m.rango, m.recompensa, mn.fecha_fin from Mision m inner join MisionNinja mn on m.id = mn.id_mision WHERE m.id = ? and mn.fecha_fin is not null";
        try (Connection conn = con.establecerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println("+-------------------------+--------------------------------+");
                    System.out.println("| Campo                   | Valor                          |");
                    System.out.println("+-------------------------+--------------------------------+");
                    System.out.printf("| %-23s | %-30s |\n", "ID", rs.getInt("id"));
                    System.out.printf("| %-23s | %-30s |\n", "Nombre", rs.getString("descripcion"));
                    System.out.printf("| %-23s | %-30s |\n", "Identificación", rs.getString("rango"));
                    System.out.printf("| %-23s | %-30s |\n", "Teléfono", rs.getString("recompensa"));
                    System.out.printf("| %-23s | %-30s |\n", "fecha final", rs.getString("fecha_final"));
                    System.out.println("+-------------------------+--------------------------------+");
                } else {
                    System.out.println("No se encontró la mision con ID: " + id);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener mision por ID: " + e.getMessage());
        }
    }        
    
    public void VerMisionesPNinja() {
        System.out.print("Ingrese el ID del ninja: ");
        int id = scanner.nextInt();  // Leer el ID ingresado por el usuario
        scanner.nextLine(); // Consumir la nueva línea

        String sql = "select m.id, m.descripcion, m.rango, m.recompensa, mn.fecha_inicio, n.id from Mision m inner join MisionNinja mn on m.id = mn.id_mision inner join Ninja n on n.id = mn.id_ninja WHERE n.id = ? and mn.fecha_inicio is not null and mn.fecha_final is null";
        try (Connection conn = con.establecerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println("+-------------------------+--------------------------------+");
                    System.out.println("| Campo                   | Valor                          |");
                    System.out.println("+-------------------------+--------------------------------+");
                    System.out.printf("| %-23s | %-30s |\n", "ID mision", rs.getInt("m.id"));
                    System.out.printf("| %-23s | %-30s |\n", "Nombre", rs.getString("m.descripcion"));
                    System.out.printf("| %-23s | %-30s |\n", "Identificación", rs.getString("m.rango"));
                    System.out.printf("| %-23s | %-30s |\n", "Teléfono", rs.getString("m.recompensa"));
                    System.out.printf("| %-23s | %-30s |\n", "fecha inicio", rs.getString("mn.fecha_inicial"));
                    System.out.printf("| %-23s | %-30s |\n", "ID ninja", rs.getInt("n.id"));                    
                    System.out.println("+-------------------------+--------------------------------+");
                } else {
                    System.out.println("No se encontró la mision con ID: " + id);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener mision por ID: " + e.getMessage());
        }
    }        

    public void VerMisionesFNinja() {
        System.out.print("Ingrese el ID del ninja: ");
        int id = scanner.nextInt();  // Leer el ID ingresado por el usuario
        scanner.nextLine(); // Consumir la nueva línea

        String sql = "select m.id, m.descripcion, m.rango, m.recompensa, mn.fecha_inicio, n.id from Mision m inner join MisionNinja mn on m.id = mn.id_mision inner join Ninja n on n.id = mn.id_ninja WHERE n.id = ? and mn.fecha_final is not null";
        try (Connection conn = con.establecerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println("+-------------------------+--------------------------------+");
                    System.out.println("| Campo                   | Valor                          |");
                    System.out.println("+-------------------------+--------------------------------+");
                    System.out.printf("| %-23s | %-30s |\n", "ID mision", rs.getInt("m.id"));
                    System.out.printf("| %-23s | %-30s |\n", "Nombre", rs.getString("m.descripcion"));
                    System.out.printf("| %-23s | %-30s |\n", "Identificación", rs.getString("m.rango"));
                    System.out.printf("| %-23s | %-30s |\n", "Teléfono", rs.getString("m.recompensa"));
                    System.out.printf("| %-23s | %-30s |\n", "fecha inicio", rs.getString("mn.fecha_final"));
                    System.out.printf("| %-23s | %-30s |\n", "ID ninja", rs.getInt("n.id"));                    
                    System.out.println("+-------------------------+--------------------------------+");
                } else {
                    System.out.println("No se encontró la mision con ID: " + id);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener mision por ID: " + e.getMessage());
        }
    }            
    
    public void AsignarMisionNinja(){
        int id = 0;
        while (true) {
            try {
                System.out.println("Ingrese el ID de la mision a la que quieres asignar un ninja:");
                id = scanner.nextInt();
                scanner.nextLine(); // Consumir la nueva línea
                if (id <= 0) {
                    throw new IllegalArgumentException("El ID de la mision debe ser un número positivo.");
                }
                break;
            } catch (InputMismatchException e) {
                System.err.println("Error: Debe ingresar un número entero para el ID de la mision.");
                scanner.next(); // Limpiar la entrada incorrecta
            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage());
            }
        }

        System.out.println("Ingrese el id del ninja encargado de la mision:");
        String id_ninja = scanner.nextLine();
        if (id_ninja.isEmpty()) {
            System.err.println("Error: el id_ninja no puede estar vacía.");
            return;
        }

        System.out.println("Ingrese la fecha de inicio para la mision:");
        Date fecha_inicio  = Date.valueOf(scanner.nextLine());


        String sql = "insert into MisionNinja (id_ninja, id_mision, fecha_inicio) values (?, ?, ?)";
        try (Connection conn = con.establecerConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id_ninja);
            stmt.setInt(2, id);
            stmt.setDate(3, fecha_inicio);
            stmt.executeUpdate();
            System.out.println("Mision asignada con exito");
        } catch (SQLException e) {
            System.err.println("Error al asignar Mision: " + e.getMessage());
        }
    }    
    
    public void CompletarMision(){
        int id = 0;
        while (true) {
            try {
                System.out.println("Ingrese el ID de la mision que quieres terminar:");
                id = scanner.nextInt();
                scanner.nextLine(); // Consumir la nueva línea
                if (id <= 0) {
                    throw new IllegalArgumentException("El ID de la mision debe ser un número positivo.");
                }
                break;
            } catch (InputMismatchException e) {
                System.err.println("Error: Debe ingresar un número entero para el ID de la mision.");
                scanner.next(); // Limpiar la entrada incorrecta
            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage());
            }
        }

        System.out.println("Ingrese la fecha de fin para la mision:");
        Date fecha_final  = Date.valueOf(scanner.nextLine());


        String sql = "Update MisionNinja SET fecha_final = ? where id_mision = ?";
        try (Connection conn = con.establecerConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(3, fecha_final);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Mision finalizada con exito");
        } catch (SQLException e) {
            System.err.println("Error al finalizar Mision: " + e.getMessage());
        }
    }    
    
    
}

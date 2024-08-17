package javaapplication1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class Conexion {
    String bd = "translate_prototype";
    String url = "jdbc:mysql://127.0.0.1:3306/";
    String user = "root";
    String password = "Pro@Tra%&DB234";
    String driver = "com.mysql.cj.jdbc.Driver";
    Connection cx = null;
    
    Statement stmt = null;
    ResultSet rs = null;
    int rs1 = 0;

    public Conexion (String bd){
        this.bd = bd;
    }
    
    public Connection conectar(){
        try{
            Class.forName(driver);
            cx = DriverManager.getConnection(url+bd, user, password); 
            stmt = cx.createStatement();
            System.out.println("Conexion establecida con exito"); 
        }
        catch (ClassNotFoundException ex) {
            System.out.println("Error: No se pudo encontrar la clase del driver JDBC. " + ex.getMessage());
        }
        catch(SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return cx;
    }

    public void crearUsuarios(){
        try {
            System.out.println("------------------------------------------------------");            
            System.out.println("Insercion de datos");
            System.out.println("------------------------------------------------------");

            rs1 = stmt.executeUpdate("INSERT INTO usuarios (nombre, apellido, contraseña)"
                                    + "VALUES ('Oscar', 'Profesor', 'miContraseñaSegura')");
            if (rs1 > 0) {
                System.out.println("Usuario Oscar creado exitosamente");
            }

            rs1 = stmt.executeUpdate("INSERT INTO usuarios (nombre, apellido, contraseña)"
                                    + "VALUES ('Santiago', 'Estudiante', 'pooeoerp545')");
            if (rs1 > 0) {
                System.out.println("Usuario Santiago creado exitosamente");
            }

            rs1 = stmt.executeUpdate("INSERT INTO usuarios (nombre, apellido, contraseña)"
                                    + "VALUES ('Osorio', 'Estudiante', '23a41s56e4f')");
            if (rs1 > 0) {
                System.out.println("Usuario Osorio creado exitosamente");
            }

            rs1 = stmt.executeUpdate("INSERT INTO usuarios (nombre, apellido, contraseña)"
                                    + "VALUES ('Kevin', 'Aprendiz Sena', 'asdfe')");
            if (rs1 > 0) {
                System.out.println("Usuario Kevin creado exitosamente");
            }

            rs1 = stmt.executeUpdate("INSERT INTO usuarios (nombre, apellido, contraseña)"
                                    + "VALUES ('OscarP', 'Instructor Sena', 'miContraseñaSegura')");
            if (rs1 > 0) {
                System.out.println("Usuario Oscar Instructor creado exitosamente");
            }
        } catch(SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
        }
    }
    
    public void leerUsuarios(){
        try {
            rs = stmt.executeQuery("SELECT * FROM usuarios");

            System.out.println(" ");            
            System.out.println("------------------------------------------------------");
            System.out.println("Tabla usuarios: ");         
            System.out.println("Id, Nombre, Apellido, Contrasena");
            System.out.println("------------------------------------------------------");

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String contraseña = rs.getString("contraseña");

                System.out.println(id + ", " + nombre + ", " + apellido + ", " + contraseña);
            }
        } catch(SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
        }
    }

    public void actualizarUsuario(){
        try {
            System.out.println(" ");
            System.out.println("------------------------------------------------------");            
            System.out.println("Actualizacion de datos");
            System.out.println("------------------------------------------------------");

            rs1 = stmt.executeUpdate("UPDATE usuarios SET apellido = 'Romero', contraseña = 'PassSecure' WHERE nombre = 'Santiago'");
            if (rs1 > 0) {
                System.out.println("Usuario Santiago actualizado exitosamente");
            }

            rs1 = stmt.executeUpdate("UPDATE usuarios SET contraseña = '8797a8sdf54' WHERE nombre = 'OscarP'");
            if (rs1 > 0) {
                System.out.println("Usuario OscarP Instructor actualizado exitosamente");
            }
        } catch(SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
        }
    }

    public void eliminarUsuarios(){
        try {
            System.out.println(" ");
            System.out.println("------------------------------------------------------");            
            System.out.println("Eliminacion de datos");
            System.out.println("------------------------------------------------------");

            rs1 = stmt.executeUpdate("DELETE FROM usuarios WHERE nombre = 'Oscar'");
            if (rs1 > 0) {
                System.out.println("Usuario Oscar eliminado exitosamente");
            }

            rs1 = stmt.executeUpdate("DELETE FROM usuarios WHERE nombre = 'Osorio'");
            if (rs1 > 0) {
                System.out.println("Usuario Osorio eliminado exitosamente");
            }
        } catch(SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
        }
    }

    public void limpiarTabla(){
        try {
            System.out.println(" ");
            System.out.println("------------------------------------------------------");            
            System.out.println("Limpiar la tabla");
            System.out.println("------------------------------------------------------");

            rs1 = stmt.executeUpdate("TRUNCATE TABLE usuarios");
            System.out.println("Tabla limpiada exitosamente");
        } catch(SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
        }
    }
    
    public void desconectar(){
        try{
            cx.close();
            System.out.println(" ");            
            System.out.println("------------------------------------------------------");            
            System.out.println("Conexion cerrada con exito");     
            System.out.println("------------------------------------------------------");            
        }
        catch(SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode()); 
        }
    }
    
    public static void main (String[] args){
        Conexion conexion = new Conexion("pruebas");
        conexion.conectar();
        
        conexion.crearUsuarios();
        conexion.leerUsuarios();
        conexion.actualizarUsuario();
        conexion.eliminarUsuarios();
        conexion.limpiarTabla();
        // Para verificar que si se haya eliminado la tabla
        conexion.leerUsuarios();  
        
        conexion.desconectar();
    }
}

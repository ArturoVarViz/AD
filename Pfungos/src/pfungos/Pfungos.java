/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pfungos;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author arturo
 */
public class Pfungos {
     private static Connection conn;

    public static void conexion() throws SQLException {
        String driver = "jdbc:postgresql:";
        String host = "//localhost:"; // tamen podria ser una ip como "192.168.1.14"
        String porto = "5432";
        String sid = "postgres";
        String usuario = "arturo";
        String password = "arturo";
        String url = driver + host + porto + "/" + sid;
        conn = DriverManager.getConnection(url, usuario, password);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
        conexion();
        Statement st = conn.createStatement();
        // Crear un stream de entrada para el archivo
        FileInputStream fis = new FileInputStream("detectados");

        // Crear un stream de entrada para el objeto
        ObjectInputStream ois = new ObjectInputStream(fis);

        // Leer el objeto del stream y hacer un casting para el tipo de objeto correcto
        Detectados detectados = null;
        while ((detectados = (Detectados) ois.readObject()) != null) {
            // Procesar la reserva aquí
            int codr = detectados.getCodarea();
            int codFungo = detectados.getCodfungo();
            int numero = detectados.getNumero();
            double superficie =detectados.getSuperficie();
            
              String sql = "UPDATE areas SET numerofungos=numerofungos + 1 WHERE coda=?";
              PreparedStatement pstmt = conn.prepareStatement(sql);
              pstmt.setInt(1, codr);
              pstmt.executeUpdate();
             
             
               System.out.println("codarea:"+codr+", codFungos:"+ codFungo+", numero:"+ numero+", superficie:"+superficie);
        
        }
        ois.close();
        fis.close();
        conn.close();
    }
    
}

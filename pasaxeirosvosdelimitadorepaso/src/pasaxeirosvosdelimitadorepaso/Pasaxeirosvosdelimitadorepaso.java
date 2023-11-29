/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pasaxeirosvosdelimitadorepaso;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author arturo
 */
public class Pasaxeirosvosdelimitadorepaso {





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

   public static void main(String[] args) throws IOException, SQLException {
    conexion();
    Statement st = conn.createStatement();

    // Crear un stream de entrada para el archivo
    BufferedReader reader = new BufferedReader(new FileReader("/home/arturo/NetBeansProjects/AD/pasaxeirosvosdelimitadorepaso/reservas.txt"));


    String line;
    while ((line = reader.readLine()) != null) {
        // Dividir la línea por el delimitador para obtener los campos
        String[] fields = line.split(",");  // Asume que el delimitador es una coma

        // Procesar la reserva aquí
        int codr = Integer.parseInt(fields[0]);
        String dni = fields[1];
        int idvooida = Integer.parseInt(fields[2]);
        int idvoovolta = Integer.parseInt(fields[3]);

        // Incrementar el valor de nreservas en la base de datos para el dni correspondiente
        String sql = "UPDATE PASAXEIROS SET nreservas = nreservas + 1 WHERE dni = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, dni);
        pstmt.executeUpdate();
        
            String queryIda = "SELECT * FROM voos WHERE voo = " + idvooida;
            ResultSet rsIda = st.executeQuery(queryIda);
            rsIda.next();
            int prezoIda = rsIda.getInt("prezo");
            System.out.println("ida" + prezoIda);

            String queryVolta = "SELECT * FROM voos WHERE voo = " + idvoovolta;
            ResultSet rsVolta = st.executeQuery(queryVolta);
            rsVolta.next();
            int prezoVolta = rsVolta.getInt("prezo");
            System.out.println("volta" + prezoVolta);

            String queryDni = "SELECT * FROM pasaxeiros WHERE dni = '" + dni + "'";
            ResultSet rsDni = st.executeQuery(queryDni);
            rsDni.next();
            String nome = rsDni.getString("nome");
            System.out.println(nome);
            
           int prezoreserva = prezoIda + prezoVolta;
            System.out.println(prezoreserva);
           String reservasFeitas = "INSERT INTO reservasfeitas (codr, dni, nome, prezoreserva) VALUES ( "+codr+",'" + dni + "', '" + nome + "', " + prezoreserva + ")";
           st.executeUpdate(reservasFeitas);
    }

    reader.close();
    conn.close();
}}

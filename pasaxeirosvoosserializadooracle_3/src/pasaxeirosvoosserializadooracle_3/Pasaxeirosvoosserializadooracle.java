package pasaxeirosvoosserializadooracle_3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Pasaxeirosvoosserializadooracle {
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

    public static void lerReservas() throws FileNotFoundException, IOException, ClassNotFoundException, SQLException {
        // Crear un stream de entrada para el archivo
        FileInputStream fis = new FileInputStream("reservas");

        // Crear un stream de entrada para el objeto
        ObjectInputStream ois = new ObjectInputStream(fis);

        // Leer el objeto del stream y hacer un casting para el tipo de objeto correcto
        Reserva reserva = (Reserva) ois.readObject();

        // Enquanto a reserva não for nula, continue lendo as reservas
       while (reserva != null) {
    // Procesar la reserva aquí
    System.out.println(reserva.toString());

    // Incrementar el valor de nreservas en la base de datos para el dni correspondiente
    String sql = "UPDATE PASAXEIROS SET nreservas = nreservas + 1 WHERE dni = ?";
    PreparedStatement pstmt = conn.prepareStatement(sql);
    pstmt.setString(1, reserva.getDni());
    pstmt.executeUpdate();

    // Leer la próxima reserva
    reserva = (Reserva) ois.readObject();

        // Cerrar los streams y la conexión a la base de datos
      
    }
         ois.close();
        fis.close();
        conn.close();
    }
    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException, SQLException {
        conexion();
        lerReservas();
    }
}

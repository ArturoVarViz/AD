package baserelacionala;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class BaserelacionalA {

    public static Connection conn;

    public static void conexion() throws SQLException {
        String driver = "jdbc:postgresql:";
        String host = "//localhost:"; // tamen podria ser una ip como "192.168.1.14"
        String porto = "5432";
        String sid = "postgres";
        String usuario = "postgres";
        String password = "postgres";
        String url = driver + host + porto + "/" + sid;
        conn = DriverManager.getConnection(url, usuario, password);
    }

    public static void insireProduto(String cod, String des, int prez, java.sql.Date data) throws SQLException {
        try {
            Statement st = conn.createStatement();
            String cadea = "insert into produtos values('" + cod + "','" + des + "'," + prez + ",'" + data + "')";
            st.executeUpdate(cadea);
        } catch (SQLException e) {
            if (e instanceof org.postgresql.util.PSQLException) {
                System.out.println("El producto con el código '" + cod + "' ya existe en la base de datos.");
            } else {
                e.printStackTrace();
            }
        }

    }

    public static void listaProductos() throws SQLException {
        
        Statement st = conn.createStatement();
        String query = ("SELECT * FROM produtos");
        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            String cod = rs.getString("codigo");
            String des = rs.getString("descricion");
            int prez = rs.getInt("prezo");
            java.sql.Date data = rs.getDate("datac");
            System.out.println("Código:" + cod + ",Descipcion:" + des + ",Precio:" + prez + ",Fechas:" + data);
        }
    }
    public static void listaProductosCod(String cod) throws SQLException {
        
        Statement st = conn.createStatement();
        String query = ("SELECT * FROM produtos WHERE codigo = '"+cod+"'");
        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            
            String des = rs.getString("descricion");
            int prez = rs.getInt("prezo");
            java.sql.Date data = rs.getDate("datac");
            System.out.println("Código:" + cod + ",Descipcion:" + des + ",Precio:" + prez + ",Fechas:" + data);
        }
    }
    
    public static void actualizarPre(String cod,int prez)throws
            SQLException{
        Statement st =conn.createStatement();
        String cadena =("UPDATE produtos SET prezo= "+ prez +"WHERE codigo='"+ cod +"'");
        st.executeUpdate(cadena);
    }
    public static void eliminarProducto(String cod) throws SQLException{
        Statement st = conn.createStatement();
        String cadena= ("DELETE FROM produtos WHERE codigo ='"+cod+"'");
        
    }
    public static void main(String[] args) throws SQLException, ParseException {
        conexion();
        SimpleDateFormat format = new SimpleDateFormat("ddMMyyyy");
        java.util.Date di;
        di = format.parse("28022021");
        java.sql.Date df = new java.sql.Date(di.getTime());
        //insireProduto("p6", "cepillo", 9, df);
        //listaProductos();
        //listaProductosCod("p1");
        //actualizarPre("p1",3);
       
        listaProductosCod("p1");
        System.out.println();
        listaProductos();
        eliminarProducto("p1");
        System.out.println();
        listaProductos();
        
        
        conn.close();

    }

}

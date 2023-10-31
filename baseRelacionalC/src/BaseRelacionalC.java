import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class BaseRelacionalC {

    public static Connection conn;

    public static void conexion() throws SQLException {
        String driver = "jdbc:postgresql:";
        String host = "//localhost:";
        String porto = "5432";
        String sid = "postgres";
        String usuario = "postgres";
        String password = "postgres";
        String url = driver + host + porto + "/" + sid;
        conn = DriverManager.getConnection(url, usuario, password);
    }

    public static void insireProduto(String cod, String des, int prez, java.sql.Date data) throws SQLException {
        String sql = "INSERT INTO produtos VALUES (?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, cod);
        ps.setString(2, des);
        ps.setInt(3, prez);
        ps.setDate(4, data);
        ps.executeUpdate();
    }

    public static void listaProductos() throws SQLException {
        String sql = "SELECT * FROM produtos";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            System.out.println("Código: " + rs.getString("codigo"));
            System.out.println("Descricion: " + rs.getString("descricion"));
            System.out.println("Precio: " + rs.getInt("prezo"));
            System.out.println("Fechas: " + rs.getDate("datac"));
            System.out.println("-------------------");
        }
    }

    public static void listaProductosCod(String cod) throws SQLException {
        String sql = "SELECT * FROM produtos WHERE codigo = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, cod);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            System.out.println("Código: " + rs.getString("codigo"));
            System.out.println("Descricion: " + rs.getString("descricion"));
            System.out.println("Precio: " + rs.getInt("prezo"));
            System.out.println("Fechas: " + rs.getDate("datac"));
            System.out.println("-------------------");
        }
    }

    public static void actualizarPre(String cod,int prez)throws SQLException{
        String sql = "UPDATE produtos SET prezo= ? WHERE codigo= ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, prez);
        ps.setString(2, cod);
        ps.executeUpdate();
    }

    public static void eliminarProducto(String cod) throws SQLException{
        String sql = "DELETE FROM produtos WHERE codigo = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, cod);
        ps.executeUpdate();
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
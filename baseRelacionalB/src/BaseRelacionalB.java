import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseRelacionalB {
    private static Connection conn;

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

    public static void listar() throws SQLException {
        Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = statement.executeQuery("SELECT produtos.* FROM produtos");

        while (rs.next()) {
            System.out.println(rs.getString("codigo") + ", " + rs.getString("descricion") + ", " + rs.getFloat("prezo"));
        }
    }

    public static void actualizar() throws SQLException {
        Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = statement.executeQuery("SELECT produtos.* FROM produtos WHERE codigo='p2'");

        if (rs.next()) {
            rs.updateFloat("prezo", 8);
            rs.updateRow();
        }
    }

    public static void inserir() throws SQLException {
        Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = statement.executeQuery("SELECT produtos.* FROM produtos");

        rs.moveToInsertRow();
        rs.updateString("codigo", "p4");
        rs.updateString("descricion", "martelo");
        rs.updateFloat("prezo", 20);
        rs.insertRow();
    }

    public static void borrar() throws SQLException {
        Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = statement.executeQuery("SELECT produtos.* FROM produtos WHERE codigo='p3'");

        if (rs.next()) {
            rs.deleteRow();
        }
    }
    
    public static void main(String[] args) throws SQLException {
  
            conexion();
            System.out.println("Listando productos:");
            listar();
            System.out.println("Actualizando producto p2:");
            actualizar();
            System.out.println("Insertando producto p4:");
            inserir();
            System.out.println("Borrando producto p3:");
            borrar();
            System.out.println("Listando productos después de las operaciones:");
            listar();
     
    }

}
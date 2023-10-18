
package baserelacionala;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;

public class BaserelacionalA {
 public static Connection conn;
    public static void conexion() throws SQLException{
    String driver = "jdbc:postgresql:";
    String host = "//localhost:"; // tamen poderia ser una ip como "192.168.1.14"
    String porto = "5432";
    String sid = "postgres";
    String usuario = "postgres";
    String password = "postgres";
    String url = driver + host+ porto + "/" + sid;
    conn = DriverManager.getConnection(url,usuario,password);
        }
    public static void insireProduto(String cod,String des, int prez, java.sql.Date data) throws SQLException{
  //  java.sql.Date d = Date.valueOf(data);   
    System.out.println(data.getClass()); 
     Statement st = conn.createStatement();
     String cadea ="insert into produtos values('"+cod+"','"+des+"',"+prez+",'"+data+"')";
    st.executeUpdate(cadea);   
        
    
    }
        public static void main(String[] args) throws SQLException, ParseException {
    conexion();
      SimpleDateFormat format = new SimpleDateFormat("ddMMyyyy");
       java.util.Date di;
       di = format.parse("28022021");
       java.sql.Date df = new java.sql.Date(di.getTime());
     insireProduto("p6", "cepillo", 9, df);    
             

     
    
    conn.close();



    }
    
}

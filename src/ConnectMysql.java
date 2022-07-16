import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.*;

public class ConnectMysql {
        public static Connection ConnDB() {
        	
              try{
            	 String JDBCURL="jdbc:mysql://localhost:3306/Assingment";
                 Connection conn=null;
                 conn = DriverManager.getConnection(JDBCURL,"admin","Akash@9595");
                 System.out.print(" connection to DB");
                 return conn;
              }
             catch(Exception e){
            	 System.out.print("not connection to DB");
            	 return null;
             }
        }
}

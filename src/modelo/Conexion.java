package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {

    String bd = "taller";
    String url = "jdbc:mysql://localhost:3306/" ;
    String user = "root";  
    String password = "";
    String driver = "com.mysql.cj.jdbc.Driver";
    Connection con = null;
   

   
    public Connection getconexion() {
        
        try {
            Class.forName(driver);
            con=DriverManager.getConnection(url+bd,user,password);
            System.out.println("Conexion Exitosa");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Conexion erronea");
        } 
        return con;         
            
       
         
     }
    public void desconectar(){
        
        try{
            con.close();
        }catch(SQLException ex){
            
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            
        }
               
    }
    
    
    }
    
    
    
    
    

  
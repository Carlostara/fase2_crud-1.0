/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


/**
 *
 * @author Carlos Andres
 */
public class UsuarioDTO extends Conexion{
    
        public boolean validarLogin(String usuario, String contraseña) {
        PreparedStatement ps = null;
        ResultSet rs;
        Boolean resultado = false;
        try {
            Connection con = getconexion();
            ps = con.prepareStatement("SELECT * FROM usuario WHERE usuario=? AND contraseña=?");
            ps.setString(1, usuario);
            ps.setString(2, contraseña);
            rs = ps.executeQuery();
            resultado = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        }
        return resultado;
    }
    
}

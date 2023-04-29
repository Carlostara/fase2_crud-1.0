
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehiculoDTO extends Conexion{
    
//-----------------------METODO PARA GUARDAR DATOS------------------------------
    
    public boolean Guardardatos(Vehiculo veh) {

        PreparedStatement ps = null;
        Connection con = getconexion();

        String sql = "INSERT INTO vehiculo(placa,tipo, estado,motivo_ingreso, id_propietario)VALUES (?,?,?,?,?(select id_propietario from propietario where nombres=?))";

        try {
            ps = con.prepareStatement(sql);           
            ps.setString(1, veh.getPlaca());
            ps.setString(2, veh.getTipo());
            ps.setString(3, veh.getEstado());
            ps.setString(4, veh.getMotivoIngreso());
            ps.setInt(5,veh.getPropietario());
           
           
            ps.execute();
            return true;

        } catch (SQLException ex) {

            System.err.println(ex);
            return false;
        } finally {

            try {
                con.close();
            } catch (SQLException ex) {
                 System.err.println(ex);
            }

        }

    }
    
//---------------------METODO PARA MODIFICAR DATOS------------------------------
    public boolean modificar(Vehiculo veh) {
        PreparedStatement ps = null;
        Connection con = getconexion();

        String sql = "UPDATE vehiculo SET placa=?,tipo=?,estado=?, motivo_ingreso=?,id_propietario=?, WHERE id_vehiculo=? ";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, veh.getPlaca());
            ps.setString(2, veh.getTipo());
            ps.setString(3, veh.getEstado());
            ps.setString(4, veh.getMotivoIngreso());
            ps.setInt(5, veh.getPropietario());
            ps.setInt(6,veh.getId_vehiculo());
           
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
    
//---------------------METODO PARA ELIMINAR DATOS-------------------------------
     
    public void eliminar(int id_vehiculo) {
        PreparedStatement ps = null;
        Connection con = getconexion();

        String sql = "DELETE FROM vehiculo WHERE id_vehiculo="+id_vehiculo;

        try {
            ps = con.prepareStatement(sql);
         
            ps.executeUpdate();
           
        } catch (SQLException e) {
            System.err.println(e);
           
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
    

//---------------------METODO PARA LISTAR DATOS---------------------------------
   
    public List<Vehiculo> listar() {
        Connection con = getconexion();
        PreparedStatement ps = null;
        ResultSet rs;

        List datos = new ArrayList();

        String sql = "SELECT * FROM vehiculo";

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Vehiculo v = new Vehiculo();
                v.setId_vehiculo(rs.getInt(1));
                v.setPlaca(rs.getString(2));
                v.setTipo(rs.getString(3));
                v.setEstado(rs.getString(4));
                v.setMotivoIngreso(rs.getString(5));
                v.setPropietario(rs.getInt(6));
               
                
                datos.add(v);
            }

        } catch (SQLException e) {
            System.err.println(e);
        }

        return datos;

    }
    
      public ArrayList<Propietario> getpropietarios() {
       
        Connection con = getconexion();
        PreparedStatement ps = null;
        ResultSet rs;       
        ArrayList datos = new  ArrayList();       
       

          try {
              String sql = "SELECT id_propietario,nombres,apellidos FROM propietario";
              ps = con.prepareStatement(sql);
              rs = ps.executeQuery();

              while (rs.next()) {
                  Propietario nom = new Propietario();
                 nom.setId_propietario(rs.getInt(1));
                  nom.setNombres(rs.getString(2));
                  nom.setApellidos(rs.getString(3));
                  datos.add(nom);
              }

        } catch (SQLException e) {
            System.err.println(e);
        }
         return datos; 
      }
     

      

       

 }
      

   


                         
      
                
      

   
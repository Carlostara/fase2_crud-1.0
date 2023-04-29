package modelo;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;

public class PropietarioDTO extends Conexion {

    public boolean Guardardatos(Propietario pro) {

        PreparedStatement ps = null;
        Connection con = getconexion();

        String sql = "INSERT INTO propietario(nombres,apellidos, direccion,telefono, cedula,carta_propiedad)VALUES (?,?,?,?,?,?)";

        try {
            ps = con.prepareStatement(sql);           
            ps.setString(1, pro.getNombres());
            ps.setString(2, pro.getApellidos());
            ps.setString(3, pro.getDireccion());
            ps.setString(4, pro.getTelefono());
            ps.setInt(5, pro.getCedula());  
            ps.setString(6, pro.getCarta_propiedad());
          
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
    public boolean modificar(Propietario pro) {
        PreparedStatement ps = null;
        Connection con = getconexion();

        String sql = "UPDATE propietario SET nombres=?,apellidos=?,direccion=?, telefono=?,cedula=?,carta_propiedad=? WHERE id_propietario=? ";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, pro.getNombres());
            ps.setString(2, pro.getApellidos());
            ps.setString(3, pro.getDireccion());
            ps.setString(4, pro.getTelefono());
            ps.setInt(5, pro.getCedula());
            ps.setString(6, pro.getCarta_propiedad());
            
            ps.setInt(7, pro.getId_propietario());
            ps.execute();
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
    
    public void eliminar(int id_propietario) {
        PreparedStatement ps = null;
        Connection con = getconexion();

        String sql = "DELETE FROM propietario WHERE id_propietario="+id_propietario;

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

    
    public List<Propietario> listar() {
        Connection con = getconexion();
        PreparedStatement ps = null;
        ResultSet rs;

        List datos = new ArrayList();

        String sql = "SELECT * FROM propietario";

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Propietario pa = new Propietario();
                pa.setId_propietario(rs.getInt(1));
                pa.setNombres(rs.getString(2));
                pa.setApellidos(rs.getString(3));
                pa.setDireccion(rs.getString(4));
                pa.setTelefono(rs.getString(5));
                pa.setCedula(rs.getInt(6));
                pa.setCarta_propiedad(rs.getString(7));
             
                datos.add(pa);
            }

        } catch (SQLException e) {
            System.err.println(e);
        }

        return datos;

    }
    //-----------------METODO PARA RELLENAR COMBOBOX---------------------------

      public ArrayList<Vehiculo> getvehiculos() {
          
       
        Connection con = getconexion();
        PreparedStatement ps = null;
        ResultSet rs;       
        ArrayList datos = new  ArrayList();       
       

          try {
              String sql = "SELECT id_vehiculo,placa FROM vehiculo";
              ps = con.prepareStatement(sql);
              rs = ps.executeQuery();

              while (rs.next()) {
                  Vehiculo placa = new Vehiculo();
                  placa.setId_vehiculo(rs.getInt(1));
                  placa.setPlaca(rs.getString(2));
                  datos.add(placa);
              }

        } catch (SQLException e) {
            System.err.println(e);
        }
         return datos; 
      }
}



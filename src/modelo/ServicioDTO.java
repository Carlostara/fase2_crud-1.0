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
import java.util.List;

/**
 *
 * @author Carlos Andres
 */
public class ServicioDTO extends Conexion {
    
    //Metodo Listar Empleados
    public List<Servicio> listar() {
        Connection con = getconexion();
        PreparedStatement ps = null;
        ResultSet rs;

        List datos = new ArrayList();

        String sql = "SELECT * FROM servicio";

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Servicio s = new Servicio();
                s.setId_servicio(rs.getInt(1));
                //s.setFechaIngreso(rs.getString(2));
                //s.setFechaEntrega(rs.getString(3));
                s.setCosto(rs.getFloat(4));
                s.setDescripcion(rs.getString(5));
                s.setId_empleado(rs.getInt(6));
                s.setId_vehiculo(rs.getInt(7));
                datos.add(s);
            }

        } catch (SQLException e) {
            System.err.println(e);
        }

        return datos;

    }

    //Metodo Registar Empleados
    public boolean registrar(Servicio ser) {
        PreparedStatement ps = null;
        Connection con = getconexion();

        String sql = "INSERT INTO servicio(fecha_ingreso, fecha_entrega,costo_servicio, descripcion, id_empleado, id_vehiculo) VALUES (?,?,?,?,?,?)";

        try {
            ps = con.prepareStatement(sql);
              //java.sql.Date fechaIngresosql = new java.sql.Date(ser.getFechaIngreso().getTime());
            //ps.setDate(1, fechaIngresosql());
            //ps.setString(2, ser.getFechaEntrega());
            ps.setFloat(3, ser.getCosto());
             ps.setString(4,ser.getDescripcion());
              ps.setInt(5, ser.getId_empleado());
               ps.setInt(6, ser.getId_vehiculo());
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

    public boolean modificar(Servicio ser) {
        PreparedStatement ps = null;
        Connection con = getconexion();

        String sql = "UPDATE servicio SET fecha_ingreso=?,fecha_entrega=?,costo_servicio=?,descripcion=?,id_empleado=?,id_vehiculo=? WHERE id_servicio=? ";

        try {
            ps = con.prepareStatement(sql);

            //ps.setString(1, ser.getFechaIngreso());
            //ps.setString(2, ser.getFechaEntrega());
            ps.setFloat(3, ser.getCosto());
            ps.setString(4, ser.getDescripcion());
            ps.setInt(5, ser.getId_empleado());
             ps.setInt(6, ser.getId_empleado());
            
            ps.setInt(7,ser.getId_servicio());
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

    public void eliminar(int id_servicio) {
        PreparedStatement ps = null;
        Connection con = getconexion();

        String sql = "DELETE FROM servicio WHERE id_servicio=" + id_servicio;

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

    
}

package modelo;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.*;
import java.util.List;

public class EmpleadoDTO extends Conexion {

    //Metodo Listar Empleados
    public List<Empleado> listar() {
        Connection con = getconexion();
        PreparedStatement ps = null;
        ResultSet rs;

        List datos = new ArrayList();

        String sql = "SELECT * FROM empleado";

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Empleado e = new Empleado();
                e.setId_empleado(rs.getInt(1));
                e.setNombres(rs.getString(2));
                e.setApellidos(rs.getString(3));
                e.setCedula(rs.getInt(4));
                datos.add(e);
            }

        } catch (SQLException e) {
            System.err.println(e);
        }

        return datos;

    }

    //Metodo Registar Empleados
    public boolean registrar(Empleado emp) {
        PreparedStatement ps = null;
        Connection con = getconexion();

        String sql = "INSERT INTO empleado (  nombres, apellidos , cedula) VALUES(?,?,?)";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, emp.getNombres());
            ps.setString(2, emp.getApellidos());
            ps.setInt(3, emp.getCedula());
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

    public boolean modificar(Empleado emp) {
        PreparedStatement ps = null;
        Connection con = getconexion();

        String sql = "UPDATE empleado SET nombres=?, apellidos=?, cedula=? WHERE id_empleado=? ";

        try {
            ps = con.prepareStatement(sql);

            ps.setString(1, emp.getNombres());
            ps.setString(2, emp.getApellidos());
            ps.setInt(3, emp.getCedula());
            ps.setInt(4, emp.getId_empleado());
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

    public void eliminar(int id_empleado) {
        PreparedStatement ps = null;
        Connection con = getconexion();

        String sql = "DELETE FROM empleado WHERE id_empleado=" + id_empleado;

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

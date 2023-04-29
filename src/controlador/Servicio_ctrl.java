
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import modelo.Servicio;
import modelo.ServicioDTO;

import vista.Gestion_servicio;


public class Servicio_ctrl implements ActionListener{
    
     Servicio modelo_s;
   ServicioDTO dto_s;
  Gestion_servicio v_ser;

    public Servicio_ctrl(Servicio modelo_s, ServicioDTO dto_s, Gestion_servicio v_ser) {
        this.modelo_s = modelo_s;
        this.dto_s = dto_s;
        this.v_ser = v_ser;
        this.v_ser.btn_guardar.addActionListener(this);
        this.v_ser.btnListar.addActionListener(this);
        
    }

   

    

    public void Llenartabla(JTable tabla) {
        DefaultTableModel table = new DefaultTableModel();
        table.addColumn("id_servicio");
        table.addColumn("fecha ingreso");
        table.addColumn("fecha entrega");
        table.addColumn("costo servicio");
        table.addColumn("descripcion");
       table.addColumn("id_empleado");
      table.addColumn("vehiculo");
      

        List<Servicio> lista = dto_s.listar();
        Object[] columna = new Object[7];
        for (int i = 0; i < lista.size(); i++) {
            columna[0] = lista.get(i).getId_servicio();
            columna[1] = lista.get(i).getFechaIngreso();
            columna[2] = lista.get(i).getFechaEntrega();
            columna[3] = lista.get(i).getCosto();
            columna[4] = lista.get(i).getDescripcion();
            columna[5] = lista.get(i).getId_empleado();
            columna[6] = lista.get(i).getId_vehiculo();
            
            table.addRow(columna);
        }
        v_ser.jTabla.setModel(table);

    }

    public void iniciavista() {

        v_ser.setTitle("propietarios");
        v_ser.setLocationRelativeTo(null);
        v_ser.txtid.setVisible(false);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == v_ser.btn_guardar) {
            SimpleDateFormat formatofecha = new  SimpleDateFormat("yyyy-MM-dd");
           
           String fecha_ingreso = formatofecha.format(v_ser.txtfecha.getDate());
           String fecha_entrega = formatofecha.format(v_ser.txtfecha2.getDate());
           float costo = Float.parseFloat(v_ser.txt_costo.getText());          
           modelo_s.setDescripcion(v_ser.txt_descripcion.getText());           
            modelo_s.setId_empleado(Integer.parseInt(v_ser.txtemp.getText()));
            modelo_s.setId_vehiculo(Integer.parseInt(v_ser.txtvehi.getText()));
            
          
         
            if (dto_s.registrar(modelo_s)) {
                JOptionPane.showMessageDialog(null, "Registro Guardado");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al Guardar");
                limpiar();
            }
            limpiarTabla();
            Llenartabla(v_ser.jTabla);
        }

        if (e.getSource() == v_ser.btn_actualizar) {
             modelo_s.setId_servicio(Integer.parseInt(v_ser.txtid.getText()));
            SimpleDateFormat formatofecha = new  SimpleDateFormat("yyyy-MM-dd");
           
           String fecha_ingreso = formatofecha.format(v_ser.txtfecha.getDate());
           String fecha_entrega = formatofecha.format(v_ser.txtfecha2.getDate());
           float costo = Float.parseFloat(v_ser.txt_costo.getText());          
           modelo_s.setDescripcion(v_ser.txt_descripcion.getText());           
            modelo_s.setId_empleado(Integer.parseInt(v_ser.txtemp.getText()));
            modelo_s.setId_vehiculo(Integer.parseInt(v_ser.txtvehi.getText()));
            if (dto_s.modificar(modelo_s)) {
                JOptionPane.showMessageDialog(null, "Registro Modificado");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al Modificar");
                limpiar();
            }
            limpiarTabla();
            Llenartabla(v_ser.jTabla);
        }

        if (e.getSource() == v_ser.btn_eliminar) {
            int fila = v_ser.jTabla.getSelectedRow();

            if (fila == -1) {

                JOptionPane.showMessageDialog(v_ser, "Debes seleccionar un usuario");
            } else {
                int id_empleado = Integer.parseInt((String) v_ser.jTabla.getValueAt(fila, 0).toString());

               dto_s.eliminar(id_empleado);
                JOptionPane.showMessageDialog(v_ser, "Usuario eliminado =X");
                limpiar();
            }
            limpiarTabla();
            Llenartabla(v_ser.jTabla);

        }
        if (e.getSource() == v_ser.btnListar) {

            Llenartabla(v_ser.jTabla);
            limpiar();
        }
       

        if (e.getSource() == v_ser.btn_limpiar) {
            limpiar();
        }
    }
    public void limpiarTabla() {
        DefaultTableModel model = (DefaultTableModel)v_ser.jTabla.getModel();
        model.setRowCount(0);
    }

    public void limpiar() {
        v_ser.txtid.setText(null);
        v_ser.txtfecha.setDate(null);
       v_ser.txtfecha2.setDate(null);
        v_ser.txt_costo.setText(null);
        v_ser.txt_descripcion.setText(null);
        v_ser.txtemp.setText(null);
        v_ser.txtvehi.setText(null);
    }

    
}
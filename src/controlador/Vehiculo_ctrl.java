
package controlador;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Propietario;
import modelo.Vehiculo;
import modelo.VehiculoDTO;
import vista.Gestion_vehiculo;
import vista.Login;






public class Vehiculo_ctrl implements ActionListener{
private Vehiculo modelo_v;
    private VehiculoDTO dto_v;
    private Gestion_vehiculo vista_v;

    public Vehiculo_ctrl(Vehiculo modelo, VehiculoDTO dto_v, Gestion_vehiculo vista) {
        this.modelo_v = modelo;
        this.dto_v = dto_v;
        this.vista_v = vista;
        this.vista_v.btn_guardar.addActionListener(this);
        this.vista_v.btn_actualizar.addActionListener(this);
        this.vista_v.btn_eliminar.addActionListener(this);
        this.vista_v.btn_editar.addActionListener(this);
        this.vista_v.btn_limpiar.addActionListener(this);
        this.vista_v.btn_listar.addActionListener(this);
    }
     
     public void Llenartabla(JTable tabla) {
        DefaultTableModel table = new DefaultTableModel();
        table.addColumn("id_vehiculo");
        table.addColumn("Placa");
        table.addColumn("tipo");
        table.addColumn("estado");
        table.addColumn("motivo_ingreso");
        table.addColumn("propietario");
        

        List<Vehiculo> lista = dto_v.listar();
        Object[] columna = new Object[7];
        for (int i = 0; i < lista.size(); i++) {
            columna[0] = lista.get(i).getId_vehiculo();
            columna[1] = lista.get(i).getPlaca();
            columna[2] = lista.get(i).getTipo();
            columna[3] = lista.get(i).getEstado();
            columna[4] = lista.get(i).getMotivoIngreso();
            columna[5] = lista.get(i).getPropietario();
             
           
            table.addRow(columna);
        }
       vista_v.jTabla.setModel(table);

    }
    
    
    public void iniciavista() {

        vista_v.setTitle("Vehiculos");
        vista_v.setLocationRelativeTo(null);
        vista_v.txt_id.setVisible(false);

    }

     

    @Override
    public void actionPerformed(ActionEvent e) {
        

        if (e.getSource() == vista_v.btn_guardar) {
            
           
            modelo_v.setPlaca(vista_v.txt_placa.getText());
            modelo_v.setTipo(vista_v.txttipo.getText());
            modelo_v.setEstado(vista_v.txtestado.getText());
            modelo_v.setMotivoIngreso(vista_v.txtMotivo.getText());
            Propietario pro = (Propietario) vista_v.Cbx_propietario.getSelectedItem();
            


        }
           
            
           
   
            

            if (dto_v.Guardardatos(modelo_v)) {
                JOptionPane.showMessageDialog(null, "Registro Guardado");
                limpiar();

            } else {
                JOptionPane.showMessageDialog(null, "Error al Guardar");
        

            }
    

        
        if (e.getSource() == vista_v.btn_actualizar) {
            
            modelo_v.setId_vehiculo(Integer.parseInt(vista_v.txt_id.getText()));
            

            if (dto_v.modificar(modelo_v)) {
                JOptionPane.showMessageDialog(null, "Registro Guardado");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al Guardar");
                limpiar();

            }
            limpiarTabla();
            Llenartabla(vista_v.jTabla);
            

        }
        if (e.getSource() == vista_v.btn_eliminar) {
            int fila = vista_v.jTabla.getSelectedRow();

            if (fila == -1) {

                JOptionPane.showMessageDialog(vista_v, "Debes seleccionar un usuario");
            } else {
                int id_vehiculo = Integer.parseInt((String) vista_v.jTabla.getValueAt(fila, 0).toString());

               dto_v.eliminar(id_vehiculo);
                JOptionPane.showMessageDialog(vista_v, "Usuario eliminado =X");
                limpiar();
            }
            limpiarTabla();
            Llenartabla(vista_v.jTabla);

        }
        if (e.getSource() == vista_v.btn_listar) {

            Llenartabla(vista_v.jTabla);
            limpiar();
        }
        if (e.getSource() == vista_v.btn_editar) {
            int fila = vista_v.jTabla.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(vista_v, "Debes seleccionar una fila");
            } else {
                int id_vehiculo = Integer.parseInt((String) vista_v.jTabla.getValueAt(fila, 0).toString());
                String placa = (String) vista_v.jTabla.getValueAt(fila, 1);
                String tipo = (String) vista_v.jTabla.getValueAt(fila, 2);
                String estado = (String) vista_v.jTabla.getValueAt(fila, 3);
                String motivo_Ingreso = (String) vista_v.jTabla.getValueAt(fila, 4);
                String nombrepropi = (String) vista_v.jTabla.getValueAt(fila, 5);

                vista_v.txt_id.setText("" + id_vehiculo);
                vista_v.txt_placa.setText(placa);
                
               

            }

        }
         if (e.getSource() == vista_v.btn_limpiar) {
            limpiar();
        }
        

    }
     public void limpiarTabla() {
        DefaultTableModel model = (DefaultTableModel)vista_v.jTabla.getModel();
        model.setRowCount(0);
    }
     public void limpiar() {
        
        
    }

 
    
}

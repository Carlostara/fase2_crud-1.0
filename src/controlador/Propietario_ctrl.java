package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import modelo.Propietario;
import modelo.PropietarioDTO;
import vista.Gestion_propietario;

public class Propietario_ctrl implements ActionListener {

    private final Propietario modelo_p;
    private final PropietarioDTO consultas_p;
    private final Gestion_propietario v_pro;

    public Propietario_ctrl(Propietario modelo_p, PropietarioDTO consultas_p, Gestion_propietario v_pro) {
        this.modelo_p = modelo_p;
        this.consultas_p = consultas_p;
        this.v_pro = v_pro;
        this.v_pro.btnGuardar.addActionListener(this);
        this.v_pro.btnActualizar.addActionListener(this);
        this.v_pro.btnEliminar.addActionListener(this);
        this.v_pro.btnLimpiar.addActionListener(this);
        this.v_pro.btnListar.addActionListener(this);
        this.v_pro.btnEditar.addActionListener(this);
    }

    public void Llenartabla(JTable tabla) {
        DefaultTableModel table = new DefaultTableModel();
        table.addColumn("id_propietario");
        table.addColumn("Nombres");
        table.addColumn("Apellidos");
        table.addColumn("Direccion");
        table.addColumn("Telefono");
        table.addColumn("Cédula");
        table.addColumn("Carta propiedad");
      

        List<Propietario> lista = consultas_p.listar();
        Object[] columna = new Object[8];
        for (int i = 0; i < lista.size(); i++) {
            columna[0] = lista.get(i).getId_propietario();
            columna[1] = lista.get(i).getNombres();
            columna[2] = lista.get(i).getApellidos();
            columna[3] = lista.get(i).getDireccion();
            columna[4] = lista.get(i).getTelefono();
            columna[5] = lista.get(i).getCedula();
            columna[6] = lista.get(i).getCarta_propiedad();
            
            table.addRow(columna);
        }
        v_pro.jtabla.setModel(table);

    }

    public void iniciavista() {

        v_pro.setTitle("propietarios");
        v_pro.setLocationRelativeTo(null);
        v_pro.txtId.setVisible(false);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == v_pro.btnGuardar) {
            
            modelo_p.setNombres(v_pro.txtNombres.getText());
            modelo_p.setApellidos(v_pro.txtApellidos.getText());
            modelo_p.setDireccion(v_pro.txtDireccion.getText());
            modelo_p.setTelefono(v_pro.txtTelefono.getText());
            modelo_p.setCedula(Integer.parseInt(v_pro.txtCedula.getText()));
            modelo_p.setCarta_propiedad(v_pro.txtCarta.getText());
            
          
         
            if (consultas_p.Guardardatos(modelo_p)) {
                JOptionPane.showMessageDialog(null, "Registro Guardado");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al Guardar");
                limpiar();
            }
            limpiarTabla();
            Llenartabla(v_pro.jtabla);
        }

        if (e.getSource() == v_pro.btnActualizar) {
            modelo_p.setId_propietario(Integer.parseInt(v_pro.txtId.getText()));
            modelo_p.setNombres(v_pro.txtNombres.getText());
            modelo_p.setApellidos(v_pro.txtApellidos.getText());
            modelo_p.setDireccion(v_pro.txtDireccion.getText());
            modelo_p.setTelefono(v_pro.txtTelefono.getText());
            modelo_p.setCedula(Integer.parseInt(v_pro.txtCedula.getText()));
              modelo_p.setCarta_propiedad(v_pro.txtCarta.getText());

            if (consultas_p.modificar(modelo_p)) {
                JOptionPane.showMessageDialog(null, "Registro Modificado");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al Modificar");
                limpiar();
            }
            limpiarTabla();
            Llenartabla(v_pro.jtabla);
        }

        if (e.getSource() == v_pro.btnEliminar) {
            int fila = v_pro.jtabla.getSelectedRow();

            if (fila == -1) {

                JOptionPane.showMessageDialog(v_pro, "Debes seleccionar un usuario");
            } else {
                int id_empleado = Integer.parseInt((String) v_pro.jtabla.getValueAt(fila, 0).toString());

                consultas_p.eliminar(id_empleado);
                JOptionPane.showMessageDialog(v_pro, "Usuario eliminado =X");
                limpiar();
            }
            limpiarTabla();
            Llenartabla(v_pro.jtabla);

        }
        if (e.getSource() == v_pro.btnListar) {

            Llenartabla(v_pro.jtabla);
            limpiar();
        }
        if (e.getSource() == v_pro.btnEditar) {
            int fila = v_pro.jtabla.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(v_pro, "Debes seleccionar una fila");
            } else {
                int id_propietario = Integer.parseInt((String) v_pro.jtabla.getValueAt(fila, 0).toString());
                String nombres = (String) v_pro.jtabla.getValueAt(fila, 1);
                String apellidos = (String) v_pro.jtabla.getValueAt(fila, 2);
                String direccion = (String) v_pro.jtabla.getValueAt(fila, 3);
                String telefono = (String) v_pro.jtabla.getValueAt(fila, 4);
                int cedula = (int) v_pro.jtabla.getValueAt(fila, 5);
                String cartapropiedad = (String)v_pro.jtabla.getValueAt(fila, 6);
                v_pro.txtId.setText("" + id_propietario);
                v_pro.txtNombres.setText(nombres);
                v_pro.txtApellidos.setText(apellidos);
                v_pro.txtDireccion.setText(direccion);
                v_pro.txtTelefono.setText(telefono);
                v_pro.txtCedula.setText("" + cedula);
                v_pro.txtCarta.setText("" + cartapropiedad);

            }

        }

        if (e.getSource() == v_pro.btnLimpiar) {
            limpiar();
        }
    }
    public void limpiarTabla() {
        DefaultTableModel model = (DefaultTableModel)v_pro.jtabla.getModel();
        model.setRowCount(0);
    }

    public void limpiar() {
        v_pro.txtId.setText(null);
        v_pro.txtNombres.setText(null);
        v_pro.txtApellidos.setText(null);
        v_pro.txtDireccion.setText(null);
        v_pro.txtTelefono.setText(null);
        v_pro.txtCedula.setText(null);
        v_pro.txtCarta.setText(null);
    }

    
}

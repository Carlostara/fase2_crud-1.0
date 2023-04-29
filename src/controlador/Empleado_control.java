package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Empleado;
import modelo.EmpleadoDTO;
import vista.Vista_empleado;

public class Empleado_control implements ActionListener {

    private Empleado modelo;
    private EmpleadoDTO consultas;
    private Vista_empleado vista;

    public Empleado_control(Empleado modelo, EmpleadoDTO consultas, Vista_empleado vista) {
        this.modelo = modelo;
        this.consultas = consultas;
        this.vista = vista;
        this.vista.btnGuardar.addActionListener(this);
        this.vista.btnModificar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        this.vista.btnLimpiar.addActionListener(this);
        this.vista.btnListar.addActionListener(this);
        this.vista.btnEditar.addActionListener(this);

    }

    public void Llenartabla(JTable tabla) {
        DefaultTableModel table = new DefaultTableModel();
        table.addColumn("ID");
        table.addColumn("Nombres");
        table.addColumn("Apellidos");
        table.addColumn("Cédula");

        List<Empleado> lista = consultas.listar();
        Object[] columna = new Object[4];
        for (int i = 0; i < lista.size(); i++) {
            columna[0] = lista.get(i).getId_empleado();
            columna[1] = lista.get(i).getNombres();
            columna[2] = lista.get(i).getApellidos();
            columna[3] = lista.get(i).getCedula();
            table.addRow(columna);
        }
        vista.jtabla.setModel(table);

    }

    public void iniciavista() {

        vista.setTitle("empleados");
        vista.setLocationRelativeTo(null);
        vista.txtId.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vista.btnListar) {

            Llenartabla(vista.jtabla);
            limpiar();
        }
        if (e.getSource() == vista.btnEditar) {
            int fila = vista.jtabla.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(vista, "Debes seleccionar una fila");
            } else {
                int id_empleado = Integer.parseInt((String) vista.jtabla.getValueAt(fila, 0).toString());
                String nombres = (String) vista.jtabla.getValueAt(fila, 1);
                String apellidos = (String) vista.jtabla.getValueAt(fila, 2);
                int cedula = (int) vista.jtabla.getValueAt(fila, 3);
                vista.txtId.setText("" + id_empleado);
                vista.txtNombres.setText(nombres);
                vista.txtApellidos.setText(apellidos);
                vista.txtCedula.setText("" + cedula);

            }

        }

        if (e.getSource() == vista.btnGuardar) {

            modelo.setNombres(vista.txtNombres.getText());
            modelo.setApellidos(vista.txtApellidos.getText());
            modelo.setCedula(Integer.parseInt(vista.txtCedula.getText()));

            if (consultas.registrar(modelo)) {
                JOptionPane.showMessageDialog(null, "Registro Guardado");

                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al Guardar");

                limpiar();
            }
            limpiarTabla();
            Llenartabla(vista.jtabla);
        }

        if (e.getSource() == vista.btnModificar) {
            modelo.setId_empleado(Integer.parseInt(vista.txtId.getText()));

            modelo.setNombres(vista.txtNombres.getText());
            modelo.setApellidos(vista.txtApellidos.getText());
            modelo.setCedula(Integer.parseInt(vista.txtCedula.getText()));

            if (consultas.modificar(modelo)) {
                JOptionPane.showMessageDialog(null, "Registro Modificado");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al Modificar");
                limpiar();
            }
            limpiarTabla();
            Llenartabla(vista.jtabla);
        }

        if (e.getSource() == vista.btnEliminar) {
            int fila = vista.jtabla.getSelectedRow();

            if (fila == -1) {

                JOptionPane.showMessageDialog(vista, "Debes seleccionar un usuario");
            } else {
                int id_empleado = Integer.parseInt((String) vista.jtabla.getValueAt(fila, 0).toString());

                consultas.eliminar(id_empleado);
                JOptionPane.showMessageDialog(vista, "Usuario eliminado =X");
                    limpiar();
            }
            limpiarTabla();
            Llenartabla(vista.jtabla);

        }

        if (e.getSource() == vista.btnLimpiar) {
            limpiar();
        }

    }

    public void limpiar() {
        vista.txtId.setText(null);

        vista.txtNombres.setText(null);
        vista.txtApellidos.setText(null);
        vista.txtCedula.setText(null);
    }

    public void limpiarTabla() {
        DefaultTableModel model = (DefaultTableModel) vista.jtabla.getModel();
        model.setRowCount(0);
    }
}

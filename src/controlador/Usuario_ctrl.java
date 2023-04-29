 
package controlador;

/**
 *
 * @author DHANI
 */
import modelo.Usuario;
import modelo.UsuarioDTO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import vista.Login;


public class Usuario_ctrl implements ActionListener {
    private final Usuario modelo;
    private final UsuarioDTO usuarioDTO;
    private final Login vista;

    public Usuario_ctrl(Usuario modelo, UsuarioDTO usuarioDTO, Login vista) {
        this.modelo = modelo;
        this.usuarioDTO = usuarioDTO;
        this.vista = vista;
        this.vista.btn_inicio.addActionListener(this);
        this.vista.btn_limpiar.addActionListener(this);
    }

    

    
    public void iniciavista() {

        vista.setTitle("propietarios");
        vista.setLocationRelativeTo(null);
       

    }
    public void cerrar(){
        vista.setVisible(false);
    }
    public void limpiar(){
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vista.btn_inicio) {
            limpiar();
            vista.txt_user.requestFocus();
        }
        if (e.getSource() == vista.btn_inicio) {
            String usuario, contraseña;
            usuario = vista.txt_user.getText();
            contraseña = vista.txt_password.getText();
            if(usuarioDTO.validarLogin(usuario, contraseña)){
                JOptionPane.showMessageDialog(null, "Correcto");
                cerrar();
                
            }else{
                JOptionPane.showMessageDialog(null, "Usuario o Contraseña invalida");
                vista.txt_user.requestFocus();
            }
        }
        if (e.getSource() == vista.btn_limpiar) {
            vista.txt_user.setText(null);
            vista.txt_password.setText(null);
        }
    }
    
    
}
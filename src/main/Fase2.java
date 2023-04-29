
package main;



//import controlador.Empleado_control;

import controlador.Usuario_ctrl;
//import modelo.Empleado;
//import modelo.EmpleadoDTO;


import modelo.Usuario;
import modelo.UsuarioDTO;

import vista.Login;
//import vista.Vista_empleado;




public class Fase2 {
    public static void main(String args[]) {
     Usuario modelo = new Usuario();
    UsuarioDTO usuarioDTO = new UsuarioDTO();
    Login vista = new Login();

     

   Usuario_ctrl v = new Usuario_ctrl(modelo, usuarioDTO, vista);
   v.iniciavista();
   vista.setVisible(true);
  
   
   
 
   

    
   
    
}}

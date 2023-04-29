
package modelo;

import java.util.ArrayList;
import java.util.List;





public class Propietario {
    private int id_propietario;
    private String nombres;
    private String apellidos;
    private String direccion;
    private String telefono;
    private int cedula;
    private String carta_propiedad;
   

    
  

    public Propietario() {
    }

  

   
    
      
    //Getters and Setters

    public int getId_propietario() {
        return id_propietario;
    }

    public void setId_propietario(int id_propietario) {
        this.id_propietario = id_propietario;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getCarta_propiedad() {
        return carta_propiedad;
    }

    public void setCarta_propiedad(String carta_propiedad) {
        this.carta_propiedad = carta_propiedad;
    }

  

    @Override
    public String toString() {
        return  this.nombres+" "+this.apellidos;
    }

  


    
  
}

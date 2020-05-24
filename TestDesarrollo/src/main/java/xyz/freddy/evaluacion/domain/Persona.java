package xyz.freddy.evaluacion.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Persona {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id ;
  private String nombre;
  private String apellido;
  private int edad;
  @JsonFormat(pattern="yyyy-MM-dd")
  //@Temporal(TemporalType.DATE)
  private Date fechaNac;
  private String fechaNacString;
  @Column(length = 6000)
  private String mensaje;
  
  public Persona() {
  }
  
  public Persona(String nombre, String apellido, Date fechaNac) {
    this.nombre=nombre;
    this.apellido=apellido;
    this.fechaNac=fechaNac;
  }
  
  public long getId() {
    return id;
  }
  public void setId(long id) {
    this.id = id;
  }
  public String getNombre() {
    return nombre;
  }
  public void setNombre(String nombre) {
    this.nombre = nombre;
  }
  public String getApellido() {
    return apellido;
  }
  public void setApellido(String apellido) {
    this.apellido = apellido;
  }
  public int getEdad() {
    return edad;
  }
  public void setEdad(int edad) {
    this.edad = edad;
  }
  public Date getFechaNac() {
    return fechaNac;
  }
  public void setFechaNac(Date fechaNac) {
    this.fechaNac = fechaNac;
  }
  public String getMensaje() {
    return mensaje;
  }
  public void setMensaje(String mensaje) {
    this.mensaje = mensaje;
  }

  public String getFechaNacString() {
    return fechaNacString;
  }

  public void setFechaNacString(String fechaNacString) {
    this.fechaNacString = fechaNacString;
  }
  
  
  
  
  
 
}

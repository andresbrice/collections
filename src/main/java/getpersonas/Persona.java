package getpersonas;

import java.util.Objects;

public class Persona {
  private int dni = 0;
  private String apellido = null;
  private int edad = 0;

  public Persona(int dni, String apellido, int edad) {
    super();
    this.dni = dni;
    this.apellido = apellido;
    this.edad = edad;
  }

  @Override
  public String toString() {
    return dni + "," + apellido + "," + edad;
  }

  public Integer getDni() {
    return dni;
  }

  public Integer getEdad() {
    return edad;
  }

  public String getApellido() {
    return apellido;
  }

  public void setDni(int dni) {
    this.dni = dni;
  }

  public void setApellido(String apellido) {
    this.apellido = apellido;
  }

  public void setEdad(int edad) {
    this.edad = edad;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Persona)) return false;
    Persona persona = (Persona) o;
    return dni == persona.dni &&
          edad == persona.edad &&
          apellido.equals(persona.apellido);
  }

  @Override
  public int hashCode() {
    return Objects.hash(dni, apellido, edad);
  }
}

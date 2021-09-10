package getpersonas;

import java.io.IOException;
import java.util.List;

public class App {
  public static void main(String[] args) throws IOException {
    List<Persona> personas = IOPersonas.getPersonas("personas.txt");

    IOPersonas.escribirMayoresDeEdadOrdenadasPorEdad(personas,30);
    IOPersonas.escribirMayoresDeEdadOrdenadasPorDNI(personas,35);
  }
}
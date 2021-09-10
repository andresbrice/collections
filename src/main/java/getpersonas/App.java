package getpersonas;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class App {
  public static void main(String[] args) throws IOException {
    List<Persona> personas = IOPersonas.getPersonas("personas.txt");

    IOPersonas.escribirMayoresDeEdadOrdenadasPorEdad(personas,30);
    IOPersonas.escribirMayoresDeEdadOrdenadasPorDNI(personas,35);


    Map<Integer, ArrayList<Persona>> personasPorEdad = IOPersonas.agruparPorEdad(personas);
    IOPersonas.escribirPersonasPorEdad(personasPorEdad,"agrupadasPorEdad.txt");
  }
}
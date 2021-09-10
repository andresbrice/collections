package getpersonas;

import java.io.*;
import java.util.*;

public class IOPersonas {
  public static LinkedList<Persona> getPersonas(String archivo) {

    LinkedList<Persona> personas = new LinkedList<Persona>(); //solo podre tener objetos del tipo que defino entre corchangulos
    Scanner sc = null;

    try {
      sc = new Scanner(new File(archivo));

      while (sc.hasNext()) {
        String linea = sc.nextLine();
        String[] datos = linea.split(" ");
        int dni = 0;
        int edad = 0;
        String apellido = null;

        try {
          dni = Integer.parseInt(datos[0]);
        } catch (NumberFormatException e) {
          System.err.println("El dni debe ser un numero entero");
        }

        try {
          apellido = datos[1];
        } catch (RuntimeException e) {
          System.err.println("El apellido debe ser una cadena de texto ");
        }

        try {
          edad = Integer.parseInt(datos[2]);
        } catch (NumberFormatException e) {
          System.err.println("El dni debe ser un numero entero");
        }


        Persona p = new Persona(dni, apellido, edad);

        if (!personas.contains(p)) {
          personas.add(p);
        }
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    sc.close();
    return personas;
  }

  public static void ordenarPersonasPorDni(List<Persona> lista) {
    Collections.sort(lista, new DniComparator());
  }

  public static void ordenarPersonasPorApellido(List<Persona> lista) {
    Collections.sort(lista, new ApellidoComparator());
  }

  public static void ordenarPersonasPorEdad(List<Persona> lista) {
    Collections.sort(lista, new EdadComparator());
  }

  public static List<Persona> getPersonasMayoresDeEdad(List<Persona> personas, int edad) {
    List<Persona> personasMayores = new ArrayList<Persona>();

    for (Persona p : personas) {
      if (p.getEdad() > edad){
        personasMayores.add(p);
      }
    }
    return personasMayores;
  }

  public static void escribirPersonas(List<Persona> personas, String file) throws IOException {
    PrintWriter salida = new PrintWriter(new FileWriter(file));
    for (Persona p: personas) {
      salida.println(p);
    }
    salida.close();
  }

  public static void escribirMayoresDeEdadOrdenadasPorEdad(List<Persona> personas, int edad) throws IOException {

    List<Persona> personasMayores = getPersonasMayoresDeEdad(personas,edad);

    ordenarPersonasPorEdad(personasMayores);

    escribirPersonas(personasMayores, "MayoresDe"+edad+"OrdenadosPorEdad"+".csv");

  }

  public static void escribirMayoresDeEdadOrdenadasPorDNI(List<Persona> personas, int edad) throws IOException {

    List<Persona> personasMayores = getPersonasMayoresDeEdad(personas,edad);

    ordenarPersonasPorDni(personasMayores);

    escribirPersonas(personasMayores, "MayoresDe"+edad+"OrdenadosPorDNI"+".csv");

  }
}

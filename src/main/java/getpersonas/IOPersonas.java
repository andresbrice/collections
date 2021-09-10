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
      if (p.getEdad() > edad) {
        personasMayores.add(p);
      }
    }
    return personasMayores;
  }

  public static void escribirPersonas(List<Persona> personas, String file) throws IOException {
    PrintWriter salida = new PrintWriter(new FileWriter(file));
    for (Persona p : personas) {
      salida.println(p);
    }
    salida.close();
  }

  public static void escribirMayoresDeEdadOrdenadasPorEdad(List<Persona> personas, int edad) throws IOException {

    List<Persona> personasMayores = getPersonasMayoresDeEdad(personas, edad);

    ordenarPersonasPorEdad(personasMayores);

    escribirPersonas(personasMayores, "MayoresDe" + edad + "OrdenadosPorEdad" + ".csv");

  }

  public static void escribirMayoresDeEdadOrdenadasPorDNI(List<Persona> personas, int edad) throws IOException {

    List<Persona> personasMayores = getPersonasMayoresDeEdad(personas, edad);

    ordenarPersonasPorDni(personasMayores);

    escribirPersonas(personasMayores, "MayoresDe" + edad + "OrdenadosPorDNI" + ".csv");

  }

  public static Map<Integer, ArrayList<Persona>> agruparPorEdad(List<Persona> personas) {
    Map<Integer, ArrayList<Persona>> personasPorEdad = new TreeMap<Integer, ArrayList<Persona>>();

    ArrayList<Persona> auxiliar;
    Integer key;

    for (Persona p : personas) {
      key = p.getEdad();                      //le pregunto al objeto persona su edad

      if (personasPorEdad.containsKey(key)) { //le pregunto al mapa si contiene esa edad
        auxiliar = personasPorEdad.get(key);  //si la tiene le digo que me de ese registro y lo guardo en un arraylist auxiliar
      } else {
        auxiliar = new ArrayList<Persona>();  //sino la tiene creo un nuevo arraylist del tipo persona
      }

      auxiliar.add(p);                        //agrego ese objeto persona en el registro del mapa correspondiente  a  la edad
      personasPorEdad.put(key, auxiliar);     //actualizo ese valor.
    }

    return personasPorEdad;
  }


  public static void escribirPersonasPorEdad(Map <Integer,ArrayList<Persona>> map, String file) throws IOException {
    PrintWriter salida = new PrintWriter(new FileWriter(file));                   //creo el printwriter

    List<Persona> auxiliar;                                                       //creo variable aux

    for (Map.Entry<Integer,ArrayList<Persona>> cadaEdad: map.entrySet()) {        //recorro cada entrada del mapa e imprimo la key y al valor lo guardo en aux
      salida.println(cadaEdad.getKey());
      auxiliar = cadaEdad.getValue();

      for (Persona persona: auxiliar) {                                           //recorro cada auxiliar e imprimo su dni y el apellido
        salida.println(persona.getDni() + " " + persona.getApellido());
      }
    }

    salida.close();                                                               //no olvidar de cerrar el PrintWriter sino no escribe nada
  }
}

package ejemplos;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class SetExample {
  /*public static void main(String[] args) {
    Set<String> set = new HashSet<String>(); //no se imprime en orden porque se ordena por hash

    set.add("one");
    set.add("second");
    set.add("3rd");
    set.add("4");
    set.add("5.0F");
    set.add("second");
    set.add("4");

    System.out.println(set);
  }*/

  public static void main(String[] args) {
    TreeSet<String> set = new TreeSet<String>(); //imprime ordenado ya que implementa comparator

    set.add("one");
    set.add("second");
    set.add("3rd");
    set.add("4");
    set.add("5.0F");
    set.add("second");
    set.add("4");
    System.out.println(set);

    set.remove("4");
    System.out.println(set);

    set.add("4");
    System.out.println(set);

    System.out.println(set.contains("one"));

  }
}

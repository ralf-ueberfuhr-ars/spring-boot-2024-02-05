package de.schulungen.java;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LambdaSample {

  private static double add(double d1, double d2) {
    return d1 + d2;
  }

  private static double add2() {
    double result = 0d;
    while (result <= 10) {
      result = result + Math.random();
    }
    return result;
  }

  // Interface mit 1 abstr. Methode = Functional Interface
  @FunctionalInterface
  private interface NumberGenerator {
    double generate();
  }

  private static double add3(NumberGenerator generator) {
    double result = 0d;
    while (result <= 10) {
      result = result + generator.generate();
    }
    return result;
  }


  public static void main(String[] args) {
    // Addiere 2 Zahlen
    System.out.println(add(0.5, 2.7));
    // Addiere 2 Zufallszahlen
    /* Sofortige Ausführung von random()
     * 1. Math.random()
     * 2. Math.random()
     * 3. add(...)
     * [4. println()]
     */
    System.out.println(add(Math.random(), Math.random()));
    // Addiere Zufallszahlen solange, bis die Summe > 10
    System.out.println(add2());
    // Verantwortlichkeiten:
    //  - Entscheidung "Zufallszahlen" ist Verantw. von main()
    //  - Logik: wie oft summieren ist Verantw. von add2()
    /* Sofortige Ausführung von random()
     * [0. new NumberGenerator()]
     * 1. add3(...)
     * 2. generate() -> Math.random() [beliebig oft]
     * [3. println()]
     */
    System.out.println(add3(new NumberGenerator() {
      @Override
      public double generate() {
        return Math.random();
      }
    }));
    // Entfernen von Redundanzen
    System.out.println(add3(/*new NumberGenerator() {
      @Override
      public double generate*/() -> {
        return Math.random();
      }
      /*}*/));
    System.out.println(add3(() -> {
        return Math.random();
      }
    ));
    // Spezialfall: nur 1 Anweisung
    System.out.println(add3(() -> Math.random()));
    // Spezialfall: Parameter matchen [ generate() -> Math.random() ]
    System.out.println(add3(Math::random));

    // Anwendungsfall: Collections API
    Collection<String> namen = List.of(
      "Jacob",
      "Sebastian",
      "Jens",
      "Robert",
      "Elmar",
      "Katja",
      "Simon",
      "Sarah",
      "Benjamin",
      "Ralf"
    );
    // Anforderung: nur Kurznamen (max. 5 Buchstaben) in Großbuchstaben
    Collection<String> kurzNamen = new LinkedList<>();
    for (String name : namen) {
      if (name.length() <= 5) {
        kurzNamen.add(name.toUpperCase());
      }
    }
    System.out.println(kurzNamen);
    // jetzt mit Lambdas
    System.out.println(
      namen.stream()
        .filter(name -> name.length() <= 5)
        .map(String::toUpperCase)
        .toList()
    );
    Function<String, String> f = String::toUpperCase;
    System.out.println(
      namen.stream()
        .filter(name -> name.length() <= 5)
        .map(String::toUpperCase)
        .takeWhile(name -> !name.equals("ELMAR"))
        .toList()
    );

  }


}

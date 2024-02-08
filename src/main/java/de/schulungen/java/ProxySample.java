package de.schulungen.java;

import java.lang.reflect.Proxy;

public class ProxySample {

  private interface Person {

    String sayHello();

  }


  public static void main(String[] args) {

    Person person = new Person() {
      @Override
      public String sayHello() {
        return "Hello World.";
      }

      @Override
      public String toString() {
        return "Ich bin eine Person";
      }
    };

    // Technische Anforderung: Rückgabewert in toUpperCase()
    Person personProxy = (Person) Proxy.newProxyInstance(
      Person.class.getClassLoader(),
      new Class[]{Person.class},
      (proxy, method, args1) -> {
        var result =  method.invoke(person, args1);
        if(
          result instanceof String text
            && "sayHello".equals(method.getName())
        ) {
          return text.toUpperCase();
        }
        return result;
      }
    );

    System.out.println(personProxy.sayHello());
    System.out.println(personProxy.hashCode());
    System.out.println(personProxy);

  }

}

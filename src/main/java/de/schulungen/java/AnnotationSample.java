package de.schulungen.java;

public class AnnotationSample {

    /*
     * Ziel: Framework, das "Hello Welt" sagen kann,
     *       indem wir @SayHello an eine Methode hängen
     */
    public static void main(String[] args) {
        Person person = new Person("Tom");
        SayHelloFramework framework = new SayHelloFramework();
        framework.scan(person);
    }

}

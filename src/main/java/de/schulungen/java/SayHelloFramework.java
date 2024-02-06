package de.schulungen.java;

import lombok.SneakyThrows;

import java.lang.reflect.Method;

public class SayHelloFramework {

    @SneakyThrows
    public void scan(Object o) {
        // Klasse ermitteln
        Class<?> clazz = o.getClass();
        // Class stellt Metainformationen über die Klasse bereit
        // Metainfos abrufen/verwerten -> Reflection
        // Finde alle Methoden mit @SayHello
        Method[] methods = clazz.getDeclaredMethods();
        //System.out.println(Arrays.toString(methods));
        // Für jede Methode: rufe auf und schreibe Ergebnis auf Konsole
        for(Method method : methods) {
            SayHello annotation = method.getAnnotation(SayHello.class);
            if(annotation != null) {
                String prefix = annotation.value();
                Object result = method.invoke(o);
                System.out.println(prefix + result);
            }
        }
    }

}

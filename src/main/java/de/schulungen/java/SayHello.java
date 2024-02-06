package de.schulungen.java;

import java.lang.annotation.*;

@Documented // für Javadocs
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SayHello {
    String value() default "";
}

package de.schulungen.java;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Person {

    private final String name;

    @SayHello
    public String sayHello() {
        return "Hello, my name is " + name;
    }

    @SayHello("Person saying: ")
    public String sayHelloWorld() {
        return "Hello World!";
    }

}

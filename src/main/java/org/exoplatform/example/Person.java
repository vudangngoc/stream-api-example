package org.exoplatform.example;

//This example come from http://winterbe.com/posts/2014/07/31/java8-stream-tutorial-examples/
public class Person {
	public String name;
    public int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return name;
    }
}

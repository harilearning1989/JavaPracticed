package com.dp.creational.single;

public class Singleton implements Cloneable{

     /*Creational Patterns
    Structural Patterns
    Behavioral Patterns*/

    //Singleton,Prototype,Builder,Factory,Abstract Factory
    //Structural
    //Decorator,Proxy,Bridge,Adaptor,Composite
    //Behavioral
    //Observer,Iterator,Mediator,State,Interpreter,Visitor,Template

    //SOLID

    private static volatile Singleton instance = null;
    private Singleton() {
        if(instance != null){  //restricting reflection
            throw new RuntimeException("Do not try to break the Singleton class");
        }
    }
    public static Singleton getInstance() {
        if (instance == null) {                 //single check
            synchronized (Singleton.class){     //double check
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance ;
    }
    @Override
    protected Object clone() throws CloneNotSupportedException  {  //restricting from cloning
        throw new CloneNotSupportedException();
    }
}

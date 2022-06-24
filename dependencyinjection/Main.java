package main.java.dependencyinjection;

import main.java.dependencyinjection.vehicles.AudiR8ConstructorInjection;
import main.java.dependencyinjection.vehicles.AudiR8FieldInjection;
import main.java.dependencyinjection.vehicles.AudiR8SetterInjection;
import main.java.dependencyinjection.vehicles.Vehicle;

public class Main {

  public static void main(String[] args) {
    // field injection
    Vehicle audiR8FieldInjection = new AudiR8FieldInjection();

    // constructor injection
    Vehicle audiR8ConstructorInjection = new AudiR8ConstructorInjection();

    // setter injection
    Vehicle audiR8SetterInjection = new AudiR8SetterInjection();
    audiR8SetterInjection.setEngine(null);
  }


}

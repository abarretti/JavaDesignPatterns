package main.java.dependencyinjection.vehicles;

import main.java.dependencyinjection.engines.Engine;

public interface Vehicle {

  boolean turnOn();
  boolean turnOff();
  void setEngine(Engine engine);
  void removeEngine();
}

/*
https://www.javacodegeeks.com/2010/12/dependency-injection-manual-way.html
https://www.youtube.com/watch?v=kzZnWVm0h98
 */
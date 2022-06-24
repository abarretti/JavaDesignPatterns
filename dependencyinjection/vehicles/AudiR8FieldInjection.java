package main.java.dependencyinjection.vehicles;

import java.util.Arrays;
import main.java.dependencyinjection.enginedependencyinjection.EngineDependencyInjection;
import main.java.dependencyinjection.engines.Engine;
import main.java.dependencyinjection.engines.EngineName;

public class AudiR8FieldInjection implements Vehicle {

  @EngineDependencyInjection(engineName = EngineName.V10)
  private Engine engine;

  public AudiR8FieldInjection() {
    create();
  }

  private void create() {
    Arrays.asList(this.getClass().getDeclaredFields()).forEach(
        field -> {
          EngineDependencyInjection annotation =
              field.getDeclaredAnnotation(EngineDependencyInjection.class);
          if (field.getName().equals(Engine.class.getSimpleName().toLowerCase())) {
            this.engine = EngineDependencyInjection.ENGINE_MAP.get(annotation.engineName());
          }
        });
  }

  @Override
  public boolean turnOn() {
    if (engine == null) {
      throw new RuntimeException("You can't start a vehicle that doesn't have an engine!");
    }
    if (engine.isRunning()) {
      throw new RuntimeException("You can't start a vehicle that is already running!");
    }
    engine.start();
    System.out.println("Vehicle is now on.");
    return true;
  }

  @Override
  public boolean turnOff() {
    if (engine == null) {
      throw new RuntimeException("You can't turn off a vehicle that doesn't have an engine!");
    }
    if (!engine.isRunning()) {
      throw new RuntimeException("The vehicle is already off!");
    }
    engine.stop();
    System.out.println("Vehicle is now off.");
    return false;
  }

  @Override
  public void setEngine(Engine engine) {
    if (this.engine != null) {
      throw new RuntimeException("The vehicle already has an engine!");
    }
    this.engine = engine;
  }

  @Override
  public void removeEngine() {
    if (this.engine == null) {
      throw new RuntimeException("The vehicle doesn't have an engine to remove!");
    }
    this.engine.remove();
    this.engine = null;
  }
}

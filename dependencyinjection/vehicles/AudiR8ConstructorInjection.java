package main.java.dependencyinjection.vehicles;

import java.util.Arrays;
import main.java.dependencyinjection.enginedependencyinjection.EngineDependencyInjection;
import main.java.dependencyinjection.engines.Engine;

public class AudiR8ConstructorInjection implements Vehicle {

  private Engine engine;

  @EngineDependencyInjection
  public AudiR8ConstructorInjection() {
    create();
  }

  private void create() {
    this.engine =
        Arrays.stream(this.getClass().getDeclaredConstructors())
            .map(constructor -> EngineDependencyInjection.ENGINE_MAP.get(
                constructor.getDeclaredAnnotation(EngineDependencyInjection.class).engineName()))
            .findFirst().orElse(null);
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

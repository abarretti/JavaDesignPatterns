package main.java.dependencyinjection.engines;

public class V8 implements Engine {

  public static final EngineName ENGINE_NAME = EngineName.V8;

  private boolean running;

  @Override
  public void start() {
    if (running) {
      throw new RuntimeException("Engine is already running!");
    }
    running = true;
  }

  @Override
  public void stop() {
    if (!running) {
      throw new RuntimeException("Engine already off!");
    }
    running = false;
  }

  @Override
  public boolean isRunning() {
    return running;
  }

  @Override
  public void remove() {
  }
}

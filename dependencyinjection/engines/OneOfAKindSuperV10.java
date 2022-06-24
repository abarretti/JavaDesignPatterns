package main.java.dependencyinjection.engines;

public class OneOfAKindSuperV10 implements Engine {

  public static final EngineName ENGINE_NAME = EngineName.OneOfAKindSuperV10;
  private boolean running;
  private boolean inUse;
  private static final OneOfAKindSuperV10 instance = new OneOfAKindSuperV10();

  private OneOfAKindSuperV10() {}

  public static OneOfAKindSuperV10 install() {
    if (!instance.getInUse()) {
      instance.setInUse(true);
      return instance;
    }
    throw new RuntimeException("Engine is not available!");
  }

  public boolean getInUse() {
    return inUse;
  }

  public void setInUse(boolean bool) {
    inUse = bool;
  }

  public void remove() {
    if (!inUse) {
      throw new RuntimeException("Engine can't be removed if it's not in use!");
    }
    inUse = false;
  }

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
}

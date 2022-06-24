package main.java.dependencyinjection.engines;

public interface Engine {
  void start();
  void stop();
  boolean isRunning();
  void remove();
}

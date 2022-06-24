package main.java.dependencyinjection.engines;

public enum EngineName {
  V8("V8"),
  V10("V10"),
  OneOfAKindSuperV10("OneOfAKindSuperV10");

  public String name;

  EngineName(String name) {
    this.name = name;
  }
}

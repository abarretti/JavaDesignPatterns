package main.java.dependencyinjection.enginedependencyinjection;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;
import main.java.dependencyinjection.engines.Engine;
import main.java.dependencyinjection.engines.EngineName;
import main.java.dependencyinjection.engines.OneOfAKindSuperV10;
import main.java.dependencyinjection.engines.V10;
import main.java.dependencyinjection.engines.V8;

@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EngineDependencyInjection {

  Map<EngineName, Engine> ENGINE_MAP = Map.of(
      EngineName.V8, new V8(),
      EngineName.V10, new V10(),
      EngineName.OneOfAKindSuperV10, OneOfAKindSuperV10.install()
  );

  EngineName engineName() default EngineName.V8;

  // https://dzone.com/articles/creating-custom-annotations-in-java
}

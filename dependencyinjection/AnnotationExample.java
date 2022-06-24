package main.java.dependencyinjection;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AnnotationExample {

  @Target(ElementType.FIELD)
  @Retention(RetentionPolicy.RUNTIME)
  public static @interface IncludeToString {
    boolean obfuscate() default false;
  }


  public static class User {
    @IncludeToString
    private String firstName;
    @IncludeToString
    private String lastName;

    @IncludeToString(obfuscate = true)
    private String email;

    private String securityNumber;

    public String getFirstName() {
      return firstName;
    }

    public void setFirstName(String firstName) {
      this.firstName = firstName;
    }

    public String getLastName() {
      return lastName;
    }

    public void setLastName(String lastName) {
      this.lastName = lastName;
    }

    public String getEmail() {
      return email;
    }

    public void setEmail(String email) {
      this.email = email;
    }

    public String getSecurityNumber() {
      return securityNumber;
    }

    public void setSecurityNumber(String securityNumber) {
      this.securityNumber = securityNumber;
    }
  }

  public static void main(String[] args) throws Exception {
    User user = new User();
    user.firstName = "John";
    user.lastName = "Doe";
    user.email = "john.doe@unknown.com";
    user.securityNumber = "1234567890";

    System.out.println(toString(user));

  }

  public static String toString(Object value) throws Exception {
    if (value == null) {
      return null;
    }

//    Method setEmail = User.class.getDeclaredMethod("setEmail", String.class);
//    setEmail.invoke(value, "1231");
//
//    Field email = User.class.getDeclaredField("email");
//    email.set(value, "12343");
//
//    Object o = email.get(value);
//    email.getDeclaredAnnotations()


    return Stream.of(value.getClass().getDeclaredFields())
        .filter(field -> field.isAnnotationPresent(IncludeToString.class))
        .map(field -> {
          try {
            IncludeToString annotation = field.getDeclaredAnnotation(IncludeToString.class);
            String fieldValue = Objects.toString(field.get(value));
            return field.getName() + " = " + obfuscateIfNeeded(fieldValue, annotation.obfuscate());
          } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
          }
        })
        .collect(Collectors.joining(", ", value.getClass().getSimpleName() + "{", "}"));


  }

  private static String obfuscateIfNeeded(String fieldValue, boolean obfuscate) {
    return obfuscate ? obfuscate(fieldValue) : fieldValue;
  }


  public static String obfuscate(String value) {
    return "<obfuscated:" + value + ">";
  }
  
  
  @interface Qualifier{}
  @interface Produce{}
  
  @Qualifier
  @interface CreditCard {}
  @Qualifier
  @interface PayPal {}
  
  
  class MyUSage {
    private final Object object;

    MyUSage(@PayPal Object object) {
      this.object = object;
    }
  }

  
  class MyConfig {
    @Produce
    @PayPal
    public Object paypal() {
      return new Object();
    }
  }
}

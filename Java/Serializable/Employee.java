import java.util.*;
import java.lang.*;
import java.io.*;

/*
    This example demonstrates how to use Serializable interface.

    Most impressive is that the entire process is JVM independent, meaning an object can be serialized on one 
    platform and deserialized on an entirely different platform.

    Notice that for a class to be serialized successfully,two conditions must be met:

    -   The class must implement the java.io.Serializable interface.
    -   All of the fields in the class must be serializable. If a field is not serializable, it must be marked
        transient.

*/

public class Employee implements java.io.Serializable {
    public String name;
    public String address;
    public transient int SSN;
    public int number;
    public void maliCheck() {
        System.out.println("Mailing a check to " + name + " " + address);
    }
}

import java.util.*;
import java.lang.*;
import java.io.*;

public class SerializeDemo {
   public static void main(String [] args) {
      Employee e = new Employee();
      e.name = "Reyan Ali";
      e.address = "Phokka Kuan, Ambehta Peer";
      e.SSN = 11122333;
      e.number = 101;
      try {
         FileOutputStream fileOut = new FileOutputStream("/home/alexyu/Desktop/study/Java/Serializable/employees.ser");

         ObjectOutputStream out = new ObjectOutputStream(fileOut);
         out.writeObject(e);
         out.close();
         fileOut.close();
         System.out.println("Serialized data is saved in /tmp/employee.ser");
      } catch(IOException i) {
          i.printStackTrace();
      }
   }
}

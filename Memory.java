import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Memory {
   String[] addresses;
   String currAcc;
   int currCounter;
   
   
   public Memory(int size) {
      addresses = new String[size];
      currAcc = new String("+0000");
      currCounter = 0;   
      
      for (int i = 0; i < size; i++)
         addresses[i] = "0000";
   }
   
   public void addItem (int index, String value) {
      addresses[index] = value;
   }
   
   public String getItem (int index) {
      return addresses[index];  
   }
   
   public void update() {
      // load registers
      System.out.println("REGISTERS:");
      System.out.printf("accumulator:%11s%5s%n", " ", currAcc);
      System.out.printf("programCounter:%11s%02d%n", " ", currCounter);
      System.out.printf("instructionRegister:%3s%5s%n", " ", currAcc);
      System.out.printf("operationCode:%12s%02d%n", " ", currCounter);
      System.out.printf("operand:%18s%02d%n%n", " ", currCounter);
      
      // load memory addresses   
      System.out.println("MEMORY:");
      System.out.printf("%-6s", " ");
      for (int i = 0; i < 10; i++) {
         System.out.printf("%5d%4s", i, " ");
      }
      System.out.println();
            
      for (int i = 0; i < 100; i++) {
         if (i % 10 == 0) {
            System.out.printf("%02d", i);
            System.out.printf("%4s", " ");
         }
         if (addresses[i].startsWith("-"))
            System.out.print(addresses[i]);
         else   
            System.out.print("+" + addresses[i]);
         System.out.printf("%4s", " ");
         if (i % 10 == 9)
            System.out.println();
      }
   }
   
   public static void main(String ...args) throws FileNotFoundException {
      Memory mem = new Memory(100);
      File file = new File("test.sml");
      Scanner scan = new Scanner(file);
      try {
         while (scan.hasNextLine()) {
            String line = scan.nextLine();
            if (!line.isEmpty()) {
               String[] tokens = line.trim().split("\\s+"); 
               int index = Integer.parseInt(tokens[0]);
               String value = tokens[1];
               
               mem.addItem(index, value);
               
               for (int i = 0; i < tokens.length; i++) {
                  tokens[i] = null; 
               }
            }
            else if (line.isEmpty() && scan.hasNextLine())
               continue;
            else 
               break;
         } 
         mem.update();
      } catch (Exception e) {
         System.out.print(e.getMessage());
      }
   }
}

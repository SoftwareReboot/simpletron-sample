import java.util.Scanner;

public class Processor {
   private String register;
   private Scanner scan = new Scanner(System.in);
   
   
   public Processor() {
      
   }
   
   public static void performOperation(String instruction) {
      int intInstr = Integer.parseInt(instruction);
      int opCode = intInstr / 10;
      int operand = intInstr % 10;
      
      switch (opCode) {
         case 10: readAdd(operand); break;
         case 11: write(operand); break;
         case 20: loadM(operand); break;
         
         
      }  
   }
   
   public void readAdd(int address) {
      System.out.print("Enter value: ");
      String val = scan.next();   
      Memory.addItem(address, val);
   }
   
   public void write(int address){
      String value = Memory.getItem(address);
      System.out.println(value);
   }
   
   public String loadM(int address) {
      String value = Memory.getItem(address);
      Memory.setAcc(value);
   }
}

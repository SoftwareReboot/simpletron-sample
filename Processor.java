import java.util.Scanner;

public class Processor {
   private StringBuilder res;
   private String accumulator;
   private Scanner scan;
   private int counter;
   private Memory memory; 
   
   public Processor(Memory mem) {
      this.memory = mem;
      this.accumulator = "+0000";
      this.counter = 0;
      this.scan = new Scanner(System.in);
      this.res = new StringBuilder();
   }

   public String getAcc() {
      return accumulator;
   }

   public int getCounter() {
      return counter;
   }

   public String getRes() {
      return res.toString();
   }
   
   public void execute() {
      while (counter < memory.getMemSize()) {
         String instruction = memory.getItem(counter);
         int intInstr = Integer.parseInt(instruction);
         int opCode = intInstr / 100;    
         int operand = intInstr % 100;   

         switch (opCode) {
               case 10: 
                  readAdd(operand);
                  break;
               case 11: 
                  write(operand);
                  break;
               case 43: 
                  return;
               default:
                  System.out.println("Unknown opcode: " + opCode);
         }
         counter++;
      }
   }
   
   public void readAdd(int address) {
      System.out.print("Enter value: ");
      String val = scan.next();   
      memory.addItem(address, val);
   }
   
   public void write(int address){
      String value = memory.getItem(address);
      System.out.println(value);
   }
   
   public void loadM(int address) {
      String value = memory.getItem(address);
      accumulator = value;
   }
}

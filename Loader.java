import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Loader {
    public Memory mem;
    public Processor pro;

    public Loader(String filename) {
        mem = new Memory(100);
        loadFile(filename);

        pro = new Processor(mem);
        dump();
    }

    public void loadFile(String filename) {
        File file = new File(filename);
        try (Scanner scan = new Scanner(file)) { 
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                if (!line.isEmpty() && !line.startsWith(";")) {
                    String[] tokens = line.trim().split("\\s+");
                    if (tokens.length >= 2) {
                        int index = Integer.parseInt(tokens[0]);
                        String value = tokens[1];
                        mem.addItem(index, value);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error: File not found: " + filename); 
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    public void dump() {
        String[] addresses = mem.getAdds();
        String currAcc = pro.getAcc();
        int currCounter = pro.getCounter();
        String currReg = mem.getItem(currCounter);

        // Load registers
        System.out.println("REGISTERS:");
        System.out.printf("accumulator:%11s%5s%n", " ", currAcc);
        System.out.printf("programCounter:%11s%02d%n", " ", currCounter);
        System.out.printf("instructionRegister:%3s%5s%n", " ", currReg);
        System.out.printf("operationCode:%12s%02d%n", " ", Integer.parseInt(currReg) / 100);
        System.out.printf("operand:%18s%02d%n%n", " ", Integer.parseInt(currReg) % 100);

        // Load memory addresses   
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

    public static void main(String ...args) {
        if (args.length == 0) {
            System.out.println("Please provide a file name.");
            return;
        }

        new Loader(args[0]);
    }
}

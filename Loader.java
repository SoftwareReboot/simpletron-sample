import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Loader {
    public Loader(String filename) {
        Memory mem = new Memory(100);
        File file = new File(filename);
        
        try (Scanner scan = new Scanner(file)) { 
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                if (!line.isEmpty()) {
                    if (line.startsWith(";"))
                        continue;
                    else {
                        String[] tokens = line.trim().split("\\s+");
                        if (tokens.length == 1)
                            continue;
                        else {
                            int index = Integer.parseInt(tokens[0]);
                            String value = tokens[1];

                            mem.addItem(index, value);

                            for (int i = 0; i < tokens.length; i++) {
                                tokens[i] = null;
                            }
                        }
                    }
                } else if (line.isEmpty() && scan.hasNextLine())
                    continue;
                else
                    break;
            }
            update();
        } catch (FileNotFoundException e) {
            System.err.println("Error: File not found: " + filename); 
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    public void update() {
        String[] addresses = Memory.getAdds();
        String currAcc = Memory.getCurrAcc();
        int currCounter = Memory.getCurrCounter();

        // Load registers
        System.out.println("REGISTERS:");
        System.out.printf("accumulator:%11s%5s%n", " ", currAcc);
        System.out.printf("programCounter:%11s%02d%n", " ", currCounter);
        System.out.printf("instructionRegister:%3s%5s%n", " ", currAcc);
        System.out.printf("operationCode:%12s%02d%n", " ", currCounter);
        System.out.printf("operand:%18s%02d%n%n", " ", currCounter);

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

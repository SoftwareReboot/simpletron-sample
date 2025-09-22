import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Loader {
    public Memory mem;
    public Processor pro;

    public Loader(String filename, String showMode) {
        mem = new Memory(100);
        loadFile(filename);

        pro = new Processor(mem);
        if (showMode.equals("-s"))
            pro.dumpStep();
        else if (showMode.equals("-d"))
            pro.dumpDirect();
        else {
            System.out.println("INVALID DISPLAY MODE. TERMINATING PROGRAM...");
            System.exit(1);
        }    
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

    public static void main(String ...args) {
        if (args.length == 0) {
            System.out.println("Please provide a file name.");
            return;
        } else if (args.length == 1)
            new Loader(args[0], "-d");
        else 
            new Loader(args[0], args[1]);
    }
}

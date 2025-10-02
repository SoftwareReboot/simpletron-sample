import java.util.HashMap;
import java.util.Map;

public class Compiler {
    private static Map<String, Integer> addInstructions() {
        Map<String, Integer> instructions = new HashMap<>();
        // I/O Operations
        instructions.put("READ", 10);
        instructions.put("WRITE", 11);
        // Load/Store Operations
        instructions.put("LOADM", 20);
        instructions.put("STORE", 21);
        instructions.put("LOAD", 22);
        // Arithmetic Operations
        // Add 
        instructions.put("ADD", 30);
        instructions.put("ADDI", 35);
        // Sub
        instructions.put("SUB", 31);
        instructions.put("SUBI", 36);
        // Div 
        instructions.put("DIV", 32);
        instructions.put("DIVI", 37);
        // Mod
        instructions.put("MOD", 33);
        instructions.put("MODI", 38);
        // Mul
        instructions.put("MUL", 34);
        instructions.put("MULI", 39);
        // Control Operations
        instructions.put("JMP", 40);
        instructions.put("JN", 41);
        instructions.put("JZ", 42);
        instructions.put("HALT", 43);

        return instructions;
    }
} 

class Lexer {
    
}

class Parser {

}


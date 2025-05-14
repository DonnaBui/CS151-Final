package echoCustomizations;
import echo.*;
import java.net.Socket;

public class MathHandler extends RequestHandler {

    public MathHandler(Socket s) {
        super(s);
    }

    public MathHandler() {
        super();
    }

    @Override
    protected String response(String request) throws Exception {
        String invalidCmd = "Invalid command. Usage: <operation> <num1> <num2> <...>";
        
        // Make sure request.split() doesn't break
        if (request == null || request.trim().isEmpty()) return invalidCmd;
        
        String[] args = request.split("\\s+");
        double result = 0;

        // Make sure there's at least 3 arguments: operation and 2 or more numbers
        if (args.length < 3) return invalidCmd;
        
        String operation = args[0].toLowerCase();
        double num;

        for (int i = 1; i < args.length; i++) {
            // Make sure the argument is a valid number
            try { num = Double.valueOf(args[i]); }
            catch (NumberFormatException e) {
                return "Invalid input " + args[i] + ". Please provide valid numbers.";
            }

            if (i == 1) {
                result = num; // Set the first number to perform operations on
                continue; // Skip to the next number
            }

            switch (operation) {
                case "add":
                    result += num;
                    break;
                case "sub":
                    result -= num;
                    break;
                case "mul":
                    result *= num;
                    break;
                case "div":
                    if (num == 0) return "Cannot divide by 0. Please enter valid operands.";
                    else result /= num;
                    break;
                default:
                    return "Invalid operation. Valid commands: add | sub | mul | div";
            }
        }
        return String.valueOf(result);
    }
}

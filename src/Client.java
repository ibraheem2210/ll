import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        final String SERVER_IP = "localhost";
        final int SERVER_PORT = 12345;

        try {
            // Connect to the server
            Socket socket = new Socket(SERVER_IP, SERVER_PORT);
            System.out.println("Connected to server.");

            // Input and output streams for communication
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            while (true) {
                // Read user input
                System.out.print("Enter a line of characters (type 'exit' to quit): ");
                String input = userInput.readLine();

                // Send user input to server
                out.println(input);

                if (input.equalsIgnoreCase("exit")) {
                    break;
                }

                // Receive modified data from server
                String modifiedData = in.readLine();
                System.out.println("Modified data received: " + modifiedData);
            }

            // Close connections
            userInput.close();
            in.close();
            out.close();
            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

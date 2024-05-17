import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Chat_Client {
	public static void main(String argv[]) throws Exception {
        String to_server;
        String from_server;
        Socket clientsocket = new Socket("localhost", 5231);
        System.out.println("Connected to server.");
        
        BufferedReader infromuser = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader infromserver = new BufferedReader(new InputStreamReader(clientsocket.getInputStream()));
        DataOutputStream outtoserver = new DataOutputStream(clientsocket.getOutputStream());
        
        String fromuser, fromserver;
        
        while(true) {
        	System.out.println("Input from client: ");
        	fromuser = infromuser.readLine();
	        
        	outtoserver.writeBytes(fromuser + "\n");
        	
	        from_server = infromserver.readLine();
	        
	        System.out.println("From server: " + from_server);   
        }
	}
}

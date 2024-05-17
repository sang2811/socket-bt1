import java.io.*;
import java.net.*;

public class Chat_Server {
    public static void main(String argv[]) throws Exception {
        String from_client;
        String to_client;
        
        ServerSocket svsocket = new ServerSocket(5231);
        System.out.println("Server is running...");
        while(true) {
        	Socket connectionsocket = svsocket.accept();
        	System.out.println("Client connected.");
        	
        	BufferedReader infromclient = new BufferedReader(new InputStreamReader(connectionsocket.getInputStream()));
        	DataOutputStream outtoclient = new DataOutputStream(connectionsocket.getOutputStream());
        	
        	
        	while((from_client = infromclient.readLine()) != null) {
        		System.out.println("Client: " +from_client);
	        	to_client = from_client +" (Server accepted!)" + "\n";
	        	outtoclient.writeBytes(to_client);
        	}
        	
        }
    }
}

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Clock_Server {
	public static void main(String[] args) {
		try {
			ServerSocket svsocket = new ServerSocket(6544);
		    System.out.println("Server is running...");
		    
		    while(true) {
		    	Socket clientsocket = svsocket.accept();
		    	
		    	System.out.println("Client connected!");
		    	
		    	BufferedReader infromclient = new BufferedReader(new InputStreamReader(clientsocket.getInputStream()));
		    	
		    	DataOutputStream outtoclient = new DataOutputStream(clientsocket.getOutputStream());
		    	
		    	String mess = infromclient.readLine();
		    	
		    	if (mess.equals("time")) {
		    		outtoclient.writeBytes(new Date().toString() +"\n");
		    	}
		    	clientsocket.close();
		    }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
    
}

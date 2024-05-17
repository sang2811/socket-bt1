import java.awt.Container;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;
import java.awt.Font;

public class Clock_Client extends JFrame {
	public Clock_Client() {
		Container con = getContentPane();
		JLabel lb = new JLabel("");
		lb.setFont(new Font("Tahoma", Font.BOLD, 15));
		lb.setHorizontalAlignment(SwingConstants.CENTER);
		
		ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
		
		executor.scheduleAtFixedRate(() -> {
			try {
				Socket clientsocket = new Socket("localhost", 6544);
				System.out.println("Connected to server.");
				
				BufferedReader infromserver = new BufferedReader(new InputStreamReader(clientsocket.getInputStream()));
				
				clientsocket.getOutputStream().write("time\n".getBytes());

				String time = infromserver.readLine();
		        
		        SwingUtilities.invokeLater(() -> {
                    lb.setText(time);
                });
		        
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		},0,1,TimeUnit.SECONDS);
		
		con.add(lb);
		
		setVisible(true);
		setResizable(false);
		setSize(500,300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
            new Clock_Client();
        });
	}

}

package gui;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javax.swing.JOptionPane;

public class CheckIP {
		public static void main() throws Exception {
		//goes to http://checkip.amazonaws.com
		URL whatismyip = new URL("http://checkip.amazonaws.com");
		//reads in IP Address
		BufferedReader in = new BufferedReader(new InputStreamReader(whatismyip.openStream()));
		//Convert IP to String
		String ip = in.readLine();
		
		//Checks if IP is a GSU public IP Address
		if (ip.matches("141.165.(.*)")) {
			JOptionPane.showMessageDialog(null, ip, "GSU IP Checker", 1);
		}
		else 
			JOptionPane.showMessageDialog(null, "Sorry, your IP Address does not match one on Georgia Southern Statesboro Campus. "
					+ "\rPlease use a computer on campus to take this quiz.", "GSU IP Checker", 0);
			//System.exit(0);
			//Uncomment when done with testing
		}
	}

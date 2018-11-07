package comm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JOptionPane;

public class IPChecker {

	public int Check() {
		URL whatismyip;
		try {
			// goes to http://checkip.amazonaws.com
			whatismyip = new URL("http://checkip.amazonaws.com");
			// reads in IP Address
			BufferedReader in = new BufferedReader(new InputStreamReader(whatismyip.openStream()));
			// Convert IP to String
			String ip = in.readLine();

			// Checks if IP is a GSU public IP Address
			if (ip.matches("141.165.(.*)")) {
				return 0;
			} else {
				return 1;
			}
		} catch (Exception e) {
			return 2;
		}

	}
}
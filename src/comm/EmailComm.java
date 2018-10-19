package comm;

import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailComm {


	public void sendEmails(String email, String pass, String sub, String bod, String list) {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(email, pass);
			}
		});
		
			
			try {
				Message message = new MimeMessage(session);
				message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(list));
				message.setSubject(sub);
				message.setText(bod);
				Transport.send(message);
			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
	}
}

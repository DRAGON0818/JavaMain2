package Chapter04Net.mail;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;


public class MailTest {
    public static void main(String[] args) throws IOException, MessagingException {
        Properties properties = new Properties();
        try (InputStream in = Files.newInputStream(Paths.get("mail.properties"))) {
            properties.load(in);
        }
        List<String> lines = Files.readAllLines(Paths.get(args[0]), Charset.forName("UTF-8"));

        String from = lines.get(0);
        String to = lines.get(1);
        String subject = lines.get(2);

        StringBuilder bu = new StringBuilder();
        for (int i = 3; i < lines.size(); i++) {
            bu.append(lines.get(i));
            bu.append("\n");
        }

        Console console = System.console();
        String password = new String(console.readPassword("Password:"));

        Session mailSession = Session.getDefaultInstance(properties);
        MimeMessage message = new MimeMessage(mailSession);
        message.setFrom(new InternetAddress(from));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
        message.setSubject(subject);
        message.setText(bu.toString());
        Transport tr = mailSession.getTransport();
        try {
            tr.connect(null, password);
            tr.sendMessage(message, message.getAllRecipients());
        }
        finally {
            tr.close();
        }
    }
}

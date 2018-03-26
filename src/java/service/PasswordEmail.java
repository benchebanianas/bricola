/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Client;
import java.security.SecureRandom;
import java.util.List;
import java.util.Properties;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Topo
 */
@Stateless
public class PasswordEmail extends AbstractFacade<Client> {

    @PersistenceContext(name = "ServiceMarketv1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PasswordEmail() {
        super(Client.class);
    }
    public EmailHtmlService emailHtmlService = new EmailHtmlService();
    String username = "heizenhamza@gmail.com";
    String password = "0699272930";
    private static final String ALPHA_CAPS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String ALPHA = "abcdefghijklmnopqrstuvwxyz";
    private static final String NUMERIC = "0123456789";
    private static SecureRandom random = new SecureRandom();
    int len = 6;

    public static String generatePassword(int len, String dic) {
        String result = "";
        for (int i = 0; i < len; i++) {
            int index = random.nextInt(dic.length());
            result += dic.charAt(index);
        }
        return result;
    }

    public int recByEmail(Client client, String des, String emet) {
        List<Client> test = getEntityManager().createQuery("SELECT c FROM Client c WHERE c.email='" + client.getEmail() + "'").getResultList();
        if (test != null) {
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");
            Session session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });
            try {
                String pass = generatePassword(len, des);
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(emet));
                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(des));
                message.setSubject("Service Market");
                test.get(0).setPassword(pass);
                message.setContent(emailHtmlService.sendHtmlEmail(test.get(0).getNom() + " " + test.get(0).getPrenom(), pass), "text/html");
                Transport.send(message);
                System.out.println("Done");
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
            edit(test.get(0));
            return 1;
        } else if (test.get(0).isBlocked()) {
            return -2;
        } else {
            return -1;
        }
    }

}

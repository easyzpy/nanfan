package com.randing;

import org.junit.Test;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.util.Properties;

public class EmailUtils {
    public static final String EMAIL_SMTP = "smtp.office365.com";//邮件服务器
    public static final String EMAIL_PRO = "587";//端口号
    private final static String EMAIL_FROM = "clients@wicresoft.com";//发送邮箱
    private final static String EMAIL_FROM_USERNAME = EMAIL_FROM;//用户名
    private final static String EMAIL_FROM_PASSWORD = "9tSuj66#3M";//密码


    /*public static final String EMAIL_SMTP = "smtp.office365.com";//邮件服务器
    public static final String EMAIL_PRO = "587";//端口号
    private final static String EMAIL_FROM = "clients@wicresoft.com";//发送邮箱
    private final static String EMAIL_FROM_USERNAME = EMAIL_FROM;//用户名
    private final static String EMAIL_FROM_PASSWORD = "9tSuj66#3M";//密码
*/

    /**
     * @param to      接收人邮箱
     * @param subject 主题
     * @param content 内容
     * @param files   附件
     */
    public static void sendEmail(String to, String subject, String content, File[] files) {
        try {
            Properties props = new Properties();
            try {
                props.put("mail.smtp.host", EMAIL_SMTP);
                props.put("mail.smtp.port", EMAIL_PRO);
                props.put("mail.smtp.starttls.enable", "true");
            } catch (Exception e) {
                e.printStackTrace();
            }
            Session session;
            class EmailAuthenticator extends Authenticator {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(EMAIL_FROM_USERNAME, EMAIL_FROM_PASSWORD);
                }
            }
            Authenticator authenticator = new EmailAuthenticator();
            session = Session.getInstance(props, authenticator);
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(EMAIL_FROM));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);//会议标题
            message.setFlag(Flags.Flag.DELETED, true);
            message.setHeader("Importance", "High");
            // 创建消息部分
            BodyPart bodyPart = new MimeBodyPart();
            // 消息
            bodyPart.setText(content);
            // 创建多重消息
            Multipart multipart = new MimeMultipart();
            // 设置文本消息部分
            multipart.addBodyPart(bodyPart);
            // 附件部分
            if (files != null) {
                for (File file : files) {
                    bodyPart = new MimeBodyPart();
                    DataSource source = new FileDataSource(file);
                    bodyPart.setDataHandler(new DataHandler(source));
                    bodyPart.setFileName(MimeUtility.encodeText(source.getName()));
                    multipart.addBodyPart(bodyPart);
                }
            }
            //发送完整消息
            message.setContent(multipart);
            //发送消息
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param to      接收人邮箱
     * @param subject 主题
     * @param content 内容
     */

    public static void sendEmail(String to, String subject, String content) {
        sendEmail(to, subject, content, null);
    }

    @Test
    public void testSend() throws Exception {
//        EmailUtils.sendEmail("285442936@qq.com", "subject", "<p>content</p>");
        EmailUtils.testMail();

    }


    private static void testMail() throws Exception {
        // 给用户发送邮件的邮箱
        final String from = "285442936@qq.com";
        // 邮箱的用户名
        final String username = "285442936@qq.com";
        // 邮箱授权码，刚刚保存的授权码，不是qq密码
        final String password = "bfuzuhegxcatcaca";
        // 发送邮件的服务器地址，QQ服务器
        final String host = "smtp.qq.com";
        // 接收人邮箱
        final String to = "616708200@qq.com";
        // 邮件主题
        final String title = "验证码发送";

        // 使用QQ邮箱时配置
        Properties prop = new Properties();
        prop.setProperty("mail.host", "smtp.qq.com");    // 设置QQ邮件服务器
        prop.setProperty("mail.transport.protocol", "smtp");      // 邮件发送协议
        prop.setProperty("mail.smtp.auth", "true");      // 需要验证用户名和密码
        // 关于QQ邮箱，还要设置SSL加密，其他邮箱不需要
//            MailSSLSocketFactory sf = new MailSSLSocketFactory();
//            sf.setTrustAllHosts(true);
//            prop.put("mail.smtp.ssl.enable", "true");
//            prop.put("mail.smtp.ssl.socketFactory", sf);

        // 创建定义整个邮件程序所需的环境信息的 Session 对象，QQ才有，其他邮箱就不用了
        Session session = Session.getDefaultInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // 发件人邮箱用户名，授权码
                return new PasswordAuthentication(username, password);
            }
        });

        // 开启 Session 的 debug 模式，这样就可以查看程序发送 Email 的运行状态
        session.setDebug(true);

        // 通过 session 得到 transport 对象
        Transport ts = session.getTransport();

        // 使用邮箱的用户名和授权码连上邮箱服务器
        ts.connect(host, username, password);

        // 创建邮件，写邮件
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from)); // 指明邮件的发件人
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));   // 指明邮件的收件人
        message.setSubject(title);     // 邮件主题
        message.setContent("验证码为:8824", "text/html;charset=utf-8");    // 邮件内容

        // 发送邮件
        ts.sendMessage(message, message.getAllRecipients());
        System.out.println("验证码发送成功");
        // 释放资源
        ts.close();


    }
}
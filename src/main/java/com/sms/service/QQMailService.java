package com.sms.service;


import com.alibaba.fastjson.JSONObject;
import com.sms.util.CodeUtil;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

@Service
public class QQMailService {


    /**
     * ClassName：QQMail
     * Description：
     *
     * @author lihw
     * CreateTime: 2020/6/3 14:47
     * @version 1.0.0
     */
    public JSONObject sendQQMail(String addressee) {
        //发件人
        String mailUser = "1914526816@qq.com";
        //发件人昵称（可为空）
        String mailUser_nikeName = "李宏伟的网站";
        //收件人
        String toMailUser = addressee;
        //主题
        String subject = "李宏伟网站的验证码";
        //邮件内容
        System.out.println(CodeUtil.getCode());
        String content = "您用于李宏伟的网站的验证码是：" + CodeUtil.getCode() + "，十分钟内有效。";
        JSONObject jsonObject = new JSONObject();
        try {
            //创建Properties类用于记录邮箱的一些属性
            Properties props = new Properties();
            //表示SMTP发送邮件，必须进行身份验证
            props.put("mail.smtp.auth", "true");
            //此处填写SMTP服务器
            props.put("mail.smtp.host", "smtp.qq.com");
            //端口号，QQ邮箱端口587
            props.put("mail.smtp.port", "587");
            //此处填写，写信人的账号
//            props.put("mail.user", "1914526816@qq.com");
            props.put("mail.user", mailUser);
            //此处填写16位STMP口令（也就是授权码）
            props.put("mail.password", "zvdsvvmqyownfaji");

            //构建授权信息，用于进行SMTP进行身份验证
            Authenticator authenticator = new Authenticator() {

                protected PasswordAuthentication getPasswordAuthentication() {
                    // 用户名、密码
                    String userName = props.getProperty("mail.user");
                    String password = props.getProperty("mail.password");
                    return new PasswordAuthentication(userName, password);
                }
            };
            // 使用环境属性和授权信息，创建邮件会话
            Session mailSession = Session.getInstance(props, authenticator);
            // 创建邮件消息
            MimeMessage message = new MimeMessage(mailSession);
            // 设置发件人
            InternetAddress form = new InternetAddress(props.getProperty("mail.user"), mailUser_nikeName, "UTF-8");
            message.setFrom(form);

            // 设置收件人的邮箱
            InternetAddress to = new InternetAddress(toMailUser);
            message.setRecipient(RecipientType.TO, to);

            // 设置邮件标题（主题）
            message.setSubject(subject);

            // 设置邮件的内容体
            message.setContent(content, "text/html;charset=UTF-8");

            // 最后当然就是发送邮件啦
            Transport.send(message);

            jsonObject.put("status", 200);
        } catch (UnsupportedEncodingException | MessagingException e) {
            jsonObject.put("status", 401);
            e.printStackTrace();
        }
        return jsonObject;
    }

}


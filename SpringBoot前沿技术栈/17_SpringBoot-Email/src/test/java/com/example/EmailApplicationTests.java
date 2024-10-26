package com.example;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.net.URISyntaxException;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import java.io.File;
import java.util.Date;
import java.util.Objects;

@SpringBootTest
class EmailApplicationTests {

    // 注入邮件发送对象
    @Autowired
    private JavaMailSender mailSender;

    /***
     * 简单邮件发送：A写了一封邮件，将其同时发送给了B1，B2，B3；并且抄送给了C1，C2，C3；密送给了D1，D2，D3。
     *     对于A来说，A知道了自己发送邮件给了B1，B2，B3，并且将该邮件的内容抄送给了C1,C2,C3，密送给了D1,D2, D3。
     *     B1知道，这份邮件是发给B1，B2，B3的，并且将该邮件的内容抄送给了C1，C2，C3，但是不知道密送给了D1，D2，D3。
     *     C1知道，这封邮件是发送给B1，B2，B3的，并且将该邮件的内容抄送给了C1，C2，C3，但是不知道密送给了D1，D2，D3。
     *     D1知道，这封邮件是A发送给B1，B2，B3的，并且将该邮件的内容抄送给了C1，C2，C3，但是不知道密送给了D2，D3。
     */
    @Test
    void testMailA() {
        // 创建简单的邮件发送对象
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("195630075@qq.com");            // 设置发件人邮箱（若配置默认邮箱则不用再设置）
        message.setTo("youshicheng97@163.com");         // 设置收件人邮箱
        // message.setCc("xiaofeng500@qq.com");         // 设置抄送人邮箱（可以不填写）
        // message.setBcc("575814158@qq.com");          // 设置密送人邮箱（可以不填写）
        message.setSubject("缴费通知");                  // 设置邮件主题
        message.setText("您手机已欠费100元，请及时缴费！"); // 设置邮件文本内容
        message.setSentDate(new Date());               // 设置邮件发送时间
        // 发送
        mailSender.send(message);
    }

    @Test
    void testMailB() throws MessagingException, URISyntaxException {
        // 创建复杂有限发送对象
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
        messageHelper.setFrom("195630075@qq.com");           // 设置发件人邮箱（若配置默认邮箱则不用再设置）
        messageHelper.setTo("youshicheng97@163.com");        // 设置收件人邮箱
        // messageHelper.setCc("xiaofeng500@qq.com");        // 设置抄报人邮箱（可以不填写）
        // messageHelper.setBcc("575814158@qq.com");         // 设置密送人邮箱（可以不填写）
        messageHelper.setSubject("暴富通知");                 // 设置邮件主题

        // 获取项目资源根目录 resources/file  并准备资源
        String rootPath = Objects.requireNonNull(EmailApplicationTests.class.getClassLoader().getResource("static")).toURI().getPath();
        FileSystemResource jpg = new FileSystemResource(new File(rootPath + "/v2.jpg"));
        FileSystemResource xls = new FileSystemResource(new File(rootPath + "/demo.xlsx"));
        FileSystemResource mp3 = new FileSystemResource(new File(rootPath + "/起风了.mp3"));
        FileSystemResource zip = new FileSystemResource(new File(rootPath + "/text.zip"));

        // 关于附件  资源  HTML 文本的设置
        // 设置附件
        // 设置一个 图片附件
        messageHelper.addAttachment(Objects.requireNonNull(jpg.getFilename()), jpg);
        // 设置一个 excel附件
        messageHelper.addAttachment(Objects.requireNonNull(xls.getFilename()), xls);
        // 设置一个 mp3附件
        messageHelper.addAttachment(Objects.requireNonNull(mp3.getFilename()), mp3);
        // 设置一个 zip附件  不过发送垃圾附件可能会被识别 554 HL:IHU 发信IP因发送垃圾邮件或存在异常的连接行为
        messageHelper.addAttachment(Objects.requireNonNull(zip.getFilename()), zip);

        // 设置邮件内容   cid:资源id     在内容中引用资源    后面true代表是html内容
        messageHelper.setText("<h2 style='color:#f00;'>暴富通知：您已进入次元空间，完成究极体升级进化，未来可期！<br/><img src='cid:p01' alt='' style='width:200px;height:50px;'></h2>", true);

        // 设置资源
        FileSystemResource resPng = new FileSystemResource(new File(rootPath + "/p.png"));
        messageHelper.addInline("p01", resPng);

        // 发送
        mailSender.send(mimeMessage);
    }
}

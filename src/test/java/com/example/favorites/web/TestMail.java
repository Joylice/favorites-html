//package com.example.favorites.web;
//
//import com.example.favorites.web.mail.MailService;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.ui.velocity.VelocityEngineUtils;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * Created by cuiyy on 2017/11/16.
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest
//public class TestMail {
//    @Autowired
//    private MailService mailService;
//
//    private String to = "xujijun@mail.cn";
//
//    @Test
//    public void sendSimpleMail() {
//        mailService.sendSimpleMail(to, "主题：简单邮件", "测试邮件内容");
//    }
//
//
//    @Test
//    public void sendHtmlMail() {
//        Map<String, Object> model = new HashMap<String, Object>();
//        model.put("time", new Date().toString());
//        model.put("message", "这是测试的内容。。。");
//        model.put("toUserName", "张三");
//        model.put("fromUserName", "老许");
//        //String content = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "welcome.vm", "UTF-8", model);
//
//        mailService.sendHtmlMail(to, "主题：html邮件", "");
//    }
//
//    @Test
//    public void sendAttachmentsMail() {
//        mailService.sendAttachmentsMail(to, "主题：带附件的邮件", "有附件，请查收！", "C:\\Users\\Xu\\Desktop\\csdn\\1.png");
//    }
//
//    @Test
//    public void sendInlineResourceMail() {
//        String rscId = "rscId001";
//        mailService.sendInlineResourceMail(to,
//                "主题：嵌入静态资源的邮件",
//                "<html><body>这是有嵌入静态资源：<img src=\'cid:" + rscId + "\' ></body></html>",
//                "C:\\Users\\Xu\\Desktop\\csdn\\1.png",
//                rscId);
//    }
//}

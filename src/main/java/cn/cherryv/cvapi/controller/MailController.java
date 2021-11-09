package cn.cherryv.cvapi.controller;

import cn.cherryv.cvapi.model.Intercept;
import cn.cherryv.cvapi.model.Mingxi;
import cn.cherryv.cvapi.model.test.DemoData;
import cn.cherryv.cvapi.model.test.DemoMergeData;
import cn.cherryv.cvapi.model.test.person;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.merge.LoopMergeStrategy;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.WriteTable;
import lombok.SneakyThrows;
import org.junit.Test;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.internet.MimeMessage;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/mail")
public class MailController {

    private final JavaMailSender javaMailSender;

    private final MailProperties mailProperties;

    public MailController(JavaMailSender javaMailSender,
                          MailProperties mailProperties) {
        this.javaMailSender = javaMailSender;
        this.mailProperties = mailProperties;
    }

    @PostMapping("/send")
    public String send() {
        byte[] bytes = excelToByteArray(data());
        email(bytes);
        return "OK";
    }

    @SneakyThrows
    private void email(byte[] attachment) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(mailProperties.getUsername());
        helper.setTo("18855097348@163.com");
        helper.setText("内容：测试");
        helper.setSubject("主题：测试");
        // 附件
        helper.addAttachment("测试.xlsx", new ByteArrayResource(attachment));
        javaMailSender.send(message);
    }

    /**
     * 将 Excel 写入 ByteArrayOutputStream
     */
    private byte[] excelToByteArray(List<person> list) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        EasyExcel.write(outputStream, person.class).sheet("测试").doWrite(list);
        return outputStream.toByteArray();
    }

    /**
     * 数据
     */
    public static List<person> data() {
        List<person> list = new ArrayList<>();
        list.add(new person("daixuanyu", "21", "shanghai"));
        list.add(new person("daixuanyu", "21", "shanghai"));
        list.add(new person("daixuanyu", "21", "shanghai"));
        return list;
    }

    @RequestMapping("testmail")
    public void mergeWrite() {
        String fileName = "D:\\test1.xlsx";
        ExcelWriter excelWriter = null;
        try {
            excelWriter = EasyExcel.write(fileName, Intercept.class).build();
            // 把sheet设置为不需要头 不然会输出sheet的头 这样看起来第一个table 就有2个头了
            WriteSheet writeSheet = EasyExcel.writerSheet("模板").needHead(Boolean.FALSE).build();
            // 这里必须指定需要头，table 会继承sheet的配置，sheet配置了不需要，table 默认也是不需要
            WriteTable writeTable0 = EasyExcel.writerTable(0).needHead(Boolean.TRUE).build();
            WriteTable writeTable1 = EasyExcel.writerTable(1).needHead(Boolean.TRUE).build();
            // 第一次写入会创建头
            excelWriter.write(data(), writeSheet, writeTable0);
            // 第二次写如也会创建头，然后在第一次的后面写入数据
            excelWriter.write(data(), writeSheet, writeTable1);
        } finally {
            // 千万别忘记finish 会帮忙关闭流
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }
    }

}


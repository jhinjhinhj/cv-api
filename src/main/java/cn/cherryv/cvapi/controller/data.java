package cn.cherryv.cvapi.controller;

import cn.cherryv.cvapi.model.Mingxi;
import cn.cherryv.cvapi.model.SaleMans;
import cn.cherryv.cvapi.model.Stroes;
import cn.cherryv.cvapi.model.WeiFa;
import cn.cherryv.cvapi.model.test.person;
import cn.cherryv.cvapi.service.MingXiService;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.WriteSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.excel.metadata.Sheet;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class data {

    @Autowired
    private MingXiService mingXiService;

    @Autowired
    private JavaMailSender javaMailSender;


    @RequestMapping("s1")
    @ResponseBody
    public Object s1() {
        return mingXiService.selectMingXi();
    }

    @RequestMapping("s2")
    @ResponseBody
    public Object s2() {
        return mingXiService.selectSaleMans();
    }

    @RequestMapping("s3")
    @ResponseBody
    public Object s3() {
        return mingXiService.selectStore();
    }

    @RequestMapping("s4")
    @ResponseBody
    public Object s4() {
        return mingXiService.selectWeiFa();
    }

    @RequestMapping("all")
    @ResponseBody
    public void testExcel() {
        String fileName = ".\\xxxx.xlsx";

        ExcelWriter excelWriter = EasyExcel.write(fileName).build();

        //sheet0
        List<Mingxi> sheet0 = mingXiService.selectMingXi();

        //sheet1
        List<SaleMans> sheet1 = mingXiService.selectSaleMans();

        //sheet2
        List<Stroes> sheet2 = mingXiService.selectStore();
        System.out.println("sheet2 = " + sheet2);

        //sheet3
        List<WeiFa> mingxis = mingXiService.selectWeiFa();


        WriteSheet writeSheet = EasyExcel.writerSheet(0, "明细表").head(Mingxi.class).build();
        excelWriter.write(sheet0, writeSheet);

        writeSheet = EasyExcel.writerSheet(1, "导购汇总表").head(SaleMans.class).build();
        excelWriter.write(sheet1, writeSheet);

        writeSheet = EasyExcel.writerSheet(2, "门店汇总表").head(Stroes.class).build();
        excelWriter.write(sheet2, writeSheet);

        writeSheet = EasyExcel.writerSheet(3, "未发放明细").head(WeiFa.class).build();
        excelWriter.write(mingxis, writeSheet);

        excelWriter.finish();

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper;
        try {
            messageHelper = new MimeMessageHelper(message, true);
            messageHelper.setSubject("all");
            messageHelper.setFrom("1344932584@qq.com");
            messageHelper.setTo("18855097348@163.com");
            messageHelper.setSentDate(new Date());
            messageHelper.setText("邮件测试");

            FileSystemResource file = new FileSystemResource(fileName);
            String filePath = fileName.substring(fileName.lastIndexOf(File.separator));
            messageHelper.addAttachment(filePath,file);
            javaMailSender.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }


    @RequestMapping("sendmail")
    @ResponseBody
    public void test() throws MessagingException, IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ExcelWriter excelWriter = EasyExcel.write(outputStream).build();

        /*EasyExcel.write(outputStream, person.class).sheet(0,"测试").doWrite(mingXiService.selectMingXi());
        EasyExcel.write(outputStream, person.class).sheet(1,"nihao").doWrite(mingXiService.selectSaleMans());
        EasyExcel.write(outputStream, person.class).sheet(2,"nihao").doWrite(mingXiService.selectStore());
        EasyExcel.write(outputStream, person.class).sheet(3,"nihao").doWrite(mingXiService.selectWeiFa());*/


        //sheet0
        WriteSheet writeSheet = EasyExcel.writerSheet(0, "明细表").head(Mingxi.class).build();
        ExcelWriter write = excelWriter.write(mingXiService.selectMingXi(), writeSheet);


        //sheet1
        writeSheet = EasyExcel.writerSheet(1, "导购汇总表").head(SaleMans.class).build();
        excelWriter.write(mingXiService.selectSaleMans(), writeSheet);

        //sheet2
        writeSheet = EasyExcel.writerSheet(2, "门店汇总表").head(Stroes.class).build();
        excelWriter.write(mingXiService.selectStore(), writeSheet);

        //sheet3
        writeSheet = EasyExcel.writerSheet(3, "未发放明细").head(WeiFa.class).build();
        excelWriter.write(mingXiService.selectWeiFa(), writeSheet);

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setSubject("sendmail");
        helper.setFrom("1344932584@qq.com");
        helper.setTo("18855097348@163.com");
        helper.setSentDate(new Date());
        helper.setText("邮件测试");

        helper.addAttachment("shuju.xlsx", new ByteArrayResource(outputStream.toByteArray()));
        excelWriter.finish();

        javaMailSender.send(message);

    }
}

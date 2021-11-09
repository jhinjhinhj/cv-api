package cn.cherryv.cvapi.utils;

import cn.cherryv.cvapi.service.MingXiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SpringTask {

//    @Scheduled(fixedRate = 3000)
//    public void show() {
//        System.out.println("任务执行时间：" + LocalDateTime.now());
//    }

    @Scheduled(cron = "0 0 2 1 * ?")
    public void generatePlan() {
        System.out.println("kaishijieshou");
    }

    @Scheduled(cron="0 0/2 8-20 * * ?")
    public void test(){
        System.out.println("nihao");
    }

}

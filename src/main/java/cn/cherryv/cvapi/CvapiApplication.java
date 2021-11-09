package cn.cherryv.cvapi;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan(value = "cn.cherryv.cvapi.mapper")
@EnableScheduling
public class CvapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CvapiApplication.class, args);
    }

}

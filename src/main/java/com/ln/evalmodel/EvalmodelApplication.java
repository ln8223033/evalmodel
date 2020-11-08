package com.ln.evalmodel;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.ln.evalmodel"})
@MapperScan(basePackages = {"com.ln.evalmodel.dao"})
public class EvalmodelApplication {
    public static void main(String[] args) {
        SpringApplication.run(EvalmodelApplication.class, args);
    }

}

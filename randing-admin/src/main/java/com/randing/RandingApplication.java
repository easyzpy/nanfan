package com.randing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 启动程序
 * 
 * @author randing
 */
@EnableAsync
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class RandingApplication
{
    public static void main(String[] args)
    {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(RandingApplication.class, args);
        System.out.println(" Randing启动成功" );
    }
}

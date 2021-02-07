package com.zaw.superarch;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import springfox.documentation.oas.annotations.EnableOpenApi;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * 启动类
 *
 * @author zhangaiwen
 */
@EnableOpenApi
@SpringBootApplication
public class Application<beans> {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        // todo 打印出具体的垃圾收集器
        List<GarbageCollectorMXBean> beans = ManagementFactory.getGarbageCollectorMXBeans();
        for (GarbageCollectorMXBean bean : beans) {
            System.out.println("\n 垃圾收集器:" + bean.getName());
        }
        // todo 打印系统运行时参数
        Properties properties = System.getProperties();
    }

    /**
     * 监控springboot提供的bean
     *
     * @param ctx 上下文
     * @return 对象
     */
    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            System.out.println("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
//                System.out.println(beanName);
            }
        };
    }
}

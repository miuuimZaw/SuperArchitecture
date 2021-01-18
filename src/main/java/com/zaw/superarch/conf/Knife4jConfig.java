package com.zaw.superarch.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author zhangaiwen
 */
@Configuration
public class Knife4jConfig {

    @Bean(value = "defaultApi3")
    public Docket defaultApi2() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("SuperArchitecture 超级架构服务")
                        .description("超级架构服务 APIs")
                        .termsOfServiceUrl("/")
                        .contact(new Contact("aiwen", "http://localhost:3030/doc.html", "zhang7aiwen@163.com"))
                        .version("1.0")
                        .build())
                //分组名称
                .groupName("3.X版本")
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.zaw.superarch.controller"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }

}

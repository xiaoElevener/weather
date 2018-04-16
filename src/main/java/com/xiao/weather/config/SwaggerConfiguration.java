package com.xiao.weather.config;

/**
 * swagger
 *
 * @author xiao_elevener
 * @date 2018-04-16 14:30
 */

import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by max on 8/16/16.
 */
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket getApiInfo() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("outer api")
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(outApiInfo());

    }

    private ApiInfo outApiInfo() {
        return new ApiInfo(
                "weather 前后端接口-外部",
                "外部接口文档",
                "1.0.0",
                null,
                new Contact("Lin LingXiao", "", "xiao_elevener@163.com"),
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0.html"
        );

    }

    @Bean
    public UiConfiguration getUiConfig() {
        return new UiConfiguration(
                null,// url,暂不用
                "none",       // docExpansion          => none | list
                "alpha",      // apiSorter             => alpha
                "schema",     // defaultModelRendering => schema
                UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS,
                false,        // enableJsonEditor      => true | false
                true);        // showRequestHeaders    => true | false
    }
}
package cn.andyjee.smartdev.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger配置
 *
 * @author AndyJee
 */
@EnableSwagger2
@Configuration
@ComponentScan(basePackages = {"**.controller"})
public class SwaggerConfig {


    private String projectName;
    private String projectVersion;
    private String projectDescription;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo());
    }

    /**
     * 构建 api文档的详细信息函数
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(projectName + " RESTful API接口")
                .version(projectVersion)
                .description(projectDescription)
                .build();
    }
}
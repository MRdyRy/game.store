package com.rudy.ryanto.report.config;

import com.rudy.ryanto.report.util.Constant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import static org.thymeleaf.templatemode.TemplateMode.HTML;

@Configuration
public class PdfConfig {
    @Bean
    public ClassLoaderTemplateResolver classLoaderTemplateResolver(){
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(HTML);
        templateResolver.setCharacterEncoding(Constant.UTF_8);
        return templateResolver;
    }

    @Bean
    public TemplateEngine templateEngine(){
        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(classLoaderTemplateResolver());
        return templateEngine;
    }

    @Bean
    public Context context(){
        return new Context();
    }
}

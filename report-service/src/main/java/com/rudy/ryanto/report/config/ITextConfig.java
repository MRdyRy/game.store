package com.rudy.ryanto.report.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.xhtmlrenderer.extend.FontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.IOException;

@Configuration
public class ITextConfig {

    @Bean
    public ITextRenderer iTextRenderer() throws IOException {
        ITextRenderer iTextRenderer = new ITextRenderer();
        FontResolver fontResolver = iTextRenderer.getFontResolver();
        iTextRenderer.getFontResolver().addFont("assets/SCRUBLAND.ttf",true);
        return iTextRenderer;
    }

}

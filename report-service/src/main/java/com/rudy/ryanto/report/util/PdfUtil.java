package com.rudy.ryanto.report.util;

import lombok.extern.slf4j.Slf4j;
import org.w3c.tidy.Tidy;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;

import static com.rudy.ryanto.report.util.Constant.UTF_8;

@Slf4j
public class PdfUtil {

    public static  SimpleDateFormat formatReportDate = new SimpleDateFormat("yyyyMMdd");

    public static String converToXHtml(String renderHtmlContent) throws UnsupportedEncodingException {
        log.info("convert to xhtml......!");
        Tidy tidy = new Tidy();
        tidy.setInputEncoding(UTF_8);
        tidy.setOutputEncoding(UTF_8);
        tidy.setXHTML(true);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(renderHtmlContent.getBytes(UTF_8));
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        tidy.parseDOM(inputStream, outputStream);
        return outputStream.toString(UTF_8);
    }
}

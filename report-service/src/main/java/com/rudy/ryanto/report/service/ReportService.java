package com.rudy.ryanto.report.service;

import com.rudy.ryanto.report.domain.Cart;
import com.rudy.ryanto.report.util.PdfUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Date;

@Slf4j
@Service
public class ReportService {

    @Autowired
    TemplateEngine templateEngine;

    @Autowired
    ClassLoaderTemplateResolver classLoaderTemplateResolver;

    @Autowired
    ITextRenderer iTextRenderer;

    @Autowired
    Context context;

    private final String REPORT_TEMPLATE_NAME = "game-transaction";
    public byte[] generatePdf(Cart cart, HttpServletResponse httpServletResponse) {
      log.info("generate pdf transaction in ReportService!");
        byte[]report = null;
        try {
            context.setVariable("data", cart);
            String renderHtml = templateEngine.process(REPORT_TEMPLATE_NAME,context);
            String xhtml = PdfUtil.converToXHtml(renderHtml);
            String reportName = "Game-transaction No".concat(cart.getIdCart().toString())
                    .concat(" - ").concat(PdfUtil.formatReportDate.format(new Date()));
            File outputFile = new File(reportName.concat(".pdf"));
            log.info("report output :{} ",outputFile.getPath());

            iTextRenderer.setDocumentFromString(xhtml);
            iTextRenderer.layout();
            ByteArrayOutputStream result = new ByteArrayOutputStream();
            iTextRenderer.createPDF(result);
            report = result.toByteArray();
            result.close();
            httpServletResponse.setContentType("application/pdf");
            httpServletResponse.setHeader("Content-Disposition", "attachment; filename=\"" + reportName + ".pdf\"");
            httpServletResponse.setHeader("Content-Filename", reportName + ".pdf");
        }catch (Exception e){
            log.error("failed generate pdf Game Transaction id : {} caused by : ",cart.getIdCart(),e.getMessage());
            e.printStackTrace();
        }
        return report;
    }
}

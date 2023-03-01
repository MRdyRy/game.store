package com.rudy.ryanto.report.controller;

import com.rudy.ryanto.report.domain.Cart;
import com.rudy.ryanto.report.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
@RequestMapping("/api/generate")
public class ReportTransactionController {

    @Autowired
    ReportService reportService;

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/transaction", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_PDF_VALUE)
    public byte[] generatePdf(@RequestBody Cart cart, HttpServletResponse httpServletResponse){
        log.info("genereate transaction report : {}",cart);
        return reportService.generatePdf(cart,httpServletResponse);
    }

}

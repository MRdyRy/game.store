package com.rudy.ryanto.transaction.management.controller;

import com.rudy.ryanto.transaction.management.service.TransactionManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transaction-management")
public class TransactionManagmentController {

    @Autowired
    TransactionManagementService transactionManagementService;
}

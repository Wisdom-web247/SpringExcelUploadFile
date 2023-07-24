package com.example.excelupload.controller;

import com.example.excelupload.model.Transactions;
import com.example.excelupload.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "api/vi/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {

        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<List<Transactions>> getTransactionsFromExcel(@RequestParam("file")MultipartFile files) throws IOException {
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(transactionService.getTransactionsFromExcel(files), status);
    }
}

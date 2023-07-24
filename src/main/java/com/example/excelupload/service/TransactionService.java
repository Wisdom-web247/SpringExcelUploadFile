package com.example.excelupload.service;

import com.example.excelupload.model.Transactions;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface TransactionService {

    //List imports transactions from the Excel file
    List<Transactions> getTransactionsFromExcel(MultipartFile files) throws IOException;



}

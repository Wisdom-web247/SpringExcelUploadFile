package com.example.excelupload.service;

import com.example.excelupload.model.Transactions;
import com.example.excelupload.repository.TransactionRepo;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional //In anything fails it does not commit the file
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepo transactionRepo;

    //Constructor Injecting the repo into the service
    @Autowired
    public TransactionServiceImpl (TransactionRepo transactionRepo){
        this.transactionRepo = transactionRepo;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public List<Transactions> getTransactionsFromExcel(MultipartFile files) throws IOException {

        List<Transactions> transactionsList = new ArrayList<>();

        XSSFWorkbook workbook = new XSSFWorkbook(files.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);

        //Looping through the number of rows in the worksheet
        for (int i = 0; i < worksheet.getPhysicalNumberOfRows(); i++) {

            if (i > 0) {

                Transactions transactions = new Transactions();
                XSSFRow row = worksheet.getRow(i);

                //Get index value of the cell
                //
                //  Integer id = (int) row.getCell(0).getNumericCellValue();
            //    Double amount = row.getCell(2).getNumericCellValue();

                transactions.setId((long) row.getCell(0).getNumericCellValue());
                 transactions.setTransactionDate(row.getCell(1).getDateCellValue());
                 transactions.setDescription(row.getCell(2).getStringCellValue());
                 transactions.setAmount(row.getCell(3).getNumericCellValue());
                 transactions.setTransactionType(row.getCell(4).getStringCellValue());
                 transactions.setMonthlyBalance(row.getCell(5).getNumericCellValue());

                 //Compile the list of transactions to display
                 transactionsList.add(transactions);

                 //Saving the transactions file to the DB
                 transactionRepo.save(transactions);
            }
        }

        return transactionsList;
    }
}

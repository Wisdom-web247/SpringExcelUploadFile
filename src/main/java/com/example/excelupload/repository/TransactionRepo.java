package com.example.excelupload.repository;

import com.example.excelupload.model.Transactions;
import org.omg.PortableInterceptor.LOCATION_FORWARD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


//Connects to the DATABASE
@Repository
public interface TransactionRepo extends JpaRepository<Transactions, Long> {


}

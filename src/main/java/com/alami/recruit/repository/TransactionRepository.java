package com.alami.recruit.repository;

import com.alami.recruit.entity.jpa.Transaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface TransactionRepository extends CrudRepository<Transaction, String> {

    @Query("select t from Transaction t where t.cifId = :cifId")
    List<Transaction> findAllByCifId(@Param("cifId") String cifId);

    @Query("select t from Transaction t where t.timestamp >= :startDate and t.timestamp <= :endDate")
    List<Transaction> findTransactionsByTimestampBetween(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}

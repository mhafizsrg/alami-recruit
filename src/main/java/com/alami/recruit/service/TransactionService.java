package com.alami.recruit.service;

import com.alami.recruit.entity.jpa.Cif;
import com.alami.recruit.entity.jpa.Transaction;
import com.alami.recruit.model.DateParam;
import com.alami.recruit.model.TransactionParam;
import com.alami.recruit.repository.CifRepository;
import com.alami.recruit.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final CifRepository cifRepository;

    private static final String DATE_PATTERN = "yyyy-MM-dd";

    public TransactionService(TransactionRepository transactionRepository, CifRepository cifRepository) {
        this.transactionRepository = transactionRepository;
        this.cifRepository = cifRepository;
    }

    public Transaction createTransaction(TransactionParam param) throws ParseException {
        try {
            Cif cif = cifRepository.findById(param.getCifId()).orElse(null);
            if (cif != null) {
                Transaction transaction = new Transaction();
                transaction.setAmount(param.getAmount());
                transaction.setCifId(param.getCifId());
                transaction.setCreditDebit(param.getCreditDebit());
                transaction.setTimestamp(new SimpleDateFormat(DATE_PATTERN).parse(param.getYear() + "-" + param.getMonth() + "-" + param.getDay()));
                if (param.getCreditDebit().equalsIgnoreCase("DEBIT")) {
                    cif.setBalance(cif.getBalance().add(param.getAmount()));
                } else {
                    cif.setBalance(cif.getBalance().subtract(param.getAmount()));
                }
                cifRepository.save(cif);
                return transactionRepository.save(transaction);
            } else {
                return null;
            }
        } catch (ParseException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public List<Transaction> getTransactionByCifId(String cifId) {
        return transactionRepository.findAllByCifId(cifId);
    }

    public List<Transaction> getTransactionBetween(DateParam param) throws Exception {
        try {
            Date startDate = new SimpleDateFormat(DATE_PATTERN).parse(param.getStartYear() + "-" + param.getStartMonth() + "-" + param.getStartDay());
            Date endDate = new SimpleDateFormat(DATE_PATTERN).parse(param.getEndYear() + "-" + param.getEndMonth() + "-" + param.getEndDay());
            log.info("startDate = {}", startDate);
            log.info("endDate = {}", endDate);
            if (endDate.after(startDate)) {
                return transactionRepository.findTransactionsByTimestampBetween(startDate, endDate);
            } else {
                throw new Exception("Tanggal akhir melebihi tanggal awal");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}

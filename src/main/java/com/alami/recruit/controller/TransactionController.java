package com.alami.recruit.controller;

import com.alami.recruit.entity.jpa.Transaction;
import com.alami.recruit.entity.mongo.ControllerLog;
import com.alami.recruit.model.DateParam;
import com.alami.recruit.model.TransactionParam;
import com.alami.recruit.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class TransactionController {

    private final TransactionService transactionService;
    private final MongoTemplate mongoTemplate;

    public TransactionController(TransactionService transactionService, MongoTemplate mongoTemplate) {
        this.transactionService = transactionService;
        this.mongoTemplate = mongoTemplate;
    }

    @PostMapping("/transaction")
    public void createTransaction(@RequestBody TransactionParam param) throws Exception {
        try {
            if (param != null && param.getAmount().compareTo(BigDecimal.ZERO) > 0) {
                log.info("PARAM = {}", param);
                Transaction transaction = transactionService.createTransaction(param);
                mongoTemplate.insert(new ControllerLog(LocalDate.now(), param.toString(), transaction.toString()));
            } else {
                throw new Exception("Periksa kembali parameter yang dikirimkan");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @GetMapping("/transaction/{cifId}")
    public List<Transaction> getTransactionByCifId(@PathVariable String cifId) {
        if (cifId != null && !cifId.equalsIgnoreCase("")) {
            return transactionService.getTransactionByCifId(cifId);
        }
        return new ArrayList<>();
    }

    @PostMapping("/transaction/between")
    public List<Transaction> getTransactionBetween(@RequestBody DateParam param) throws Exception {
        try {
            if (param != null) {
                return transactionService.getTransactionBetween(param);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return new ArrayList<>();
    }
}

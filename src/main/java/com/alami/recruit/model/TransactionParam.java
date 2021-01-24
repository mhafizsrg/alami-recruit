package com.alami.recruit.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransactionParam {
    private String cifId;
    private BigDecimal amount;
    private Integer day;
    private Integer month;
    private Integer year;
    private String creditDebit;
}

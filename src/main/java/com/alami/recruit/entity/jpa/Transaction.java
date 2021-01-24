package com.alami.recruit.entity.jpa;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "transaction")
@Data
public class Transaction {

    @Id

    private String id;

    @Column(name = "cif_id", nullable = false)
    private String cifId;

    @Column(name = "timestamp", nullable = false)
    private Date timestamp;

    @Column(name = "amount", precision = 19, scale = 2, nullable = false)
    private BigDecimal amount;

    @Column(name = "credit_debit", nullable = false)
    private String creditDebit;

    public Transaction() {
        this.id = UUID.randomUUID().toString().replace("-", "");
    }
}

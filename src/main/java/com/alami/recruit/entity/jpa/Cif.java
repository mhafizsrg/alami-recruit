package com.alami.recruit.entity.jpa;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;


@Entity
@Table(name = "cif")
@Data
public class Cif {

    @Id
    private String id;


    @Column(name = "first_name", nullable = false)
    private String firstName;


    @Column(name = "last_name", nullable = false)
    private String lastName;


    @Column(name = "tanggal_lahir", nullable = false)
    private Integer tanggalLahir;


    @Column(name = "bulan_lahir", nullable = false)
    private Integer bulanLahir;


    @Column(name = "tahun_lahir", nullable = false)
    private Integer tahunLahir;


    @Column(name = "alamat", nullable = false)
    private String alamat;

    @Column(name = "balance", precision = 19, scale = 2, nullable = false)
    private BigDecimal balance;

    public Cif() {
        this.balance = BigDecimal.ZERO;
    }
}

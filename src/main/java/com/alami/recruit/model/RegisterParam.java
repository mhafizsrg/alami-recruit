package com.alami.recruit.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RegisterParam {

    private String firstName;
    private String lastName;
    private Integer tanggalLAhir;
    private Integer bulanLahir;
    private Integer tahunLahir;
    private String alamat;
    private String pekerjaan;
    private BigDecimal gaji;
}

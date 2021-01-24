package com.alami.recruit.model;

import lombok.Data;

@Data
public class RegisterParam {

    private String firstName;
    private String lastName;
    private Integer tanggalLAhir;
    private Integer bulanLahir;
    private Integer tahunLahir;
    private String alamat;
}

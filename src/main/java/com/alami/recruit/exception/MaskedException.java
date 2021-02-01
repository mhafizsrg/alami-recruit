package com.alami.recruit.exception;

import lombok.Getter;

@Getter
public enum MaskedException {
    GAJI_MINUS("GAJI TIDAK BOLEH MINUS"),
    UMUR_LIMIT("UMUR MELEBIHI BATAS MAKSIMUM 100 TAHUN"),
    PARAM_EMPTY("PERIKSA KEMBALI PARAMETER");

    private String label;

    MaskedException(String label) {
        this.label = label;
    }
}

package com.alami.recruit.entity.mongo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ControllerLog {
    private LocalDate requestTimestamp;
    private String request;
    private String response;
}

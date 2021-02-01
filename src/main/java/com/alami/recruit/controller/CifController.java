package com.alami.recruit.controller;

import com.alami.recruit.entity.jpa.Cif;
import com.alami.recruit.entity.mongo.ControllerLog;
import com.alami.recruit.exception.GajiException;
import com.alami.recruit.exception.MaskedException;
import com.alami.recruit.exception.ParamException;
import com.alami.recruit.exception.UmurException;
import com.alami.recruit.model.RegisterParam;
import com.alami.recruit.service.CifService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@Slf4j
public class CifController {

    private final CifService cifService;
    private final MongoTemplate mongoTemplate;

    public CifController(CifService cifService, MongoTemplate mongoTemplate) {
        this.cifService = cifService;
        this.mongoTemplate = mongoTemplate;
    }

    @PostMapping(path = "/cif")
    public Cif register(@RequestBody RegisterParam param) {
        log.info("param = {}", param);
        Cif cif = null;
        if (param != null) {
            if (param.getGaji().compareTo(BigDecimal.ZERO) <= 0) {
                throw new GajiException();
            }
            if (param.getTahunLahir() < 1920) {
                throw new UmurException();
            }
            if (param.getFirstName().length() >= 3 && param.getLastName().length() >= 3 && !param.getPekerjaan().equalsIgnoreCase("")) {
                cif = cifService.registerCif(param);
                mongoTemplate.insert(new ControllerLog(LocalDate.now(), param.toString(), cif.toString()));
            } else {
                throw new ParamException();
            }
        }
        return cif;
    }

    @GetMapping("/cif")
    public List<Cif> getCif() {
        return cifService.getAllCif();
    }
}

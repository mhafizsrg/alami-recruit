package com.alami.recruit.controller;

import com.alami.recruit.entity.jpa.Cif;
import com.alami.recruit.entity.mongo.ControllerLog;
import com.alami.recruit.model.RegisterParam;
import com.alami.recruit.service.CifService;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class CifController {

    private final CifService cifService;
    private final MongoTemplate mongoTemplate;

    public CifController(CifService cifService, MongoTemplate mongoTemplate) {
        this.cifService = cifService;
        this.mongoTemplate = mongoTemplate;
    }

    @PostMapping(path = "/cif")
    public Cif register(@RequestBody RegisterParam param) throws Exception {
        try {

            if (cifService != null && param.getFirstName().length() > 3 && param.getLastName().length() > 3) {
                Cif cif = cifService.registerCif(param);
                mongoTemplate.insert(new ControllerLog(LocalDate.now(), param.toString(), cif.toString()));
                return cif;
            } else {
                throw new Exception("Periksa lagi param yang dikirimkan");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @GetMapping("/cif")
    public List<Cif> getCif() {
        return cifService.getAllCif();
    }
}

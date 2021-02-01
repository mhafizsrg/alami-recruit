package com.alami.recruit.service;

import com.alami.recruit.entity.jpa.Cif;
import com.alami.recruit.model.RegisterParam;
import com.alami.recruit.repository.CifRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CifService {

    private final CifRepository cifRepository;

    public CifService(CifRepository cifRepository) {
        this.cifRepository = cifRepository;
    }

    public Cif registerCif(RegisterParam param) {
        Cif cif = new Cif();
        cif.setAlamat(param.getAlamat());
        cif.setBulanLahir(param.getBulanLahir());
        cif.setFirstName(param.getFirstName());
        cif.setLastName(param.getLastName());
        cif.setTahunLahir(param.getTahunLahir());
        cif.setTanggalLahir(param.getTanggalLAhir());
        cif.setId(param.getFirstName() + param.getLastName() + param.getTanggalLAhir() + param.getBulanLahir() + param.getTahunLahir());
        cif.setPekerjaan(param.getPekerjaan());
        cif.setGaji(param.getGaji());
        return cifRepository.save(cif);
    }

    public List<Cif> getAllCif() {
        Iterable<Cif> all = cifRepository.findAll();
        List<Cif> result = new ArrayList<>();
        for (Cif cif : all) {
            result.add(cif);
        }
        return result;
    }
}

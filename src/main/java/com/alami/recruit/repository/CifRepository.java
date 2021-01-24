package com.alami.recruit.repository;

import com.alami.recruit.entity.jpa.Cif;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CifRepository extends CrudRepository<Cif, String> {
}

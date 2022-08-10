package com.mycompany.app.dataprovider.repository;

import com.mycompany.app.dataprovider.entity.CatFactsEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface CatFactsRepository extends CrudRepository<CatFactsEntity, Integer>,
        JpaSpecificationExecutor<CatFactsEntity> {
}

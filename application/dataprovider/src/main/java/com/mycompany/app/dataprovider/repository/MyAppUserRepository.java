package com.mycompany.app.dataprovider.repository;

import com.mycompany.app.dataprovider.entity.MyAppUserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MyAppUserRepository extends CrudRepository<MyAppUserEntity, Integer> {
    Optional<MyAppUserEntity> findByUserName(String userName);
}

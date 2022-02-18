package com.emsi.meteo.app.ws.repositorys;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.emsi.meteo.app.ws.entities.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long>{
    UserEntity findByEmail(String email);
}

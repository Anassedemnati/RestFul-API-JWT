package com.emsi.meteo.app.ws.repositorys;

import com.emsi.meteo.app.ws.entities.AddresseEntity;
import com.emsi.meteo.app.ws.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddresseRepository extends JpaRepository<AddresseEntity,Long> {

    List<AddresseEntity> findByUser(UserEntity currentUser);


}

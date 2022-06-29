package com.emsi.meteo.app.ws.repositorys;

import com.emsi.meteo.app.ws.entities.AddresseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddresseRepository extends JpaRepository<AddresseEntity,Long> {


}

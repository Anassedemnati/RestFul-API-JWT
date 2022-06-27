package com.emsi.meteo.app.ws.repositorys;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.emsi.meteo.app.ws.entities.UserEntity;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {

    UserEntity findByEmail(String email);

    UserEntity findByUserId(String id);

    @Query(value = "select * from users u where u.first_name='anasse'",nativeQuery = true)
    Page<UserEntity> findAllByFirstName(Pageable pageableRequest);
}

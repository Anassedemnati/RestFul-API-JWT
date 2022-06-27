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

    @Query(value = "SELECT * FROM users",nativeQuery = true)
    Page<UserEntity> findAllUsers(Pageable pageableRequest);

    @Query(value = "SELECT * FROM users u WHERE (u.first_name = ?1 OR u.last_name = ?1) AND u.email_verification_status= ?2",nativeQuery = true)
    Page<UserEntity> findByFirstNameAndLastName(Pageable pageableRequest,String kw,int EmailStatus);
}

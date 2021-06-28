package com.icommerceapis.restapis.repository;

import com.icommerceapis.restapis.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    Optional<UserPassword> findByEmail(@Param("email") String email);

    interface UserPassword {
        Integer getId();

        String getPassword();
    }
}

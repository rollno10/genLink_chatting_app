package com.binarybachelor.genlink.repository;

import com.binarybachelor.genlink.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long>{
    Optional<UserEntity>findByMobile(String mobile);
    Optional<UserEntity>findById(Long id);
}
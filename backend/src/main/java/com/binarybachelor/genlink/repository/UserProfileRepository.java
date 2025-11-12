package com.binarybachelor.genlink.repository;

import com.binarybachelor.genlink.entity.UserProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import com.binarybachelor.genlink.entity.UserEntity;

public interface UserProfileRepository extends JpaRepository<UserProfileEntity, Long>{

    Optional<UserProfileEntity> findByUserId(UserEntity userId);
}
package com.binarybachelor.genlink.repository;

import com.binarybachelor.genlink.entity.UserPresenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import com.binarybachelor.genlink.entity.UserEntity;

public interface UserPresenceRepository extends JpaRepository<UserPresenceEntity, Long>{

   Optional<UserPresenceEntity> findByUserId(UserEntity userId);
}
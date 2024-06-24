package com.complaint5.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.complaint5.entities.AccountEntity;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, UUID>{
    Optional<AccountEntity> findByActiveTrueAndId(UUID id);
    List<AccountEntity> findAllByActiveTrue();
}

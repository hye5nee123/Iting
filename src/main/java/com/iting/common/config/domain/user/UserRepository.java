package com.iting.common.config.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Mem, Long> {

    Optional<Mem> findByMail(String mail);
}
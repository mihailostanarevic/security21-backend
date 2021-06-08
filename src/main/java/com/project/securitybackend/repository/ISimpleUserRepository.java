package com.project.securitybackend.repository;

import com.project.securitybackend.entity.SimpleUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISimpleUserRepository extends JpaRepository<SimpleUser, Long> {
}

package com.example.audit_server.repositoryTest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.audit_server.entity.Audit;

@Repository
public interface AuditRepositoryTest extends JpaRepository<Audit, Long> {

}

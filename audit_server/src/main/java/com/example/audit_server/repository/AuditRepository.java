package com.example.audit_server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.audit_server.entity.Audit;

@Repository
public interface AuditRepository extends JpaRepository<Audit, Long> {

}

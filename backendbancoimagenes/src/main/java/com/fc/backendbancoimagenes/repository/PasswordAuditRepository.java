package com.fc.backendbancoimagenes.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.fc.backendbancoimagenes.model.PasswordAudit;

public interface PasswordAuditRepository extends JpaRepository<PasswordAudit, Long> {
	Optional<PasswordAudit> findByUserId(Long userId);
}
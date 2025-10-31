package com.fc.backendbancoimagenes.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fc.backendbancoimagenes.model.PasswordAudit;
import com.fc.backendbancoimagenes.repository.PasswordAuditRepository;
import com.fc.backendbancoimagenes.util.EncriptacionUtil;

@Service
public class PasswordAuditService {
	
	@Autowired
	private PasswordAuditRepository repository;
	
	public void guardar(Long userId, String encryptedPassword) {
		PasswordAudit audit = new PasswordAudit();
		audit.setUserId(userId);
		audit.setEncrypPassword(encryptedPassword);
		audit.setCreatedAt(LocalDateTime.now());
		repository.save(audit);
	}
	
	public String ObtenerConDesencriptada(Long userId) throws Exception{
		PasswordAudit audit = repository.findByUserId(userId)
				.orElseThrow(() -> new RuntimeException("No se encontro"));
		return EncriptacionUtil.Desencriptacion(audit.getEncrypPassword());
	}

}

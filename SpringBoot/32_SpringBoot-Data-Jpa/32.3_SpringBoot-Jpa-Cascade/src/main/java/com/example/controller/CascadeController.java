package com.example.controller;

import com.example.service.CascadeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 游家纨绔
 * @dateTime 2025-11-02 13:10
 * @apiNote TODO
 */
@RestController
public class CascadeController {

	@Autowired
	private CascadeServices cascadeServices;

	@RequestMapping("/cascade")
	public ResponseEntity<Void> cascade(
			@RequestParam(value = "memberId", defaultValue = "MEM001") String memberId,
			@RequestParam(value = "recordId", defaultValue = "REC002") String recordId,
			@RequestParam(value = "recordType", defaultValue = "CORPORATE") String recordType,
			@RequestParam(value = "newMemberId", defaultValue = "MEM005") String newMemberId) {
		// cascadeServices.updateCascade(memberId, recordId, recordType, newMemberId);
		return ResponseEntity.ok().build();
	}

	@RequestMapping("/cascade2")
	public ResponseEntity<Void> cascade2(
			@RequestParam(value = "memberId", defaultValue = "MEM001") String memberId,
			@RequestParam(value = "recordId", defaultValue = "REC002") String recordId,
			@RequestParam(value = "recordType", defaultValue = "CORPORATE") String recordType,
			@RequestParam(value = "newMemberId", defaultValue = "MEM005") String newMemberId) {
		cascadeServices.updateCascade2(memberId, recordId, recordType, newMemberId);
		return ResponseEntity.ok().build();
	}
}

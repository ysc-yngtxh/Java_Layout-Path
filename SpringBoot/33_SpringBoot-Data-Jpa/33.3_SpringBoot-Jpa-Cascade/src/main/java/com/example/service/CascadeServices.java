package com.example.service;

import com.example.entity.Crs;
import com.example.entity.Member;
import com.example.repository.AmlRepository;
import com.example.repository.CrsRepository;
import com.example.repository.MemberRepository;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 游家纨绔
 * @dateTime 2025-11-02 13:00
 * @apiNote TODO
 */
@Service
public class CascadeServices {

	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private AmlRepository amlRepository;
	@Autowired
	private CrsRepository crsRepository;
	@PersistenceContext
	private EntityManager entityManager;

	@Transactional(rollbackFor = Exception.class)
	public void updateCascade(String memberId, String recordId, String recordType, String newMemberId) {
		// TODO 由于 Hibernate 的级联操作和关联关系管理，在进行删除和复制操作时，可能会引发冲突或异常。
		//      例如当删除子表记录后，Hibernate 仍然在内存中维护着对象之间的关联关系，
		//      导致在保存新记录时试图重新保存已被删除的对象，从而引发异常。
		//      为了解决这个问题，可以清除持久化上下文，确保 Hibernate 不再维护已删除对象的状态。
		// 1、清除持久化上下文，避免级联冲突
		entityManager.clear();
		// 2、查找原记录
		Member member = memberRepository.findByMemberIdAndRecordIdAndRecordType(memberId, recordId, recordType);
		if (member == null) {
			throw new RuntimeException("Member not found: " + memberId);
		}
		// 3、复制记录
		Member newMember = member.copyWithNewMemberId(newMemberId);
		// 4、先删除所有子表记录
		if (member.getAml() != null) {
			amlRepository.deleteById(member.getAml().getAmlId());
		}
		if (member.getCrs() != null && !member.getCrs().isEmpty()) {
			List<Integer> crsIds = member.getCrs().stream()
			                                .map(Crs::getCrsId)
			                                .collect(Collectors.toList());
			crsRepository.deleteAllById(crsIds);
		}
		// 5、清除关联关系
		member.setAml(null);
		if (member.getCrs() != null) {
			member.getCrs().clear();
		}
		// 6、删除主表记录
		memberRepository.delete(member);
		// 7. 立即刷新，确保删除操作执行
		memberRepository.flush();
		// 8、复制并保存新记录
		memberRepository.saveAndFlush(newMember);
	}
}

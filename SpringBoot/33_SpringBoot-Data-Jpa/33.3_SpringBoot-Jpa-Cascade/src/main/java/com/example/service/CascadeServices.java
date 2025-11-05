package com.example.service;

import com.example.entity.Crs;
import com.example.entity.CrsItem;
import com.example.entity.Member;
import com.example.repository.AmlRepository;
import com.example.repository.CrsItemRepository;
import com.example.repository.CrsRepository;
import com.example.repository.MemberRepository;
import java.util.ArrayList;
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
	@Autowired
	private CrsItemRepository crsItemRepository;
	@PersistenceContext
	private EntityManager entityManager;



	@Transactional(rollbackFor = Exception.class)
	public void updateCascade2(String memberId, String recordId, String recordType, String newMemberId) {
		// 1、查找原记录
		Member member = memberRepository.findByMemberIdAndRecordIdAndRecordType(memberId, recordId, recordType);
		if (member == null) {
			throw new RuntimeException("Member not found: " + memberId);
		}
		deleteOriginalMember(memberId, recordId, recordType);
		// 2、复制记录
		Member newMember = member.copyWithNewMemberId2(newMemberId);
		// 3、保存新记录
		memberRepository.saveAndFlush(newMember);
	}

	private void deleteOriginalMember(String memberId, String recordId, String recordType) {
		// 重新查询要删除的记录
		Member memberToDelete = memberRepository.findByMemberIdAndRecordIdAndRecordType(memberId, recordId, recordType);
		if (memberToDelete != null) {
			// 先删除子表记录
			if (memberToDelete.getAml() != null) {
				amlRepository.deleteById(memberToDelete.getAml().getAmlId());
			}

			if (memberToDelete.getCrs() != null && !memberToDelete.getCrs().isEmpty()) {
				List<Integer> crsItemList = new ArrayList<>();
				memberToDelete.getCrs().forEach(crs -> {
					crsItemList.addAll(
							crs.getCrsItemList().stream()
							      .map(CrsItem::getCrsItemId)
							      .collect(Collectors.toList())
					);
				});
				crsItemRepository.deleteAllById(crsItemList);

				List<Integer> crsIds = memberToDelete.getCrs().stream()
				                                     .map(Crs::getCrsId)
				                                     .collect(Collectors.toList());
				crsRepository.deleteAllById(crsIds);
			}

			// 删除主记录
			memberRepository.delete(memberToDelete);
			memberRepository.flush();
		}
	}
}

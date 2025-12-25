package com.example;

import com.example.entity.Member;
import com.example.repository.AmlRepository;
import com.example.repository.CrsRepository;
import com.example.repository.MemberRepository;
import org.hibernate.id.IntegralDataTypeHolder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class SpringBootJpaCascadeApplicationTests {

	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private AmlRepository amlRepository;
	@Autowired
	private CrsRepository crsRepository;

	@Test
	@Commit
	@Transactional(rollbackFor = Exception.class)
	void contextLoads() {
		String memberId = "MEM001";
		String recordId = "REC002";
		String recordType = "CORPORATE";

		Member member = memberRepository.findByMemberIdAndRecordIdAndRecordType(memberId, recordId, recordType);
		Member newMember = member.copyWithNewMemberId("MEM005");

		amlRepository.delete(member.getAml());
		crsRepository.deleteAll(member.getCrs());
		memberRepository.delete(member);

		memberRepository.saveAndFlush(newMember);
	}

}

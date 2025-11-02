package com.example.entity;

import com.example.entity.pk.MemberKey;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="member")
public class Member implements Serializable {
	private static final long serialVersionUID = -2268520671243573103L;

	@EmbeddedId
	private MemberKey id;

	@Column(name = "member_id", nullable = false, insertable = false, updatable = false)
	private String memberId;

	@Column(name = "record_id", nullable = false, insertable = false, updatable = false)
	private String recordId;

	@Column(name = "record_type", nullable = false, insertable = false, updatable = false)
	private String recordType;

	@Column(name = "first_name", nullable = false, insertable = true, updatable = false)
	private String firstName;

	@Column(name = "last_name", nullable = false, insertable = true, updatable = false)
	private String lastName;

	@Column(name = "email", nullable = false, insertable = true, updatable = false)
	private String email;

	@Column(name = "phone_number", nullable = false, insertable = true, updatable = false)
	private String phoneNumber;

	@Column(name = "date_of_birth", nullable = false, insertable = true, updatable = false)
	private Date dateOfBirth;

	@Column(name = "registration_date", nullable = false, insertable = true, updatable = false)
	private Date registrationDate;

	@Column(name = "status", nullable = false, insertable = true, updatable = false)
	private String status;

	@Column(name = "created_at", nullable = false, insertable = true, updatable = false)
	private Date createdAt;

	@Column(name = "updated_at", nullable = false, insertable = true, updatable = false)
	private Date updatedAt;

	@OneToOne(mappedBy = "member", cascade = {CascadeType.ALL})
	private Aml aml;

	@OneToMany(mappedBy = "member", cascade = {CascadeType.ALL})
	private List<Crs> crs;

	@Override
	public String toString() {
		return "Member{" +
				"id=" + (id != null ?
				"MemberKey(memberId=" + id.getMemberId() +
						", recordId=" + id.getRecordId() +
						", recordType=" + id.getRecordType() + ")" : "null") +
				// ", memberId='" + memberId + '\'' +
				// ", recordId='" + recordId + '\'' +
				// ", recordType='" + recordType + '\'' +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", email='" + email + '\'' +
				", phoneNumber='" + phoneNumber + '\'' +
				", dateOfBirth=" + dateOfBirth +
				", registrationDate=" + registrationDate +
				", status='" + status + '\'' +
				", createdAt=" + createdAt +
				", updatedAt=" + updatedAt +
				// 不包含关联对象，避免循环引用
				'}';
	}

	// 辅助方法：复制实体（深拷贝）
	public Member copyWithNewMemberId(String newMemberId) {
		// 深拷贝 Member 记录
		Member newMember = new Member();
		MemberKey memberKey = new MemberKey();
		memberKey.setMemberId(newMemberId);
		memberKey.setRecordId(this.getId().getRecordId());
		memberKey.setRecordType(this.getId().getRecordType());
		newMember.setId(memberKey);
		newMember.setMemberId(newMemberId);
		newMember.setRecordId(this.getRecordId());
		newMember.setRecordType(this.getRecordType());
		newMember.setFirstName(this.getFirstName());
		newMember.setLastName(this.getLastName());
		newMember.setEmail(this.getEmail());
		newMember.setPhoneNumber(this.getPhoneNumber());
		newMember.setDateOfBirth(this.getDateOfBirth());
		newMember.setRegistrationDate(this.getRegistrationDate());
		newMember.setStatus(this.getStatus());
		newMember.setCreatedAt(this.getCreatedAt());
		newMember.setUpdatedAt(this.getUpdatedAt());

		// 深拷贝 AML 记录
		if (this.getAml() != null) {
			Aml newAml = new Aml();
			newAml.setAmlId(this.getAml().getAmlId());
			newAml.setMemberId(newMemberId);
			newAml.setRecordId(this.getAml().getRecordId());
			newAml.setRecordType(this.getAml().getRecordType());
			newAml.setRiskLevel(this.getAml().getRiskLevel());
			newAml.setScreeningStatus(this.getAml().getScreeningStatus());
			newAml.setScreeningDate(this.getAml().getScreeningDate());
			newAml.setPepFlag(this.getAml().getPepFlag());
			newAml.setSanctionMatch(this.getAml().getSanctionMatch());
			newAml.setAdverseMedia(this.getAml().getAdverseMedia());
			newAml.setRiskScore(this.getAml().getRiskScore());
			newAml.setLastReviewDate(this.getAml().getLastReviewDate());
			newAml.setNextReviewDate(this.getAml().getNextReviewDate());
			newAml.setReviewerNotes(this.getAml().getReviewerNotes());
			newAml.setCreatedAt(this.getAml().getCreatedAt());
			newAml.setUpdatedAt(this.getAml().getUpdatedAt());
			newAml.setMember(newMember); // 设置关联关系
			newMember.setAml(newAml);
		}

		// 深拷贝 CRS 记录
		if (this.getCrs() != null && !this.getCrs().isEmpty()) {
			List<Crs> newCrsList = new ArrayList<>();
			for (Crs crs : this.getCrs()) {
				Crs newCrs = new Crs();
				newCrs.setCrsId(crs.getCrsId());
				newCrs.setMemberId(newMemberId);
				newCrs.setRecordId(crs.getRecordId());
				newCrs.setRecordType(crs.getRecordType());
				newCrs.setTaxResidencyCountry(crs.getTaxResidencyCountry());
				newCrs.setTinNumber(crs.getTinNumber());
				newCrs.setCrsStatus(crs.getCrsStatus());
				newCrs.setReportingRequired(crs.getReportingRequired());
				newCrs.setSelfCertificationDate(crs.getSelfCertificationDate());
				newCrs.setSelfCertificationValid(crs.getSelfCertificationValid());
				newCrs.setCrsEntityType(crs.getCrsEntityType());
				newCrs.setControllingPersonsIdentified(crs.getControllingPersonsIdentified());
				newCrs.setDueDiligenceLevel(crs.getDueDiligenceLevel());
				newCrs.setReportingJurisdiction(crs.getReportingJurisdiction());
				newCrs.setLastReportingDate(crs.getLastReportingDate());
				newCrs.setNextReportingDate(crs.getNextReportingDate());
				newCrs.setComplianceNotes(crs.getComplianceNotes());
				newCrs.setCreatedAt(crs.getCreatedAt());
				newCrs.setUpdatedAt(crs.getUpdatedAt());
				newCrs.setMember(newMember); // 设置关联关系
				newCrsList.add(newCrs);
			}
			newMember.setCrs(newCrsList);
		}

		return newMember;
	}
}

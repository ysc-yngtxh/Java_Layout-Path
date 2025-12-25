package com.example.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="crs")
public class Crs implements Serializable {
	private static final long serialVersionUID = 5596770566256882883L;

	@Id
	@Column(name = "crs_id", nullable = false, insertable = true, updatable = false)
    private Integer crsId;

	@Column(name = "member_id", nullable = false, insertable = false, updatable = false)
	private String memberId;

	@Column(name = "record_id", nullable = false, insertable = false, updatable = false)
	private String recordId;

	@Column(name = "record_type", nullable = false, insertable = false, updatable = false)
	private String recordType;

	@Column(name = "tax_residency_country", nullable = false, insertable = true, updatable = false)
	private String taxResidencyCountry;

	@Column(name = "tin_number", nullable = false, insertable = true, updatable = false)
	private String tinNumber;

	@Column(name = "crs_status", nullable = false, insertable = true, updatable = false)
	private String crsStatus;

	@Column(name = "reporting_required", nullable = false, insertable = true, updatable = false)
	private Boolean reportingRequired;

	@Column(name = "self_certification_date", nullable = false, insertable = true, updatable = false)
	private Date selfCertificationDate;

	@Column(name = "self_certification_valid", nullable = false, insertable = true, updatable = false)
	private Boolean selfCertificationValid;

	@Column(name = "crs_entity_type", nullable = false, insertable = true, updatable = false)
	private String crsEntityType;

	@Column(name = "controlling_persons_identified", nullable = false, insertable = true, updatable = false)
	private Boolean controllingPersonsIdentified;

	@Column(name = "due_diligence_level", nullable = false, insertable = true, updatable = false)
	private String dueDiligenceLevel;

	@Column(name = "reporting_jurisdiction", nullable = false, insertable = true, updatable = false)
	private String reportingJurisdiction;

	@Column(name = "last_reporting_date", nullable = false, insertable = true, updatable = false)
	private Date lastReportingDate;

	@Column(name = "next_reporting_date", nullable = false, insertable = true, updatable = false)
	private Date nextReportingDate;

	@Column(name = "compliance_notes", nullable = false, insertable = true, updatable = false)
	private String complianceNotes;

	@Column(name = "created_at", nullable = false, insertable = true, updatable = false)
	private Date createdAt;

	@Column(name = "updated_at", nullable = false, insertable = true, updatable = false)
	private Date updatedAt;


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "member_id", referencedColumnName = "member_id", nullable = false, insertable = true, updatable = false),
			@JoinColumn(name = "record_id", referencedColumnName = "record_id", nullable = false, insertable = true, updatable = false),
			@JoinColumn(name = "record_type", referencedColumnName = "record_type", nullable = false, insertable = true, updatable = false)
	})
	private Member member;

	@OneToMany(mappedBy = "crs", cascade = {CascadeType.ALL})
	private List<CrsItem> crsItemList = new ArrayList<>();


	@Override
	public String toString() {
		return "Crs{" +
				"crsId=" + crsId +
				", memberId='" + memberId + '\'' +
				", recordId='" + recordId + '\'' +
				", recordType='" + recordType + '\'' +
				", taxResidencyCountry='" + taxResidencyCountry + '\'' +
				", tinNumber='" + tinNumber + '\'' +
				", crsStatus='" + crsStatus + '\'' +
				", reportingRequired=" + reportingRequired +
				", selfCertificationDate=" + selfCertificationDate +
				", selfCertificationValid=" + selfCertificationValid +
				", crsEntityType='" + crsEntityType + '\'' +
				", controllingPersonsIdentified=" + controllingPersonsIdentified +
				", dueDiligenceLevel='" + dueDiligenceLevel + '\'' +
				", reportingJurisdiction='" + reportingJurisdiction + '\'' +
				", lastReportingDate=" + lastReportingDate +
				", nextReportingDate=" + nextReportingDate +
				", complianceNotes='" + complianceNotes + '\'' +
				", createdAt=" + createdAt +
				", updatedAt=" + updatedAt +
				// 只显示 member 的 ID 信息
				", member=" + (member != null ?
				"Member(id=" + member.getId() + ")" : "null") +
				'}';
	}
}

package com.example.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="aml")
public class Aml implements Serializable {
	private static final long serialVersionUID = -3743549351692508137L;

	@Id
    private Integer amlId;

	@Column(name = "member_id", nullable = false, insertable = false, updatable = false)
	private String memberId;

	@Column(name = "record_id", nullable = false, insertable = false, updatable = false)
	private String recordId;

	@Column(name = "record_type", nullable = false, insertable = false, updatable = false)
	private String recordType;

	@Column(name = "risk_level", nullable = false, insertable = true, updatable = false)
	private String riskLevel;

	@Column(name = "screening_status", nullable = false, insertable = true, updatable = false)
	private String screeningStatus;

	@Column(name = "screening_date", nullable = false, insertable = true, updatable = false)
	private Date screeningDate;

	@Column(name = "pep_flag", nullable = false, insertable = true, updatable = false)
	private Boolean pepFlag;

	@Column(name = "sanction_match", nullable = false, insertable = true, updatable = false)
	private Boolean sanctionMatch;

	@Column(name = "adverse_media", nullable = false, insertable = true, updatable = false)
	private Boolean adverseMedia;

	@Column(name = "risk_score", nullable = false, insertable = true, updatable = false)
	private Integer riskScore;

	@Column(name = "last_review_date", nullable = false, insertable = true, updatable = false)
	private Date lastReviewDate;

	@Column(name = "next_review_date", nullable = false, insertable = true, updatable = false)
	private Date nextReviewDate;

	@Column(name = "reviewer_notes", nullable = false, insertable = true, updatable = false)
	private String reviewerNotes;

	@Column(name = "created_at", nullable = false, insertable = true, updatable = false)
	private Date createdAt;

	@Column(name = "updated_at", nullable = false, insertable = true, updatable = false)
	private Date updatedAt;


	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "member_id", referencedColumnName = "member_id", nullable = false, insertable = true, updatable = false),
			@JoinColumn(name = "record_id", referencedColumnName = "record_id", nullable = false, insertable = true, updatable = false),
			@JoinColumn(name = "record_type", referencedColumnName = "record_type", nullable = false, insertable = true, updatable = false)
    })
	private Member member;

	@Override
	public String toString() {
		return "Aml{" +
				"amlId=" + amlId +
				", memberId='" + memberId + '\'' +
				", recordId='" + recordId + '\'' +
				", recordType='" + recordType + '\'' +
				", riskLevel='" + riskLevel + '\'' +
				", screeningStatus='" + screeningStatus + '\'' +
				", screeningDate=" + screeningDate +
				", pepFlag=" + pepFlag +
				", sanctionMatch=" + sanctionMatch +
				", adverseMedia=" + adverseMedia +
				", riskScore=" + riskScore +
				", lastReviewDate=" + lastReviewDate +
				", nextReviewDate=" + nextReviewDate +
				", reviewerNotes='" + reviewerNotes + '\'' +
				", createdAt=" + createdAt +
				", updatedAt=" + updatedAt +
				// 只显示 member 的 ID 信息，不调用 member.toString()
				", member=" + (member != null ?
				"Member(id=" + member.getId() + ")" : "null") +
				'}';
	}
}

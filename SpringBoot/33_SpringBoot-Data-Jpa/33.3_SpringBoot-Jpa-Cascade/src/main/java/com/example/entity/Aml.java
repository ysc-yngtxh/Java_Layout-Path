package com.example.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Table(name="aml")
public class Aml implements Serializable {
	private static final long serialVersionUID = -3743549351692508137L;

	@Id
    @GeneratedValue(generator = "JDBC")
    private Integer amlId;

    private String memberId;

    private String recordId;

    private String recordType;

    private String riskLevel;

    private String screeningStatus;

    private Date screeningDate;

    private Boolean pepFlag;

    private Boolean sanctionMatch;

    private Boolean adverseMedia;

    private Integer riskScore;

    private Date lastReviewDate;

    private Date nextReviewDate;

    private String reviewerNotes;

    private Date createdAt;

    private Date updatedAt;
}

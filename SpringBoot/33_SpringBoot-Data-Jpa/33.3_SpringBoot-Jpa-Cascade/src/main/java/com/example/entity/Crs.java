package com.example.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Table(name="crs")
public class Crs implements Serializable {
	private static final long serialVersionUID = 5596770566256882883L;

	@Id
    @GeneratedValue(generator = "JDBC")
    private Integer crsId;

    private String memberId;

    private String recordId;

    private String recordType;

    private String taxResidencyCountry;

    private String tinNumber;

    private String crsStatus;

    private Boolean reportingRequired;

    private Date selfCertificationDate;

    private Boolean selfCertificationValid;

    private String crsEntityType;

    private Boolean controllingPersonsIdentified;

    private String dueDiligenceLevel;

    private String reportingJurisdiction;

    private Date lastReportingDate;

    private Date nextReportingDate;

    private String complianceNotes;

    private Date createdAt;

    private Date updatedAt;
}

package com.example.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="crs_item")
public class CrsItem implements Serializable {
	private static final long serialVersionUID = -4594153560238417284L;

	@Id
    private Integer crsItemId;

	@Column(name="crs_id", nullable = false, insertable = false, updatable = false)
    private Integer crsId;

	@Column(name="item_type")
    private String itemType;

	@Column(name="item_value")
    private String itemValue;

	@Column(name="item_description")
    private String itemDescription;

	@Column(name="verification_status")
    private String verificationStatus;

	@Column(name="verification_date")
    private Date verificationDate;

	@Column(name="verified_by")
    private String verifiedBy;

	@Column(name="document_reference")
    private String documentReference;

	@Column(name="due_diligence_notes")
    private String dueDiligenceNotes;

	@Column(name="created_at")
    private Date createdAt;

	@Column(name="updated_at")
    private Date updatedAt;


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "crs_id", referencedColumnName = "crs_id", insertable = true, updatable = false)
	private Crs crs;
}

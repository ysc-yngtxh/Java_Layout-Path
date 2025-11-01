package com.example.entity.pk;

import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Table(name="member")
@Data
public class MemberKey implements Serializable {

	private static final long serialVersionUID = 1854716081000330754L;

	@Id
    @GeneratedValue(generator = "JDBC")
    private String memberId;

    @Id
    @GeneratedValue
    private String recordId;

    @Id
    @GeneratedValue
    private String recordType;
}

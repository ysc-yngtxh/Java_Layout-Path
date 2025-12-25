package com.example.entity.pk;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// @Embeddable 表示该类是一个可嵌入的值类型（value object），可以被实体通过 @Embedded 或用于复合主键通过 @EmbeddedId 嵌入
@Embeddable
public class MemberKey implements Serializable {
	private static final long serialVersionUID = 1854716081000330754L;

	@Column(name = "member_id", unique = true, nullable = false)
    private String memberId;

	@Column(name = "record_id", unique = true, nullable = false)
    private String recordId;

	@Column(name = "record_type", unique = true, nullable = false)
    private String recordType;
}

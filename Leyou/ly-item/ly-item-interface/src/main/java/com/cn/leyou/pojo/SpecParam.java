package com.cn.leyou.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "tb_spec_pream")
public class SpecParam {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;
    private Long gid;
    private Long groupid;
    private String name;

    @Column(name="`numeric`")  //numeris在sql里是一个关键字，为了用于区分，加上了一个``
    private Boolean numeric;
    private String unit;
    private Boolean generic;
    private Boolean searching;
    private String segments;
}

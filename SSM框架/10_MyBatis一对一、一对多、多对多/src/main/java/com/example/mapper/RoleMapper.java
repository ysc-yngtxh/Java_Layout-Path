package com.example.mapper;

import com.example.pojo.SSMRole;

public interface RoleMapper {

    /* 查询角色id=? 的角色以及对应的用户
     *       select *
     *       from ssm_role r
     *         inner join ssm_user_role ur
     *         inner join ssm_user u
     *           on r.id = ur.rid and u.id = ur.uid
     *       where r.id = ?;
     *   1. 参数类型: Integer
     *   2. 返回类型: SSMRole
     */
    SSMRole RoleWithUsers(Integer rid);
}

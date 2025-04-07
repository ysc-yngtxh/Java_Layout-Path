package com.example.mapper;

import com.example.pojo.SSMOrder;

public interface OrderMapper {

    /* # 查询 id=? 的订单以及对应的用户
     *       select *
     *       from ssm_order o
     *         inner join ssm_user u
     *         on o.uid = u.id
     *       where o.id = ?;
     *
     *    1. 参数类型:   Integer id
     *    2. 返回值类型: SSMOrder
     */
    SSMOrder OrderWithUser(Integer id);
}

package com.example.mapper;

import com.example.pojo.Order;

public interface OrderMapper {
    /*
        # 查询id=?的订单以及对应的用户
            select *
            from orders o
              inner join user u
              on o.uid = u.id
            where o.id = ?;

         1. 参数类型: Integer id
         2. 返回值类型  Order
    */
    Order OrderWithUser(Integer id);
}
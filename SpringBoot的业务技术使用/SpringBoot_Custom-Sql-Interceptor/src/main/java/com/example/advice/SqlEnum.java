package com.example.advice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author 游家纨绔
 * @dateTime 2023-08-25 15:51
 * @apiNote TODO
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum SqlEnum {

    TENANT_NULL(500, "请在请求头中携带租户：Global-TenantId值");
    private Integer code;
    private String message;
}

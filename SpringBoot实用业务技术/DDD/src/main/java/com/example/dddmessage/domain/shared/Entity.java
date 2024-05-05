package com.example.dddmessage.domain.shared;


/**
 * 实体
 *
 * @author 游家纨绔
 * @since 2024-04-18 07:20:00
 */
public interface Entity<T> {

    /**
     * 实体通过唯一ID比较
     *
     * @param other 另一个实体
     * @return true 只要ID相同就返回ture，忽略属性
     */
    boolean sameIdentityAs(T other);
}
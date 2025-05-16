package com.example.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author 游家纨绔
 * @date 2022/07/06
 * @apiNote
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_user", autoResultMap = true)
public class SysUser implements Serializable {
    @Serial
    private static final long serialVersionUID = 1134421647748519297L;

    /**
     * 主键
     */
    @TableId
    private Long id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 密码
     */
    private String passWord;

    /**
     * 帐号状态(0正常，1停用)
     */
    private String status;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String phoneNumber;

    /**
     * 用户性别(0男， 1女， 2未知)
     */
    private String sex;

    /**
     * 头像
     */
    private byte[] avatar;

    /**
     * 用户类型(0管理员，1普通用户)
     */
    private String userType;

    /**
     * 用户权限
     */
    @TableField(typeHandler= FastjsonTypeHandler.class)
    private List<String> permission;

    /**
     * 创建人的用户id
     */
    private Long createBy;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新人
     */
    private Long updateBy;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 删除标志(0未删除， 1已删除)
     */
    private Integer delFlag;

    /**
     * 前后端项目需要用到的token
     */
    private transient String token;
}

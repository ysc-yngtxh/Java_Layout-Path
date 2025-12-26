package com.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
// 此处有大坑：
//     由于各类数据库方言有差异，不同的数据库对大小写的处理方式不同：
//        H2/PostgreSQL：默认对标识符区分大小写
//        MySQL：默认不区分大小写
//     因此，spring-boot-starter-data-r2dbc 为了保持跨数据库兼容性，使用了引号来保护标识符，即对于表名添加引号【"h2_users"】，
//     这样可以确保在不同数据库中都能正确识别表名，避免因大小写敏感问题导致的错误。
// 坑点问题：H2数据库在建表时会默认将 表名 转为 大写字母，而 R2DBC 查询时使用的是带引号的表名【"h2_users"】，导致找不到对应的表。
// 解决办法：1、在建表时使用引号保护表名；
//         2、修改实体类上的@Table注解，使用大写字母的表名。
//         3、修改H2数据库配置，设置不区分大小写。
@Table("h2_users")
public class User {

    @Id
    @Column("id")
    private Integer id;

    @Column("user_name")
    private String userName;

    @Column("email")
    private String email;

    @Column("full_name")
    private String fullName;

    // 由于使用的是 R2DBC 作为连接驱动，对应数据库中的 tinyint(1) 类型，使用 byte 类型来映射
    @Column("active")
    private byte active;
}

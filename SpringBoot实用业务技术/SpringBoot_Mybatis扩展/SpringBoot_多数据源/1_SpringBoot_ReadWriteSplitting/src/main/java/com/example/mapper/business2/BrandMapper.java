package com.example.mapper.business2;

import com.example.entity.TbBrand;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.type.JdbcType;

/**
 * 品牌表，一个品牌下有多个商品（spu），一对多关系(TbBrand)表数据库访问层
 * @author 游家纨绔
 * @since 2023-09-02 14:48:25
 */
public interface BrandMapper {

    /** @Results用法
     * 当数据库字段名与实体类对应的属性名不一致时，可以使用@Results映射来将其对应起来。
     * column为数据库字段名，property为实体类属性名，jdbcType为数据库字段数据类型，id为是否为主键。
     */
    @Select("select * from business2.tb_brand")
    @Results(id = "selectResult", value = {
            // 这种注解类型其实跟xml配置有异曲同工之妙，但是不符合企业开发规范，自己项目做着玩就行
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true), // id=true表示是否为主键
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "image", property = "image", jdbcType = JdbcType.INTEGER),
            @Result(column = "letter", property = "letter", jdbcType = JdbcType.INTEGER)
    })
    List<TbBrand> selectAll();

    /** @ResultMap用法
     * 当这段@Results代码需要在多个方法用到时，为了提高代码复用性，
     * 我们可以为这个@Results注解设置id，然后使用@ResultMap注解来复用这段代码。
     */
    @Select("select * from business2.tb_brand where name=#{name}")
    @ResultMap("selectResult")
    TbBrand selectByName(@Param("name") String name);

    /**
     * @Options用法 其实就相当于我们在xml文件中 <insert></insert> 头部标签的属性配置
     * useGeneratedKeys = true 表示使用id自增的方式插入
     * keyProperty = "id" 表示自增的主键对应实体类中的字段"id",会返回插入的id值
     * keyColumn = "id"   表示数据库中自增的字段名是"id"
     */
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("insert into business2.tb_brand(name, image, letter) values(#{brand.name}, #{brand.image}, #{brand.letter})")
    void insert(@Param("brand") TbBrand brand);

    /**
     * @SelectKey用法 其实就相当于我们在xml文件中 <insert></insert> 头部标签的属性配置
     * selectKey的statement属性是SQL语句，before属性表示是否在执行insert语句之前执行selectKey语句，
     * resultType属性表示返回的结果类型，keyProperty属性表示将返回的结果赋值给实体类中的哪个属性。
     * last_insert_id()函数是获取数据库中下一个主键值。
     */
    @SelectKey(statement = "select last_insert_id()", keyProperty = "id", before = true, resultType = Integer.class)
    @Insert("insert into business2.tb_brand(name, image, letter) values(#{brand.name}, #{brand.image}, #{brand.letter})")
    void insertNameAndSelectKey(@Param("brand") TbBrand brand);
}

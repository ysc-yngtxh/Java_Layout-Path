package com.example.executor;

import com.example.annotation.AutoDiscoverClass;

import java.io.Serializable;

/**
 * 类别表(Category)实体类
 *
 * @author makejava
 * @since 2024-02-27 22:24:25
 */
@AutoDiscoverClass  // 使用自定义注解
public class Category implements Serializable {
    private static final long serialVersionUID = -79781314987912932L;

    /**
     * 主键Id
     */
    private Long id;
    /**
     * 类别名称
     */
    private String categoryName;
    /**
     * 类别英文名称
     */
    private String categoryNameEn;
    /**
     * 逻辑删除：0存在,1删除
     */
    private Integer deleteFlag;
    /**
     * 备注
     */
    private String remark;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryNameEn() {
        return categoryNameEn;
    }

    public void setCategoryNameEn(String categoryNameEn) {
        this.categoryNameEn = categoryNameEn;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", categoryName='" + categoryName + '\'' +
                ", categoryNameEn='" + categoryNameEn + '\'' +
                ", deleteFlag=" + deleteFlag +
                ", remark='" + remark + '\'' +
                '}';
    }
}

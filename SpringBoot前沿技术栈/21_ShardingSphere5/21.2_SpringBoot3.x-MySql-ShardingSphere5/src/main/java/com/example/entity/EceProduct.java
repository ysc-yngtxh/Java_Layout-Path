package com.example.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 产品管理表(EceProduct)实体类
 *
 * @author 游家纨绔
 * @since 2024-09-20 21:23:25
 */
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EceProduct implements Serializable {
    @Serial
    private static final long serialVersionUID = 119346344877955569L;

    /**
     * 产品ID
     */
    private Long productId;
    /**
     * 产品名称
     */
    private String name;
    /**
     * 产品描述
     */
    private String description;
    /**
     * 产品类别
     */
    private String category;
    /**
     * 产品价格
     */
    private Double price;
    /**
     * 库存数量
     */
    private Integer stockQuantity;
    /**
     * 产品图片的 URL
     */
    private String imageUrl;
    /**
     * 创建时间
     */
    private Date createdAt;
    /**
     * 更新时间
     */
    private Date updatedAt;
    /**
     * 产品状态(可用,缺货,停用)，默认为 AVAILABLE
     */
    private String status;


    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}


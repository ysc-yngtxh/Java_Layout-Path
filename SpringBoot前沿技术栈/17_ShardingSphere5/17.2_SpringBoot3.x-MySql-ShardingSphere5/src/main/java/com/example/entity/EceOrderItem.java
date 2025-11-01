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
 * 订单商品项表(EceOrderItem)实体类
 *
 * @author 游家纨绔
 * @since 2024-09-20 21:20:30
 */
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EceOrderItem implements Serializable {
    @Serial
    private static final long serialVersionUID = -99617789672354444L;

    /**
     * 订单商品项ID
     */
    private Long orderItemId;
    /**
     * 订单ID
     */
    private Long orderId;
    /**
     * 产品ID
     */
    private Long productId;
    /**
     * 库存
     */
    private Integer quantity;
    /**
     * 商品价格
     */
    private Double price;
    /**
     * 创建时间
     */
    private Date createdAt;
    /**
     * 更新时间
     */
    private Date updatedAt;


    public Long getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Long orderItemId) {
        this.orderItemId = orderItemId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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

}

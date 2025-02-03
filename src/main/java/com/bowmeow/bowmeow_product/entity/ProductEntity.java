package com.bowmeow.bowmeow_product.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * Info of Product
 */
@Getter
@Setter
@Entity(name = "products")
@Table(name = "products")
@ToString
public class ProductEntity {
    /** 상품 ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;

    /** 상품 명 */
    @Column(name = "product_name")
    private String productNm;

    /** 상품 가격 */
    @Column(name = "product_amount")
    private float productAmount;

    /** 상품 설명 */
    @Column(name = "product_description")
    private String productDescription;

    /** 상품 생성 일자 */
    @Column(name = "product_start_dt")
    private LocalDateTime productStartDt;

    /** 상품 종료 일자 */
    @Column(name = "product_end_dt")
    private LocalDateTime productEndDt;

    @Transient // 비영속 필드에 포함시킴
    private Integer productPurchaseCount;

    @Transient
    private String userId;
}

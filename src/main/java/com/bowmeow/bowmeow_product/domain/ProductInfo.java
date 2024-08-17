package com.bowmeow.bowmeow_product.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Info of Product
 */
@Getter
@Entity(name = "products")
@ToString
public class ProductInfo {
    /** 상품 ID */
    @Id
    @GeneratedValue
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
}

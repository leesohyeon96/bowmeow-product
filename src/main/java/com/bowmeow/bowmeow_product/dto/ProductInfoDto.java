package com.bowmeow.bowmeow_product.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Info of Product Dto
 */
@Getter
@Setter
@ToString
public class ProductInfoDto {
    /** 상품 ID */
    private Integer productId;
    /** 상품 명 */
    private String productNm;
    /** 상품 가격 */
    private float productAmount;
    /** 상품 설명 */
    private String productDescription;
}

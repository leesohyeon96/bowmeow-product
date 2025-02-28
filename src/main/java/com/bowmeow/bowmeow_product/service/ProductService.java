package com.bowmeow.bowmeow_product.service;

import com.bowmeow.bowmeow_product.ProductServiceProto;
import com.bowmeow.bowmeow_product.entity.ProductEntity;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductGrpcService productGrpcService;
    private final ProductJPAService productJPAService;
    private final JWTService jwtService;

    /**
     * 상품 전체 목록 조회
     * @return 상품 목록
     */
    public List<ProductEntity> getProducts() {
        return productJPAService.getProducts();
    }

    /**
     * 상품 상세 조회
     * @return 상품 상세 정보
     */
    public ProductEntity getProduct(Integer productId) {
        return productJPAService.getProduct(productId);
    }

    /**
     * 주문서 생성
     * @param productInfo 생성할 상품 정보
     * @param authorizationHeader 토큰 정보
     * @return 생성된 주문서 정보 및 redirect URL
     */
    @Transactional(rollbackOn = Exception.class)
    public ProductServiceProto.CreateOrderResponse createOrder(ProductEntity productInfo, String authorizationHeader) {
        Integer productId = productInfo.getProductId();
        Integer productPurchaseCount = productInfo.getProductPurchaseCount();
        String token = authorizationHeader.replace("Bearer ", "");
        String userId = jwtService.getExtractUserIdFromToken(token);

        // 1. 상품 상제 조회 화면에서 productId를 받아서 [구매하기]버튼 클릭시 조회 > JPA를 통해 재 조회
        ProductServiceProto.CreateOrderResponse response = null;
        ProductEntity latestProductInfo = getProduct(productId);
        if (latestProductInfo != null) {
            latestProductInfo.setProductPurchaseCount(productPurchaseCount);
            latestProductInfo.setUserId(userId);
            // 2. 데이터 조회 성공하면 gRPC 호출
            response = productGrpcService.createOrder(latestProductInfo);
        }
        return response;
    }
}

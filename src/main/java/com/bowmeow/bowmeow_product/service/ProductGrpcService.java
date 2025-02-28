package com.bowmeow.bowmeow_product.service;

import com.bowmeow.bowmeow_product.ProductPaymentServiceGrpc;
import com.bowmeow.bowmeow_product.ProductServiceProto;
import com.bowmeow.bowmeow_product.client.ProductPaymentClient;
import com.bowmeow.bowmeow_product.entity.ProductEntity;
import com.bowmeow.bowmeow_product.repository.ProductRepository;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 다른 서비스에서 product 서비스 gRPC 호출시에 사용하는 Service
 * - 추가로 gRPC 관련 로직이 모여있는 용도도 함
 */
@Service
@RequiredArgsConstructor
public class ProductGrpcService extends ProductPaymentServiceGrpc.ProductPaymentServiceImplBase {
    private final ProductRepository productRepository;
    private ProductPaymentClient productPaymentClient;

    @Override
    public void getProductInfo(ProductServiceProto.ProductRequest request, StreamObserver<ProductServiceProto.ProductInfo> responseObserver) {
        // 상품 정보 조회
        ProductEntity productInfo = productRepository.findById((long) request.getProductSno())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // 상품 정보 반환 데이터 세팅
        ProductServiceProto.ProductInfo response = ProductServiceProto.ProductInfo.newBuilder()
                .setProductSno(request.getProductSno())
                .setProductName(productInfo.getProductNm())
                .setProductPrice(productInfo.getProductAmount())
                .setProductPurchaseCount(productInfo.getProductPurchaseCount())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    public ProductServiceProto.CreateOrderResponse createOrder(ProductEntity productInfo) {
        return productPaymentClient.createOrder(productInfo);
    }
}

package com.bowmeow.bowmeow_product.service;

import com.bowmeow.bowmeow_product.ProductServiceGrpc;
import com.bowmeow.bowmeow_product.ProductServiceProto;
import com.bowmeow.bowmeow_product.client.ProductClient;
import com.bowmeow.bowmeow_product.domain.ProductInfo;
import com.bowmeow.bowmeow_product.repository.ProductRepository;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductGrpcService extends ProductServiceGrpc.ProductServiceImplBase {
    private final ProductRepository productRepository;
    private ProductClient productClient;

    @Override
    public void getProductInfo(ProductServiceProto.ProductRequest request, StreamObserver<ProductServiceProto.ProductInfo> responseObserver) {
        // 상품 정보 조회
        ProductInfo productInfo = productRepository.findById((long) request.getProductSno())
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

    public ProductServiceProto.CreateOrderResponse createOrder(ProductInfo productInfo) {
        return productClient.createOrder(productInfo);
    }
}

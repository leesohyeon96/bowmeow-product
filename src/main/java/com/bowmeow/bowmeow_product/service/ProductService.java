package com.bowmeow.bowmeow_product.service;

import com.bowmeow.bowmeow_product.ProductServiceGrpc;
import com.bowmeow.bowmeow_product.ProductServiceProto;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService extends ProductServiceGrpc.ProductServiceImplBase {

    @Override
    public void getProductInfo(ProductServiceProto.ProductRequest request, StreamObserver<ProductServiceProto.ProductInfo> responseObserver) {
        // 실제 상품 정보를 조회하는 로직 추가 필요
        ProductServiceProto.ProductInfo response = ProductServiceProto.ProductInfo.newBuilder()
                .setProductSno(request.getProductSno())
                .setProductName("Sample Product Name")
                .setProductPrice(100.0)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}

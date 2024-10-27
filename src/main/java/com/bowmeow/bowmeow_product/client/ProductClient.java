package com.bowmeow.bowmeow_product.client;

import com.bowmeow.bowmeow_product.ProductServiceGrpc;
import com.bowmeow.bowmeow_product.ProductServiceProto;
import com.bowmeow.bowmeow_product.domain.ProductInfo;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProductClient {
    private final ProductServiceGrpc.ProductServiceBlockingStub blockingStub;

    public ProductClient(String host, int port) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build();
        blockingStub = ProductServiceGrpc.newBlockingStub(channel);
    }

    /**
     * 결제를 위한 상품 정보 반환
     * @param productSno 상품 정보 일련번호
     * @return 결제를 위한 상품 정보
     */
    public ProductServiceProto.ProductInfo getProductInfo(int productSno) {
        ProductServiceProto.ProductRequest request = ProductServiceProto.ProductRequest.newBuilder().setProductSno(productSno).build();
        return blockingStub.getProductInfo(request);
    }

    /**
     * 결제를 위한 주문서 생성
     * @param productInfo 상품 정보
     */
    public ProductServiceProto.CreateOrderResponse createOrder(ProductInfo productInfo) {
        ProductServiceProto.ProductInfoForOrder request = ProductServiceProto.ProductInfoForOrder.newBuilder()
                .setProductId(productInfo.getProductId())
                .setProductName(productInfo.getProductNm())
                .setProductPrice(productInfo.getProductId())
                .setProductPurchaseCount(productInfo.getProductPurchaseCount())
                .setUserId(productInfo.getUserId()).build();
        return blockingStub.createOrder(request);
    }

}

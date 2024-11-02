package com.bowmeow.bowmeow_product.client;

import com.bowmeow.bowmeow_product.ProductPaymentServiceGrpc;
import com.bowmeow.bowmeow_product.ProductServiceProto;
import com.bowmeow.bowmeow_product.domain.ProductInfo;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import lombok.extern.slf4j.Slf4j;

/**
 * product 서비스에서 다른 서비스 gRPC 호출시에 사용되는 Class
 * - product -> payment 호출
 */
@Slf4j
public class ProductPaymentClient {
    private final ProductPaymentServiceGrpc.ProductPaymentServiceBlockingStub blockingStub;

    public ProductPaymentClient(String host, int port) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build();
        blockingStub = ProductPaymentServiceGrpc.newBlockingStub(channel);
    }

    /**
     * 결제를 위한 주문서 생성
     * @param productInfo 상품 정보
     */
    public ProductServiceProto.CreateOrderResponse createOrder(ProductInfo productInfo) {
        ProductServiceProto.CreateOrderResponse response = null;
        try {
            ProductServiceProto.ProductInfoForOrder request = ProductServiceProto.ProductInfoForOrder.newBuilder()
                    .setProductId(productInfo.getProductId())
                    .setProductName(productInfo.getProductNm())
                    .setProductPrice(productInfo.getProductId())
                    .setProductPurchaseCount(productInfo.getProductPurchaseCount())
                    .setUserId(productInfo.getUserId()).build();
            response = blockingStub.createOrder(request);
        } catch (StatusRuntimeException e) {
            // 에러 처리
            String errorMessage = e.getStatus().getDescription();
            System.out.println("에러 메시지: " + errorMessage);
        }
        return response;
    }

}

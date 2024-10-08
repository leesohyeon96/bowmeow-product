package com.bowmeow.bowmeow_product.config;

import com.bowmeow.bowmeow_product.service.ProductServer;
import com.bowmeow.bowmeow_product.service.ProductGrpcService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.IOException;


@Configuration
@RequiredArgsConstructor
public class GrpcSeverConfig {

        @Value("${grpc.server.port}")
        private int grpcPort;

        private final ProductGrpcService productGrpcService;
        private final ProductServer productServer;

        @PostConstruct
        public void startGrpcServer() throws IOException {
            productServer.setPort( grpcPort );
            productServer.initialize(productGrpcService);
            productServer.start();
        }
    }


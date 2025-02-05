package com.bowmeow.bowmeow_product.controller;

import com.bowmeow.bowmeow_product.ProductServiceProto;
import com.bowmeow.bowmeow_product.entity.ProductEntity;
import com.bowmeow.bowmeow_product.dto.ProductInfoDto;
import com.bowmeow.bowmeow_product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.net.URI;
import java.util.List;

/**
 * Product Controller
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
@Slf4j
public class ProductController {
    private final ModelMapper modelMapper;
    private final ProductService productService;

    /**
     * 상품 전체 목록 조회
     * @return 상품 목록
     */
    @GetMapping("")
    public List<ProductInfoDto> getProducts() {
        // 상품에 대한 목록들 조회
        List<ProductEntity> productInfos = productService.getProducts();
        return modelMapper.map(productInfos, new TypeToken<List<ProductInfoDto>>() {}.getType());
    }


    /**
     * 상품 상세 조회
     * @return 상품 상세 정보
     */
    @GetMapping("/{productId}")
    public ProductInfoDto getProduct(@PathVariable Integer productId) {
        ProductEntity productInfo = productService.getProduct(productId);
        return modelMapper.map(productInfo, ProductInfoDto.class);
    }

    /**
     * 주문서 생성
     * - 상품 상세 화면 > 구매하기 버튼 클릭시 호출되는 API
     */
    @PostMapping("/orders")
    public ResponseEntity<Void> createOrder(@RequestHeader("Authorization") String authorizationHeader
                                        , @RequestBody ProductInfoDto productInfoDto
                                        , RedirectAttributes redirectAttributes) {
        // productInfoDto 에는 productPurchaseCount(구매할 상품 개수), productId(상품 아이디)가 담겨서 옮
        ProductEntity productInfo = modelMapper.map(productInfoDto, ProductEntity.class);
        ProductServiceProto.CreateOrderResponse createOrderResponse = productService.createOrder(productInfo, authorizationHeader);
        if (createOrderResponse == null) {
            // 에러 발생 시 internalServerError 상태코드 반환
            return ResponseEntity.internalServerError().build();
        }
        redirectAttributes.addFlashAttribute("orderProductResponse", createOrderResponse.getOrderProductResponse());
        String redirectUrl = createOrderResponse.getRedirectUrl();
        URI location = URI.create(redirectUrl);
        return ResponseEntity.status(HttpStatus.SEE_OTHER).location(location).build();
    }

    // todo:1 상품 생성 API 필요!
    // todo:2 주문서 생성에서 일단 jwt bearea 토큰 필요한데 서영이 그 jwt 토큰 만들어서
    // 어디다 저장하는지 물어보깅 ㅇㅇ
}

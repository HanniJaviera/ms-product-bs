package cl.duoc.ms_product_bs.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import cl.duoc.ms_product_bs.clients.ProductdbFeignClient;
import cl.duoc.ms_product_bs.model.dto.ProductDTO;

public class ProductServiceTest {
    @Mock
    private ProductdbFeignClient productdbFeignClient;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getProductByIdTest(){
        Long productId = 100L; //ID de producto que definimos
        ProductDTO test = new ProductDTO(productId, "Test", "Test Descripción", 100L, 0L);
        when(productdbFeignClient.findProductById(productId)).thenReturn(ResponseEntity.ok(test));
        ProductDTO actualProduct = productService.getProductById(productId);
        assertNotNull(actualProduct);
        assertEquals(test.getId(), actualProduct.getId());
        assertEquals(test.getNombreProduct(), actualProduct.getNombreProduct());
        verify(productdbFeignClient, times(1)).findProductById(productId);
    }
}

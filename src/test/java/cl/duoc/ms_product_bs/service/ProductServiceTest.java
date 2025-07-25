package cl.duoc.ms_product_bs.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

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

        @Test
        void createProductTest() {
        ProductDTO productToCreate = new ProductDTO(null, "Nuevo Producto", "Descripción Nuevo", 200L, 10L);
        ProductDTO createdProductFromClient = new ProductDTO(1L, "Nuevo Producto", "Descripción Nuevo", 200L, 10L);
        when(productdbFeignClient.createProduct(productToCreate)).thenReturn(createdProductFromClient);
        ProductDTO actualCreatedProduct = productService.createProduct(productToCreate);
        assertNotNull(actualCreatedProduct);
        assertNotNull(actualCreatedProduct.getId());
        assertEquals(createdProductFromClient.getId(), actualCreatedProduct.getId());
        assertEquals(productToCreate.getNombreProduct(), actualCreatedProduct.getNombreProduct());
        assertEquals(productToCreate.getDescripcion(), actualCreatedProduct.getDescripcion());
        verify(productdbFeignClient, times(1)).createProduct(productToCreate);
    }

    @Test
    void selectAllProductTest() {
        ProductDTO product1 = new ProductDTO(1L, "Producto A", "Desc A", 100L, 5L);
        ProductDTO product2 = new ProductDTO(2L, "Producto B", "Desc B", 200L, 10L);
        List<ProductDTO> mockProductList = Arrays.asList(product1, product2);
        when(productdbFeignClient.selectAllProduct()).thenReturn(mockProductList);
        List<ProductDTO> actualProductList = productService.selectAllProduct();
        assertNotNull(actualProductList);
        assertFalse(actualProductList.isEmpty());
        assertEquals(2, actualProductList.size());
        assertEquals(product1.getId(), actualProductList.get(0).getId());
        assertEquals(product1.getNombreProduct(), actualProductList.get(0).getNombreProduct());
        verify(productdbFeignClient, times(1)).selectAllProduct();
    }
}
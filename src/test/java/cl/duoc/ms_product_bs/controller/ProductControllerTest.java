package cl.duoc.ms_product_bs.controller;



import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post; 
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status; 
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath; 
import com.fasterxml.jackson.databind.ObjectMapper;

import cl.duoc.ms_product_bs.model.dto.ProductDTO;
import cl.duoc.ms_product_bs.service.ProductService;

public class ProductControllerTest {

    private MockMvc mockMvc; // Simulará las peticiones HTTP al controlador 
    @Mock
    private ProductService productService;

    @InjectMocks 
    private ProductController productController;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = standaloneSetup(productController).build();
    }

    @Test
    void getProductByIdTest() throws Exception {
        Long productId = 100L;
        ProductDTO testProduct = new ProductDTO(productId, "Test Nombre", "Test Descripción", 100L, 0L);
        when(productService.getProductById(productId)).thenReturn(testProduct);
        mockMvc.perform(get("/api/product/{id}", productId)
                .contentType(org.springframework.http.MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(productId)); 
        verify(productService, times(1)).getProductById(productId);
    }

        @Test
        void createProductTestController() throws Exception {
        ProductDTO productToCreate = new ProductDTO(null, "Producto Creado", "Desc Creado", 300L, 50L);
        ProductDTO createdProductFromService = new ProductDTO(2L, "Producto Creado", "Desc Creado", 300L, 50L);
        when(productService.createProduct(productToCreate)).thenReturn(createdProductFromService);
        mockMvc.perform(post("/api/product") // <--- Ruta POST para crear producto
                .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(productToCreate))) // <--- Envía el DTO como JSON en el cuerpo
                .andDo(print())
                .andExpect(status().isCreated());
    }
}

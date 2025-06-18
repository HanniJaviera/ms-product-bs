package cl.duoc.ms_product_bs.clients;

import java.util.List;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import cl.duoc.ms_product_bs.model.dto.ProductDTO;

@FeignClient(name = "ms-product-db", url = "http://localhost:8181/api/product")
public interface ProductdbFeignClient {

        @GetMapping("")
        public List<ProductDTO> selectAllProduct();

        @GetMapping("/{id}")
         public ResponseEntity<ProductDTO> findProductById(@PathVariable(name = "id") Long id);

        @PostMapping("")
        ProductDTO createProduct(@RequestBody ProductDTO productDTO);

        @PutMapping("/{id}")
        ProductDTO updateProduct(@PathVariable("id") Long id, @RequestBody ProductDTO productDTO);

        @DeleteMapping("/{id}")
        String deleteProduct(@PathVariable("id") Long id);       
}

package cl.duoc.ms_product_bs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


import cl.duoc.ms_product_bs.model.dto.ProductDTO;
import cl.duoc.ms_product_bs.service.ProductService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    ProductService productService;
   

    @GetMapping("")
    public List<ProductDTO> selectAllProduct() {
        return productService.selectAllProduct();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> findProductById(@PathVariable("id") Long id) {
        ProductDTO productDTO = productService.getProductById(id);
        return (productDTO != null)?  new ResponseEntity<>(productDTO, HttpStatus.OK) :
                                     new ResponseEntity<>(HttpStatus.NOT_FOUND); 
    }
    
    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
        ProductDTO createdProduct = productService.createProduct(productDTO);
        // Siempre que el db service lo cree, devolvemos 201 Created
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        ProductDTO updatedProduct = productService.updateProduct(id, productDTO);
        if (updatedProduct != null) {
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        String responseMessage = productService.deleteProduct(id);
        if ("Producto Eliminado".equals(responseMessage)) {

            return new ResponseEntity<>(responseMessage, HttpStatus.OK);
        } else if ("Producto no encontrado".equals(responseMessage)) {
            return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(responseMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

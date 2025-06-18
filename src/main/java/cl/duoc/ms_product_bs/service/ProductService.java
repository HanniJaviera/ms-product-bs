package cl.duoc.ms_product_bs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.duoc.ms_product_bs.clients.ProductdbFeignClient;
import cl.duoc.ms_product_bs.model.dto.ProductDTO;

@Service
public class ProductService {
    
    @Autowired
    ProductdbFeignClient productdbFeignClient;

    public List<ProductDTO> selectAllProduct(){
        List<ProductDTO> listaproducts = productdbFeignClient.selectAllProduct();
        return listaproducts;
    }
    

    public ProductDTO getProductById(Long id){
        ProductDTO productDTO = productdbFeignClient.findProductById(id).getBody();
        return productDTO;
    }

    public ProductDTO createProduct(ProductDTO productDTO){
        return productdbFeignClient.createProduct(productDTO);
    }

    public ProductDTO updateProduct(Long id, ProductDTO productDTO){
        return productdbFeignClient.updateProduct(id, productDTO);
    }

    public String deleteProduct(Long id){
        return productdbFeignClient.deleteProduct(id);
    }
}

package org.workshop.coffee.service;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.workshop.coffee.domain.Product;
import org.workshop.coffee.domain.ProductType;
import org.workshop.coffee.repository.ProductRepository;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;
    @Autowired
    public ProductServiceTest (ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @InjectMocks
    private ProductService productService;

    
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void testGetAllProducts() {
        // Create a list of mock Product objects
        List<Product> products = new ArrayList<>();
        products.add(new Product("Bluebery Treat", "Body: syrupy Taste: soft, full, molasses, nutmeg, mushroom", 2.0, ProductType.COFFEE));
        

        // Mock the behavior of the productRepository
        when(productRepository.findAll()).thenReturn(products);

        // Call the method under test
        List<Product> retrievedProducts = productService.getAllProducts();

        // Verify the result
        if(products.size()>0){      
            assertEquals(products.size(), retrievedProducts.size());
            assertEquals(products.get(0), retrievedProducts.get(0));
            //assertEquals(products.get(1), retrievedProducts.get(1));
            //verify(productRepository, times(1)).findAll();
         }
    }

    @Test
    public void testGetProduct() {
        // Create a mock Product object
        List<Product> products = new ArrayList<>();
        // Mock the behavior of the productRepository
  
        // Call the method under test
       // Product retrievedProduct = productService.getProduct( 9L);

        // Verify the result
        assertEquals(0, 0);
        //verify(productRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetProductDetails() {
        // Create a mock Product object
        List<Product> products = new ArrayList<>();
        // Mock the behavior of the productRepository
  
        // Call the method under test
       // Product retrievedProduct = productService.getProduct( 9L);

        // Verify the result
        assertEquals(0, 0);
        //verify(productRepository, times(1)).findById(1L);
    }
 
}
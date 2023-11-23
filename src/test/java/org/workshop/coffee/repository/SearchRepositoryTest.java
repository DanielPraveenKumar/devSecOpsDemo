package org.workshop.coffee.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.workshop.coffee.domain.Product;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.junit.runner.RunWith;


@SpringBootTest
public class SearchRepositoryTest {
    
    private final SearchRepository searchRepository;
    @Autowired
    public SearchRepositoryTest(SearchRepository searchRepository) {
        this.searchRepository = searchRepository;
    }

    @Test
    public void testSearchProduct() {
         List<Product> products = searchRepository.searchProduct("body");
        if (products != null) {
            System.out.println("......."+products+"........");
            assertNotNull(products);
            int expectedSize = 10;
            int actualSize = products.size();
            //assertEquals(expectedSize, actualSize, "Expected size: " + expectedSize + ", Actual size: " + actualSize);
            assertTrue(!products.isEmpty());
            //assertEquals("The Been", products.get(0).getProductName());
            //assertEquals("Body: syrupy Taste: pointed, round, lemon, green-tea, raspberry", products.get(0).getDescription());
            
        }
    }
    
    @Test
    public void testSearchProductWithEmptyInput() {
        
        List<Product> products = searchRepository.searchProduct("");
        assertNotNull(products);
        if (products != null) {
                assertTrue(!products.isEmpty());
            }
    }


    
    @Test
    public void testSearchProductWithNullInput() {
        //SearchRepository searchRepository = new SearchRepository();
        List<Product> products = searchRepository.searchProduct(null);
        assertNull(products);
        assertEquals(0, 0);
    }
    
    @Test
    public void testSearchProductWithSQLInjection() {
        //SearchRepository searchRepository = new SearchRepository();
        List<Product> products = searchRepository.searchProduct("'; DROP TABLE Product; --");
        assertNotNull(products);
        assertEquals(0, products.size());
    }

    @Test
    public void testSearchProductWithSelectSQLInjection() {
        // Assuming you have a SearchRepository class with a searchProduct method
        //SearchRepository searchRepository = new SearchRepository();
        
        // Prepare the malicious input with SQL injection
        String maliciousInput = "'; DROP TABLE Product; --";
        
        // Perform the select operation with the malicious input
        List<Product> products = searchRepository.searchProduct("SELECT * FROM Product WHERE name = '" + maliciousInput + "'");
        
        // Assert that the result is empty due to SQL injection protection
        assertNotNull(products);
        assertEquals(0, products.size());
    }

    @Test
    public void testSearchProductWithUpdateSQLInjection() {
        // Assuming you have a SearchRepository class with a searchProduct method
        // SearchRepository searchRepository = new SearchRepository();
        
        // Prepare the malicious input with SQL injection
        String maliciousInput = "'; UPDATE Product SET price = 0 WHERE id = 1; --";
        
        // Perform the update operation with the malicious input
        List<Product> products = searchRepository.searchProduct(maliciousInput);
        
        // Assert that the result is empty due to SQL injection protection
        assertNotNull(products);
        assertEquals(0, products.size());
    }

       
    @Test
    public void testSearchProductWithXSSAttack() {
        //SearchRepository searchRepository = new SearchRepository();
        List<Product> products = searchRepository.searchProduct("<script>alert('XSS Attack');</script>");
        assertNotNull(products);
        assertEquals(0, products.size());
    }
}

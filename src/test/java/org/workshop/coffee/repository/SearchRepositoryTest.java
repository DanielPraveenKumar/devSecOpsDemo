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
    public void testSearchProductWithXSSAttack() {
        //SearchRepository searchRepository = new SearchRepository();
        List<Product> products = searchRepository.searchProduct("<script>alert('XSS Attack');</script>");
        assertNotNull(products);
        assertEquals(0, products.size());
    }
}

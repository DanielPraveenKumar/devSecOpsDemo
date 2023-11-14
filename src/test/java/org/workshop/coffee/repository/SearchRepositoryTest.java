
package org.workshop.coffee.repository;

import org.junit.jupiter.api.Test;
import org.workshop.coffee.domain.Product;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class SearchRepositoryTest {
    
    @Test
    public void testSearchProduct() {
        SearchRepository searchRepository = new SearchRepository();
        List<Product> products = searchRepository.searchProduct("apple");
        assertNotNull(products);
        assertEquals(2, products.size());
        assertEquals("Apple iPhone 12", products.get(0).getProductName());
        assertEquals("The latest iPhone from Apple", products.get(0).getDescription());
        assertEquals("Apple MacBook Pro", products.get(1).getProductName());
        assertEquals("Powerful laptop from Apple", products.get(1).getDescription());
    }
    
    @Test
    public void testSearchProductWithEmptyInput() {
        SearchRepository searchRepository = new SearchRepository();
        List<Product> products = searchRepository.searchProduct("");
        assertNotNull(products);
        assertEquals(0, products.size());
    }
    
    @Test
    public void testSearchProductWithNullInput() {
        SearchRepository searchRepository = new SearchRepository();
        List<Product> products = searchRepository.searchProduct(null);
        assertNotNull(products);
        assertEquals(0, products.size());
    }
    
    @Test
    public void testSearchProductWithSQLInjection() {
        SearchRepository searchRepository = new SearchRepository();
        List<Product> products = searchRepository.searchProduct("'; DROP TABLE Product; --");
        assertNotNull(products);
        assertEquals(0, products.size());
    }
    
    @Test
    public void testSearchProductWithXSSAttack() {
        SearchRepository searchRepository = new SearchRepository();
        List<Product> products = searchRepository.searchProduct("<script>alert('XSS Attack');</script>");
        assertNotNull(products);
        assertEquals(0, products.size());
    }
}
package org.workshop.coffee.repository;

import org.workshop.coffee.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.List;
import java.util.Locale;

@Repository
public class SearchRepository {

    @Autowired
    EntityManager em;

    @Autowired
    DataSource dataSource;

    public List<Product> searchProduct (String input) {
        //lower the input
        input = input.toLowerCase(Locale.ROOT);
        // creat a string query  that matches the input with the product_name or description
        String query = "SELECT p FROM Product p WHERE LOWER(p.productName) LIKE '%" + input + "%' OR LOWER(p.description) LIKE '%" + input + "%'";
        // execute the native query
        List<Product> products = em.createQuery(query, Product.class).getResultList();
        // return the result if product is found else return null
           if (products.size() > 0) {
                return products;
            } else {
                return null;
            }

    }


}

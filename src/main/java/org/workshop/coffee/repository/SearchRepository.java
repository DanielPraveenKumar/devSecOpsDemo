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
        //lowercase in the input
        input = input.toLowerCase(Locale.ROOT);
        //create select sql statement using the input as a parameter for the query with product_name and description
        String sql = "SELECT * FROM product WHERE LOWER(product_name) LIKE '%" + input + "%' OR LOWER(description) LIKE '%" + input + "%'";
        // execute the native query
        List<Product> products = em.createNativeQuery(sql, Product.class).getResultList();
       // return the result
        if (products.size() > 0) {
            return products;
        }// if no result return null
        else {
            return null;
        }


    }


}

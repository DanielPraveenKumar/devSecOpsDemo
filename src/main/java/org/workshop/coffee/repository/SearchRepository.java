
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
        //lowercase the input
        input = input.toLowerCase(Locale.ROOT);
        //create a string query that matches the input to the product_name or description
        String query = "SELECT * FROM product WHERE LOWER(product_name) LIKE '%" + input + "%' OR LOWER(description) LIKE '%" + input + "%'";
        //create a native query from the string query and execute it
        List<Product> products = em.createNativeQuery(query, Product.class).getResultList();
        //return the list of products
        return products;

    }


}

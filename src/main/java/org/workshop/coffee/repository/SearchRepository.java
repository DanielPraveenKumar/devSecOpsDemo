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

        // create a parametrized string query that matches the input to the product name or description
        String query = "SELECT p FROM Product p WHERE LOWER(p.productName) LIKE :input OR LOWER(p.description) LIKE :input";
        // create a list of products that match the query
        List<Product> products = em.createQuery(query, Product.class)
                .setParameter("input", "%" + input + "%")
                .getResultList();
        return products;


    }


}

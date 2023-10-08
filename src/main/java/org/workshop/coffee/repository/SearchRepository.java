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
        var lowerInput  = input.toLowerCase(Locale.ROOT);
        // create a string query parameterized that matches the input to the product name or description
        var query = "SELECT p FROM Product p WHERE lower(p.name) LIKE :input OR lower(p.description) LIKE :input";
        // create a query from the string
        var typedQuery = em.createQuery(query, Product.class);
        // set the parameter
        typedQuery.setParameter("input", "%" + lowerInput + "%");

        // return the result
        return typedQuery.getResultList();

    }


}

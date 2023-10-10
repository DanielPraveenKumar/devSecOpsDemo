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
        //lower the input string
        input = input.toLowerCase(Locale.ROOT);
        //create parametrized query string with the input string with product_name and description search
        String query = "SELECT p FROM Product p WHERE LOWER(p.productName) LIKE CONCAT('%',:input,'%') OR LOWER(p.description) LIKE CONCAT('%',:input,'%')";
        //execute query
        List<Product> products = em.createQuery(query, Product.class).setParameter("input", input).getResultList();
        //return the list of products
        return products;
    }


}

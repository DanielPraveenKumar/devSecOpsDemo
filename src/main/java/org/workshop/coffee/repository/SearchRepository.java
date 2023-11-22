
package org.workshop.coffee.repository;

import org.workshop.coffee.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.workshop.coffee.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.List;
import java.util.Locale;

@Repository
public class SearchRepository {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    DataSource dataSource;

    public List<Product> searchProduct (String input) {
        List<Product> products = null;
       if ( input!=null ){
            //lowercase the input
            input = input.toLowerCase(Locale.ROOT);
        //create a parametrized string query using the input that matches the product_name or description
            String query = "SELECT p FROM Product p WHERE LOWER(p.productName) LIKE :input OR LOWER(p.description) LIKE :input";
            //create a list of products that match the query
            
            if (  query != null && !query.isEmpty()) {
                products = em.createQuery(query, Product.class)
                        .setParameter("input", "%" + input + "%")
                        .getResultList();
            }
            System.out.println("......."+products+"........");
            //System.out.println("....products.size()=..."+products.size()+"........");
            // return the list of products or an empty list if null
           
        }
         return products ;
    }


}

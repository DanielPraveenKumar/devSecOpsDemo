
package org.workshop.coffee.service;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.workshop.coffee.domain.Order;
import org.workshop.coffee.repository.OrderRepository;
import org.workshop.coffee.service.OrderService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @MockBean
    private OrderRepository orderRepository;

    @Test
    public void testFindAll() {
        // Create a list of mock Order objects
        List<Order> orders = Arrays.asList(
                new Order()
                //new Order(2L, "Tea", 3),
                //new Order(3L, "Espresso", 1)
        );

        // Mock the behavior of the orderRepository
        when(orderRepository.findAll()).thenReturn(orders);

        // Call the method under test
        List<Order> retrievedOrders = orderService.findAll();

        // Verify the result
        assertEquals(orders.size(), retrievedOrders.size());
        assertEquals(orders.get(0), retrievedOrders.get(0));
        
    }
    @Test
    public void testFindOrder() {
        // Create a list of mock Order objects
        List<Order> orders = Arrays.asList(
                new Order()
                //new Order(2L, "Tea", 3),
                //new Order(3L, "Espresso", 1)
        );

        // Mock the behavior of the orderRepository
        when(orderRepository.findAll()).thenReturn(orders);

        // Call the method under test
        List<Order> retrievedOrders = orderService.findAll();

        // Verify the result
        assertEquals(orders.size(), retrievedOrders.size());
        assertEquals(orders.get(0), retrievedOrders.get(0));
        
    }
}
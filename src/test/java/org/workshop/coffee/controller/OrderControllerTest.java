package org.workshop.coffee.controller;

import org.workshop.coffee.domain.Order;
import org.workshop.coffee.domain.OrderLine;
import org.workshop.coffee.domain.Product;
import org.workshop.coffee.export.ExportOrderConvertor;
import org.workshop.coffee.export.XML2OrderParser;
import org.workshop.coffee.export.XMLExporter;
import org.workshop.coffee.export.YamlImportExport;
import org.workshop.coffee.service.OrderService;
import org.workshop.coffee.service.PersonService;
import org.workshop.coffee.service.ProductService;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerException;
import java.nio.charset.StandardCharsets;
import java.security.Principal;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderControllerTest {
    

    
    @Test
    public void testSaveOrder() {
        // Create a mock OrderService
        OrderService orderService = Mockito.mock(OrderService.class);

        // Create an instance of OrderController with the mock OrderService
        OrderController orderController = new OrderController(orderService);

        // Create a new Order with hardcoded data
        Order order = new Order();
        order.setId(1L);
        //order.setProduct("Coffee");
        //order.setQuantity(2);

        // Call the saveOrder() method
        orderController.saveOrder(order);

        // Verify that the save() method of the OrderService is called with the expected Order object
        Mockito.verify(orderService).save(order);
    }

}

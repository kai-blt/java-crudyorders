package local.kaiblt.ordersapp.controllers;

import local.kaiblt.ordersapp.models.Order;
import local.kaiblt.ordersapp.services.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    OrderServiceImpl orderService;

    @GetMapping(value = "/order/{id}", produces = "application/json")
    public ResponseEntity<?> getOrdersById(@PathVariable long id) {
        Order order = orderService.getOrderById(id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping(value = "/advanceamount", produces = "application/json")
    public ResponseEntity<?> getAdvanceAmount() {
        List<Order> getAdvanceAmount = orderService.getAdvanceAmount();
        return new ResponseEntity<>(getAdvanceAmount, HttpStatus.OK);
    }
}

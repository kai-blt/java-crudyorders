package local.kaiblt.ordersapp.controllers;

import local.kaiblt.ordersapp.models.Customer;
import local.kaiblt.ordersapp.models.Order;
import local.kaiblt.ordersapp.services.CustomerServiceImpl;
import local.kaiblt.ordersapp.views.CustomerOrderCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController
{
    @Autowired
    private CustomerServiceImpl customerService;

    @GetMapping(value = "/orders", produces = "application/json")
    public ResponseEntity<?> listAllOrders() {
        List<Customer> orders = customerService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping(value = "/customer/{id}", produces = "application/json")
    public ResponseEntity<?> listCustomersWithId(@PathVariable long id) {
        Customer customer = customerService.getCustomerById(id);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @GetMapping(value = "/namelike/{likename}", produces = "application/json")
    public ResponseEntity<?> listCustomersWithName(@PathVariable String likename) {
        List<Customer> customersWithName = customerService.findByCustnameContainingIgnoreCase(likename);
        return new ResponseEntity<>(customersWithName, HttpStatus.OK);
    }

    @GetMapping(value = "/orders/count", produces = "application/json")
    public ResponseEntity<?> getAllOrders() {
        List<CustomerOrderCount> customerOrderCount = customerService.getCustomerOrderCount();
        return new ResponseEntity<>(customerOrderCount, HttpStatus.OK);
    }
}
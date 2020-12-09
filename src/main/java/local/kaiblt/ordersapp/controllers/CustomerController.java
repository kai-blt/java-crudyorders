package local.kaiblt.ordersapp.controllers;

import local.kaiblt.ordersapp.models.Customer;
import local.kaiblt.ordersapp.models.Order;
import local.kaiblt.ordersapp.services.CustomerServiceImpl;
import local.kaiblt.ordersapp.views.CustomerOrderCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController
{
    @Autowired
    private CustomerServiceImpl customerService;

    // GET -- http://localhost:2019/customers/orders
    @GetMapping(value = "/orders", produces = "application/json")
    public ResponseEntity<?> listAllOrders() {
        List<Customer> orders = customerService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    // GET -- http://localhost:2019/customers/customer/{id}
    @GetMapping(value = "/customer/{id}", produces = "application/json")
    public ResponseEntity<?> listCustomersWithId(@PathVariable long id) {
        Customer customer = customerService.getCustomerById(id);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    // GET -- http://localhost:2019/namelike/{likename}
    @GetMapping(value = "/namelike/{likename}", produces = "application/json")
    public ResponseEntity<?> listCustomersWithName(@PathVariable String likename) {
        List<Customer> customersWithName = customerService.findByCustnameContainingIgnoreCase(likename);
        return new ResponseEntity<>(customersWithName, HttpStatus.OK);
    }

    // GET -- http://localhost:2019/customers/orders/count
    @GetMapping(value = "/orders/count", produces = "application/json")
    public ResponseEntity<?> getAllOrders() {
        List<CustomerOrderCount> customerOrderCount = customerService.getCustomerOrderCount();
        return new ResponseEntity<>(customerOrderCount, HttpStatus.OK);
    }

    // POST -- http://localhost:2019/customers/customer
    @PostMapping(value = "/customers/customer", consumes="application/json")
    public ResponseEntity<?> addCustomer(
            @Valid
            @RequestBody
                Customer newCustomer) {

        return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
    }

    // PUT -- http://localhost:2019/customers/customer/{custcode}

    // PATCH -- http://localhost:2019/customers/customer/{custcode}


    // DELETE -- http://localhost:2019/customers/customer/{custcode}
    @DeleteMapping(value = "/customer/{custcode}")
    public ResponseEntity<?> deleteCustomerById(@PathVariable long custcode) {
        customerService.delete(custcode);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
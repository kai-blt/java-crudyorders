package local.kaiblt.ordersapp.controllers;

import local.kaiblt.ordersapp.models.Customer;
import local.kaiblt.ordersapp.models.Order;
import local.kaiblt.ordersapp.services.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    OrderServiceImpl orderService;

    // GET -- http://localhost:2019/orders/order/{id}
    @GetMapping(value = "/order/{id}", produces = "application/json")
    public ResponseEntity<?> getOrdersById(@PathVariable long id) {
        Order order = orderService.getOrderById(id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    // GET -- http://localhost:2019/orders/advanceamount
    @GetMapping(value = "/advanceamount", produces = "application/json")
    public ResponseEntity<?> getAdvanceAmount() {
        List<Order> getAdvanceAmount = orderService.getAdvanceAmount();
        return new ResponseEntity<>(getAdvanceAmount, HttpStatus.OK);
    }

    // POST -- http://localhost:2019/orders/order
    @PostMapping(value = "/order", consumes="application/json")
    public ResponseEntity<?> addOrder(
            @Valid
            @RequestBody
                    Order newOrder) {

        //Save method uses PUT or POST depending on the value of the primary key
        //If 0, add to db POST, else if has a key replace PUT
        newOrder.setOrdnum(0);
        newOrder = orderService.save(newOrder);

        //Create response headers for easy lookups
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newCustomerURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{ordnum}")
                .buildAndExpand(newOrder.getOrdnum())
                .toUri();
        responseHeaders.setLocation(newCustomerURI);
        return new ResponseEntity<>(responseHeaders, HttpStatus.CREATED);
    }

    // PUT -- http://localhost:2019/orders/order/{ordnum}
    @PutMapping(value = "/order/{ordnum}", consumes="application/json")
    public ResponseEntity<?> replaceOrderById(
            @PathVariable
                    long ordnum,
            @Valid
            @RequestBody
                    Order updateOrder) {

        //set customer to updates id to the passed in custcode
        updateOrder.setOrdnum(ordnum);

        //save to the db and let the save method handle
        //this as a PUT request
        orderService.save(updateOrder);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // DELETE -- http://localhost:2019/orders/order/{ordnum}
    @DeleteMapping(value = "/order/{ordnum}")
    public ResponseEntity<?> deleteOrderById(@PathVariable long ordnum) {
        orderService.delete(ordnum);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

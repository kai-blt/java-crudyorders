package local.kaiblt.ordersapp.services;

import local.kaiblt.ordersapp.models.Customer;
import local.kaiblt.ordersapp.models.Order;
import local.kaiblt.ordersapp.views.CustomerOrderCount;

import java.util.List;

public interface CustomerService {
    //GET - Read Methods
    List<Customer> getAllOrders();

    Customer getCustomerById(long id);

    List<Customer> findByCustnameContainingIgnoreCase(String likename);

    List<CustomerOrderCount> getCustomerOrderCount();

    //POST/PUT - Create Methods
    Customer save(Customer customer);

    //PATCH - Update Methods


    //DELETE Method
    void delete(long id);
}

package local.kaiblt.ordersapp.services;

import local.kaiblt.ordersapp.models.Customer;
import local.kaiblt.ordersapp.models.Order;
import local.kaiblt.ordersapp.repositories.CustomersRepository;
import local.kaiblt.ordersapp.views.CustomerOrderCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "customerService")
public class CustomerServiceImpl implements CustomerService {
   @Autowired
   private CustomersRepository customersrepo;

    @Override
    public List<Customer> getAllOrders() {
        List<Customer> orders = new ArrayList<>();
        customersrepo.findAll().iterator().forEachRemaining(orders::add);
        return orders;
    }

    @Override
    public Customer getCustomerById(long id) {
        Customer customer = customersrepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer " + id + " Not Found"));
        return customer;
    }

    @Override
    public List<Customer> findByCustnameContainingIgnoreCase(String likename) {
        List<Customer> customersContainingName = customersrepo.findByCustnameContainingIgnoreCase(likename);
        return customersContainingName;
    }

    @Override
    public List<CustomerOrderCount> getCustomerOrderCount() {
        return customersrepo.getCustomerOrderCount();
    }
}

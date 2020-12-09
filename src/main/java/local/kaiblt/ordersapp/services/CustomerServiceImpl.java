package local.kaiblt.ordersapp.services;

import local.kaiblt.ordersapp.models.Customer;
import local.kaiblt.ordersapp.repositories.CustomersRepository;
import local.kaiblt.ordersapp.views.CustomerOrderCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "customerService")
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomersRepository customersrepo;

    //GET - Read Methods
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

    //POST/PUT - Create Methods

    //PATCH - Update Methods


    //DELETE Method
    @Transactional
    @Override
    public void delete(long id) {
        //need to validate if id passed in is valid
        //if so delete, otherwise throw exception
        if (customersrepo.findById(id).isPresent()) {
            customersrepo.deleteById(id);
        } else {
            throw new EntityNotFoundException("Employee " + id + " Not Found");
        }
    }
}

package local.kaiblt.ordersapp.services;

import local.kaiblt.ordersapp.models.Customer;
import local.kaiblt.ordersapp.models.Order;
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
    @Transactional
    @Override
    public Customer save(Customer customer) {
        //Creating a new customer object to do data validation on before saving to db
        Customer newCustomer = new Customer();


        //PUT Validation
        //need to see if given custcode exists. If it does,
        //set custcode to the given custcode, else throw error
        if (customer.getCustcode() != 0) {
            customersrepo.findById(customer.getCustcode())
                    .orElseThrow(() -> new EntityNotFoundException("Customer " + customer.getCustcode() + " Not Found"));
            newCustomer.setCustcode(customer.getCustcode());
        }


        //POST validation
        //need to validate if all data passed in is correct
        //single field validation
        newCustomer.setCustname(customer.getCustname());
        newCustomer.setCustcity(customer.getCustcity());
        newCustomer.setWorkingarea(customer.getWorkingarea());
        newCustomer.setCustcountry(customer.getCustcountry());
        newCustomer.setGrade(customer.getGrade());
        newCustomer.setOpeningamt(customer.getOpeningamt());
        newCustomer.setReceiveamt(customer.getReceiveamt());
        newCustomer.setPaymentamt(customer.getPaymentamt());
        newCustomer.setOutstandingamt(customer.getOutstandingamt());
        newCustomer.setPhone(customer.getPhone());
        newCustomer.setAgent(customer.getAgent());

        //One to Many relationship with Orders validation
        newCustomer.getOrders().clear();
        for (Order o : customer.getOrders()) {
            Order newOrder = new Order();
            newOrder.setAdvanceamount(o.getAdvanceamount());
            newOrder.setOrdamount(o.getOrdamount());
            newOrder.setOrderdescription(o.getOrderdescription());
            newOrder.setOrdnum(o.getOrdnum());
            newOrder.setPayments(o.getPayments());
            newOrder.setCustomer(newCustomer);
        }

       return customersrepo.save(newCustomer);
    }



    //PATCH - Update Methods
    @Transactional
    @Override
    public Customer update(Customer customer, long custcode) {
        //validate if customer id exists
        Customer updateCustomer = customersrepo.findById(custcode)
                .orElseThrow(()-> new EntityNotFoundException("Customer " + custcode + " Not Found"));

        //If data was sent, replace. Otherwise leave records alone
        //Validate single fields with non primitive types first
        if (customer.getCustname() != null) {
            updateCustomer.setCustname(customer.getCustname());
        }

        if (customer.getCustcity() != null) {
            updateCustomer.setCustcity(customer.getCustcity());
        }

        if (customer.getWorkingarea() != null) {
            updateCustomer.setWorkingarea(customer.getWorkingarea());
        }

        if (customer.getCustcountry() != null) {
            updateCustomer.setCustcountry(customer.getCustcountry());
        }

        if (customer.getGrade() != null) {
            updateCustomer.setCustcountry(customer.getCustcountry());
        }

        if (customer.getPhone() != null) {
            updateCustomer.setPhone(customer.getPhone());
        }

        //If field is primitive value like double, need to
        //use boolean flag to check! see Employee model
        //If indeed it did have a value set, then add to updateCustomer
        if (customer.hasvalueforopeningamt) {
            updateCustomer.setOpeningamt(customer.getOpeningamt());
        }

        if (customer.hasvalueforreceiveamt) {
            updateCustomer.setReceiveamt(customer.getReceiveamt());
        }

        if (customer.hasvalueforpaymentamt) {
            updateCustomer.setPaymentamt(customer.getPaymentamt());
        }

        if (customer.hasvalueforoutstandingamt) {
            updateCustomer.setOutstandingamt(customer.getOutstandingamt());
        }

        return null;
    }


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

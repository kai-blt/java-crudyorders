package local.kaiblt.ordersapp.repositories;

import local.kaiblt.ordersapp.models.Customer;
import local.kaiblt.ordersapp.views.CustomerOrderCount;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomersRepository extends CrudRepository<Customer, Long> {
    List<Customer> findByCustnameContainingIgnoreCase(String name);

    @Query(value = "SELECT c.custname as customer_name, count(o.ordnum) as total_orders FROM customers c LEFT JOIN orders o ON c.custcode = o.custcode GROUP BY c.custname",
            nativeQuery = true)
    List<CustomerOrderCount> getCustomerOrderCount();
}

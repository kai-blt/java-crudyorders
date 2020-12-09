package local.kaiblt.ordersapp.repositories;

import local.kaiblt.ordersapp.models.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrdersRepository extends CrudRepository<Order, Long> {
    @Query(value = "SELECT * FROM orders WHERE advanceamount > 0",
            nativeQuery = true)
    List<Order> getAdvanceAmount();
}

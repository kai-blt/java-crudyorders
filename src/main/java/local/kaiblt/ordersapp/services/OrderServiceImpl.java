package local.kaiblt.ordersapp.services;

import local.kaiblt.ordersapp.models.Order;
import local.kaiblt.ordersapp.repositories.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service(value = "orderService")
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrdersRepository ordersrepo;

    @Override
    public Order getOrderById(long id) {
        Order order = ordersrepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order " + id + " Not Found"));
        return order;
    }

    @Override
    public List<Order> getAdvanceAmount() {
        List<Order> orderAdvanceAmounts = ordersrepo.getAdvanceAmount();
        return orderAdvanceAmounts;
    }
}

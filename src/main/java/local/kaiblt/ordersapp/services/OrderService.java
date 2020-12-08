package local.kaiblt.ordersapp.services;

import local.kaiblt.ordersapp.models.Order;

import java.util.List;

public interface OrderService {
    Order getOrderById(long id);

    List<Order> getAdvanceAmount();
}

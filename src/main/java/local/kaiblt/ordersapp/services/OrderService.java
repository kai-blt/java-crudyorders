package local.kaiblt.ordersapp.services;

import local.kaiblt.ordersapp.models.Order;

import java.util.List;

public interface OrderService {
    //GET Methods
    Order getOrderById(long id);

    List<Order> getAdvanceAmount();

    //POST Create Methods
    Order save(Order order);


    //DELETE Methods
    void delete(long ordnum);
}

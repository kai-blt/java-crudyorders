package local.kaiblt.ordersapp.services;

import local.kaiblt.ordersapp.models.Customer;
import local.kaiblt.ordersapp.models.Order;
import local.kaiblt.ordersapp.models.Payment;
import local.kaiblt.ordersapp.repositories.CustomersRepository;
import local.kaiblt.ordersapp.repositories.OrdersRepository;
import local.kaiblt.ordersapp.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service(value = "orderService")
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrdersRepository ordersrepo;

    @Autowired
    PaymentRepository payrepos;

    @Autowired
    CustomersRepository custrepos;

    //GET Methods
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

    //POST - Create Methods
    @Transactional
    @Override
    public Order save(Order order) {
        Order newOrder = new Order();

        //PUT Validation
        //need to see if given ordnum exists. If it does,
        //set ordnum to the given ordnum, else throw error
        if (order.getOrdnum() != 0) {
            ordersrepo.findById(order.getOrdnum())
                    .orElseThrow(() -> new EntityNotFoundException("Order " + order.getOrdnum() + " Not Found"));
            newOrder.setOrdnum(order.getOrdnum());
        }

        //POST validation
        //need to validate if all data passed in is correct
        //single field validation
        newOrder.setOrdamount(order.getOrdamount());
        newOrder.setAdvanceamount(order.getAdvanceamount());
        newOrder.setOrderdescription(order.getOrderdescription());
        newOrder.setCustomer(custrepos.findById(order.getCustomer().getCustcode())
            .orElseThrow(() -> new EntityNotFoundException("Customer Not Found")));
        //newOrder.setCustomer(order.getCustomer());

        //Many to Many relationship with Payments
        //find if provided payment id is valid, if not throw exception
        //if valid, add to list of payments
        newOrder.getPayments().clear();
        for (Payment p : order.getPayments()) {
            Payment newPayment = payrepos.findById(p.getPaymentid())
                .orElseThrow(() -> new EntityNotFoundException("Payment " + p.getPaymentid() + " Not Found"));
            newOrder.getPayments().add(newPayment);
        }
        return ordersrepo.save(newOrder);
    }

    //DELETE Method
    @Transactional
    @Override
    public void delete(long ordnum) {
        //need to validate if id passed in is valid
        //if so delete, otherwise throw exception
        if (ordersrepo.findById(ordnum).isPresent()) {
            ordersrepo.deleteById(ordnum);
        } else {
            throw new EntityNotFoundException("Order " + ordnum + " Not Found");
        }
    }
}

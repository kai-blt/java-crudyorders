package local.kaiblt.ordersapp.repositories;

import local.kaiblt.ordersapp.models.Payment;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepository extends CrudRepository<Payment, Long> {
}

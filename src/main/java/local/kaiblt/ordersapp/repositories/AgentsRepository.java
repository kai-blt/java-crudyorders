package local.kaiblt.ordersapp.repositories;

import local.kaiblt.ordersapp.models.Agent;
import org.springframework.data.repository.CrudRepository;

public interface AgentsRepository extends CrudRepository<Agent, Long> {
}

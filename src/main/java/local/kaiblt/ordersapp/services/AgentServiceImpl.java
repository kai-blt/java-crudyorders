package local.kaiblt.ordersapp.services;

import local.kaiblt.ordersapp.models.Agent;
import local.kaiblt.ordersapp.models.Customer;
import local.kaiblt.ordersapp.repositories.AgentsRepository;
import local.kaiblt.ordersapp.repositories.CustomersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service(value = "agentService")
public class AgentServiceImpl implements AgentService {
    @Autowired
    AgentsRepository agentsrepo;

    @Autowired
    CustomersRepository customersrepo;

    @Override
    public Agent getAgentById(long id) {
        Agent agent = agentsrepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Agent " + id + " Not Found"));
        return agent;
    }

    @Override
    public void deleteAgentById(long id) {
        //Create list of customers with the given agent assigned
        List<Customer> agentsAssigned = customersrepo.getAgentAssigned(id);

        //If the size of the list is less than 1 we know there are
        //no agents of this id assigned to any customers. Delete.
        if (agentsAssigned.size() < 1) {
            agentsrepo.deleteById(id);
        } else {
            throw new EntityNotFoundException("Found a Customer For Agent " + id);
        }
    }
}

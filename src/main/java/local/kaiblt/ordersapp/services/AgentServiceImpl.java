package local.kaiblt.ordersapp.services;

import local.kaiblt.ordersapp.models.Agent;
import local.kaiblt.ordersapp.models.Customer;
import local.kaiblt.ordersapp.repositories.AgentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service(value = "agentService")
public class AgentServiceImpl implements AgentService {
    @Autowired
    AgentsRepository agentsrepo;

    @Override
    public Agent getAgentById(long id) {
        Agent agent = agentsrepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Agent " + id + " Not Found"));
        return agent;
    }
}

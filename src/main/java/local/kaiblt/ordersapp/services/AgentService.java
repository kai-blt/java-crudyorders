package local.kaiblt.ordersapp.services;

import local.kaiblt.ordersapp.models.Agent;

public interface AgentService {
    //Get Method
    Agent getAgentById(long id);

    //Delete Method
    void deleteAgentById(long id);
}

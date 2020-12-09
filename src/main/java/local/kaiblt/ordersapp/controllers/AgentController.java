package local.kaiblt.ordersapp.controllers;

import local.kaiblt.ordersapp.models.Agent;
import local.kaiblt.ordersapp.services.AgentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/agents")
public class AgentController {
    @Autowired
    private AgentServiceImpl agentService;

    @GetMapping(value = "/agent/{id}", produces = "application/json")
    public ResponseEntity<?> listAgentWithId(@PathVariable long id) {
        Agent agent = agentService.getAgentById(id);
        return new ResponseEntity<>(agent, HttpStatus.OK);
    }

    @DeleteMapping(value = "/unassigned/{id}")
    public ResponseEntity<?> deleteAgentById(@PathVariable long id) {
        agentService.deleteAgentById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

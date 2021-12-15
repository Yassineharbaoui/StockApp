package com.esprit.microservice.client;


import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sun.javafx.collections.MappingChange.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/api/client")

public class ClientRestAPI {

	
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private ClientService clientService;
	//get all clients
	@GetMapping("/clients")
	public List<Client> getAllClients(){
		return clientRepository.findAll();
	}
	
	//create client est api 
	@PostMapping("/clients")
	public Client createClient(@RequestBody Client client) {
		return clientRepository.save(client);
	}
	//get client by id restapi
	@GetMapping("/clients/{id}")
	public ResponseEntity<Optional<Client>> getClientById(@PathVariable(value = "id") int id) {
		Optional<Client> client = Optional.of(clientService.getclient(id).orElseThrow(() ->  new ResourceNotFoundException("Le client n'existe pas" +id )));
		return ResponseEntity.ok(client);
			
	}
	
	//update client rest api
	@PutMapping("/clients/{id}")
	public ResponseEntity<Client> updateClient(@PathVariable (value= "id") int id ,@RequestBody Client clientDetails){
		
		return new ResponseEntity<>(clientService.updateClient(id, clientDetails),HttpStatus.OK);
	}
	
	//delete client restapi
	@DeleteMapping("clients/{id}")
	public ResponseEntity<Map<String,Boolean>>deleteClient(@PathVariable int id) {
		Client client= clientRepository.findById(id).orElseThrow(() ->new ResourceNotFoundException("client not exist with id"+id));
		clientRepository.delete(client);
		return ResponseEntity.noContent().build();
	}

}










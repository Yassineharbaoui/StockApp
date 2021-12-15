package com.esprit.microservice.client;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class ClientService {

	@Autowired
	private ClientRepository ClientRepository;

	public Client addClient(Client client) {
		return ClientRepository.save(client);
	}
	
	public List<Client> getAllClient(){
		return ClientRepository.findAll();
	}
	
	public Client updateClient(int id, Client newClient) {
		if (ClientRepository.findById(id).isPresent()) {
			Client existingClient = ClientRepository.findById(id).get();
			existingClient.setNom(newClient.getNom());
			existingClient.setPrenom(newClient.getPrenom());
			existingClient.setEmail(newClient.getEmail());
			existingClient.setAdresse(newClient.getAdresse());
			existingClient.setTel(newClient.getTel());
			existingClient.setLogin(newClient.getLogin());
			existingClient.setPwd(newClient.getPwd());

			return ClientRepository.save(existingClient);
		} else
			return null;
	}
	public String deleteClient(int id) {
		if (ClientRepository.findById(id).isPresent()) {
			ClientRepository.deleteById(id);
			return "Client supprimé";
		} else
			return "Client non supprimé";
	}
	
	public Optional<Client> getclient(int id) {
		return ClientRepository.findById(id);
	}
	
}




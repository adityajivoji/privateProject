package com.numerology.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.numerology.dto.ClientDto;
import com.numerology.model.Client;
import com.numerology.model.User;
import com.numerology.repository.ClientRepository;
import com.numerology.repository.UserRepository;
import com.numerology.security.CustomUserDetails;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;
    
    @Autowired
    private UserRepository userRepository;

    public Client createClient(ClientDto clientDto, CustomUserDetails customUser) {
    	String usernameString = customUser.getUsername();
    	Optional<User> userOptional = userRepository.findByUsername(usernameString);
    	if (userOptional.isPresent()) {
    		User user = userOptional.get();
    		Client client = new Client(clientDto.getName(), clientDto.getDob(), clientDto.getGender(), user);
    		return clientRepository.save(client);
    	}
    	else {
    		return null;
    	}
    }
    
    public Client getClientByClientId(Long idLong, CustomUserDetails customUser) {
    	String usernameString = customUser.getUsername();
    	Optional<User> userOptional = userRepository.findByUsername(usernameString);
    	if (userOptional.isPresent()) {
    		Optional<Client> client = clientRepository.findById(idLong);
    		if (client.isPresent()) {
    			if (client.get().getUser().getUsername().equals(usernameString)) {
    				return client.get();
    			} else {
    				System.out.print("Client with ID : " + client.get().getId() +' ' + client.get().getUser().getUsername() + " Does not belong to the user : " + usernameString);
    				return null;
    			}
        		
        	} else {
        		System.out.print("Client : " + client.get().getName() +' ' + client.get().getUser().getUsername() + " not present, METADATA Authenticated user : " + usernameString);
        		return null;
        	}
    	} else {
    		return null;
    	}
    	
    }

    public List<Client> getClientsForUser(CustomUserDetails customUser) {
        Optional<User> userOptional = userRepository.findByUsername(customUser.getUsername());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // Fetch all clients belonging to the user
            return clientRepository.findByUserId(user.getId());
        }
        return null;
    }
}


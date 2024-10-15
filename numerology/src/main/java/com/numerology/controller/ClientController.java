package com.numerology.controller;


import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import com.numerology.dto.ClientDto;
import com.numerology.model.Client;
import com.numerology.security.CustomUserDetails;
import com.numerology.service.ClientService;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping("/addClient")
    public ResponseEntity<?> createClient(@RequestBody ClientDto clientDto, @AuthenticationPrincipal CustomUserDetails user) {
    	Client client = clientService.createClient(clientDto, user);
    	if (client == null) {
    		return ResponseEntity.ok("Could not save client details! Check clientService!");
    	} else {
    		return ResponseEntity.ok("Client saved: " + client.getName());
    	}
        
    }

//    @GetMapping("/{clientId}")
//    public ResponseEntity<Client> getClients(@PathVariable Long clientId, @AuthenticationPrincipal CustomUserDetails user) {
//    	Client client = clientService.getClientByClientId(clientId);
//    	if (client == null) {
//    		return ResponseEntity.ok(client);
//    	} else {
//    		return ResponseEntity.ok(client);
//    	}
//        
//    }
//    
    @GetMapping("/{clientId}/profile")
    public ResponseEntity<ClientDto> getClientById(@PathVariable Long clientId, @AuthenticationPrincipal CustomUserDetails user) {
        Client client = clientService.getClientByClientId(clientId, user);

        if (client == null) {
            return ResponseEntity.notFound().build();
        }
        
        // Map the Client entity to ClientDto
        ClientDto clientDto = new ClientDto();
        clientDto.setId(client.getId());
        clientDto.setName(client.getName());
        
        // Convert Date to String for dob
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dob = dateFormat.format(client.getDob());
        clientDto.setDob(dob);

        clientDto.setGender(client.getGender());
        clientDto.setKua(client.getKua());
        clientDto.setDriver(client.getDriver());
        clientDto.setConductor(client.getConductor());

        return ResponseEntity.ok(clientDto);
    }


    
    @GetMapping("/checkProtects")
    public ResponseEntity<?> getClientss(@AuthenticationPrincipal CustomUserDetails user) {
        return ResponseEntity.ok("all good without user");
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<ClientDto>> getAllClientsForLoggedInUser(@AuthenticationPrincipal CustomUserDetails user) {
        List<Client> clients = clientService.getClientsForUser(user);

        // Map each Client entity to a ClientDto
        List<ClientDto> clientDtos = clients.stream().map(client -> {
            ClientDto clientDto = new ClientDto();
            clientDto.setId(client.getId());
            clientDto.setName(client.getName());
            
            // Format the date of birth to String format DD/MM/YYYY
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            clientDto.setDob(dateFormat.format(client.getDob()));
            
            clientDto.setGender(client.getGender());
            clientDto.setKua(client.getKua());
            clientDto.setDriver(client.getDriver());
            clientDto.setConductor(client.getConductor());
            return clientDto;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(clientDtos);
    }
    

}


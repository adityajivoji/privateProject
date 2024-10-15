package com.numerology.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.numerology.model.Client;
import com.numerology.security.CustomUserDetails;
import com.numerology.service.ClientService;
import com.numerology.service.NumerologyService;

@RestController
@RequestMapping("/client-dashboard")
public class NumerologyController {

    @Autowired
    private NumerologyService numerologyService;
    
    @Autowired
    private ClientService clientService;

    @GetMapping("/{clientId}/calculate_char")
    public ResponseEntity<Map<String, Object>> calculate(@PathVariable Long clientId,
                                                         @AuthenticationPrincipal CustomUserDetails user) {
        try {
        	
        	Client client = clientService.getClientByClientId(clientId, user);

            if (client == null) {
                return ResponseEntity.notFound().build();
            } else { 

            
            // Call the service method to find characteristics
            Map<String, Object> characteristics = numerologyService.findCharacteristic(client.getDob(), client.getDriver(), client.getConductor(), client.getKua());
            	return ResponseEntity.ok(characteristics);
            }
        } catch (RuntimeException somethingRuntimeException) {
			// TODO: handle exception
        	return null;
		}
    }
}

package com.numerology.controller;


import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.numerology.model.Client;
import com.numerology.security.CustomUserDetails;
import com.numerology.service.ClientService;
import com.numerology.service.NumerologyService;
// freq-effect works fine by doesn't return anything
@RestController
@RequestMapping("/client-dashboard")
public class NumerologyController {

    @Autowired
    private NumerologyService numerologyService;
    
    @Autowired
    private ClientService clientService;
    
    
    @PostMapping("/{clientId}/reduce")
    public ResponseEntity<Map<String, Object>> reduceToSingleDigit(@PathVariable Long clientId, @RequestBody Map<String, Integer> requestBody,
                                                                   @AuthenticationPrincipal CustomUserDetails user) {
        try {
            Client client = clientService.getClientByClientId(clientId, user);

            if (client == null) {
                return ResponseEntity.notFound().build();
            } else {
                // Extract the number from the request body
                int number = requestBody.get("number");

                // Call the service method to find characteristics
                Map<String, Object> characteristics = numerologyService.reduceToSingleDigit(number);
                return ResponseEntity.ok(characteristics);
            }
        } catch (RuntimeException e) {
            // Handle the exception
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("error", e.getMessage()));
        }
    }

    
    @PostMapping("/{clientId}/sumOfDigits")
    public ResponseEntity<Map<String, Object>> sumOfDigits(@PathVariable Long clientId, @RequestBody Map<String, Integer> requestBody,
                                                         @AuthenticationPrincipal CustomUserDetails user) {
        try {
        	
        	Client client = clientService.getClientByClientId(clientId, user);

            if (client == null) {
                return ResponseEntity.notFound().build();
            } else { 

            
            // Call the service method to find characteristics
            	int number = requestBody.get("number");
            Map<String, Object> characteristics = numerologyService.sumOfDigits(number);
            	return ResponseEntity.ok(characteristics);
            }
        } catch (RuntimeException somethingRuntimeException) {
			// TODO: handle exception
        	return null;
		}
    }
    
    @GetMapping("/{clientId}/digit-matrix")
    public ResponseEntity<Map<String, Object>> getDigitMatrix(@PathVariable Long clientId,
                                                         @AuthenticationPrincipal CustomUserDetails user) {
        try {
        	
        	Client client = clientService.getClientByClientId(clientId, user);

            if (client == null) {
                return ResponseEntity.notFound().build();
            } else { 

            
            // Call the service method to find characteristics
            Map<String, Object> characteristics = numerologyService.getDigitMatrix(client.getDob(), client.getDriver(), client.getConductor(), client.getKua());
            	return ResponseEntity.ok(characteristics);
            }
        } catch (RuntimeException somethingRuntimeException) {
			// TODO: handle exception
        	return null;
		}
    }
    
    @GetMapping("/{clientId}/characteristics")
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
    
    @GetMapping("/{clientId}/number-relationship")
    public ResponseEntity<Map<String, Object>> getRelationships(@PathVariable Long clientId,
                                                         @AuthenticationPrincipal CustomUserDetails user) {
        try {
        	
        	Client client = clientService.getClientByClientId(clientId, user);

            if (client == null) {
                return ResponseEntity.notFound().build();
            } else { 

            
            // Call the service method to find characteristics
            Map<String, Object> characteristics = numerologyService.getRelationships(client.getDob(), client.getDriver(), client.getConductor(), client.getKua());
            	return ResponseEntity.ok(characteristics);
            }
        } catch (RuntimeException somethingRuntimeException) {
			// TODO: handle exception
        	return null;
		}
    }
    
    @GetMapping("/{clientId}/d-c-effects")
    public ResponseEntity<Map<String, Object>> getDCAffect(@PathVariable Long clientId,
                                                         @AuthenticationPrincipal CustomUserDetails user) {
        try {
        	
        	Client client = clientService.getClientByClientId(clientId, user);

            if (client == null) {
                return ResponseEntity.notFound().build();
            } else { 

            
            // Call the service method to find characteristics
            Map<String, Object> characteristics = numerologyService.getDCAffect(client.getDriver(), client.getConductor());
            	return ResponseEntity.ok(characteristics);
            }
        } catch (RuntimeException somethingRuntimeException) {
			// TODO: handle exception
        	return null;
		}
    }
    
    @GetMapping("/{clientId}/d-c-characteristics")
    public ResponseEntity<Map<String, String>> getDcCharacteristic(@PathVariable Long clientId,
                                                         @AuthenticationPrincipal CustomUserDetails user) {
        try {
        	
        	Client client = clientService.getClientByClientId(clientId, user);

            if (client == null) {
                return ResponseEntity.notFound().build();
            } else { 

            
            // Call the service method to find characteristics
            Map<String, String> characteristics = numerologyService.getDcCharacteristic(client.getDriver(), client.getConductor());
            	return ResponseEntity.ok(characteristics);
            }
        } catch (RuntimeException somethingRuntimeException) {
			// TODO: handle exception
        	return null;
		}
    }
    
    @GetMapping("/{clientId}/freq-effect")
    public ResponseEntity<List<Map<String, String>>> FreqEffect(@PathVariable Long clientId,
                                                         @AuthenticationPrincipal CustomUserDetails user) {
        try {
        	
        	Client client = clientService.getClientByClientId(clientId, user);

            if (client == null) {
                return ResponseEntity.notFound().build();
            } else { 

            
            // Call the service method to find characteristics
            	List<Map<String, String>> characteristics = numerologyService.FreqEffect(client.getDob(), client.getDriver(), client.getConductor(), client.getKua());
            	return ResponseEntity.ok(characteristics);
            }
        } catch (RuntimeException somethingRuntimeException) {
			// TODO: handle exception
        	return null;
		}
    }
    
    @GetMapping("/{clientId}/missing-number")
    public ResponseEntity<Map<String, Object>> missingNumber(@PathVariable Long clientId,
                                                         @AuthenticationPrincipal CustomUserDetails user) {
        try {
        	
        	Client client = clientService.getClientByClientId(clientId, user);

            if (client == null) {
                return ResponseEntity.notFound().build();
            } else { 

            
            // Call the service method to find characteristics
            	Map<String, Object> characteristics = numerologyService.missingNumber(client.getDob(), client.getDriver(), client.getConductor(), client.getKua());
            	return ResponseEntity.ok(characteristics);
            }
        } catch (RuntimeException somethingRuntimeException) {
			// TODO: handle exception
        	return null;
		}
    }
    
    @GetMapping("/{clientId}/get-directions")
    public ResponseEntity<Map<String, Object>> getDirections(@PathVariable Long clientId,
                                                         @AuthenticationPrincipal CustomUserDetails user) {
        try {
        	
        	Client client = clientService.getClientByClientId(clientId, user);

            if (client == null) {
                return ResponseEntity.notFound().build();
            } else { 

            
            // Call the service method to find characteristics
            	Map<String, Object> characteristics = numerologyService.getDirections(client.getKua());
            	return ResponseEntity.ok(characteristics);
            }
        } catch (RuntimeException somethingRuntimeException) {
			// TODO: handle exception
        	return null;
		}
    }
    
    @GetMapping("/{clientId}/personal-year-effect")
    public ResponseEntity<Map<String, Object>> personalYearEffect(@PathVariable Long clientId,
                                                         @AuthenticationPrincipal CustomUserDetails user) {
        try {
        	
        	Client client = clientService.getClientByClientId(clientId, user);

            if (client == null) {
                return ResponseEntity.notFound().build();
            } else { 

            
            // Call the service method to find characteristics
            	Map<String, Object> characteristics = numerologyService.personalYearEffect();
            	return ResponseEntity.ok(characteristics);
            }
        } catch (RuntimeException somethingRuntimeException) {
			// TODO: handle exception
        	return null;
		}
    }
    
    @PostMapping("/{clientId}/calculate-personal-year")
    public ResponseEntity<Map<String, Object>> calculatePersonalYear(@PathVariable Long clientId, @RequestBody Map<String, Integer> requestBody,
                                                         @AuthenticationPrincipal CustomUserDetails user) {
        try {
        	
        	Client client = clientService.getClientByClientId(clientId, user);

            if (client == null) {
                return ResponseEntity.notFound().build();
            } else { 

            
            // Call the service method to find characteristics
            	int year = requestBody.get("year");
            	Map<String, Object> characteristics = numerologyService.calculatePersonalYear(client.getDob(), year);
            	return ResponseEntity.ok(characteristics);
            }
        } catch (RuntimeException somethingRuntimeException) {
			// TODO: handle exception
        	return null;
		}
    }
    
    @PostMapping("/{clientId}/calculate-personal-month")
    public ResponseEntity<Map<String, Object>> calculatePersonalMonth(@PathVariable Long clientId, @RequestBody Map<String, Integer> requestBody,
                                                         @AuthenticationPrincipal CustomUserDetails user) {
        try {
        	
        	Client client = clientService.getClientByClientId(clientId, user);

            if (client == null) {
                return ResponseEntity.notFound().build();
            } else { 

            
            // Call the service method to find characteristics
            	Map<String, Object> characteristics = numerologyService.calculatePersonalMonth(client.getDob(), requestBody.get("year"), requestBody.get("month"));
            	return ResponseEntity.ok(characteristics);
            }
        } catch (RuntimeException somethingRuntimeException) {
			// TODO: handle exception
        	return null;
		}
    }
    
    @GetMapping("/{clientId}/get-profession")
    public ResponseEntity<Map<String, Object>> getProfession(@PathVariable Long clientId,
                                                         @AuthenticationPrincipal CustomUserDetails user) {
        try {
        	
        	Client client = clientService.getClientByClientId(clientId, user);

            if (client == null) {
                return ResponseEntity.notFound().build();
            } else { 

            
            // Call the service method to find characteristics
            	Map<String, Object> characteristics = numerologyService.getProfession(client.getDriver(), client.getConductor());
            	return ResponseEntity.ok(characteristics);
            }
        } catch (RuntimeException somethingRuntimeException) {
			// TODO: handle exception
        	return null;
		}
    }
    
    @PostMapping("/{clientId}/calculate-name-sum")
    public ResponseEntity<Map<String, Object>> calculateNameSum(@PathVariable Long clientId, @RequestBody Map<String, String> requestBody,
                                                         @AuthenticationPrincipal CustomUserDetails user) {
        try {
        	
        	Client client = clientService.getClientByClientId(clientId, user);

            if (client == null) {
                return ResponseEntity.notFound().build();
            } else { 

            
            // Call the service method to find characteristics
            	Map<String, Object> characteristics = numerologyService.calculateNameSum(requestBody.get("name"));
            	return ResponseEntity.ok(characteristics);
            }
        } catch (RuntimeException somethingRuntimeException) {
			// TODO: handle exception
        	return null;
		}
    }
    
    @GetMapping("/{clientId}/PY-table")
    public ResponseEntity<String[][]> createPYTable(@PathVariable Long clientId, @RequestBody Map<String, Integer> requestBody,
                                                         @AuthenticationPrincipal CustomUserDetails user) {
        try {
        	
        	Client client = clientService.getClientByClientId(clientId, user);

            if (client == null) {
                return ResponseEntity.notFound().build();
            } else { 

            
            // Call the service method to find characteristics
            	int year = requestBody.get("year");
            	String[][] characteristics = numerologyService.createPYTable(year, client.getDob(), client.getDriver(), client.getConductor(), client.getKua());
            	return ResponseEntity.ok(characteristics);
            }
        } catch (RuntimeException somethingRuntimeException) {
			// TODO: handle exception
        	return null;
		}
    }
    
    
    
    
}

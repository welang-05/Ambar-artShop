package com.challengerFinal.arte.controllers;

import com.challengerFinal.arte.dtos.ClientsDto;
import com.challengerFinal.arte.dtos.ClientRegisterDto;
import com.challengerFinal.arte.dtos.ToUpdateClientsDto;
import com.challengerFinal.arte.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api")
public class ClientController {
    @Autowired
    ClientService clientService;
    @GetMapping("/clients")
    public List<ClientsDto> getClientsAll() {
        return clientService.getUsers();
    }
    @GetMapping("/clients/{id}")
    public ClientsDto getClientId(@PathVariable Long id) {
        return clientService.getUserId(id);
    }
    @PostMapping("/clients")
    public ResponseEntity<Object> registerUser(@RequestBody ClientRegisterDto user){
        return clientService.registerUser(user);
    }
    @GetMapping("/clients/current")
    public ClientsDto getClient(Authentication authentication){
        return clientService.getClient(authentication);
    }
    @PatchMapping("/clients/updata/{id}")
    public ResponseEntity<Object> patchClient(@PathVariable Long id,
                                              @RequestBody ToUpdateClientsDto toUpdateClientsDto){
        return clientService.patchClient(id,toUpdateClientsDto);
    }
    @PatchMapping({"/clients/update/current"})
    public ResponseEntity<Object> patchClientCurrent(Authentication authentication, @RequestBody ToUpdateClientsDto  toUpdateClientsDto) {
        return this.clientService.patchClientCurrent(authentication, toUpdateClientsDto);
    }

    @DeleteMapping({"/clients/delete/{id}"})
    public ResponseEntity<Object> deleteClient(@PathVariable("id") Long id) {
        return this.clientService.deleteClient(id);
    }

    @DeleteMapping({"/clients/delete/current"})
    public ResponseEntity<Object> deleteClientCurrent(Authentication authentication) {
        return this.clientService.deleteClientCurrent(authentication);
    }

}

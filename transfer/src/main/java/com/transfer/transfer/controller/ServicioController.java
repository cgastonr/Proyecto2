package com.transfer.transfer.controller;



import com.transfer.transfer.dto.TransferDto;
import com.transfer.transfer.entity.Transfer;
import com.transfer.transfer.service.TransferService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/transferencia")
public class ServicioController {
	
    @Autowired
    private TransferService service;

 
    //transferencia

    @GetMapping("/listar")
    public Flux<TransferDto> getAllTransfer(){
        return service.getTransfer();
    }

    @GetMapping("/{id}")
    public Mono<TransferDto> getTransferPorId(@PathVariable String id){
        return service.getTransferPorId(id);
    }

    @PostMapping("/create")
    public Mono<TransferDto> saveTransfer(@RequestBody Mono<TransferDto> clienteDtoMono){ 
        return service.saveTransfer(clienteDtoMono);
    }

    @PutMapping("/update/{id}")
    public Mono<TransferDto> updateTransfer(@RequestBody Mono<TransferDto> clienteDtoMono,@PathVariable String id){
        return service.updateCTransfer(clienteDtoMono, id);
    }

    @DeleteMapping("/delete/{id}")
    public Mono<Void> deleteTransferDto(@PathVariable String id){
        return service.deleteTransfer(id);
    }


}

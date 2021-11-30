package com.transfer.transfer.service;



import com.transfer.transfer.dto.TransferDto;
import com.transfer.transfer.entity.Transfer;
import com.transfer.transfer.repository.TransferRepository;
import com.transfer.transfer.service.util.AppUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.web.reactive.function.client.WebClient;

@Service
public class TransferService {

  

    @Autowired
    private TransferRepository repository;

    //Creditos
    public Flux<TransferDto> getTransfer(){
        return repository.findAll().map(AppUtils::entityToDto);
    }

    public Mono<TransferDto> getTransferPorId(String id){
        return repository.findById(id).map(AppUtils::entityToDto);
    }

    public Mono<TransferDto> saveTransfer(Mono<TransferDto> transfDtoMono){
        System.out.println("service method called ...");
        return transfDtoMono.map(AppUtils::dtoToEntity)
                .flatMap(repository::insert)
                .map(AppUtils::entityToDto);
    }

    public Mono<TransferDto> updateCTransfer(Mono<TransferDto> clienteDtoMono,String id){
        System.out.println("service method called ...");

        return repository.findById(id)
                .flatMap(p->clienteDtoMono.map(AppUtils::dtoToEntity)
                        .doOnNext(e->e.setId(id)))
                .flatMap(repository::save)
                .map(AppUtils::entityToDto);

    }

    public Mono<Void> deleteTransfer(String id){
        return repository.deleteById(id);
    }


    
    
}

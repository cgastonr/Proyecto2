package com.report.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.report.dto.ReportDto;
import com.report.repository.ReportRepository;
import com.report.services.util.AppUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class reportServices {

	
	@Autowired
	ReportRepository repository;
	
    public Flux<ReportDto> getAllReport(){
        return repository.findAll().map(AppUtils::entityToDto);
    }

    public Mono<ReportDto> getReportId(String id){
        return repository.findById(id).map(AppUtils::entityToDto);
    }
	
    
    public Mono<ReportDto> saveReport(Mono<ReportDto> clienteDtoMono){
        System.out.println("service method called ...");
        return  clienteDtoMono.map(AppUtils::dtoToEntity)
                .flatMap(repository::insert)
                .map(AppUtils::entityToDto);
    }
    
    public Mono<ReportDto> updateReport(Mono<ReportDto> clienteDtoMono,String id){
        System.out.println("service method called ...");

        
        /* me retorna un ClienteDto y yo quiero un Cliente**/
        return repository.findById(id)
                .flatMap(p->clienteDtoMono.map(AppUtils::dtoToEntity)
                        .doOnNext(e->e.setId(id)))
                .flatMap(repository::save)
                .map(AppUtils::entityToDto);
         
    }

    public Mono<Void> deleteReport(String id){
        return repository.deleteById(id);
    }
    
    

	
}

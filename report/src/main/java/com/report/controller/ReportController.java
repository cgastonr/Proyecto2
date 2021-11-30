package com.report.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.report.dto.ReportDto;
import com.report.services.reportServices;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/report")
public class ReportController {

	@Autowired
	private reportServices service;
	
	    @GetMapping(value = "/listar",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	    public Flux<ReportDto> getAllReport(){
	        return service.getAllReport();
	    }
	    
	    @GetMapping("/{id}")
	    public Mono<ReportDto> getReportId(@PathVariable String id){
	        return service.getReportId(id);
	    }
	    
	    @PostMapping("/create")
	    public Mono<ReportDto> saveTarjetaCredito(@RequestBody Mono<ReportDto> clienteDtoMono){
	        System.out.println("controller method called ...");
	        return service.saveReport(clienteDtoMono);
	    }
	    
	    @PutMapping("/update/{id}")
	    public Mono<ReportDto> updateReport(@RequestBody Mono<ReportDto> clienteDtoMono,@PathVariable String id){
	        return service.updateReport(clienteDtoMono, id);
	    }
	    
	    @DeleteMapping("/delete/{id}")
	    public Mono<Void> deleteReport(@PathVariable String id){
	        return service.deleteReport(id);
	    }
}

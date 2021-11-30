package com.proyecto1.clientes.Controller;

import com.proyecto1.clientes.Dto.ClienteDto;
import com.proyecto1.clientes.Entidad.Cliente;
import com.proyecto1.clientes.Service.ClienteService;
import com.proyecto1.clientes.Utils.AppUtils;

import io.github.resilience4j.timelimiter.TimeLimiterConfig;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
     //private final Logger logger = LoggerFactory.getLogger(ItemController.class);
	
    //sirve pa la configuracion del  Resilience4J
	@Autowired
	private CircuitBreakerFactory cbFactory;
	
	
    @Autowired
    private ClienteService service;

    @GetMapping(value = "/listar",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ClienteDto> getTarjetaCreditos(){
        return service.getClientes();
    }

    @GetMapping("/{id}")
    public Mono<ClienteDto> getTarjetaCredito(@PathVariable String id) throws InterruptedException{
    	
    	if(id.equals(10L)) {
			throw new IllegalStateException("Producto no encontrado!");
		}
		
		if(id.equals(7L)) {
			TimeUnit.SECONDS.sleep(2L);
		}
       return service.getClienteId(id);
    	
 
    }

	@PostMapping("/create")
    public Mono<ClienteDto> saveTarjetaCredito(@RequestBody Mono<ClienteDto> clienteDtoMono){
        System.out.println("controller method called ...");
        return service.saveCliente(clienteDtoMono);
    }

    @PutMapping("/update/{id}")
    public Mono<ClienteDto> updateTarjetaCredito(@RequestBody Mono<ClienteDto> clienteDtoMono,@PathVariable String id){
        return service.updateCliente(clienteDtoMono,id);
    }

    @DeleteMapping("/delete/{id}")
    public Mono<Void> deleteTarjetaCredito(@PathVariable String id){
        return service.deleteCliente(id);
    }
    
    /*
    public Mono<Cliente> metodoAlternativo(String id, Throwable e) {
		//logger.info(e.getMessage());
    	 Cliente clie = new  Cliente();

		clie.setId(id);
		clie.setNroDoc("456449135");
		clie.setNroDoc("dni");
		clie.setNombres("roberto");
		clie.setApellidos("flores");
		clie.setCelular(987586914);
		clie.setRazonSocial("Tienda mi descuento");
		
		return Mono.just(clie);
	}
	
    */
  
}

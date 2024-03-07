package co.com.gml.alianza.controller;

import java.util.List;

import co.com.gml.alianza.service.IClientService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.gml.alianza.model.Client;

/**
 * Controlador de registros y consulta de clientes con los servicios rest.
 * 
 * @author CarlosCubillos
 *
 */

@AllArgsConstructor
@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class ClientController {

	private static final Logger logger = LoggerFactory.getLogger(ClientController.class);
	private final IClientService clientService;

	/**
	 * Consulta el cliente por el parametro sharedKey.
	 * 
	 * @param sharedKey
	 * @return ResponseEntity de cliente
	 */
	@GetMapping("/clients/{sharedKey}")
	public ResponseEntity<Client> getClientBySharedKey(@PathVariable("sharedKey") String sharedKey) {

		Client clientData = clientService.getClientBySharedKey(sharedKey);

		if (clientData != null) {
			logger.info("Consulta de Cliente con Shared Key: " + sharedKey);
			return new ResponseEntity<>(clientData, HttpStatus.OK);
		} else {
			logger.error("Se generó error al consultar al Cliente con Shared Key" + sharedKey);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Consulta el listado de clientes.
	 * 
	 * @return ResponseEntity de listado de cliente
	 */
	@GetMapping("/clients/")
	public ResponseEntity<List<Client>> getAllClients() {

		List<Client> clientData = clientService.getClients();

		if (clientData.size() > 0) {
			logger.info("Consulta del Listado de Clientes");
			return new ResponseEntity<>(clientData, HttpStatus.OK);
		} else {
			logger.error("No existen registros de Clientes");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Registra el cliente.
	 * 
	 * @param client
	 * @return ResponseEntity de cliente
	 */
	@PostMapping("/client")
	public ResponseEntity<Client> createClient(@RequestBody Client client) {
		try {
			Client _client = clientService.createClient(client);
			logger.info("Se ha registrado el cliente: " + client.getSharedKey());
			return new ResponseEntity<>(_client, HttpStatus.CREATED);
		} catch (Exception e) {
			logger.error("Se genero error al guardar al cliente: " + client.getSharedKey());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Actualiza el cliente
	 * 
	 * @param client
	 * @return ResponseEntity de cliente
	 */
	@PutMapping("/client")
	public ResponseEntity<Client> updateClient(@RequestBody Client client) {
		try {
			Client clientData = clientService.updateClient(client);
			if (clientData != null) {
				logger.info("Se ha actualizado el cliente: " + client.getSharedKey());
				return new ResponseEntity<>(clientData, HttpStatus.OK);
			} else {
				logger.error("Se genero error al actualizar al cliente: " + client.getSharedKey()
						+ ". No se genera actualización");
				return new ResponseEntity<>(null, HttpStatus.NOT_MODIFIED);
			}
		} catch (Exception e) {
			logger.error("Se genero error al actualizar al cliente: " + client.getSharedKey());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}

package co.com.gml.alianza.service;

import java.util.Date;
import java.util.List;

import co.com.gml.alianza.controller.ClientController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.gml.alianza.model.Client;
import co.com.gml.alianza.repository.ClientRepository;

/**
 * Clase servicio que implementa los metodos de la interface
 * 
 * @author CarlosCubillos
 *
 */
@Service
public class ClientService implements IClientService {
	private static final Logger logger = LoggerFactory.getLogger(ClientService.class);

	@Autowired
	ClientRepository clientRepository;

	/**
	 * Obtiene el cliente por el parametro sharedKey.
	 * 
	 * @param sharedKey del cliente
	 * @return Client retorna el cliente
	 */
	public Client getClientBySharedKey(String sharedKey) {
		if (sharedKey == null) {
			logger.error("Service: El shared key del cliente es nulo. No se realiza la consulta: " + sharedKey);
			return null;
		}
		logger.info("Service: Se ha consultado al cliente " + sharedKey);
		return clientRepository.findBySharedKey(sharedKey);
	}

	/**
	 * Registra el cliente.
	 * 
	 * @param client informacion del cliente
	 * @return Client retorna el cliente
	 */
	@Transactional
	public Client createClient(Client client) throws Exception {
		if (client == null || client.getBusinessId() == null || client.getEmail() == null || client.getPhone() == null
				|| client.getSharedKey() == null) {
			logger.error("Service: La informacion del cliente esta incompleta. No se registra ");
			throw new Exception("Se genero error. Los datos estan incompletos");
		}

		Client _clientExist = clientRepository.findBySharedKey(client.getSharedKey());

		if (_clientExist != null) {
			logger.info("Service: El cliente se encuentra registrado. No se registra. " + client.getSharedKey());
			return _clientExist;
		}
		logger.info("Service: Se registra al cliente " + client.getSharedKey());
		client.setDateAdded(new Date());
		return clientRepository.save(client);
	}

	/**
	 * Actualiza el cliente.
	 * 
	 * @param client informacion del cliente
	 * @return Client retorna el cliente
	 * @throws Exception genera excepcion si los datos estan incompletos o no esta
	 *                   registrado.
	 */
	@Transactional
	public Client updateClient(Client client) throws Exception {
		if (client == null || client.getBusinessId() == null || client.getEmail() == null || client.getPhone() == null
				|| client.getSharedKey() == null) {
			logger.error("Service: La informacion del cliente esta incompleta. No se actualiza ");
			throw new Exception("Se genero error. Los datos estan incompletos");
		}

		Client _clientExist = clientRepository.findById(client.getId())
				.orElseThrow(() -> new Exception("Se genero error. El cliente no existe"));

		logger.info("Service: Se registra al cliente " + client.getSharedKey());
		return clientRepository.save(client);
	}

	/**
	 * Obtiene el listado de clientes.
	 * 
	 * @return List retorna el listado de clientes.
	 */
	public List<Client> getClients() {
		logger.info("Service: Se consulta el listado de clientes");
		return clientRepository.findAll();
	}
}

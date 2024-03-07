package co.com.gml.alianza.service;

import java.util.List;

import co.com.gml.alianza.model.Client;

/**
 * Interface del ClientService
 * 
 * @author CarlosCubillos
 *
 */
public interface IClientService {

	/**
	 * Obtiene el cliente por el parametro sharedKey.
	 * 
	 * @param sharedKey sharedKey del cliente.
	 * @return Client retorna el cliente.
	 */
	public Client getClientBySharedKey(String sharedKey);

	/**
	 * Registra el cliente.
	 * 
	 * @param Client informacion del cliente
	 * @return Client retorna el cliente
	 * @throws Exception genera excepcion si los datos estan incompletos
	 */
	public Client createClient(Client client) throws Exception;

	/**
	 * Actualiza el cliente.
	 * 
	 * @param Client informacion del cliente
	 * @return Client retorna el cliente
	 * @throws Exception genera excepcion si los datos estan incompletos o no esta
	 *                   registrado.
	 */
	public Client updateClient(Client client) throws Exception;

	/**
	 * Obtiene el listado de clientes.
	 * 
	 * @return List retorna el listado de clientes.
	 */
	public List<Client> getClients();

}

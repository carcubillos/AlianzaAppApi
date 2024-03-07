package co.com.gml.alianza.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.gml.alianza.model.Client;

/**
 * Interface del Repositorio del Cliente.
 * 
 * @author CarlosCubillos
 *
 */
public interface ClientRepository extends JpaRepository<Client, Long> {

	/**
	 * Consulta el cliente segun el parametro sharedKey.
	 * 
	 * @param sharedKey del cliente
	 * @return Client
	 */
	Client findBySharedKey(String sharedKey);

	/**
	 * Consulta el listado de cliente.
	 */
	List<Client> findAll();

}

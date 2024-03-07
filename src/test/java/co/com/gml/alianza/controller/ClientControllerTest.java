package co.com.gml.alianza.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import co.com.gml.alianza.service.IClientService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.Assert;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.com.gml.alianza.model.Client;

/**
 * Clase test para validar el controlador de Cliente.
 * 
 * @author CarlosCubillos
 *
 */
@SpringBootTest
public class ClientControllerTest {

	@Mock
	private IClientService clientService;

	@InjectMocks
	ClientController clientController;


	@Test
	void getClientBySharedKeyTest(){
		Client client1 = new Client(1L, "ccubillos", "Carlos Cubillos", "carlos.cubillos@correo", 13123L, new Date());

		Mockito.when(clientService.getClientBySharedKey("ccubillos")).thenReturn(client1);

		ResponseEntity<Client> responseEntity =  clientController.getClientBySharedKey("ccubillos");

		Assertions.assertNotNull(responseEntity.getBody());
		Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		Mockito.verify(clientService, Mockito.times(1)).getClientBySharedKey("ccubillos");
	}

	@Test
	void getAllClientsTest(){
		Client client1 = new Client(1L, "ccubillos", "Carlos Cubillos", "carlos.cubillos@correo", 13123L, new Date());
		Client client2 = new Client(2L, "ccubillos2", "Carlos Cubillos2", "carlos.cubillos3@correo", 134334L, new Date());
		Client client3 = new Client(3L, "ccubillos3", "Carlos Cubillos3", "carlos.cubillos3@correo", 135643L, new Date());
		List<Client> clientsList = Arrays.asList(client1, client2, client3);

		Mockito.when(clientService.getClients()).thenReturn(clientsList);

		ResponseEntity<List<Client>> responseEntity =  clientController.getAllClients();

		Assertions.assertEquals(3, responseEntity.getBody().size());
		Assertions.assertNotNull(responseEntity.getBody());
		Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		Mockito.verify(clientService, Mockito.times(1)).getClients();
	}

	@Test
	void createClientTest() throws Exception {
		Client client1 = new Client(1L, "ccubillos", "Carlos Cubillos", "carlos.cubillos@correo", 13123L, new Date());
		Mockito.when(clientService.createClient(client1)).thenReturn(client1);

		ResponseEntity<Client> responseEntity =  clientController.createClient(client1);

		Assertions.assertNotNull(responseEntity.getBody());
		Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
		Mockito.verify(clientService, Mockito.times(1)).createClient(client1);

	}

	@Test
	void updateClientTest() throws Exception {
		Client client1 = new Client(1L, "ccubillos", "Carlos Cubillos", "carlos.cubillos@correo", 13123L, new Date());

		Mockito.when(clientService.updateClient(client1)).thenReturn(client1);

		ResponseEntity<Client> responseEntity =  clientController.updateClient(client1);

		Assertions.assertNotNull(responseEntity.getBody());
		Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		Mockito.verify(clientService, Mockito.times(1)).updateClient(client1);
	}



}

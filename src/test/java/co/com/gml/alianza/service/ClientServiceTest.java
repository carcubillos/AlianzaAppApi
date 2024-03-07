package co.com.gml.alianza.service;

import co.com.gml.alianza.model.Client;
import co.com.gml.alianza.repository.ClientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Clase test para validar la clase de servicio de Cliente.
 * 
 * @author CarlosCubillos
 *
 */
@SpringBootTest
public class ClientServiceTest {

    @Mock
    ClientRepository clientRepository;

    @InjectMocks
    ClientService clientService;

    Client client = new Client();

    @Test
    void getClientBySharedKeyTest(){
        Client client = new Client(1L, "ccubillos", "Carlos Cubillos", "carlos.cubillos@correo", 13123L, new Date());
        Mockito.when(clientRepository.findBySharedKey("ccubillos")).thenReturn(client);

        Client clientSearch = clientService.getClientBySharedKey("ccubillos");

        Assertions.assertNotNull( clientSearch);
        Mockito.verify(clientRepository, Mockito.times(1)).findBySharedKey("ccubillos");
    }

    @Test
    void getClientBySharedKeyFailTest(){
        Client client = new Client(1L, "ccubillos", "Carlos Cubillos", "carlos.cubillos@correo", 13123L, new Date());
        Mockito.when(clientRepository.findBySharedKey("ccubillos")).thenReturn(client);

        Client clientSearch = clientService.getClientBySharedKey("ccubilloss");

        Assertions.assertNull(clientSearch);
        Mockito.verify(clientRepository, Mockito.times(1)).findBySharedKey("ccubilloss");
    }

    @Test
    void getClientsTest(){
        Client client1 = new Client(1L, "ccubillos", "Carlos Cubillos", "carlos.cubillos@correo", 13123L, new Date());
        Client client2 = new Client(2L, "ccubillos2", "Carlos Cubillos2", "carlos.cubillos3@correo", 134334L, new Date());
        Client client3 = new Client(3L, "ccubillos3", "Carlos Cubillos3", "carlos.cubillos3@correo", 135643L, new Date());
        List<Client> clientsList = Arrays.asList(client1, client2, client3);

        Mockito.when(clientRepository.findAll()).thenReturn(clientsList);

        List<Client> clientSearch = clientService.getClients();

        Assertions.assertEquals(3, clientSearch.size());
        Mockito.verify(clientRepository, Mockito.times(1)).findAll();
    }



    @Test
    void createClientTest() throws Exception {
        Client client = new Client(2L, "ccubillos", "Carlos Cubillos", "carlos.cubillos@correo", 13123L, new Date());
        Mockito.when(clientRepository.findBySharedKey("ccubillos")).thenReturn(null);
        Mockito.when(clientRepository.save(client)).thenReturn(client);

        Client clientCreated = clientService.createClient(client);

        Assertions.assertNotNull(clientCreated);
        Mockito.verify(clientRepository, Mockito.times(1)).findBySharedKey("ccubillos");
        Mockito.verify(clientRepository, Mockito.times(1)).save(client);
    }

    @Test
    void updateClientTest() throws Exception {
        Client client = new Client(1L, "ccubillos", "Carlos Cubillos", "carlos.cubillos@correo", 13123L, new Date());
        Mockito.when(clientRepository.findById(client.getId())).thenReturn(Optional.of(client));

        Client clientUpdate = new Client();
        clientUpdate.setId(client.getId());
        clientUpdate.setEmail("correonuevo@correo");
        clientUpdate.setPhone(342526L);
        clientUpdate.setBusinessId(client.getBusinessId());
        clientUpdate.setSharedKey(client.getSharedKey());
        clientUpdate.setDateAdded(client.getDateAdded());
        Mockito.when(clientRepository.save(clientUpdate)).thenReturn(clientUpdate);

        Client clientResult = clientService.updateClient(clientUpdate);

        Assertions.assertNotEquals(client.getEmail(), clientResult.getEmail());
        Assertions.assertNotEquals(client.getPhone(), clientResult.getPhone());
        Mockito.verify(clientRepository, Mockito.times(1)).findById(client.getId());
        Mockito.verify(clientRepository, Mockito.times(1)).save(clientUpdate);
    }
}
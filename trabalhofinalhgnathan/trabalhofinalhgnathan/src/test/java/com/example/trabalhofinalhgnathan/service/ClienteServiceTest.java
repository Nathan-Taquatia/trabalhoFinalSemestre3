package com.example.trabalhofinalhgnathan.service;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.trabalhofinalhgnathan.model.Cliente;
import com.example.trabalhofinalhgnathan.repository.ClienteRepository;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {

	@Mock
	private ClienteRepository clienteRepository;

	@InjectMocks
	private ClienteService clienteService;

	private Cliente clienteTeste;

	@BeforeEach
	void setUp() {
		clienteTeste = new Cliente("João Silva", "12345678901");
		clienteTeste.setId(1);
	}

	@Test
	void testFindById() {
		when(clienteRepository.findById(1)).thenReturn(Optional.of(clienteTeste));

		Optional<Cliente> resultado = clienteService.findById(1);

		assertTrue(resultado.isPresent());
		assertEquals("João Silva", resultado.get().getNome());
		assertEquals("12345678901", resultado.get().getCpf());
	}

	@Test
	void testFindByIdNotFound() {
		when(clienteRepository.findById(999)).thenReturn(Optional.empty());

		Optional<Cliente> resultado = clienteService.findById(999);

		assertFalse(resultado.isPresent());
	}

	@Test
	void testFindByCpf() {
		when(clienteRepository.findByCpf("12345678901")).thenReturn(Optional.of(clienteTeste));

		Optional<Cliente> resultado = clienteService.findByCpf("12345678901");

		assertTrue(resultado.isPresent());
		assertEquals("João Silva", resultado.get().getNome());
	}

	@Test
	void testFindByCpfNotFound() {
		when(clienteRepository.findByCpf("99999999999")).thenReturn(Optional.empty());

		Optional<Cliente> resultado = clienteService.findByCpf("99999999999");

		assertFalse(resultado.isPresent());
	}

	@Test
	void testCadastrar() {
		when(clienteRepository.findByCpf("12345678901")).thenReturn(Optional.empty());
		when(clienteRepository.save(clienteTeste)).thenReturn(clienteTeste);

		Cliente resultado = clienteService.cadastrar(clienteTeste);

		assertNotNull(resultado);
		assertEquals("João Silva", resultado.getNome());
		verify(clienteRepository, times(1)).save(clienteTeste);
	}

	@Test
	void testCadastrarCpfDuplicado() {
		Cliente clienteExistente = new Cliente("João Silva", "12345678901");
		clienteExistente.setId(1);
		
		when(clienteRepository.findByCpf("12345678901")).thenReturn(Optional.of(clienteExistente));

		Cliente clienteNovo = new Cliente("João Silva", "12345678901");
		
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			clienteService.cadastrar(clienteNovo);
		});
		assertNotNull(exception);
	}

	@Test
	void testCadastrarNomeVazio() {
		Cliente clienteInvalido = new Cliente("", "12345678901");
		
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			clienteService.cadastrar(clienteInvalido);
		});
		assertNotNull(exception);
	}

	@Test
	void testCadastrarCpfVazio() {
		Cliente clienteInvalido = new Cliente("João Silva", "");
		
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			clienteService.cadastrar(clienteInvalido);
		});
		assertNotNull(exception);
	}

	@Test
	void testAtualizar() {
		Cliente clienteAtualizado = new Cliente("João Pedro", "12345678901");
		when(clienteRepository.findById(1)).thenReturn(Optional.of(clienteTeste));
		when(clienteRepository.save(clienteTeste)).thenReturn(clienteTeste);

		Cliente resultado = clienteService.atualizar(1, clienteAtualizado);

		assertNotNull(resultado);
		verify(clienteRepository, times(1)).save(clienteTeste);
	}

	@Test
	void testAtualizarNaoEncontrado() {
		Cliente clienteAtualizado = new Cliente("João Pedro", "12345678901");
		when(clienteRepository.findById(999)).thenReturn(Optional.empty());

		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			clienteService.atualizar(999, clienteAtualizado);
		});
		assertNotNull(exception);
	}

	@Test
	void testDeleteById() {
		clienteService.deleteById(1);

		verify(clienteRepository, times(1)).deleteById(1);
	}

}

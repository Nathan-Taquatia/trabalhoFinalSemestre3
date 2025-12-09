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

import com.example.trabalhofinalhgnathan.model.Banco;
import com.example.trabalhofinalhgnathan.repository.BancoRepository;

@ExtendWith(MockitoExtension.class)
public class BancoServiceTest {

	@Mock
	private BancoRepository bancoRepository;

	@InjectMocks
	private BancoService bancoService;

	private Banco bancoTeste;

	@BeforeEach
	void setUp() {
		bancoTeste = new Banco("Banco Brasil", 1, "12345678901234");
		bancoTeste.setId(1);
	}

	@Test
	void testFindById() {
		when(bancoRepository.findById(1)).thenReturn(Optional.of(bancoTeste));

		Optional<Banco> resultado = bancoService.findById(1);

		assertTrue(resultado.isPresent());
		assertEquals("Banco Brasil", resultado.get().getNome());
		assertEquals(1, resultado.get().getCodigo());
	}

	@Test
	void testFindByIdNotFound() {
		when(bancoRepository.findById(999)).thenReturn(Optional.empty());

		Optional<Banco> resultado = bancoService.findById(999);

		assertFalse(resultado.isPresent());
	}

	@Test
	void testFindByCodigo() {
		when(bancoRepository.findByCodigo(1)).thenReturn(Optional.of(bancoTeste));

		Optional<Banco> resultado = bancoService.findByCodigo(1);

		assertTrue(resultado.isPresent());
		assertEquals("Banco Brasil", resultado.get().getNome());
	}

	@Test
	void testFindByCodigoNotFound() {
		when(bancoRepository.findByCodigo(999)).thenReturn(Optional.empty());

		Optional<Banco> resultado = bancoService.findByCodigo(999);

		assertFalse(resultado.isPresent());
	}

	@Test
	void testFindByCnpj() {
		when(bancoRepository.findByCnpj("12345678901234")).thenReturn(Optional.of(bancoTeste));

		Optional<Banco> resultado = bancoService.findByCnpj("12345678901234");

		assertTrue(resultado.isPresent());
		assertEquals("Banco Brasil", resultado.get().getNome());
	}

	@Test
	void testFindByCnpjNotFound() {
		when(bancoRepository.findByCnpj("99999999999999")).thenReturn(Optional.empty());

		Optional<Banco> resultado = bancoService.findByCnpj("99999999999999");

		assertFalse(resultado.isPresent());
	}

	@Test
	void testCadastrar() {
		when(bancoRepository.findByCnpj("12345678901234")).thenReturn(Optional.empty());
		when(bancoRepository.save(bancoTeste)).thenReturn(bancoTeste);

		Banco resultado = bancoService.cadastrar(bancoTeste);

		assertNotNull(resultado);
		assertEquals("Banco Brasil", resultado.getNome());
		verify(bancoRepository, times(1)).save(bancoTeste);
	}

	@Test
	void testCadastrarNomeVazio() {
		Banco bancoInvalido = new Banco("", 1, "12345678901234");

		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			bancoService.cadastrar(bancoInvalido);
		});
		assertNotNull(exception);
	}

	@Test
	void testCadastrarCodigoZero() {
		Banco bancoInvalido = new Banco("Banco Teste", 0, "12345678901234");

		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			bancoService.cadastrar(bancoInvalido);
		});
		assertNotNull(exception);
	}

	@Test
	void testCadastrarCnpjVazio() {
		Banco bancoInvalido = new Banco("Banco Teste", 1, "");

		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			bancoService.cadastrar(bancoInvalido);
		});
		assertNotNull(exception);
	}

	@Test
	void testCadastrarCnpjDuplicado() {
		Banco bancoExistente = new Banco("Banco Brasil", 1, "12345678901234");
		bancoExistente.setId(1);
		
		when(bancoRepository.findByCnpj("12345678901234")).thenReturn(Optional.of(bancoExistente));

		Banco bancoNovo = new Banco("Outro Banco", 2, "12345678901234");
		bancoNovo.setId(0); // Novo banco tem id=0
		
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			bancoService.cadastrar(bancoNovo);
		});
		assertNotNull(exception);
		assertTrue(exception.getMessage().contains("CNPJ já cadastrado"));
	}

	@Test
	void testAtualizar() {
		Banco bancoAtualizado = new Banco("Banco Central", 2, "99999999999999");
		when(bancoRepository.findById(1)).thenReturn(Optional.of(bancoTeste));
		when(bancoRepository.save(bancoTeste)).thenReturn(bancoTeste);

		Banco resultado = bancoService.atualizar(1, bancoAtualizado);

		assertNotNull(resultado);
		verify(bancoRepository, times(1)).save(bancoTeste);
	}

	@Test
	void testAtualizarNaoEncontrado() {
		Banco bancoAtualizado = new Banco("Banco Central", 2, "99999999999999");
		when(bancoRepository.findById(999)).thenReturn(Optional.empty());

		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			bancoService.atualizar(999, bancoAtualizado);
		});
		assertNotNull(exception);
	}

	@Test
	void testDeleteById() {
		bancoService.deleteById(1);

		verify(bancoRepository, times(1)).deleteById(1);
	}

}

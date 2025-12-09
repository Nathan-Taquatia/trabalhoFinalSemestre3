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

import com.example.trabalhofinalhgnathan.model.Agencia;
import com.example.trabalhofinalhgnathan.model.Banco;
import com.example.trabalhofinalhgnathan.repository.AgenciaRepository;

@ExtendWith(MockitoExtension.class)
public class AgenciaServiceTest {

	@Mock
	private AgenciaRepository agenciaRepository;

	@InjectMocks
	private AgenciaService agenciaService;

	private Agencia agenciaTeste;
	private Banco bancoTeste;

	@BeforeEach
	void setUp() {
		bancoTeste = new Banco("Banco Brasil", 1, "12345678901234");
		bancoTeste.setId(1);

		agenciaTeste = new Agencia(1001, "Agencia Centro", bancoTeste);
		agenciaTeste.setId(1);
	}

	@Test
	void testFindById() {
		when(agenciaRepository.findById(1)).thenReturn(Optional.of(agenciaTeste));

		Optional<Agencia> resultado = agenciaService.findById(1);

		assertTrue(resultado.isPresent());
		assertEquals(1001, resultado.get().getNumero());
		assertEquals("Agencia Centro", resultado.get().getNome());
	}

	@Test
	void testFindByIdNotFound() {
		when(agenciaRepository.findById(999)).thenReturn(Optional.empty());

		Optional<Agencia> resultado = agenciaService.findById(999);

		assertFalse(resultado.isPresent());
	}

	@Test
	void testFindByNumero() {
		when(agenciaRepository.findByNumero(1001)).thenReturn(Optional.of(agenciaTeste));

		Optional<Agencia> resultado = agenciaService.findByNumero(1001);

		assertTrue(resultado.isPresent());
		assertEquals("Agencia Centro", resultado.get().getNome());
	}

	@Test
	void testFindByNumeroNotFound() {
		when(agenciaRepository.findByNumero(9999)).thenReturn(Optional.empty());

		Optional<Agencia> resultado = agenciaService.findByNumero(9999);

		assertFalse(resultado.isPresent());
	}

	@Test
	void testCadastrar() {
		when(agenciaRepository.save(agenciaTeste)).thenReturn(agenciaTeste);

		Agencia resultado = agenciaService.cadastrar(agenciaTeste);

		assertNotNull(resultado);
		assertEquals(1001, resultado.getNumero());
		verify(agenciaRepository, times(1)).save(agenciaTeste);
	}

	@Test
	void testCadastrarNumeroZero() {
		Agencia agenciaInvalida = new Agencia(0, "Agencia Teste", bancoTeste);

		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			agenciaService.cadastrar(agenciaInvalida);
		});
		assertNotNull(exception);
	}

	@Test
	void testCadastrarNomeVazio() {
		Agencia agenciaInvalida = new Agencia(1001, "", bancoTeste);

		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			agenciaService.cadastrar(agenciaInvalida);
		});
		assertNotNull(exception);
	}

	@Test
	void testCadastrarBancoNulo() {
		Agencia agenciaInvalida = new Agencia(1001, "Agencia Teste", null);

		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			agenciaService.cadastrar(agenciaInvalida);
		});
		assertNotNull(exception);
	}

	@Test
	void testAtualizar() {
		Agencia agenciaAtualizada = new Agencia(1002, "Agencia Praia", bancoTeste);
		when(agenciaRepository.findById(1)).thenReturn(Optional.of(agenciaTeste));
		when(agenciaRepository.save(agenciaTeste)).thenReturn(agenciaTeste);

		Agencia resultado = agenciaService.atualizar(1, agenciaAtualizada);

		assertNotNull(resultado);
		verify(agenciaRepository, times(1)).save(agenciaTeste);
	}

	@Test
	void testAtualizarNaoEncontrada() {
		Agencia agenciaAtualizada = new Agencia(1002, "Agencia Praia", bancoTeste);
		when(agenciaRepository.findById(999)).thenReturn(Optional.empty());

		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			agenciaService.atualizar(999, agenciaAtualizada);
		});
		assertNotNull(exception);
	}

	@Test
	void testDeleteById() {
		agenciaService.deleteById(1);

		verify(agenciaRepository, times(1)).deleteById(1);
	}

}

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
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.trabalhofinalhgnathan.model.Agencia;
import com.example.trabalhofinalhgnathan.model.Cliente;
import com.example.trabalhofinalhgnathan.model.Conta;
import com.example.trabalhofinalhgnathan.repository.ContaRepository;

@ExtendWith(MockitoExtension.class)
public class ContaServiceTest {

    @Mock
    private ContaRepository contaRepository;

    @InjectMocks
    private ContaService contaService;

    private Conta contaTeste;
    private Cliente clienteTeste;
    private Agencia agenciaTeste;

    @BeforeEach
    void setUp() {
        clienteTeste = new Cliente();
        clienteTeste.setId(1);
        clienteTeste.setNome("João Silva");

        agenciaTeste = new Agencia();
        agenciaTeste.setId(1);
        agenciaTeste.setNumero(1001);

        contaTeste = new Conta(12345, 1000.00, clienteTeste, agenciaTeste);
        contaTeste.setId(1);
    }

    @Test
    void testFindById() {
        // Arrange
        when(contaRepository.findById(1)).thenReturn(Optional.of(contaTeste));

        // Act
        Optional<Conta> resultado = contaService.findById(1);

        // Assert
        assertTrue(resultado.isPresent());
        assertEquals(12345, resultado.get().getNumero());
        assertEquals(1000.00, resultado.get().getSaldo());
    }

    @Test
    void testFindByIdNotFound() {
        // Arrange
        when(contaRepository.findById(999)).thenReturn(Optional.empty());

        // Act
        Optional<Conta> resultado = contaService.findById(999);

        // Assert
        assertFalse(resultado.isPresent());
    }

    @Test
    void testDepositar() {
        // Arrange
        when(contaRepository.findById(1)).thenReturn(Optional.of(contaTeste));
        when(contaRepository.save(contaTeste)).thenReturn(contaTeste);

        // Act
        Conta resultado = contaService.depositar(1, 500.00);

        // Assert
        assertEquals(1500.00, resultado.getSaldo());
        verify(contaRepository, times(1)).save(contaTeste);
    }

    @Test
    void testDepositarValorInvalido() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            contaService.depositar(1, -100.00);
        });
    }

    @Test
    void testSacar() {
        // Arrange
        when(contaRepository.findById(1)).thenReturn(Optional.of(contaTeste));
        when(contaRepository.save(contaTeste)).thenReturn(contaTeste);

        // Act
        Conta resultado = contaService.sacar(1, 300.00);

        // Assert
        assertEquals(700.00, resultado.getSaldo());
        verify(contaRepository, times(1)).save(contaTeste);
    }

    @Test
    void testSacarSaldoInsuficiente() {
        // Arrange
        when(contaRepository.findById(1)).thenReturn(Optional.of(contaTeste));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            contaService.sacar(1, 1500.00); // Saldo insuficiente (conta tem 1000)
        });
    }

    @Test
    void testCadastrar() {
        // Arrange
        when(contaRepository.save(contaTeste)).thenReturn(contaTeste);

        // Act
        Conta resultado = contaService.cadastrar(contaTeste);

        // Assert
        assertNotNull(resultado);
        assertEquals(12345, resultado.getNumero());
        verify(contaRepository, times(1)).save(contaTeste);
    }

    @Test
    void testDeleteById() {
        // Arrange
        when(contaRepository.findById(1)).thenReturn(Optional.of(contaTeste));

        // Act
        boolean resultado = contaService.deleteById(1);

        // Assert
        assertTrue(resultado);
        verify(contaRepository, times(1)).deleteById(1);
    }

    @Test
    void testDeleteByIdNotFound() {
        // Arrange
        when(contaRepository.findById(999)).thenReturn(Optional.empty());

        // Act
        boolean resultado = contaService.deleteById(999);

        // Assert
        assertFalse(resultado);
        verify(contaRepository, never()).deleteById(999);
    }

}

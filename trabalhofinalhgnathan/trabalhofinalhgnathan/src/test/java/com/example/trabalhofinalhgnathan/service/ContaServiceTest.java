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
        
        when(contaRepository.findById(1)).thenReturn(Optional.of(contaTeste));

        Optional<Conta> resultado = contaService.findById(1);

        assertTrue(resultado.isPresent());
        assertEquals(12345, resultado.get().getNumero());
        assertEquals(1000.00, resultado.get().getSaldo());
    }

    @Test
    void testFindByIdNotFound() {
        
        when(contaRepository.findById(999)).thenReturn(Optional.empty());

        Optional<Conta> resultado = contaService.findById(999);

        assertFalse(resultado.isPresent());
    }

    @Test
    void testDepositar() {
        
        when(contaRepository.findById(1)).thenReturn(Optional.of(contaTeste));
        when(contaRepository.save(contaTeste)).thenReturn(contaTeste);

        Conta resultado = contaService.depositar(1, 500.00);

        assertEquals(1500.00, resultado.getSaldo());
        verify(contaRepository, times(1)).save(contaTeste);
    }

    @Test
    void testDepositarValorInvalido() {
        
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            contaService.depositar(1, -100.00);
        });
        assertNotNull(exception);
    }

    @Test
    void testSacar() {
       
        when(contaRepository.findById(1)).thenReturn(Optional.of(contaTeste));
        when(contaRepository.save(contaTeste)).thenReturn(contaTeste);

        Conta resultado = contaService.sacar(1, 300.00);

        assertEquals(700.00, resultado.getSaldo());
        verify(contaRepository, times(1)).save(contaTeste);
    }

    @Test
    void testSacarSaldoInsuficiente() {
        
        when(contaRepository.findById(1)).thenReturn(Optional.of(contaTeste));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            contaService.sacar(1, 1500.00);
        });
        assertNotNull(exception);
    }

    @Test
    void testCadastrar() {
        
        when(contaRepository.save(contaTeste)).thenReturn(contaTeste);

        Conta resultado = contaService.cadastrar(contaTeste);

        assertNotNull(resultado);
        assertEquals(12345, resultado.getNumero());
        verify(contaRepository, times(1)).save(contaTeste);
    }

    @Test
    void testDeleteById() {
       
        when(contaRepository.findById(1)).thenReturn(Optional.of(contaTeste));

        boolean resultado = contaService.deleteById(1);

        assertTrue(resultado);
        verify(contaRepository, times(1)).deleteById(1);
    }

    @Test
    void testDeleteByIdNotFound() {
        
        when(contaRepository.findById(999)).thenReturn(Optional.empty());

        boolean resultado = contaService.deleteById(999);

        assertFalse(resultado);
        verify(contaRepository, never()).deleteById(999);
    }

}

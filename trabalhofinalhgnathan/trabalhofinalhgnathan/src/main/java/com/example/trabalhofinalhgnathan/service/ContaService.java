package com.example.trabalhofinalhgnathan.service;
/* 
import org.springframework.transaction.annotation.Transactional;

import com.example.trabalhofinalhgnathan.model.Conta;
*/
public class ContaService {
/* 
    private final ContaRepository repository;

    public ContaService(ContaRepository repository) {
        this.repository = repository;
    }

@Transactional
public Conta depositar(Integer contaId, double valor) {
    if (valor <= 0) throw new IllegalArgumentException("Valor deve ser positivo");
    
    Conta conta = repository.findById(contaId)
        .orElseThrow(() -> new IllegalArgumentException("Conta não encontrada"));
    
    conta.setSaldo(conta.getSaldo() + valor);
    return repository.save(conta);
}

@Transactional
public Conta sacar(Integer contaId, double valor) {
    if (valor <= 0) throw new IllegalArgumentException("Valor deve ser positivo");
    
    Conta conta = repository.findById(contaId)
        .orElseThrow(() -> new IllegalArgumentException("Conta não encontrada"));
    
    if (conta.getSaldo() < valor) {
        throw new IllegalArgumentException("Saldo insuficiente");
    }
    
    conta.setSaldo(conta.getSaldo() - valor);
    return repository.save(conta);
}
*/

}

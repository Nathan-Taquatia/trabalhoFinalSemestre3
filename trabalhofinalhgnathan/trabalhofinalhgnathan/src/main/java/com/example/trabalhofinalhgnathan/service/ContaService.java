package com.example.trabalhofinalhgnathan.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.trabalhofinalhgnathan.model.Conta;
import com.example.trabalhofinalhgnathan.repository.ContaRepository;

@Service
public class ContaService {

    private final ContaRepository repository;

    public ContaService(ContaRepository repository) {
        this.repository = repository;
    }

    public Optional<Conta> findById(Integer id) {
        return repository.findById(id);
    }

    public List<Conta> findAll() {
        return repository.findAll();
    }

    public Conta cadastrar(Conta conta) {
        // Validações podem ser adicionadas aqui
        return repository.save(conta);
    }

    public Conta save(Conta conta) {
        return repository.save(conta);
    }

    public boolean deleteById(Integer id) {
        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<Conta> findByNumero(Integer numero) {
        return repository.findByNumero(numero);
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

}

package com.example.trabalhofinalhgnathan.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.trabalhofinalhgnathan.model.Conta;
import com.example.trabalhofinalhgnathan.repository.ContaRepository;

/**
 * @code ContaService é uma classe de serviço que lida com a lógica de negócio
 * para a entidade @link Conta.
 * Ela fornece métodos para operações CRUD (Create, Read, Update, Delete)
 * e operações específicas como depósito e saque.
 * @author Henrique e Nathan
 * @version 1.0
 * @since 2025-01-01
 */
@Service
public class ContaService {

    private final ContaRepository repository;

    /**
     * Construtor para injeção de dependência do repositório @code ContaRepository.
     * @param repository O repositório responsável pelo acesso aos dados das contas.
     */
    public ContaService(ContaRepository repository) {
        this.repository = repository;
    }

    /**
     * Encontra uma conta pelo seu ID.
     * @param id O ID da conta a ser recuperada.
     * @return Um objeto @link Optional contendo a conta, se encontrada.
     */
    public Optional<Conta> findById(Integer id) {
        return repository.findById(id);
    }

    /**
     * Recupera todas as contas cadastradas.
     * @return Uma lista de objetos @link Conta.
     */
    public List<Conta> findAll() {
        return repository.findAll();
    }

    /**
     * Cadastra uma nova conta.
     * @param conta O objeto @link Conta a ser cadastrado.
     * @return O objeto @link Conta cadastrado.
     */
    public Conta cadastrar(Conta conta) {
        return repository.save(conta);
    }

    /**
     * Salva uma conta no repositório.
     * @param conta O objeto @link Conta a ser salvo.
     * @return O objeto @link Conta salvo.
     */
    public Conta save(Conta conta) {
        return repository.save(conta);
    }

    /**
     * Deleta uma conta pelo seu ID.
     * @param id O ID da conta a ser deletada.
     * @return true se a conta foi deletada, false se não foi encontrada.
     */
    public boolean deleteById(Integer id) {
        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    /**
     * Encontra uma conta pelo seu número (único).
     * @param numero O número da conta a ser recuperada.
     * @return Um objeto @link Optional contendo a conta, se encontrada.
     */
    public Optional<Conta> findByNumero(Integer numero) {
        return repository.findByNumero(numero);
    }

    /**
     * Realiza um depósito em uma conta.
     * @param contaId O ID da conta onde o depósito será realizado.
     * @param valor O valor a ser depositado.
     * @return O objeto @link Conta atualizado após o depósito.
     * @throws IllegalArgumentException se o valor for negativo ou se a conta não for encontrada.
     */
    @Transactional
    public Conta depositar(Integer contaId, double valor) {
        if (valor <= 0) throw new IllegalArgumentException("Valor deve ser positivo");

        Conta conta = repository.findById(contaId)
            .orElseThrow(() -> new IllegalArgumentException("Conta não encontrada"));

        conta.setSaldo(conta.getSaldo() + valor);
        return repository.save(conta);
    }

    /**
     * Realiza um saque em uma conta.
     * @param contaId O ID da conta onde o saque será realizado.
     * @param valor O valor a ser sacado.
     * @return O objeto @link Conta atualizado após o saque.
     * @throws IllegalArgumentException se o valor for negativo, se a conta não for encontrada,
     * ou se o saldo for insuficiente.
     */
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

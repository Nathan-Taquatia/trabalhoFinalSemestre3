package com.example.trabalhofinalhgnathan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.trabalhofinalhgnathan.dto.TransacaoDTO;
import com.example.trabalhofinalhgnathan.model.Conta;
import com.example.trabalhofinalhgnathan.service.ContaService;

import jakarta.validation.Valid;

/**
 * @code ContaController é um controlador REST que lida com as operações CRUD (Create, Read, Update, Delete)
 * para a entidade @link Conta.
 * O controlador mapeia as requisições HTTP para métodos de serviço correspondentes.
 * @author Henrique e Nathan
 * @version 1.0 (ou sei lá, fizemos muitas modificações não lembro mais qual versão é essa kk)
 * @since 2025-01-01
 */
@RestController
@RequestMapping("/contas")
public class ContaController {

    private final ContaService contaService;

    /**
	 * Construtor para injeção de dependência do serviço @code ContaService.
	 * @param contateService O serviço responsável pela lógica de negócio dos contas.
	 */
	@Autowired
    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }

    /**
     * Recupera uma lista de todas as contas cadastradas.
     * Mapeado para a requisição GET em /contas.
     * @return @code ResponseEntity contendo uma lista de objetos @link Conta e status HTTP 200 (OK).
     */
    @GetMapping
    public ResponseEntity<List<Conta>> getAll() {
        return ResponseEntity.ok(contaService.findAll());
    }

    /**
     * Achar uma conta pelo seu ID.
     * Mapeado para a requisição GET em /contas/{id}.
     * @param id O ID da conta a ser recuperada (passado como variável de caminho).
     * @return @code ResponseEntity contendo o objeto @link Conta e status HTTP 200 (OK) se encontrado,
     * ou status HTTP 404 (Not Found) se a conta não existir.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Conta> getById(@PathVariable Integer id) {
        return contaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Achar uma conta pelo seu número (único).
     * Mapeado para a requisição GET em /contas/numero/{numero}.
     * @param numero O número da conta a ser recuperada (passado como variável de caminho).
     * @return @code ResponseEntity contendo o objeto @link Conta e status HTTP 200 (OK) se encontrado,
     * ou status HTTP 404 (Not Found) se a conta não existir.
     */
    @GetMapping("/numero/{numero}")
    public ResponseEntity<Conta> getByNumero(@PathVariable Integer numero) {
        return contaService.findByNumero(numero)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Cadastra uma nova conta.
     * Mapeado para a requisição POST em /contas.
     * @param conta O objeto {@link Conta} a ser cadastrado (passado no corpo da requisição).
     * @return @code ResponseEntity contendo o objeto {@link Conta} cadastrado e status HTTP 201 (Created) em caso de sucesso,
     * ou status HTTP 400 (Bad Request) em caso de falha na validação ou erro de argumento.
     */
    @PostMapping
    public ResponseEntity<Conta> cadastrar(@RequestBody @Valid Conta conta) {
        try {
            Conta saved = contaService.cadastrar(conta);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Deleta uma conta pelo seu ID.
     * Mapeado para a requisição DELETE em /contas/{id}.
     * @param id O ID da conta a ser deletada (passado como variável de caminho).
     * @return @code ResponseEntity com status HTTP 204 (No Content) em caso de sucesso,
     * ou status HTTP 404 (Not Found) se a conta não existir.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConta(@PathVariable Integer id) {
        boolean removed = contaService.deleteById(id);
        return removed ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    /**
     * Realiza um saque em uma conta específica.
     * Mapeado para a requisição POST em /contas/{id}/sacar.
     * @param id O ID da conta onde o saque será realizado (passado como variável de caminho).
     * @param transacao O objeto {@link TransacaoDTO} contendo o valor do saque (passado no corpo da requisição).
     * @return @code ResponseEntity contendo o objeto @link Conta atualizado e status HTTP 200 (OK) em caso de sucesso,
     * ou status HTTP 400 (Bad Request) em caso de saldo insuficiente ou valor inválido.
     */
    @PostMapping("/{id}/sacar")
    public ResponseEntity<Conta> sacar(@PathVariable Integer id, @RequestBody @Valid TransacaoDTO transacao) {
        try {
            Conta atualizada = contaService.sacar(id, transacao.getValor());
            return ResponseEntity.ok(atualizada);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build(); // saldo insuficiente ou valor inválido
        }
    }

    /**
     * Realiza um depósito em uma conta específica.
     * Mapeado para a requisição POST em /contas/{id}/depositar.
     * @param id O ID da conta onde o depósito será realizado (passado como variável de caminho).
     * @param transacao O objeto {@link TransacaoDTO} contendo o valor do depósito (passado no corpo da requisição).
     * @return @code ResponseEntity contendo o objeto @link Conta atualizado e status HTTP 200 (OK) em caso de sucesso,
     * ou status HTTP 400 (Bad Request) em caso de valor inválido.
     */
    @PostMapping("/{id}/depositar")
    public ResponseEntity<Conta> depositar(@PathVariable Integer id, @RequestBody @Valid TransacaoDTO transacao) {
        try {
            Conta atualizada = contaService.depositar(id, transacao.getValor());
            return ResponseEntity.ok(atualizada);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Atualiza os dados de uma conta existente.
     * Mapeado para a requisição PUT em /contas/{id}.
     * @param id O ID da conta a ser atualizada (passado como variável de caminho).
     * @param conta O objeto {@link Conta} com os dados atualizados (passado no corpo da requisição).
     * @return @code ResponseEntity contendo o objeto {@link Conta} atualizado e status HTTP 200 (OK) em caso de sucesso,
     * ou status HTTP 404 (Not Found) se a conta não existir.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Conta> atualizar(@PathVariable Integer id, @RequestBody @Valid Conta conta) {
        return contaService.findById(id)
                .map(existing -> {
                    conta.setId(id);
                    Conta saved = contaService.save(conta);
                    return ResponseEntity.ok(saved);
                })
                .orElse(ResponseEntity.notFound().build());
    }

}


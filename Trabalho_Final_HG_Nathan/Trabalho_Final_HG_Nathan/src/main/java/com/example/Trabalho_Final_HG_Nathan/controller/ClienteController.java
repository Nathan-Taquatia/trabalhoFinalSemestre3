package com.example.trabalhofinalhgnathan.controller;
/* 
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.trabalhofinalhgnathan.model.Banco;
import com.example.trabalhofinalhgnathan.model.Cliente;
import com.example.trabalhofinalhgnathan.model.Conta;

@Controller
@RequestMapping("/cliente-controller")*/
public class ClienteController {
    /*
    @Autowired
    private final ClienteService clienteService;

    public ClienteController() {
    this.clienteService = clienteService;
    }


    @PostMapping("/cadastrar")
    public ResponseEntity<Conta> cadastrar(@RequestBody Conta conta) {
        
        try {
            Conta contaCadastrado = contaService.cadastrar(conta);
            return ResponseEntity.status(HttpStatus.CREATED).body(contaCadastrado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
        
        return null;
    }
    

    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConta(@PathVariable int id) {
        boolean removed = contaService.deleteById(id);
        return removed ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
    

    
    @GetMapping("/search/{id}")
    public String buscaPorId(@PathVariable("id") String id, Model model) {
        Cliente busca = clienteService.findById(id);
        model.addAttribute("id", busca);
        return "search-cliente";
    }
    

    
    @PutMapping("/clientes/{id}")
    public ResponseEntity<Cliente> editarCliente(@PathVariable int id, @RequestBody Cliente cliente) {
    Optional<Cliente> existente = clienteService.findById(id);
    if (!existente.isPresent()) {
        return ResponseEntity.notFound().build();
    }
    // Atualiza apenas campos permitidos (exemplo: nome e cpf)
    Cliente atual = existente.get();
    atual.setNome(cliente.getNome());
    atual.setCpf(cliente.getCpf());
    Cliente atualizado = clienteService.update(id, atual);
    return ResponseEntity.ok(atualizado);
    }
    

    
    @GetMapping("/bancos/search")
    public ResponseEntity<List<Banco>> search(@RequestParam("q") String q) {
    List<Banco> results = bancoService.searchByName(q);
    return ResponseEntity.ok(results);
    }
    */

}

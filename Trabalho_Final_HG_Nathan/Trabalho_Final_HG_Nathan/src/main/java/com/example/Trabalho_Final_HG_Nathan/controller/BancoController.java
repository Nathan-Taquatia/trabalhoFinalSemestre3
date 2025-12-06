package com.example.trabalhofinalhgnathan.controller;
/* 
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.trabalhofinalhgnathan.model.Banco;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/banco-controller")*/
public class BancoController {
    /* 
    @Autowired
    private final BancoService bancoService;
    private List<Banco> bancos;

    public BancoController(BancoService bancoService) {
        this.bancoService = bancoService;
    }

    
    @PostMapping("/cadastrar")
    public ResponseEntity<Banco> cadastrar(@RequestBody Banco banco) {
        
        try {
            Banco bancoCadastrado = bancoService.cadastrar(banco);
            return ResponseEntity.status(HttpStatus.CREATED).body(bancoCadastrado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
        
        return null;
    }
    

    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBanco(@PathVariable int id) {
        boolean removed = bancoService.deleteById(id);
        return removed ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
    

    
    @GetMapping("/editar/{id}")
    public String buscaPorId(@PathVariable("id") int id, Model model) {
        Banco busca = bancoService.findById(cep);
        model.addAttribute("id", busca);
        return "editar-banco";
    }
    

    
    @GetMapping("/bancos/search")
    public ResponseEntity<List<Banco>> search(@RequestParam("q") String q) {
    List<Banco> results = bancoService.searchByName(q);
    return ResponseEntity.ok(results);
    }

    @GetMapping("/bancos/codigo/{codigo}")
    public ResponseEntity<Banco> getByCodigo(@PathVariable Integer codigo) {
    return bancoService.findByCodigo(codigo)
    .map(ResponseEntity::ok)
    .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/bancos")
    public ResponseEntity<List<Banco>> getAll() {
    return ResponseEntity.ok(bancos); // bancos é List<Banco> mantida no controller/serviço
    }

    @PatchMapping("/bancos/{id}")
    public ResponseEntity<Banco> patchBanco(@PathVariable Integer id, @RequestBody Map<String, Object> updates) {
    return bancoService.findById(id)
        .map(existing -> {
            if (updates.containsKey("nome")) existing.setNome((String) updates.get("nome"));
            if (updates.containsKey("codigo")) existing.setCodigo((Integer) updates.get("codigo"));
            if (updates.containsKey("cnpj")) existing.setCnpj((String) updates.get("cnpj"));
            Banco saved = bancoService.save(existing);
            return ResponseEntity.ok(saved);
        })
        .orElse(ResponseEntity.notFound().build());
    }
*/
    //ARRUMAR OUTRAS FUNÇÕES CONFORME NECESSÁRIO
    
}

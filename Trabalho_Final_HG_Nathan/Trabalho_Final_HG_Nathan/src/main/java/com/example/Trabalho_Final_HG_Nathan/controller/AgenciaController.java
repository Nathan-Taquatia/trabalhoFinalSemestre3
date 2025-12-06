package com.example.trabalhofinalhgnathan.controller;

/*
import java.util.List;

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

import com.example.trabalhofinalhgnathan.model.Agencia;

import jakarta.validation.Valid;


@Controller
@RequestMapping("/agencia-controller")*/
public class AgenciaController {
    
    /* 
    @Autowired
    private final AgenciaService agenciaService;

    public AgenciaController(AgenciaService agenciaService) {
    this.agenciaService = agenciaService;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Agencia> cadastrar(@RequestBody Agencia agencia) {
        
        try {
            Agencia agenciaCadastrado = agenciaService.cadastrar(agencia);
            return ResponseEntity.status(HttpStatus.CREATED).body(agenciaCadastrado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
        
        return null;
    }
     
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAgencia(@PathVariable int id) {
        boolean removed = agenciaService.deleteById(id);
        return removed ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
    
    @GetMapping("/search/{id}")
    public String buscaPorId(@PathVariable("id") int id, Model model) {
        Agencia busca = agenciaService.findById(id);
        model.addAttribute("id", busca);
        return "search-agencia";
    }
    
    @PutMapping("/agencias/{id}")
    public ResponseEntity<Agencia> updateAgencia(@PathVariable Integer id, @RequestBody @Valid Agencia agencia) {
    return agenciaService.findById(id)
        .map(existing -> {
            agencia.setId(id); // garante o id correto
            Agencia saved = agenciaService.save(agencia);
            return ResponseEntity.ok(saved);
        })
        .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/agencias/search")
    public ResponseEntity<List<Agencia>> search(@RequestParam("q") String q) {
    List<Agencia> results = agenciaService.searchByName(q);
    return ResponseEntity.ok(results);
    }
    */

    //Algumas funcionalidades foram comentadas por falta de tempo para implementar o serviço completo.
}
